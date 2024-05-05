package coffeeList.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import coffeeList.dto.Coffee;
import coffeeList.service.CoffeeUpdateService;
import controller.CommandHandler;

public class CoffeeUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("업뎃 핸들러");
		//이미지 추가 관련 로직
		//1. 저장될 디렉토리 변수에 담기
		String saveDirectory = request.getServletContext().getRealPath("resources/testimg");
		//2. 파일 크기 제한 설정 변수에 담기
		int maxPostSize = 10 * 1024 * 1024; // 10MB
		
		//3. MultipartRequest 객체 생성 (일반적으로 아규먼트 값을 하기 5개로 받음)
		/* 1) HttpServletRequest 변수 : request
		 * 2) 업로드하는 파일이 저장될 디렉토리 : saveDirectory 변수 안에 저장
		 * 3) 파일 크기 제한(byte 단위 크기) : maxPostSize 변수 안에 저장
		 * 4) 인코딩 설정(String) : "UTF-8"
		 * 5) 동일한 파일명에 대해서 처리 방식 : new DefaultFileRenamePolicy()
		    -> DefaultFileRenamePolicy : (중복방지)동일한 파일 이름이 존재하면 파일명 뒤에 숫자를 붙여 디렉토리에 파일을 저장 */
		MultipartRequest multi = new MultipartRequest(
				request,
				saveDirectory,
				maxPostSize,
				"UTF-8",
				new DefaultFileRenamePolicy()
				);
		
		try {
			//coffeeNo를 파라미터로 받아 WHERE절에 사용
			int coffeeNo = Integer.parseInt(multi.getParameter("coffeeNo"));
			String cname = multi.getParameter("cname");
			String cbrand = multi.getParameter("cbrand");
			int ccaffeine = Integer.parseInt(multi.getParameter("ccaffeine"));
			int csaccharide = Integer.parseInt(multi.getParameter("csaccharide"));
			int ccalorie = Integer.parseInt(multi.getParameter("ccalorie"));
			String ccontent = multi.getParameter("ccontent");
			String ctype = multi.getParameter("ctype");
			String cstage = multi.getParameter("cstage");
			//newImage:새 이미지 / existingImage:이전 이미지
			String newImage = multi.getFilesystemName("cimgreal");
			String existingImage = multi.getParameter("defaultImage");
			// 새 이미지가 업로드되었으면 사용, 그렇지 않으면 기존 이미지 유지
			String cimgreal;
			String existAllPath = saveDirectory+existingImage;
			System.out.println(existAllPath);
			if(newImage!=null) {
				cimgreal = newImage;
			}else {
				cimgreal = existingImage;
			};
			
			
			
			String cimgcopy = "/resources/testimg/" + cimgreal;
			System.out.println(cimgcopy);
			Coffee coffee = new Coffee
					(cname,cbrand,ccaffeine,csaccharide,ccalorie,ccontent,ctype,cstage,cimgreal,cimgcopy,coffeeNo);
			
			CoffeeUpdateService coffeeUpdateService = new CoffeeUpdateService();
			coffeeUpdateService.updateCoffee(coffee,saveDirectory);
			
			response.sendRedirect("/coffeeList.do");
			return null;
		}catch(Exception e) {
			System.out.println("coffee update handler 에러 메시지: " + e.getMessage());
			return "에러";
		}
	}
}
