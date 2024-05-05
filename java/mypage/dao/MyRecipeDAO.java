package mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import mypage.dto.MyRecipeDTO;

public class MyRecipeDAO {
	
	//Map<String, MyRecipeDTO> mRecipedto = null; 
	//내가 작성한 레시피 ,custom 레시피
	//아이디 받아서 검색하기 / 나와야 하는 항목이 여러개니까 list 사용하기
	public List<MyRecipeDTO> getRecipe(String memberid ,Connection conn) throws SQLException{
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List<MyRecipeDTO> myrecipelist = new ArrayList<>();
		//조인하기 (DTO-1개, DAO-1개)
		try {
			pstmt = conn.prepareStatement("SELECT custom_view.num, custom_view.M_ID, custom_view.CUS_TITLE, custom_img.CUS_IMG_COPY, custom_view.CUS_CONTENT, custom_view.CUS_REGDATE, custom_view.CUS_SUMGOOD FROM custom_view join custom_img on custom_view.cus_no = custom_img.CUS_NO where custom_view.M_ID=?"
											);
			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MyRecipeDTO myrecipe = new MyRecipeDTO(
						rs.getInt("NUM"),
						rs.getString("M_ID"),
						rs.getString("CUS_TITLE"),
						rs.getString("CUS_IMG_COPY"),
						rs.getString("CUS_CONTENT"),
						rs.getString("CUS_REGDATE"),
						rs.getString("CUS_SUMGOOD")
						);
				myrecipelist.add(myrecipe);
			}
			System.out.println("myrecipelist : "+myrecipelist.toString());
			return myrecipelist;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
		
	
}
