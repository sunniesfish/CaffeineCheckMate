package customRecipe.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.ConnectionProvider;
import customRecipe.dao.CustomBoardViewDao;
import customRecipe.dto.CustomBoardListDto;
import jdbc.JdbcUtil;

public class CustomBoardViewService {
	
	public ArrayList<CustomBoardListDto> boardview(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		CustomBoardViewDao dao = new CustomBoardViewDao();
		try {
			
			String num = request.getParameter("CUS_NUM");
			con = ConnectionProvider.getConnection();
			
			ArrayList<CustomBoardListDto> list = dao.boardview(con,num);
			
			System.out.println(list + "list값");
			request.setAttribute("list", list);
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
	
	
	
	public String checkid(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		CustomBoardViewDao dao = new CustomBoardViewDao();
		HttpSession session = request.getSession(false);
		 String id = (String)session.getAttribute("AUTH_USER_ID");
		 
		String num = request.getParameter("CUS_NUM");
		
		try {
			
			con = ConnectionProvider.getConnection();
			String dtoid = dao.memid(num,con);
			
			if(id !=null && id.equals(dtoid)) {
			return "/views/screens/CustomBoardMyView.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			JdbcUtil.close(con);
		}
		
		return "/views/screens/CustomBoardView.jsp";
	}
}