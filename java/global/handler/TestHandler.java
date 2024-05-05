package global.handler;

import controller.CommandHandler;
import global.service.TestService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class TestHandler extends HttpServlet implements CommandHandler {
	private static final long serialVersionUID = 1L;
       
	TestService testService = new TestService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception { 
        	String test =testService.test();
        	
        	req.setAttribute("test", test);
			return "index.jsp";
    }

}
