package global.handler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;



public class NullHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

}
