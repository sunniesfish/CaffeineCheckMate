package mypage.handler;

import controller.CommandHandler;
import mypage.dto.MypagesDTO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypage.service.MypageService;

public class MypageHandler extends HttpServlet implements CommandHandler {
   private static final long serialVersionUID = 1L;
   public static final String FORM_INDEX = "/views/screens/Main.jsp";
   public static final String FORM_MYPAGE = "/views/screens/Mypage.jsp";
   
   private MypageService mypageService = new MypageService();

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      System.out.println("마이페이지 진입 get");
      //response.getWriter().append("Served at: ").append(request.getContextPath());
      try {
         process(request, response);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("마이페이지 진입 post");
      doGet(request, response);
   }
   
//   public String process(HttpServletRequest req, HttpServletResponse res) throws Exception { 
//      HttpSession session = req.getSession(false); //세션이 없으면 null반환
//      w
//      // 세션이 null이 아니고 세션에 "userId"속성이 있다면 해당 값을 가져온다. AUTH_USER_ID=세션 속성 이름
//      if (session != null && session.getAttribute("AUTH_USER_ID") != null) {
//         String userId = (String)session.getAttribute("AUTH_USER_ID");
//         // 로그인된 경우: mypage.jsp로 리다이렉트
//         return FORM_MYPAGE;
//      } else {
//         // 로그인되지 않은 경우: index.jsp로 리다이렉트
//         return FORM_INDEX;
//      }
//   }
   
   public String process(HttpServletRequest req, HttpServletResponse res) throws Exception { 
      HttpSession session = req.getSession(false); //세션이 없으면 null반환
      
      if(session != null) {
         String m_id = (String) session.getAttribute("AUTH_USER_ID");
         System.out.println("로그인한 아이디 값 유무 체크 m_id : "+m_id);
         if(m_id !=null) {
            //로그인된 경우 : mypage.jsp로 리다이렉트
            MypagesDTO mypages = mypageService.showMyProfile(m_id);
            req.setAttribute("mypages", mypages);
            return FORM_MYPAGE;
         }
         
      }
      //로그인되지 않은 경우나 세션이 없는 경우 : INDEX.JSP로 리다이렉트
      return FORM_INDEX;
      }
   
}