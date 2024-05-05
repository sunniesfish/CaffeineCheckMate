package global.service;

import java.sql.Connection;

import connection.ConnectionProvider;
import global.dao.TestDao;
import jdbc.JdbcUtil;

public class TestService {
	TestDao testDao = new TestDao();
	public String test() {
		System.out.println("test method");
		String test = null;
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			test = testDao.test(conn);
			System.out.println("test : "+test);
		}catch (Exception e) {
			System.out.println("catch");
		} finally {
			JdbcUtil.close(conn);
		}
		return test;
	}
}
