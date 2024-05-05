package customRecipe.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import connection.ConnectionProvider;
import customRecipe.dao.CustomBoardUpdateDao;
import customRecipe.dto.CustomBoardAddDto;
import customRecipe.dto.CustomBoardHashDto;
import jdbc.JdbcUtil;

public class CustomBoardUpdateService {

	//게시글 수정
	public void boardUpdate(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		
		
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			 
			ServletContext context = request.getSession().getServletContext();
			String directory = context.getRealPath("/upload/");
			int maxSize = 1024*1024*5;
			String encoding =  "UTF-8";
			
			
			MultipartRequest mp = new MultipartRequest(request,directory,maxSize,encoding,
					new DefaultFileRenamePolicy());
			
			HttpSession session = request.getSession(false);
			String m_id = (String)session.getAttribute("AUTH_USER_ID");
			String a = mp.getParameter("img");
			
//			String c_no = mp.getParameter("c_no");  아직 태그 미생성,c_no는 커피리스트에서 받아야됨
			String c_no = "1";
			String cus_name = mp.getParameter("cus_name");
			String cus_content = mp.getParameter("cus_content");
			String cus_img= mp.getOriginalFileName("file");
			String cus_img_realname = mp.getFilesystemName("file");
			String num = mp.getParameter("num");
		
			
			String shot = mp.getParameter("shot");
			String milkType = mp.getParameter("milkType");
			String syrupType = mp.getParameter("syrupType");;
			String toppingType = mp.getParameter("toppingType");;
			String decaffeinated = mp.getParameter("decaffeinated");;
			if(cus_img_realname==null) {
				cus_img_realname = a;
			}
			
			CustomBoardUpdateDao dao = new CustomBoardUpdateDao();
			CustomBoardAddDto dto = new CustomBoardAddDto("null",m_id,c_no,cus_name,cus_content,"null",0,cus_img,cus_img_realname);
			CustomBoardHashDto hashdto = new CustomBoardHashDto(shot,milkType,syrupType,toppingType,decaffeinated);
		
			
			dao.updateList(dto,con,num);
			dao.updateimg(num, dto, con);
			dao.updatehash(num,hashdto,con);
	
			
			
			con.commit();
			
		} catch (SQLException e1) {
			System.out.println(e1.getMessage()+"SQLException");
			JdbcUtil.rollback(con);
		} catch (IOException e) {
			System.out.println(e.getMessage()+"IOException");
			JdbcUtil.rollback(con);
		}finally {
			JdbcUtil.close(con);
		}
		
		
	}
}