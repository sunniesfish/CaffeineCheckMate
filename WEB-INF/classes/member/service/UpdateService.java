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

public class UpdateService {

	
	//정보수정을위한 아이디/비번 재확인 메소드 (아이디비번체크&&처음로그인한아이디 다시한번체크)
	Connection conn = null;
	public String UpdateStart(MemberDTO memberDTO,
			HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		MemberDAO memberDAO = new MemberDAO();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		MemberDTO result = null;
		String returnPage = null;
		
			result = memberDAO.selectID(memberDTO,conn);
			
			System.out.println("requestValue "+result.getDtoPRO());
			String Value = result.getDtoPRO();
			switch (Value) {
				case "MyselfCheck":
					if(result.getDtoID().equals(request.getParameter("identgyId")) &&
							result.getDtoPW().equals(request.getParameter("identgyPw")) && 
							result.getDtoID().equals(session.getAttribute("AUTH_USER_ID"))) {
						session.setAttribute("AUTH_USER_NICKNAME", result.getDtoNICKNAME());
						session.setAttribute("AUTH_USER_TEL", result.getDtoTEL());
						session.setAttribute("AUTH_USER_SNS", result.getDtoSNS());
						returnPage = "/views/screens/updateRequest.jsp";
					}else {
						session.setAttribute("errMSG", "비밀번호가 일치하지않습니다.");
						out.println("<script>alert('로그인실패 비밀번호를 확인해주세요.'); location.href='/views/screens/login.jsp';</script>");
						out.flush();
					}
					break;
				default : 
					 out.println("<script>alert('아이디를 확인해주세요.'); location.href='/main.do';</script>");
					 out.flush();
					 break;
			}		
			return returnPage;
}
	// sns수신여부 수정 메소드
	public String UpdateSNS(MemberDTO memberDTO , HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		HttpSession session = request.getSession(false);
		MemberDAO memberDAO = new MemberDAO();
		String result = null;
		String SNS1 = memberDTO.getDtoSNS();
		String SNS2 = (String)session.getAttribute("AUTH_USER_SNS");
		
			if(SNS2.equals(SNS1)) {
				System.out.println("수정사항없음");
			}else {
				System.out.println("수정사항발생");
				String selectId = (String)session.getAttribute("AUTH_USER_ID");
				String query = "update ccm.member set M_SNSYN = ? where M_ID=?";
				memberDTO.setDtoID(selectId);
				result = memberDAO.update(memberDTO.getDtoSNS(),selectId,query,conn);			
			}
			if(result=="true") {
				if(memberDTO.getDtoSNS().equals("Y")) {
					session.setAttribute("AUTH_USER_SNS","동의");
				}else {
					session.setAttribute("AUTH_USER_SNS","거절");
				}
				result = "/views/screens/updateRequest.jsp";
			}else {
				result = "/main.do";
			}
			return result;
		}
		//전화번호 수정 메소드
		public String UpdateTEL(MemberDTO memberDTO , HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);HttpSession session = request.getSession(false);
			PrintWriter out = response.getWriter();
			MemberDAO memberDAO = new MemberDAO();
			String result = null;
			String resultPage = null;
			String TEL1 = memberDTO.getDtoTEL();
			String TEL2 = (String)session.getAttribute("AUTH_USER_TEL");
	
				if(TEL2.equals(TEL1)) {
					System.out.println("수정사항없음");
					 out.println("<script>alert('기존 전화번호와 동일한 번호입니다.'); location.href='/views/screens/updateTEL.jsp';</script>");
					 out.flush();
				}else{
					String selectId = (String)session.getAttribute("AUTH_USER_ID");
					String query = "update member set M_PHONENUMBER = ? where M_ID=?";
					memberDTO.setDtoID(selectId);
					result = memberDAO.update(memberDTO.getDtoTEL(),selectId,query,conn);
				}
					if(result=="true") {
						System.out.println("서비스 트루");
						session.setAttribute("editOK", "editOK");
						resultPage = "/views/screens/updateTEL.jsp";
					}else {
						session.setAttribute("editCANCEL", "editCANCEL");
						resultPage = "/main.do";
				}
				return resultPage;
		}
		// 비밀번호변경 메소드
		public String updateOldPassword(String identgyId) throws SQLException {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			MemberDAO memberDAO =  new MemberDAO();
			MemberDTO memberDTO =  new MemberDTO();
			memberDTO.setDtoID(identgyId);
			MemberDTO result = memberDAO.selectID(memberDTO,conn);
			identgyId = result.getDtoPW();
			System.out.println("identgyId(디비비밀번호) " + result.getDtoPW());
			return identgyId;
		}
		public String updatePassword(String pw1, String pw2,String formValue,HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			HttpSession session = request.getSession(false);
			PrintWriter out = response.getWriter();
			String result = null;
			MemberDAO memberDAO = new MemberDAO();
			JoinService joinService = new JoinService();
			String alert = "'/views/screens/passwordChange.jsp';";
			String query = "update ccm.member set M_PASSWORD = ? where M_ID=?";
			String selectId = (String)session.getAttribute("AUTH_USER_ID");
			
			result = joinService.password(pw1, pw2, alert,formValue,request, response);
			
			String returnPage = memberDAO.update(result,selectId,query,conn);
			if(returnPage == "true") {
				System.out.println("true로들어옴");
				 out.println("<script>alert('Password 변경완료.');"
					 		+ "location.href="
					 		+ "'/views/screens/testView.jsp';"
					 		+ "</script>");
				 	session.invalidate();
					 out.flush();
			}else {
				 out.println("<script>alert('변경실패오류.');"
					 		+ "location.href="
					 		+ "'/views/screens/passwordChange.jsp';"
					 		+ "</script>");
					 out.flush();
			}
			return returnPage;
		}
		public String findPassword(String pw1, String pw2,String id,HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			HttpSession session = request.getSession(false);
			PrintWriter out = response.getWriter();
			String result = null;
			String query = "update ccm.member set M_PASSWORD = ? where M_ID=?";
			MemberDAO memberDAO = new MemberDAO();
			
			if(pw1.equals(pw2)){
				result = pw1;
				String success = memberDAO.update(result, id, query, conn);
				if(success.equals("true")) {
				    response.setContentType("text/html");
				    out.println("<script>");
				    out.println("  window.opener.postMessage('login_success', '*');");
				    out.println("alert('변경성공 로그인해주세요.');");
				    out.println("self.close();");
				    out.println("window.opener.location.href = '/views/screens/login.jsp';");
				    out.println("</script>");
					}else {
						out.println("<script>alert('비밀번호가 일치하지않습니다.');"
						 		+ "location.href="
						 		+ "'/views/screens/findUser.jsp?find=REPW&result="
						 		+ id
								+"';"
						 		+ "</script>");
						 out.flush();
					}
			}else{
			out.println("<script>alert('비밀번호가 일치하지않습니다.');"
			 		+ "location.href="
			 		+ "'/views/screens/findUser.jsp?find=REPW&result="
			 		+ id
					+"';"
			 		+ "</script>");
			 out.flush();
			}
			return null;
		}
}