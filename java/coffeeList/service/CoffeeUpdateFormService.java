package coffeeList.service;

import java.sql.Connection;
import java.sql.SQLException;

import coffeeList.dao.CoffeeListDao;
import coffeeList.dto.Coffee;
import connection.ConnectionProvider;
import jdbc.JdbcUtil;

public class CoffeeUpdateFormService {

	public Coffee updateFormCoffee(int coffeeNo) throws SQLException {
		Connection conn = null;
		CoffeeListDao coffeeListDao = new CoffeeListDao();
		
		try {
			conn = ConnectionProvider.getConnection();
			//detail 메서드 재활용
			
			Coffee coffeeUpForm = coffeeListDao.getCoffeeDetail(conn, coffeeNo);
			return coffeeUpForm;
		} finally {
			JdbcUtil.close(conn);
		}
	}
}