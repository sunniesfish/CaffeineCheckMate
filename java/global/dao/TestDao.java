package global.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.catalina.valves.JDBCAccessLogValve;

import jdbc.JdbcUtil;

public class TestDao {
	public String test(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String testresult="";
		try {
			pstmt = conn.prepareStatement("select C_NAME from coffeelist where C_NO=1;");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				testresult =  rs.getString(1);
			}
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return testresult;
	}
}
