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
import member.service.DeleteService;

public class DeleteMemberHandler implements CommandHandler {
	
	private String resultPage;

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException, SQLException{
		MemberDTO memberDTO = new MemberDTO();
		DeleteService deleteService = new DeleteService();
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		resultPage = null;	
		String deleteId = request.getParameter("identgyId");
		String requestValue = request.getParameter("Value");
		// 정보수정을위한 아이디/비밀번호확인 
		switch (requestValue) {
		case "delete" :
			if(deleteId!=null) {
				memberDTO.setDtoID(request.getParameter("identgyId"));
				memberDTO.setDtoPW(request.getParameter("identgyPw"));
				memberDTO.setDtoPRO("delete");
				System.out.println("회원탈퇴 케이스안 이프문");
				System.out.println("입력아이디 "+memberDTO.getDtoID());
				System.out.println("입력비번 "+memberDTO.getDtoPW());
				resultPage = deleteService.deleteAction(memberDTO, request, response);
			}else {
				System.out.println("딜리트 케이스 엘스문");
				session.setAttribute("errMSG", "아이디나 비밀번호가 일치하지않습니다.");
				out.println("<script>alert('로그인실패'); location.href='/views/screens/login.jsp';</script>");
				out.flush();
			}
			break;
		case "deleteCancel" :
			if(deleteId!=null){
				memberDTO.setDtoID(request.getParameter("identgyId"));
				memberDTO.setDtoPW(request.getParameter("identgyPw"));
				memberDTO.setDtoPRO("deleteCancel");
				System.out.println("탈퇴취소 케이스안 이프문");
				System.out.println("입력아이디 "+memberDTO.getDtoID());
				System.out.println("입력비번 "+memberDTO.getDtoPW());
				resultPage = deleteService.deleteCancelAction(memberDTO, request, response);
			}else if(resultPage.equals("false")) {
				System.out.println("삭제핸들러 모든케이스들어가지못함");
				 out.println("<script>alert('정보확인실패.'); location.href='/views/screens/okDelete.jsp';</script>");
				 out.flush();
			}
			break;
		}	
         return resultPage;
	}
}