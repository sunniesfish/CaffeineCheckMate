package favorite.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import favorite.service.FavoriteService;


@WebServlet("/addfav")
public class AddFavoriteAsync extends HttpServlet {
	
	private FavoriteService favoriteService = new FavoriteService();
	private Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object mId =  request.getSession().getAttribute("AUTH_USER_ID");
    	String memberId = null;
    	if (mId != null) {
    		memberId = mId.toString();
    	}
    	Integer coffeeNo = Integer.parseInt(request.getParameter("C_NO"));
    	
    	HashMap favMap = null;
    	String jsonString = null;
    	
		favMap = favoriteService.addFavAsync(memberId, coffeeNo);
		jsonString = gson.toJson(favMap);
		
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
