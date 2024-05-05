package member.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandHandler;
import member.dto.MemberDTO;
import member.service.EmailService;

public class MailCheckHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException, SQLException{
		String returnPage = "/views/screens/joinEmail.jsp";
		MemberDTO memberDTO = new MemberDTO();
		HttpSession session = request.getSession(false);
		EmailService emailService = new EmailService();
		String inputEmail1 = request.getParameter("joinEmail1")+"@"+request.getParameter("joinEmail2");
		System.out.println("첫번째 메일보낼때"+inputEmail1);
		System.out.println("두번째 모달창 " + request.getAttribute("inputCode"));
		System.out.println("두번째 모달창 히든 " + request.getAttribute("inputEmail"));
		String hidden = request.getParameter("hidden");
		String emailCheck = (String)session.getAttribute("find");
		System.out.println("히든값 "+hidden);
		System.out.println("세션들어오는값  "+emailCheck);
		switch (hidden) {
		case "emailCheck" :
			System.out.println("이메일 체크 푸쉬 "+session.getAttribute("codeMail"));
			System.out.println("이메일 체크 입력"+request.getParameter("input"));
			String pushCode = (String)session.getAttribute("codeMail");
			String iputCode = request.getParameter("input");
			boolean result = emailService.checkedCode(iputCode,pushCode);
			returnPage = result == true ? emailTrue(request,response) : emailFalse(request,response);
			break;
		case "emailinput" :
			memberDTO = emailService.emailSend(inputEmail1,request,response);
			//emailCheck.equals("emailCheck") ? session.setAttribute("find", emailCheck) : session.setAttribute("code", memberDTO.getDtoEMAIL());
			//session.setAttribute("find", emailCheck);
			session.setAttribute("code", memberDTO.getDtoEMAIL());
			System.out.println("파라미터 파인드"+session.getAttribute("find"));
			System.out.println(session.getAttribute("code"));
			System.out.println("두번째 메일보낼때"+inputEmail1);
			System.out.println("세번째 (세션)메일보낼때"+session.getAttribute("inputMail"));
			returnPage = "/views/screens/joinEmail.jsp";
			break;
		case "findID" :
			String pushCodefindID = (String)session.getAttribute("codeMail");
			String iputCodefindID = request.getParameter("input");
			boolean idCode = emailService.checkedCode(iputCodefindID,pushCodefindID);
			System.out.println("코드체크값 "+idCode);
			returnPage = idCode == true ? emailService.findID(request,response) : emailFalse(request,response);
			break;
		case "findPW" :
			String pushCodefindPW = (String)session.getAttribute("codeMail");
			String iputCodefindPW = request.getParameter("input");
			boolean pwCode = emailService.checkedCode(iputCodefindPW,pushCodefindPW);
			returnPage = pwCode == true ? emailService.findPW(request,response) : emailFalse(request,response);
			System.out.println("코드체크값 "+pwCode);
			break;
		}
		System.out.println("메일핸들러 나갈때1"+session.getAttribute("inputMail"));
		return returnPage;
	}
	public String emailTrue(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
		PrintWriter out = response.getWriter();
		EmailService emailService = new EmailService();
		emailService.checkedMail(request,response);
		out.println("<script>alert('인증성공!.'); location.href="
				+ "'/views/screens/joinRequest.jsp';"
				+"window.opener.emailChecked = true;"
				+ "self.close();"
				+"updateParentWindow(); </script>");
		out.flush();
		return null;
		
	}
	public String emailFalse(HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<script>alert('인증실패.'); location.href="
				+ "'/views/screens/joinRequest.jsp';"
				+"window.opener.emailChecked = false;"
				+ "self.close(); </script>");
		out.flush();
		return null;
	}
}