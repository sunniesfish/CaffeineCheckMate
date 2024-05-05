package calendar.handler;

import controller.CommandHandler;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calendar.service.CalculatorService;


public class CalculatorHandler extends HttpServlet implements CommandHandler {
	
	private static final long serialVersionUID = 1L;
	public static final String FORM_VIEW = "/index.jsp";
	private CalculatorService calculatorService = new CalculatorService();

    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception { 
    	Object mno =  req.getSession().getAttribute("AUTH_USER_ID");
    	String memberId = null;
    	if (mno != null) {
    		memberId = mno.toString();
    	}
    	Integer coffeeNo = Integer.parseInt(req.getParameter("C_NO"));
    	if (memberId != null && coffeeNo != null) {
    		try {
    			calculatorService.calculate(memberId, coffeeNo);
    			return "/main.do";
    		} catch (Exception e) {
    			System.out.println("CalculatorHandler process 에서 예외 발생");
    		} return FORM_VIEW;    		
    	} else {
    		 return FORM_VIEW;  
    	}
    }

}
