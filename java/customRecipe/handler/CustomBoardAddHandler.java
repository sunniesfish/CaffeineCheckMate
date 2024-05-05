package customRecipe.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;
import customRecipe.service.CustomBoardAddService;


public class CustomBoardAddHandler implements CommandHandler {
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		CustomBoardAddService service = new CustomBoardAddService();
		service.addList(request, response);
		
		
		response.sendRedirect(request.getContextPath() + "/CustomBoardListHandler.do");
		
		
		return null;
		}
}

