package favorite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import favorite.dto.Favorite;
import jdbc.JdbcUtil;

public class FavoriteDao {
	

	public HashMap<Integer, Favorite> getFavList(String memberId, Connection conn) throws SQLException { 
		HashMap<Integer ,Favorite> favMap = new HashMap();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select a.M_ID, a.C_NO, b.C_NAME, a.C_FAV_DATE, b.C_IMG_COPY "
					+ "from COFFEE_FAVORITE a join COFFEELIST b "
					+ "on a.C_NO = b.C_NO "
					+ "where a.M_ID=?;");
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			Favorite favorite = null;
			while(rs.next()) {
				favorite = new Favorite(
						rs.getString("M_ID"),
						rs.getInt("C_NO"),
						rs.getString("C_NAME"),
						rs.getDate("C_FAV_DATE"),
						rs.getString("C_IMG_COPY")
						);
				System.out.println("이미지 : "+rs.getString("C_IMG_COPY"));
				favMap.put(favorite.getC_NO(), favorite);
			}
			return (HashMap<Integer, Favorite>) favMap;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int AddFav(String memberId, int coffeeNo, Connection conn) throws SQLException { //커넥션을 변수로 받음
		System.out.println("favoirtedao");
		PreparedStatement pstmt = null; //pstmt 초기화
		int affectedRow = 0;
		String sql = 
				" insert into COFFEE_FAVORITE (M_ID,C_NO) "
				+ "select ?, ? from dual where 5> "
				+ "(select count(*)	 from COFFEE_FAVORITE where M_ID=?) "
				+ "and 0=(select count(C_NO) from COFFEE_FAVORITE where M_ID=? and C_NO=?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, coffeeNo);
			pstmt.setString(3, memberId);
			pstmt.setString(4, memberId);
			pstmt.setInt(5, coffeeNo);
			affectedRow = pstmt.executeUpdate(); //쿼리 실행
			System.out.println("AddFav affect "+affectedRow+" rows");
			return affectedRow;
		} finally {
			JdbcUtil.close(pstmt); //pstmt 종료
		}
	}
	
	public boolean delete(String memberId, int coffeeNo, Connection conn) throws SQLException {
		PreparedStatement pstmt =null;
		try {
			pstmt = conn.prepareStatement(
					"delete from COFFEE_FAVORITE where M_ID = ? and C_NO=?");
			pstmt.setString(1, memberId);
			pstmt.setInt(2, coffeeNo);
			int rows = pstmt.executeUpdate();
			System.out.println("delete favorite : "+rows+" rows deleted");
			return rows>0? true : false;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
