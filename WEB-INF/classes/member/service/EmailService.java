package member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class EmailService {
	public MemberDTO emailSend(String inputValue,HttpServletRequest request, HttpServletResponse response) throws IOException{
	System.out.println("샌드이메일들어옴");
	MemberDTO memberDTO = new MemberDTO();
	HttpSession session = request.getSession(false);
	session.setAttribute("inputMail", inputValue);
	// 입력받은 이메일 얻어오기 
	String inputEmail = inputValue; 
			//req.getParameter("inputEmail"); 
	// -------------- 라이브러리 이용 -------------------
	String subject = "[CaffeineCheckMate] 회원 가입 이메일 인증번호"; // 메일 제목
	String fromEmail = "epqmfwjs9078@gmail.com"; // 보내는 사람으로 표시될 이메일 (이메일 따라서 안될수도 있음)
	String fromUsername = "관리자"; // 보내는 사람 이름
	String toEmail = inputEmail; // 받는사람, 콤마(,)로 여러개 나열 가능
	
	// 구글 이메일을 이용한 메일 보내기 (SMTP..기술 이름)
	// 1. 구글 계정 생성(기존 이메일 사용해도됨)
	// 2. 계정 -> 보안 설정 진행
	// 1) 2단계 인증 추가
	// 2) 앱 비밀번호 생성 -> 저장해두기 ( pnnadoxrewitpayh ) // 발급 받은 비밀번호.... 보안 주의!!!!!!!!!!!!
		
	final String smtpEmail = "epqmfwjs9078@gmail.com"; // 이메일
	final String password = "pnnadoxrewitpayh"; // 발급 받은 비밀번호.... 보안 주의!!!!!!!!!!!!
	// 메일 옵션 설정
	Properties props = new Properties();
	// 중요
	props.put("mail.transport.protocol", "smtp");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587"); //465, 587 구글포트번호
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.ssl.protocols", "TLSv1.2");//
	// 추가 옵션
	props.put("mail.smtp.quitwait", "false");
	props.put("mail.smtp.socketFactory.port", "587");
	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.socketFactory.fallback", "true");
	props.put("mail.smtp.starttls.enable", "true");
	System.out.println("메일전송완료1");
	try {
		// 메일 세션 생성
		Session session1 = Session.getDefaultInstance(props);
		// 메일 송/수신 옵션 설정(1명 보내기)
		Message message = new MimeMessage(session1);
		message.setFrom(new InternetAddress(fromEmail, fromUsername)); 	// 송신자(보내는 사람) 지정
		message.addRecipient(RecipientType.TO, new InternetAddress(toEmail)); // 수신자(받는사람) 지정
		message.setSubject(subject); // 이메일 제목 지정
		
		// 메일 콘텐츠 설정
		Multipart mParts = new MimeMultipart();
		MimeBodyPart mTextPart = new MimeBodyPart();
		System.out.println("메일전송완료2");
		// 인증번호 6자리 생성코드(영어 대/소문 + 숫자)
		String cNumber = "";
		for(int i=0 ; i< 6 ; i++) {
			int sel1 = (int)(Math.random() * 3); // 0:숫자 / 1,2:영어
			if(sel1 == 0) {
				int num = (int)(Math.random() * 10); // 0~9
				cNumber += num;
			}else {
				char ch = (char)(Math.random() * 26 + 65); // A~Z
				int sel2 = (int)(Math.random() * 2); // 0:소문자 / 1:대문자
				if(sel2 == 0) {
					ch = (char)(ch + ('a' - 'A')); // 대문자로 변경
				}
				cNumber += ch;
			}
		}
		System.out.println("메일전송완료3");
		// 메일에 출력할 텍스트
		StringBuffer sb = new StringBuffer(); // 가변성 문자열 저장 객체
		sb.append("<h3>[CaffeineCheckMate] 회원 가입 이메일 인증번호.</h3>\n");
		sb.append("<h3>인증 번호 : <span style='color:red'>"+ cNumber +"</span></h3>\n");
		
		String mailContent = sb.toString(); // 문자열로 반환
		
		// 메일 콘텐츠 - 내용 , 메일인코딩, "html" 추가 시 HTML 태그가 해석됨
		mTextPart.setText(mailContent, "UTF-8", "html");
		mParts.addBodyPart(mTextPart);
		
		// 메일 콘텐츠 지정
		message.setContent(mParts);
		
		System.out.println("메일전송완료4");

		// 메일 발송
		Transport t = session1.getTransport("smtp");
		t.connect(smtpEmail, password);
		t.sendMessage(message, message.getAllRecipients());
		memberDTO.setDtoEMAIL(cNumber);
		System.out.println("메일전송완료5");
		t.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return memberDTO;		

}
	//인증 체크
	public boolean checkedCode(String inputCode, String pushCode) {
		System.out.println( "체크코드 " + inputCode);
		return inputCode.equals(pushCode) ? true : false;
	}
	//이메일 중복체크
	public void checkedMail(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		Connection conn = null;
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		HttpSession session = request.getSession(false);
		MemberDAO memberDAO = new MemberDAO();
		int temp = 0;
		String query = "SELECT * FROM ccm.member WHERE M_MAIL = ?";
		System.out.println("중복체크이메일들어옴");
		String tempMail = (String)session.getAttribute("inputMail");
		temp = memberDAO.checkSystemOne(tempMail, query, conn);
		if (conn != null) conn.close();
		System.out.println("디에이오체크메일 다녀온 결과값 " + temp);
			if(temp == 1) {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('이미 존재하는 E_MAIL 입니다.'); location.href="
						+ "'/views/screens/joinRequest.jsp';"
						+ "self.close(); </script>");
				out.flush();
			}
	}
	public String findID (HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession(false);
		String userMail = (String)session.getAttribute("inputMail");
		Connection conn = null;
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		memberDTO.setDtoEMAIL(userMail);
		PrintWriter out = response.getWriter();
		System.out.println("userMail"+ userMail);
		MemberDTO find = memberDAO.find(memberDTO, conn);
		String reID = find.getDtoID();
		System.out.println("디에이오 다녀온 파인드 "+ find);
		if(find.getDtoPRO().equals("true")) {
				out.println("<script>alert('인증성공.'); location.href="
						+ "'/views/screens/findUser.jsp?find=REID&result="
						+ reID
						+"';"
						+"window.opener.emailChecked = true;"
						+"updateParentWindow();"
						+ "self.close(); </script>");
				out.flush();
				return null;
		}else{
			out.println("<script>alert('존재하지않는 이메일입니다.'); location.href="
					+ "'/views/screens//views/screens/findUser.jsp?find=ID';"
					+"window.opener.emailChecked = false;"
					+ "self.close(); </script>");
			out.flush();
		}
		return null;
		
	}
	public String findPW (HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession(false);
		String userMail = (String)session.getAttribute("inputMail");
		Connection conn = null;
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		memberDTO.setDtoEMAIL(userMail);
		PrintWriter out = response.getWriter();
		System.out.println("userMail"+ userMail);
		MemberDTO find = memberDAO.find(memberDTO, conn);
		String rePW = find.getDtoID();
		System.out.println("디에이오 다녀온 파인드 "+ find);
		if(find.getDtoPRO().equals("true")) {

				out.println("<script>alert('인증성공.'); location.href="
						+ "'/views/screens/findUser.jsp?find=REPW&result="
						+ rePW
						+"';"
						+"window.opener.emailChecked = true;"
						+"updateParentWindow();"
						+ "self.close(); </script>");
				out.flush();
				return null;
		}else{
			out.println("<script>alert('존재하지않는 이메일입니다.'); location.href="
					+ "'/views/screens//views/screens/findUser.jsp?find=ID';"
					+"window.opener.emailChecked = false;"
					+ "self.close(); </script>");
			out.flush();
			
		}
		return null;
		
	}
}