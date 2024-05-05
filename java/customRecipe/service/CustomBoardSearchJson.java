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
import customRecipe.dao.CustomBoardSearchDao;
import customRecipe.dto.CustomBoardListDto;

@WebServlet("/CustomBoardSearchJson.do")
public class CustomBoardSearchJson extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomBoardSearchDao dao = new CustomBoardSearchDao();
		Connection con = null;
		
		JSONObject json = new JSONObject();
		
		try {
		con = ConnectionProvider.getConnection();
		JSONArray jsonArray = new JSONArray();
		String content = request.getParameter("searchTerm");
		System.out.println(content);
		
		ArrayList<CustomBoardListDto> list = dao.searchlist(content, con);
			
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
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(json.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	    // 검색어(searchTerm)를 사용하여 필요한 작업 수행
	}
}
