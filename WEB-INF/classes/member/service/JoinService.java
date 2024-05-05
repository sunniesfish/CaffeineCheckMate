package member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class JoinService {

		String returnPage;
	public String joinInsert(MemberDTO memberDTO) 
			throws IOException, ClassNotFoundException, SQLException {
		System.out.println("조인인서트들어옴");
		Connection conn = null;
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		MemberDAO memberDAO = new MemberDAO();	
		//DAO메소드호출 : DTO에저장한값을들고 가서 DB에 정보저장
		returnPage = memberDAO.insert(memberDTO,conn); 
		return returnPage;  
	}
	//비번병경 메소드
	public String password(String pw1,String pw2,String alert,String formValue,HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		UpdateService updateService = new UpdateService();
		String pw3 = updateService.updateOldPassword((String)session.getAttribute("AUTH_USER_ID"));
		String result = null;
		
		if(formValue.equals("passwordChange")) {
			if(pw1.equals(pw2)) {
				System.out.println("일단 비밀번호 와 확인비밀번호는 같음");
				if(pw1.equals(pw3)) {
					System.out.println("3가지 비밀번호가 같음");
					 	out.println("<script>alert('기존비밀번호와 같은 비밀번호입니다.');"
						 			+ "location.href=" + "'/views/screens/passwordChange.jsp'" + "</script>");
						 out.flush();
				}else {
					System.out.println("기존비밀번호와 바꿀비밀번호 중복아님");
					result = pw1;
				}
			}else {
				 out.println("<script>alert('비밀번호가 일치하지않습니다.');"
					 		+ "location.href=" + "'/views/screens/passwordChange.jsp'" + "</script>");
					 out.flush();
			}
		}
		return result;
	}
	public String password(String pw1,String pw2,String alert,HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String result = null;
		
			if(pw1.equals(pw2)){
				result = pw1;
		}else{
			 out.println("<script>alert('비밀번호가 일치하지 않습니다.');"
			 		+ "location.href=" + alert + "</script>");
			 out.flush();
		}
		return result;
	}
}