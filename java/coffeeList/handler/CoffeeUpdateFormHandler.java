package coffeeList.handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffeeList.dto.Coffee;
import coffeeList.service.CoffeeUpdateFormService;
import controller.CommandHandler;

public class CoffeeUpdateFormHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
		int coffeeNo = Integer.parseInt(request.getParameter("coffeeNo"));
		
		CoffeeUpdateFormService coffeeUpdateFormService = new CoffeeUpdateFormService();
		Coffee coffee = coffeeUpdateFormService.updateFormCoffee(coffeeNo);
		
		request.setAttribute("coffeeUpdateForm",coffee);
		return "/views/screens/coffeeList_Update.jsp";
		} catch (SQLException e) {
			System.out.println("coffeedetail handler 에러 메시지: " + e.getMessage());
            return "에러";
		}
	}
}