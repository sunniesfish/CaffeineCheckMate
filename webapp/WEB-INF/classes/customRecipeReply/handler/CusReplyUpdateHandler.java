package customRecipeReply.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import connection.ConnectionProvider;
import customRecipeReply.dao.CusReplyDao;
import customRecipeReply.dto.CusReplyDto;
import jdbc.JdbcUtil;

@WebServlet("/CusReplyUpdateHandler")
public class CusReplyUpdateHandler extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
		String m_id = (String)session.getAttribute("AUTH_USER_ID"); 
    	
    	// 요청에서 여러 줄의 데이터를 읽어오고 StringBuilder를 이용해서 한줄의 문자열로 JSON 데이터 저장
    	BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
    	StringBuilder sb = new StringBuilder();
    	String line;
    	
        while ((line = br.readLine()) != null) {
        	sb.append(line).append('\n');
        }
        br.close();
        
        String json = sb.toString().trim();
        Gson gson = new Gson();
        
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        
        // JSON 객체에서 수정된 댓글 내용과 댓글 번호 추출
        String editedContent = jsonObject.get("cus_re_content").getAsString();
        int cus_re_no = jsonObject.get("cus_re_no").getAsInt();
        
        Connection conn = null;

        try {
        	conn = ConnectionProvider.getConnection();
        	CusReplyDao replyDao = new CusReplyDao();
        	CusReplyDto reply = new CusReplyDto(editedContent, cus_re_no);
        	// 업데이트 성공 시 result 값이 1 이상, 실패 시 0이 result에 담긴다
        	int result = replyDao.updateReply(conn, reply);
            
        	} catch (Exception e) {
        		e.printStackTrace();
        		response.getWriter().write("실패");
	
        	} finally {
        		JdbcUtil.close(conn);
	
        	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
}
