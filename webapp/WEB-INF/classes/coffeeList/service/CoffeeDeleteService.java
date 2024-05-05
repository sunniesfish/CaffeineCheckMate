package coffeeList.service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import coffeeList.dao.CoffeeListDao;
import coffeeList.dto.Coffee;
import connection.ConnectionProvider;
import jdbc.JdbcUtil;

public class CoffeeDeleteService {

	public void deleteCoffee(int coffeeNo, String saveDirectory) {
		Connection conn = null;
		
		CoffeeListDao coffeeListDao = new CoffeeListDao();
		
		try {
			conn = ConnectionProvider.getConnection();
			//auto commit false : 호출된 메서드가 끝나면 다시 auto로 돌아감
			conn.setAutoCommit(false);
			
			//삭제 후 게시물 이미지 실제 파일 삭제하는 로직
			Coffee deleteExistingCoffee = coffeeListDao.fileDeleteCoffee(coffeeNo, conn);
			
			
			if (deleteExistingCoffee != null && deleteExistingCoffee.getC_IMG_REAL() != null) {
				String filePath = saveDirectory + "/" + deleteExistingCoffee.getC_IMG_REAL();
				
				File file = new File(filePath);
				if (file.exists() && !file.delete()) {
					System.out.println("파일 삭제 실패:" + filePath);
				}
			}
			//db 삭제
			coffeeListDao.deleteFavCoffee(coffeeNo, conn);
			coffeeListDao.deleteCoffee(coffeeNo, conn);
			
			conn.commit();
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"SQLException");
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}