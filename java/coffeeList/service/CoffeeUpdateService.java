package coffeeList.service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import coffeeList.dao.CoffeeListDao;
import coffeeList.dto.Coffee;
import connection.ConnectionProvider;
import jdbc.JdbcUtil;

public class CoffeeUpdateService {
	
	CoffeeListDao coffeeListDao = new CoffeeListDao();
	
	//업데이트를 처리하는 메서드
	public void updateCoffee(Coffee coffee, String saveDirectory) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//수정 후 이전 사진을 삭제하는 로직
			Coffee existingCoffee = coffeeListDao.fileDeleteCoffee(coffee.getC_NO(), conn);
			//!null & 수정 전 이전 정보와 현재의 C_IMG_REAL 문자열 동일한 지 비교
			if (existingCoffee != null && !existingCoffee.getC_IMG_REAL().equals(coffee.getC_IMG_REAL())) {
				String pathDelete = saveDirectory + "/" + existingCoffee.getC_IMG_REAL();
				File oldFile = new File(pathDelete);
				if (oldFile.exists()) {
					boolean isDeleted = oldFile.delete();
					System.out.println("수정 후 이전 사진 삭제 성공");
				} else {
					System.out.println("파일 경로 오류" + pathDelete);
				}
			}
			
				coffeeListDao.updateCoffee(coffee, conn);
			
			conn.commit();
		}catch (SQLException e) {
			System.out.println(e.getMessage() + "SQLException");
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}