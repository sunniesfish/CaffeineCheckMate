package customRecipe.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandHandler;
import customRecipe.service.CustomBoardDeleteService;

public class CustomBoardDeleteHandler implements CommandHandler {
@Override
public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

	CustomBoardDeleteService service = new CustomBoardDeleteService();
	HttpSession session = request.getSession(false);
	String id = (String)session.getAttribute("AUTH_USER_ID");
	String num = request.getParameter("num");
	service.deleteRecipe(id, num);
	System.out.println("삭제완료");
	
	response.sendRedirect(request.getContextPath()+"/CustomBoardListHandler.do");
	return null;
}
}
