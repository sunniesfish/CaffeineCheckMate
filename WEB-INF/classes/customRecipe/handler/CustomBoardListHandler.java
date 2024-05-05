package customRecipe.handler;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import connection.ConnectionProvider;
import controller.CommandHandler;
import customRecipe.dao.CustomBoardListDao;
import jdbc.JdbcUtil;


public class CustomBoardListHandler  implements CommandHandler {
 
@Override
public String process(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
     CustomBoardListDao dao = new CustomBoardListDao();
     Connection con = null;
     con = ConnectionProvider.getConnection();
	 int allcount = dao.allCount(con);
	 request.setAttribute("allcount", allcount);
	 JdbcUtil.close(con);
	 
	  
	 return "/views/screens/CustomBoardList.jsp";
}
}
