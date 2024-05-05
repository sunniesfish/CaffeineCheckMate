package customRecipe.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


import connection.ConnectionProvider;
import customRecipe.dao.CustomBoardListDao;
import customRecipe.dto.CustomBoardListDto;
import jdbc.JdbcUtil;

public class CustomBoardListService {

	public ArrayList<CustomBoardListDto> Boardlist(){
		Connection con = null;
		CustomBoardListDao dao = new CustomBoardListDao();
		
		try {
			con = ConnectionProvider.getConnection();
			ArrayList<CustomBoardListDto> list = dao.getallList(con);
			
		         
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(con);
		}
		finally {
			JdbcUtil.close(con);
		}
		 return null;
	}

}
