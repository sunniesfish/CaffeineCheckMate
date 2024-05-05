package member.handler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.CheckService;
import controller.CommandHandler;

public class CheckHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException, SQLException{

		CheckService checkService = new CheckService();
			int result = 0;
			String query = null;
			String parameter = null;
			String viewPage = null;
			System.out.println("아이디중복체크 " + request.getParameter("joinId"));
			// 중복체크 분류 및 매개변수값저장
			if(request.getParameter("joinId") != null && request.getParameter("joinNick") == null) {
				query = "SELECT ccm.member.M_ID FROM ccm.member WHERE M_ID = ? UNION all SELECT ccm.member_backup.M_ID FROM ccm.member_backup WHERE M_ID = ?";
				parameter = request.getParameter("joinId");
			}else if(request.getParameter("joinNick") != null && request.getParameter("joinId") == null) {
				query = "SELECT ccm.member.M_NICKNAME FROM ccm.member WHERE M_NICKNAME = ? UNION all SELECT ccm.member_backup.M_NICKNAME FROM ccm.member_backup WHERE M_NICKNAME = ?";
				parameter = request.getParameter("joinNick");
			}
			//삼항연산자 사용해서 보여줄 뷰페이지 지정
			viewPage = parameter.equals(request.getParameter("joinId")) ? 
					"/views/screens/checkID.jsp" : "/views/screens/checkNICK.jsp";
			//checkService.checkTest 메소드 호출로 멤버테이블과 삭제대기테이블 모두 중복체크
			result = checkService.checkTest(parameter, query, request, response);
		
			request.setAttribute("joinPut",parameter);
			request.setAttribute("result", result);
          
         return viewPage;
	}
	
}
	
