package mypage.handler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditMyProfileHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditMyProfileHandler() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("프로필 수정 get");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("프로필 수정 post");
		doGet(request, response);
	}
	
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException, SQLException{
		return null;
	
	
	
	}
}