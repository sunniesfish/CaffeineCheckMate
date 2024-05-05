package mypage.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionProvider;
import mypage.dao.UserProfileDAO;
import mypage.dto.UserProfileDTO;

public class EditMyProfile {
	Connection conn = null;
	
	public String CheckPw(UserProfileDTO profiledto,HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
		UserProfileDAO memberdao = new UserProfileDAO();
		UserProfileDTO result = null;
		
		//result = memberdao.selectID(profiledto,conn);
		
		return null;
		
		
	}
	
	}