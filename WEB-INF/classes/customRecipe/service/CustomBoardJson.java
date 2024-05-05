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
import customRecipe.dao.CustomBoardListDao;
import customRecipe.dto.CustomBoardListDto;
import jdbc.JdbcUtil;


@WebServlet("/CustomBoardJson.do")
public class CustomBoardJson extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        CustomBoardListDao dao = new CustomBoardListDao();
        Connection con = null;
        
        
        JSONObject json = new JSONObject();
		try {
			con = ConnectionProvider.getConnection();

			JSONArray jsonArray = new JSONArray();
			int allcount = Integer.parseInt(request.getParameter("allcount"));
			int acount  = dao.allCount(con); //모든게시글 count	
			
			ArrayList<CustomBoardListDto> list= dao.readlist(acount,allcount, con);
			
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
		}finally {
			JdbcUtil.close(con);
		}
        
    }

}
		

