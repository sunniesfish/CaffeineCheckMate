package customRecipe.service;

import java.sql.Connection;

import connection.ConnectionProvider;
import customRecipe.dao.CustomBoardDelDao;
import jdbc.JdbcUtil;


public class CustomBoardDeleteService {
	
	CustomBoardDelDao customBoardDelDao = new CustomBoardDelDao();
	
	public void deleteRecipe(String memberId, String cusNo) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//삭제 서비스
			customBoardDelDao.deleteCus(memberId, cusNo, conn);
		
			conn.commit();
		}catch (Exception e) {
			System.out.println("deleteRecipe 에서 오류발생");
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
}
