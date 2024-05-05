package coffeeList.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffeeList.dto.Coffee;
import coffeeList.service.CoffeeDetailService;
import controller.CommandHandler;


public class CoffeeDetailpopHandler extends HttpServlet implements CommandHandler {
	private static final long serialVersionUID = 1L;

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			String param = req.getParameter("C_NO");
			System.out.println("parma : "+param);
			 int coffeeNo = Integer.parseInt(param);
			 
			 CoffeeDetailService coffeeDetailService = new CoffeeDetailService();
			 Coffee coffee = coffeeDetailService.getCoffeeDetail(coffeeNo);
			 
			 req.setAttribute("coffeeDetail", coffee);
			 return "/views/screens/coffeeList_Detail.jsp";
		}catch (Exception e) {
			System.out.println("에러 메시지: " + e.getMessage());
            return "에러";
		}
	}
	
	
}
