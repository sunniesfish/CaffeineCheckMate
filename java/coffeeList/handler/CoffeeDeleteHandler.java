package coffeeList.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffeeList.service.CoffeeDeleteService;
import controller.CommandHandler;

public class CoffeeDeleteHandler implements CommandHandler{

	@Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			int coffeeNo = Integer.parseInt(request.getParameter("coffeeNo"));
			//사진 삭제를 위해 파일 경로 얻어서 서비스로 보내기
			String saveDirectory = request.getServletContext().getRealPath("/resources/testimg");
			System.out.println(coffeeNo);
			CoffeeDeleteService coffeeDeleteService = new CoffeeDeleteService();
			coffeeDeleteService.deleteCoffee(coffeeNo, saveDirectory);
			
			response.sendRedirect("/coffeeList.do");
			return null;
		} catch (Exception e) {
			System.out.println("Coffee delete handler 에러 메시지: " + e.getMessage());
            return "에러";
		}
	}				
}
