//package mypage.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.ArrayList;
//
//import jdbc.JdbcUtil;
//import mypage.dto.FavoriteListDTO;
//
//
//public class FavoriteListDAO {
//	
//	//Map<String,FavoriteListDTO> favlistMap = null;
//
//	public List<FavoriteListDTO> getFavList(String memberId, Connection conn) throws SQLException { 
//		PreparedStatement pstmt =null;
//		ResultSet rs = null;
//		List<FavoriteListDTO> favlist = new ArrayList<>(); // 리스트 객체를 생성
//		// COFFEE_FAVORITE+COFFEELIST 조인해서 검색하기 
//		// COFFEE_FAVORITE.C_NO UQ 오류
//		try {
//			pstmt = conn.prepareStatement("SELECT coffee_favorite.M_ID, coffee_favorite.C_NO, coffeelist.c_name, coffeelist.c_caffeine, coffeelist.c_img_copy  FROM coffee_favorite  JOIN coffeelist ON coffee_favorite.C_NO = coffeelist.c_no  WHERE coffee_favorite.M_ID =?");
//			pstmt.setString(1, memberId);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				 FavoriteListDTO favorite = new FavoriteListDTO(
//						rs.getString("M_ID"),
//						rs.getInt("C_NO"),
//						rs.getString("C_NAME"),
//						rs.getInt("C_CAFFEINE"),
//						rs.getString("C_IMG_COPY")
//						); 
//				 favlist.add(favorite);
//			}
//			System.out.println("favlist : "+favlist.toString());
//			return favlist;
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//	}
//	
//}