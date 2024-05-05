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

public class DeleteService {
	
		Connection conn = null;
		boolean result = false;
		String returnPage = null;
	public String deleteAction(MemberDTO memberDTO,HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		MemberDAO memberDAO = new MemberDAO();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		
		MemberDTO temp = memberDAO.selectID(memberDTO,conn);

		System.out.println("requestValue "+temp.getDtoPRO());
		String Value = temp.getDtoPRO();
		boolean result = false;
		switch (Value) {
		case "delete":
				System.out.println("request.getParameter(\"identgyId\") "+request.getParameter("identgyId"));
				System.out.println("request.getParameter(\"identgyPw\") "+request.getParameter("identgyPw"));
				System.out.println("temp.getDtoID() "+temp.getDtoID());
				System.out.println("temp.getDtoPW() "+temp.getDtoPW());
				System.out.println("session.getAttribute(\"AUTH_USER_ID\") "+session.getAttribute("AUTH_USER_ID"));
				System.out.println("딜리트케이스들어옴");
				if(temp.getDtoID().equals(request.getParameter("identgyId")) &&
						temp.getDtoPW().equals(request.getParameter("identgyPw")) && 
						temp.getDtoID().equals(session.getAttribute("AUTH_USER_ID"))) {
					System.out.println("이프문들어옴");
					conn = ConnectionProvider.getConnection();
					conn.setAutoCommit(false);
					String deleteID = memberDTO.getDtoID();
					
					result = memberDAO.deleteMember(deleteID,conn);
					
					if(result == true) {
						System.out.println("딜리트메소드 다녀와서 if문 트루로 들어옴");
						 out.println("<script>alert('탈퇴가완료되었습니다.'); location.href='/views/screens/okDelete.jsp';</script>");
						 session.invalidate();
						 out.flush();
						 returnPage = "/views/screens/okDelete.jsp";
						 //session.invalidate();
					}else {
						System.out.println("딜리트메소드 다녀와서 else로 들어옴");
						 out.println("<script>alert('알수없는 오류로인해 회원정보를 확인할수없습니다.'); location.href='/views/screens/login.jsp';</script>");
						 session.invalidate();
						 out.flush();
					}
					conn.close();
				}else {
					System.out.println("비번이 안맞음");
					 out.println("<script>alert('비밀번호를 확인해주세요.'); location.href='/views/screens/updateRequest.jsp';</script>");
					 //session.invalidate();
					 out.flush();
					
				}
				
		case "backupEmpty":
			if(session.getAttribute("AUTH_USER_ID")!= null) {
			System.out.println("아이디가 안맞음");
			 out.println("<script>alert('아이디를 확인해주세요.'); location.href='/views/screens/updateRequest.jsp';</script>");
			 //session.invalidate();
			 out.flush();
			}else {
				
			}
		}
		return returnPage;
	}
	public String deleteCancelAction(MemberDTO memberDTO,HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		MemberDAO memberDAO = new MemberDAO();
		String cancelID = memberDTO.getDtoID();
		String cancelPW = memberDTO.getDtoPW();
		
		result = memberDAO.deleteCancelSelect(cancelID,cancelPW, conn);
		
		if(result == true) {
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			returnPage = memberDAO.deleteCancel(cancelID, conn);
		}else{
			returnPage = "false";
		}
		if(returnPage.equals("false")) {
			 session.setAttribute("errMSG", "아이디나 비밀번호가 일치하지않습니다.");
			 out.println("<script>alert('입력 정보 오류'); location.href='/views/screens/Main.jsp';</script>");
			 out.flush();
		}
		return returnPage;
	}
}
