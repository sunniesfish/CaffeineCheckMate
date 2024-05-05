package customRecipe.handler;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;
import customRecipe.service.CustomBoardViewService;

public class CustomBoardViewHandler  implements CommandHandler {
 
@Override
public String process(HttpServletRequest request, HttpServletResponse response) {
	 
	 CustomBoardViewService service = new CustomBoardViewService();
	 service.boardview(request, response);
	 
	  return service.checkid(request, response);
}
}