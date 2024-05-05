package member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class LoginService {

	Connection conn = null;
	public String loginTest(MemberDTO memberDTO,
			HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		PrintWriter out = response.getWriter();
		MemberDAO memberDAO = new MemberDAO();
		String returnPage = "/views/screens/login.jsp";
		MemberDTO result = null;
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		
		HttpSession session = request.getSession(false); // 기존 세션 가져오기 시도
		if(session == null) {
		    session = request.getSession(true); // 기존 세션 없으면 새로운 세션 생성
		}
		result = memberDAO.selectID(memberDTO,conn); // 로그인 검증 멤버와 백업테이블 조회
		String dbID = result.getDtoID();
		String dbPW = result.getDtoPW();
		String value = memberDTO.getDtoPRO();

		boolean temp = false;
			switch (value) {
			case "memberEmpty" : // 멤버에 없고 백업테이블에 있음
				Timestamp dbTIME = memberDTO.getDtoDELETEDATE();
		        // 현재 날짜 가져오기
				LocalDateTime localDateTime = dbTIME.toLocalDateTime(); // db에서 가져온 Timestamp 날짜를 LocalDateTime으로 변경한 값
				// 7일 후의 날짜와 시간 구하기
				LocalDateTime futureDateAndTime = localDateTime.plusDays(7);
				LocalDateTime localDateTime1 = futureDateAndTime;
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a").withLocale(Locale.ENGLISH);;
				String formattedDateTime = localDateTime1.format(dateTimeFormatter);
				System.out.println("dbTIME = " + dbTIME);
				session.setAttribute("D_day", formattedDateTime);
				temp = memberEmpty(loginId,loginPw,dbID,dbPW);
				if(true == temp) {
					session.setAttribute("AUTH_USER_ID", dbID);
					out.println("<script>alert('탈퇴신청상태입니다.'); location.href="
							+ "'/views/screens/okDelete.jsp';"
							+ "</script>");
					out.flush();
				}else {
					session.setAttribute("errMSG", "비밀번호가 일치하지않습니다.");
					out.println("<script>alert('로그인실패 비밀번호를 확인해주세요.'); "
							+ "location.href='/views/screens/login.jsp';</script>");
					out.flush();
				}
				break;
			case "backupEmpty" : //멤버와 백업 테이블 모두 아이디가 없음
				session.setAttribute("errMSG", "아이디가 존재하지않습니다.");
				 out.println("<script>alert('로그인실패 아이디가 존재하지않습니다.'); location.href='/views/screens/login.jsp';</script>");
				 out.flush();
				break;
			default :
				temp = loginTest(loginId,loginPw,dbID,dbPW);
				if(true == temp) {
					session.setAttribute("AUTH_USER_ID", result.getDtoID());
					session.setAttribute("AUTH_USER_NAME", result.getDtoNAME());
					session.setAttribute("AUTH_USER_SSN", result.getDtoSSN());
					session.setAttribute("AUTH_USER_EMAIL", result.getDtoEMAIL());
					session.setAttribute("AUTH_USER_NICKNAME", result.getDtoNICKNAME());
					session.setAttribute("AUTH_USER_TEL", result.getDtoTEL());
					session.setAttribute("AUTH_USER_GENDER", result.getDtoGENDER());
					session.setAttribute("AUTH_USER_SNS", result.getDtoSNS());
					returnPage = "/main.do";					
				}else {
					session.setAttribute("errMSG", " 비밀번호가 일치하지않습니다.");
					out.println("<script>alert('비밀번호를 확인해주세요.'); location.href='/views/screens/login.jsp';</script>");
					out.flush();
				}
				break;
			}
			System.out.println("서비스빠져나가기전 리턴값 "+returnPage);
			return returnPage;
	}			
	// member 테이블에 없고 backup 테이블에는 있음(삭제대기상태)		
	public boolean memberEmpty(String loginId,String loginPw,String dbID,String dbPW) {
		boolean result;
		if(loginId.equals(dbID) && loginPw.equals(dbPW)){
			result = true;
		}else {
			result = false;
		}
		return result;
	}
	// member 테이블에 아이디존재
	public boolean loginTest(String loginId,String loginPw,String dbID,String dbPW) {
		boolean result;
		if(dbID.equals(loginId) && dbPW.equals(loginPw)) {
			result = true;
		}else {
			result = false;
		}
		return result;
	}
}



