package member.handler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.dto.MemberDTO;
import member.service.LoginService;
import controller.CommandHandler;

public class LoginHandler implements CommandHandler {
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		MemberDTO memberDTO = new MemberDTO();
		LoginService loginService = new LoginService();
	
		memberDTO.setDtoID(request.getParameter("loginId"));
		memberDTO.setDtoPW(request.getParameter("loginPw"));
		memberDTO.setDtoPRO(request.getParameter("loginValue"));

		String result = null;
		try {
			result = loginService.loginTest(memberDTO, request, response);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("핸들러나가기전 리턴값 "+result);
         return result;
	}
}
	
