package calendar.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import calendar.dto.Calendar;
import jdbc.JdbcUtil;


public class CalendarDao {
	
	public Calendar createTodaysRecord(Date date, String memberId, int caffeine, String color, Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			pstmt =conn.prepareStatement(
					"insert into CALENDAR (CAL_DATE, M_ID, CAL_DAILYCF, CAL_COLOR) values(?,?,?,?)");
			pstmt.setDate(1, date);
			pstmt.setString(2, memberId);
			pstmt.setInt(3, caffeine);
			pstmt.setString(4, color);
			int rows = pstmt.executeUpdate();
			System.out.println("create todays record  : "+rows+" rows updated");
		} finally {
			JdbcUtil.close(pstmt);
		}
		return null;
	}
	
	public Calendar getTodaysRecord(String memberId, Date date, Connection conn) throws SQLException {
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		Calendar todaysCaffeine = null;
		try {
			pstmt = conn.prepareStatement(
					"select CAL_DAILYCF from CALENDAR where M_ID = ? and CAL_DATE=?");
			pstmt.setString(1,memberId);
			pstmt.setDate(2, date);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				todaysCaffeine = new Calendar(
						date,
						memberId,
						rs.getInt("CAL_DAILYCF"),
						""
						);
				System.out.println("rs.next - date : "+date+" mid : "+memberId+" dailycf : "+rs.getInt("CAL_DAILYCF"));
				return todaysCaffeine;
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void recordCaffeine(Date date, String memberId, int caffeine, String color, Connection conn) throws SQLException {
		PreparedStatement pstmt =null;
		String caffeineStr = caffeine+"";
		System.out.println("recordCaffeine reuslt:"+caffeine+" memberId : "+memberId+" date : "+date);
		try {
			pstmt=conn.prepareStatement(
					"update CALENDAR set CAL_DAILYCF=?, CAL_COLOR=?  where M_ID = ? and CAL_DATE=?");
			pstmt.setInt(1, caffeine);
			pstmt.setString(2, color);
			pstmt.setString(3, memberId);
			pstmt.setDate(4, date);
			
			int rows = pstmt.executeUpdate();
			System.out.println("recordCaffeine reuslt:"+rows+" rows updated");
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public void resetCaffeine(Date date, String memberId, Connection conn) throws SQLException {
		PreparedStatement pstmt =null;
		try {
			pstmt = conn.prepareStatement(
					"update CALENDAR set CAL_DAILYCF=0, CAL_COLOR='#BFFE01' where M_ID = ? and CAL_DATE=?");
			pstmt.setString(1, memberId);
			pstmt.setDate(2, date);	
			int rows = pstmt.executeUpdate();
			System.out.println("resetcaffeine : "+rows+" rows updated");
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Calendar> getHealthLight(String memberid , Connection conn) throws SQLException { 
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List<Calendar> healthlightlist = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement("select M_ID, CAL_DATE, CAL_COLOR, CAL_DAILYCF from CALENDAR where M_ID = ?");
			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Calendar calendar= new Calendar(
					rs.getDate("CAL_DATE"),
					rs.getString("M_ID"),
				    rs.getInt("CAL_DAILYCF"),
				    rs.getString("CAL_COLOR")
				);
				healthlightlist.add(calendar);
			}
			System.out.println("healthlightlist" + healthlightlist.toString());
			return healthlightlist;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

}
