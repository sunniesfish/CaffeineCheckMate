package customRecipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class CustomBoardDelDao {

	public void deleteCus(String memberId, String cusNo, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		String[] deleteQueries = {
		    "delete from CUSTOM_HASH where CUS_NO =(select cus_no from custom_view where num=?)",
		    "delete from CUSTOM_IMG where CUS_NO =(select cus_no from custom_view where num=?)",
		    "delete from CUSTOM_REPLY where CUS_NO =(select cus_no from custom_view where num=?)"
		};

		for (int i = 0; i < 3; i++) {
		    pstmt = conn.prepareStatement(deleteQueries[i]);
		    pstmt.setString(1, cusNo);
		    pstmt.executeUpdate();
		    pstmt = null;
		}
			
			// CUSTOM 테이블에서 레시피 삭제 - M_ID가 필요해서 따로 했음
			pstmt = conn.prepareStatement(
					"delete from CUSTOM where M_ID=? and CUS_NO=(select cus_no from custom_view where num=?)");
			pstmt.setString(1, memberId);
			pstmt.setString(2, cusNo);
			pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
		}
	
	}


