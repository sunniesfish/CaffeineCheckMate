package global.handler;

import controller.CommandHandler;
import global.dto.Main;
import global.service.MainPageService;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;




public class MainPageHandler extends HttpServlet implements CommandHandler {
	private static final long serialVersionUID = 1L;
	public static final String FORM_VIEW = "/index.jsp";
	private MainPageService mainPageService = new MainPageService();
	private Gson gson = new Gson();

    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception { 
    	Main main;

    	Object mId =  req.getSession().getAttribute("AUTH_USER_ID");
    	String memberId = mId != null? mId.toString() : null;
    	if(memberId != null ) {
    		System.out.println("authed");
    		main = mainPageService.showAuthedMain(memberId);
    	} else { // 비로그인상태    		
    		System.out.println("not authed");
    		main = mainPageService.showMain();
    	}
    	
//    	String jsonString = gson.toJson(main);
    	req.setAttribute("main", main);
		return "/views/screens/Main.jsp";
    }
}
