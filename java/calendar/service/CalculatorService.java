package calendar.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;

import calendar.dao.CalendarDao;
import calendar.dto.Calendar;
import coffeeList.dao.CoffeeListDao;
import coffeeList.dto.Coffee;
import connection.ConnectionProvider;
import global.function.CCMFunctions;
import jdbc.JdbcUtil;
import mypage.dao.ProfileDao;

public class CalculatorService {
	
	CoffeeListDao coffeeListDao = new CoffeeListDao();
	CalendarDao calendarDao = new CalendarDao();
	ProfileDao profileDao = new ProfileDao();
	
	public void calculate(String memberId, int coffeeNo) {
		Connection conn = null;
		Coffee coffee = null;
		Calendar todaysCaffeine = null;
		Date date = new Date(System.currentTimeMillis());
		int caffeine = 0;
		String color;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			double weight = profileDao.getWeight(memberId, conn);
			
			//커피제품에서 커피넘버로 커피정보 찾아 카페인양 구하기
			coffee = coffeeListDao.selectByCoffeeNo(coffeeNo, conn);
			caffeine = coffee.getC_CAFFEINE();
			
			//저장되어있는 하루섭취량 찾기
			todaysCaffeine = calendarDao.getTodaysRecord(memberId, date, conn);
			
			if (todaysCaffeine != null) {
				//저장되어있는 섭취량이 있을 경우
				//저장되어있는 섭취량을 가져와 선택한 커피의 카페인양과 합산 => 오늘 섭취량
				caffeine +=  todaysCaffeine.getCAL_DAILYCF();
				color = CCMFunctions.ColorFn(caffeine, weight);
				
				//새로 얻은 오늘 섭취량을 캘린더에 저장
				calendarDao.recordCaffeine(todaysCaffeine.getCAL_DATE(), memberId, caffeine,color, conn);

			} else {
				//저장되어있는 섭취량이 없을 경우
				//선택한 커피의 카페인양을 오늘 섭취량에 저장
				color = CCMFunctions.ColorFn(caffeine, weight);
				calendarDao.createTodaysRecord(date, memberId, caffeine, color, conn);
			}
			conn.commit();
		} catch (SQLException e){
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public void Reset(String memberId) {
		Connection conn = null;
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		System.out.println("sql date : "+date);
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			calendarDao.resetCaffeine(date, memberId, conn);
			
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	
	/*
	 * 비동기 메서드
	 */
	public HashMap calculateAsync(String memberId, int coffeeNo) {
		Connection conn = null;
		Coffee coffee = null;
		Calendar todaysCaffeine = null;
		Date date = new Date(System.currentTimeMillis());
		int caffeine = 0;
		int calculatedResult = 0;
		String color;
		HashMap<String, Object> calcMap = new HashMap();
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			double weight = profileDao.getWeight(memberId, conn);			
			coffee = coffeeListDao.selectByCoffeeNo(coffeeNo, conn);			
			caffeine = coffee.getC_CAFFEINE();
			todaysCaffeine = calendarDao.getTodaysRecord(memberId, date, conn);
			
			if (todaysCaffeine != null) {
				caffeine +=  todaysCaffeine.getCAL_DAILYCF();
				color = CCMFunctions.ColorFn(caffeine, weight);
				calendarDao.recordCaffeine(todaysCaffeine.getCAL_DATE(), memberId, caffeine,color, conn);

			} else {
				color = CCMFunctions.ColorFn(caffeine, weight);
				calendarDao.createTodaysRecord(date, memberId, caffeine, color, conn);
			}
			//========================================
			Calendar todaysRecord = calendarDao.getTodaysRecord(memberId, date, conn);
			if(todaysRecord == null) {
				calculatedResult = 0;
			} else {
				calculatedResult = todaysRecord.getCAL_DAILYCF();
			}
			calcMap.put("caffeine", calculatedResult);
			calcMap.put("rda", weight*6.4);
			calcMap.put("color", color);
			conn.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return calcMap;
	}
	
	public HashMap<String, Object> resetAsync(String memberId)  {
		Connection conn = null;
		Date date = new Date(System.currentTimeMillis());
		String color;
		HashMap<String, Object> calcMap = new HashMap();
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			double weight = profileDao.getWeight(memberId, conn);
			calendarDao.resetCaffeine(date, memberId, conn);
			
			color = CCMFunctions.ColorFn(0, 1);
			calcMap.put("caffeine", 0);
			calcMap.put("rda", weight*6.4);
			calcMap.put("color", color);
			conn.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return calcMap;
	}
	
	public HashMap<String, Object> getCalcAsync(String memberId)  {
		Connection conn = null;
		Date date = new Date(System.currentTimeMillis());
		String color;
		HashMap<String, Object> calcMap = new HashMap();
		int calculatedResult = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			double weight = profileDao.getWeight(memberId, conn);
			
			Calendar todaysRecord = calendarDao.getTodaysRecord(memberId, date, conn);
			if(todaysRecord == null) {
				calculatedResult = 0;
			} else {
				calculatedResult = todaysRecord.getCAL_DAILYCF();
			}
			
			color = CCMFunctions.ColorFn(calculatedResult, weight);
			calcMap.put("caffeine", calculatedResult);
			calcMap.put("rda", weight*6.4);
			calcMap.put("color", color);
			conn.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return calcMap;
	}
}
