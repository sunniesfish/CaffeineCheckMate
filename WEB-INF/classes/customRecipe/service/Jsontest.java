package customRecipe.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import connection.ConnectionProvider;
import customRecipe.dao.CustomBoardHashDao;
import customRecipe.dao.CustomBoardListDao;
import customRecipe.dao.CustomBoardSearchDao;
import customRecipe.dto.CustomBoardHashDto;
import customRecipe.dto.CustomBoardListDto;
import jdbc.JdbcUtil;

@WebServlet("/Jsontest.do")
public class Jsontest extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String shot = (String)request.getParameter("shot");
		String milk =(String)request.getParameter("milk");
		String syrup =(String)request.getParameter("syrup");
		String topping =(String)request.getParameter("topping");
		String decaf =(String)request.getParameter("decaf");
		
			CustomBoardHashDao hashdao = new CustomBoardHashDao();
			CustomBoardHashDto hashdto = new CustomBoardHashDto(shot,milk,syrup,topping,decaf);
			CustomBoardSearchDao dao = new CustomBoardSearchDao();
			
			
		   // CustomBoardListDao dao = new CustomBoardListDao();
	        Connection con = null;
	        
	        JSONObject json = new JSONObject();
			try {
				con = ConnectionProvider.getConnection();

			
				ArrayList<CustomBoardHashDto> hashlist =  hashdao.hashnum(con,hashdto);
				JSONArray jsonArray = new JSONArray();
				for(CustomBoardHashDto clist : hashlist) {
					String a = clist.getcus_no();
					
					ArrayList<CustomBoardListDto> list = dao.searchhash(a, con);
	    			
					for (CustomBoardListDto item : list) {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("m_id", item.getm_id());
						jsonObject.put("c_no", item.getc_no());
						jsonObject.put("cus_num", item.getcus_num());
						jsonObject.put("cus_name", item.getcus_title());
						jsonObject.put("cus_content", item.getcus_content());
						jsonObject.put("cus_regdate", item.getcus_regdate());
						jsonObject.put("cus_img_realname", item.getcus_img_realname());
						jsonObject.put("cus_sumgood", item.getcus_sumgood());
						
						jsonArray.add(jsonObject);
					
					}
					
					json.put("list", jsonArray);
					
}
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(json.toString());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(con);
			}
	        
	    }
		
		
}

