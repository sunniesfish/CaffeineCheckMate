package mypage.handler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandHandler;
import mypage.dto.MypagesDTO;
import mypage.service.MypageService;

///editMyProfile.do = mypage.handler.EditMyProfileHandler
public class EditMyProfilePageMoveHandler extends HttpServlet implements CommandHandler {
	
	private static final long serialVersionUID = 1L;
	
	public static final String FORM_INDEX = "/views/screens/index.jsp";
	public static final String FORM_PROFILE = "/views/screens/editMyProfile.jsp";
	
	private MypageService mypageService = new MypageService();

	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception { 
		HttpSession session = req.getSession(false); //세션이 없으면 null반환
		try {
			if(session != null) {
				String m_id = (String) session.getAttribute("AUTH_USER_ID");
				System.out.println("로그인한 아이디 값 유무 체크 m_id : "+m_id);
				if(m_id !=null) {
					//로그인된 경우 : mypage.jsp로 리다이렉트
					MypagesDTO profile = mypageService.showMyProfile(m_id);
					req.setAttribute("profile", profile);
					return FORM_PROFILE;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FORM_INDEX;
	}

}