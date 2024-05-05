package customRecipe.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;
import customRecipe.service.CustomBoardUpdateService;

public class CustomBoardUpdateHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		CustomBoardUpdateService service = new CustomBoardUpdateService();
		service.boardUpdate(req, res);
		
		res.sendRedirect(req.getContextPath() + "/CustomBoardListHandler.do");
		
		return null;
	}
}