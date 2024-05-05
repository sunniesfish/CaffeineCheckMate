package calendar.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import calendar.service.CalculatorService;


@WebServlet("/calc")
public class CalculatorAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CalculatorService calculatorService = new CalculatorService();
    Gson gson = new Gson();

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object mno =  request.getSession().getAttribute("AUTH_USER_ID");
    	String memberId = null;
    	if (mno != null) {
    		memberId = mno.toString();
    	}
		
		String cno = request.getParameter("C_NO");
		Integer doReset = Integer.parseInt(request.getParameter("yn")); // y(1)이면 리셋, n(0)이면 계산
		int coffeeNo;
		String jsonString = null;
		
		if (cno != null) {
			if (!cno.isEmpty()) {
				try {
					coffeeNo = Integer.parseInt(cno);
				}catch (Exception e) {
					coffeeNo = 0;
				}				
			} else {
				coffeeNo = 0;
			}
		} else {
			coffeeNo = 0;
		}
		
		if (doReset == 0) {
			HashMap<String, Object> cr = calculatorService.calculateAsync(memberId, coffeeNo);
			jsonString = gson.toJson(cr);			
		} else if (doReset ==1 ) {
			HashMap<String, Object> rr = calculatorService.resetAsync(memberId);
			jsonString = gson.toJson(rr);
		}
		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		
		out.print(jsonString);
		out.flush();	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
