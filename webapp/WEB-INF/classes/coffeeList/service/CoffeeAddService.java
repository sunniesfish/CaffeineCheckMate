package coffeeList.service;

import java.sql.Connection;
import java.sql.SQLException;

import coffeeList.dao.CoffeeListDao;
import coffeeList.dto.Coffee;
import connection.ConnectionProvider;
import jdbc.JdbcUtil;

public class CoffeeAddService {

	CoffeeListDao coffeeListDao = new CoffeeListDao();
	
	public void addCoffee(Coffee coffee) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			coffeeListDao.AddCoffee(coffee, conn);
			
			conn.commit();
		}catch (SQLException e) {
			System.out.println(e.getMessage() + "SQLException");
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}