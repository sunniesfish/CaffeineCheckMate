package favorite.handler;

import controller.CommandHandler;
import favorite.service.FavoriteService;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteFavHandler extends HttpServlet implements CommandHandler {
	private static final long serialVersionUID = 1L;
	public static final String FORM_VIEW = "/index.jsp";
	private FavoriteService favoriteService = new FavoriteService();
	
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception { 
    	Object mno =  req.getSession().getAttribute("AUTH_USER_ID");
    	String memberId = null;
    	if (mno != null) {
    		memberId = mno.toString();
    	}
    	Integer coffeeNo = Integer.parseInt(req.getParameter("C_NO"));

    	try {
    		favoriteService.deleteFav(memberId, coffeeNo);
    		return "/main.do";
    	} catch (Exception e) {
    		System.out.println("AddFavoriteHandler process에서 오류발생");
    	} return FORM_VIEW;
    }

}
