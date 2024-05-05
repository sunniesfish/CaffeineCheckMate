package member.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.dto.MemberDTO;
import member.service.UpdateService;
import controller.CommandHandler;

public class UpdateMemberHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException, SQLException{
		MemberDTO memberDTO = new MemberDTO();
		UpdateService updateService = new UpdateService();
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		String resultPage = null;	
		String updateId = request.getParameter("identgyId");
		String requestValue = (String)request.getParameter("Value");
		System.out.println("requestValue 핸들러 "+request.getParameter("Value"));
		switch (requestValue) {
			case "MyselfCheck" :
				System.out.println("MyselfCheck 케이스 들어옴");
				// 정보수정을위한 아이디/비밀번호확인 
				if(updateId!=null) {
					memberDTO.setDtoID(request.getParameter("identgyId"));
					memberDTO.setDtoPW(request.getParameter("identgyPw"));
					memberDTO.setDtoPRO("MyselfCheck");
					resultPage = updateService.UpdateStart(memberDTO, request, response);
				}else {
					System.out.println("정보확인완료상태");
				}
				break;
			case "passwordChange" :
				//패스워드 변경
				String password = request.getParameter("updatePw0");
				String oldPasword = updateService.updateOldPassword((String)session.getAttribute("AUTH_USER_ID"));

				if(oldPasword.equals(password)){
					String pw1 = request.getParameter("updatePw1");
					String pw2 = request.getParameter("updatePw2");
					String formValue = request.getParameter("Value");
					resultPage = updateService.updatePassword(pw1,pw2,formValue,request, response);					
				}else if(!oldPasword.equals(password)){
					out.println("<script>alert('기존비밀번호가 일치하지않습니다.');"
					 		+ "location.href="
					 		+ "'/views/screens/passwordChange.jsp';"
					 		+ "</script>");
					 out.flush();
				}
				break;
			case "UpdateTel" :
				//전화번호수정
			    String tel1 = request.getParameter("updateTel1");
			    String tel2 = request.getParameter("updateTel2");
			    String tel3 = request.getParameter("updateTel3");
			    String tel = (tel1 != null ? tel1 : "null") + "-" + (tel2 != null ? tel2 : "null") + "-" + (tel3 != null ? tel3 : "null");
			    
			    if (!"null-null-null".equals(tel)) {
			        System.out.println("tel 수정 요청 들어옴");
			        memberDTO.setDtoTEL(tel);
			        resultPage = updateService.UpdateTEL(memberDTO, request, response);
			    } else {
			        System.out.println("입력이 없어서 요청이 없는 것 null-null-null");
			    }
				break;
			case "UpdateSsn" :
				//sns 수신여부수정
				String sns = request.getParameter("updateSnsYN");
				System.out.println(sns);
				    if (sns != null) {
				        System.out.println("sns 수정 요청 들어옴");
				        memberDTO.setDtoSNS(sns);
				        resultPage = updateService.UpdateSNS(memberDTO, request, response);
				    } else {
				        System.out.println("sns 수정 요청 없음");
				    }
				break;
			case "findPassword" :
					String findId = request.getParameter("findId");
					String findPw1 = request.getParameter("findPassword1");
					String findPw2 = request.getParameter("findPassword2");
					updateService.findPassword(findPw1, findPw2, findId, request, response);
				break;
		}
		 System.out.println(resultPage);
         return resultPage;
	}
}