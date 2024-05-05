package mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mypage.dto.UserProfileDTO;
import jdbc.JdbcUtil;

public class UserProfileDAO {
	
	//Map<String,UserProfileDTO> updtoMap = null;
	//Map -> 지우기 
	//프로필 검색
	public UserProfileDTO ShowMyPF(String memberId, Connection conn) throws SQLException { 
		PreparedStatement pstmt =null;
		ResultSet rs = null;

		
		try {
			pstmt = conn.prepareStatement("select MEMBER.M_NICKNAME, PROFILE.P_NO, PROFILE.M_ID, PROFILE.P_WEIGHT, PROFILE.P_IMG_REAL, PROFILE.P_IMG_COPY from PROFILE join member on PROFILE.M_ID = MEMBER.M_ID where PROFILE.M_ID =?"
					+ "");
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery(); // sql 실행
			UserProfileDTO updto = null;
			if(rs.next()) {
				updto = new UserProfileDTO(
					rs.getString("M_NICKNAME"),
				    rs.getInt("P_NO"),
				    rs.getString("M_ID"),
				    rs.getInt("P_WEIGHT"),
				    rs.getString("P_IMG_REAL"),
				    rs.getString("P_IMG_COPY") 
				);
				System.out.println("updto : "+updto.toString());
				return updto;
			}
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}
	// 프로필 업데이트
	// 닉네임, 몸무게, 프로필 사진 업데이트
		public String update(String value, String id, String query, Connection conn) {
		    String returnPage = null;
		    int result = -1;
		    PreparedStatement pstmt = null;
		    try {
		        pstmt = conn.prepareStatement(query);
		        pstmt.setString(1, value);
		        pstmt.setString(2, id);
		        result = pstmt.executeUpdate();
		        if (result == 1) {
		            returnPage = "true";
		            conn.commit();
		        } else {
		            returnPage = "false";
		            conn.rollback();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (pstmt != null) pstmt.close();
		            if (conn != null) conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return returnPage;
		}
	
		public void updateProfile(UserProfileDTO dto, Connection conn)throws SQLException { 
			PreparedStatement pstmt =null;
			
			try {
				String sql = "UPDATE PROFILE AS p INNER JOIN member AS m ON p.M_ID = m.M_ID SET p.P_WEIGHT = ?,p.P_IMG_REAL = ?, p.P_IMG_COPY = ?, m.M_NICKNAME = ? WHERE p.M_ID = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getP_WEIGHT());				
				pstmt.setString(2, dto.getP_IMG_REAL());
				pstmt.setString(3, dto.getP_IMG_COPY());
				pstmt.setString(4, dto.getM_NICKNAME());
				pstmt.setString(5, dto.getM_ID());
				
				pstmt.executeUpdate();
			} finally {
				if(pstmt !=null) pstmt.close();
			}
			
		}
	
}