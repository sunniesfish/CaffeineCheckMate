package coffeeList.handler;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import coffeeList.dto.Coffee;
import coffeeList.service.CoffeeAddService;
import controller.CommandHandler;

public class CoffeeAddHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("여기는 오니..?");
		

		//이미지 추가 관련 로직
		//1. 저장될 디렉토리 변수에 담기
		String saveDirectory = request.getServletContext().getRealPath("resources/testimg");
		System.out.println(saveDirectory);
		//1-1. 저장될 디렉토리가 없으면 새로 생성
		//저장 경로 : C:\icanjava\webpg\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\ccm\resources\testimg
		File dir = new File(saveDirectory);
		if (!dir.exists()) {
			dir.mkdirs();
		}
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
				
		//4. MultipartRequest객체를 생성하여 파라미터를 받음
		// request.getParameter -> multi.getParameter 로 변경
		try {//파라미터 받은 값과 변수는 _없이 모두 소문자
			String cname = multi.getParameter("cname");
			String cbrand = multi.getParameter("cbrand");
			int ccaffeine = Integer.parseInt(multi.getParameter("ccaffeine"));
			int csaccharide = Integer.parseInt(multi.getParameter("csaccharide"));
			int ccalorie = Integer.parseInt(multi.getParameter("ccalorie"));
			String ccontent = multi.getParameter("ccontent");
			String ctype = multi.getParameter("ctype");
			String cstage = multi.getParameter("cstage");
			String cimgreal = multi.getFilesystemName("cimgreal");//img : getFilesystemName 이용
			//C_IMG_COPY 경로까지 합쳐서 저장
			String cimgcopy = "/resources/testimg/" + cimgreal;
			
			Coffee coffee = new Coffee
					(cname,cbrand,ccaffeine,csaccharide,ccalorie,ccontent,ctype,cstage,cimgreal,cimgcopy);
			
			CoffeeAddService coffeeAddService = new CoffeeAddService();
			coffeeAddService.addCoffee(coffee);
			System.out.println("여기까진..? 오니..?");
			
			response.sendRedirect("/coffeeList.do");
			
			return null;
		} catch (Exception e) {
			System.out.println("Coffee delete handler 에러 메시지: " + e.getMessage());
            return "에러";
		}
	}
}
