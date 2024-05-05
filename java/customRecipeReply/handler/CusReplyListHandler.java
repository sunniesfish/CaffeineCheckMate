package customRecipeReply.handler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import connection.ConnectionProvider;
import customRecipeReply.dao.CusReplyDao;
import customRecipeReply.dto.CusReplyDto;
import jdbc.JdbcUtil;

@WebServlet("/CusReplyListHandler")
public class CusReplyListHandler extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	JSONObject re_json = new JSONObject();
        
    	Connection conn = null;
    	CusReplyDao cus_re_dao = new CusReplyDao();
        
    	try {
    		conn = ConnectionProvider.getConnection();
    		JSONArray re_jsonArray = new JSONArray();
    		
    		int cus_no = Integer.parseInt(request.getParameter("cus_no"));
			 
    		ArrayList <CusReplyDto> cus_re_list = cus_re_dao.selectReplyList(conn, cus_no);
			 
    		for (CusReplyDto item : cus_re_list) {
    				JSONObject jsonObject = new JSONObject();
    				jsonObject.put("m_id", item.getM_id());
					jsonObject.put("m_nickname", item.getM_nickname());
					jsonObject.put("cus_no", item.getCus_no());
					jsonObject.put("cus_re_no", item.getCus_re_no());
					jsonObject.put("cus_re_regdate", item.getCus_re_regdate());
					jsonObject.put("cus_re_content", item.getCus_re_content());
					
					re_jsonArray.add(jsonObject);
				}
			 
		 		re_json.put("cus_re_list", re_jsonArray);
		 		
		 		response.setCharacterEncoding("UTF-8");
		 		response.setContentType("charset=UTF-8");
				response.getWriter().write(re_json.toString());
			
				} catch (SQLException e) {
					e.printStackTrace();
					
					
				}finally {
					JdbcUtil.close(conn);
				}
	    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
