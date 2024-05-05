//package mypage.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import jdbc.JdbcUtil;
//import mypage.dto.HealthLightDTO;
//
//public class HealthLightDAO {
//	
//	//Map<Stsring, HealthLightDTO> hlightdtoMap = null;
//	//카페인 섭취량에 따라 색을 결정하고 데이터베이스에 -> 
//	
//	public List<HealthLightDTO> getHealthLight(String memberid , Connection conn) throws SQLException { 
//		PreparedStatement pstmt =null;
//		ResultSet rs = null;
//		List<HealthLightDTO> healthlightlist = new ArrayList<>();
//		try {
//			pstmt = conn.prepareStatement("select M_ID, CAL_DATE, CAL_COLOR, CAL_DAILYCF from CALENDAR where M_ID = ?");
//			pstmt.setString(1, memberid);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				HealthLightDTO hldto = new HealthLightDTO(
//					rs.getString("M_ID"),
//				    rs.getString("CAL_DATE"),
//				    rs.getString("CAL_COLOR"),
//				    rs.getInt("CAL_DAILYCF")
//				);
//				healthlightlist.add(hldto);
//			}
//			System.out.println("healthlightlist" + healthlightlist.toString());
//			return healthlightlist;
//			
//		}finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//	}
//	
//	// 캘린더 색 업데이트
////	public void updateCalColor(HealthLightDTO healthLightDTO, Connection conn) throws SQLException {
////        PreparedStatement pstmt = null;
////        
////        try {
////            pstmt = conn.prepareStatement("UPDATE CALANDER SET CAL_COLOR = ? WHERE M_NO = ? AND CAL_DATE = ?");
////            pstmt.setString(1, healthLightDTO.getCalColor());
////            pstmt.setInt(2, healthLightDTO.getMno());
////            pstmt.setString(3, healthLightDTO.getCalDate());
////            pstmt.executeUpdate();
////        } finally {
////            JdbcUtil.close(pstmt);
////        }
////    }
	
//}
	