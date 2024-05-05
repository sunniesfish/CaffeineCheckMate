package coffeeList.service;

import java.sql.Connection;
import java.sql.SQLException;

import coffeeList.dao.CoffeeListDao;
import coffeeList.dto.Coffee;
import connection.ConnectionProvider;
import jdbc.JdbcUtil;

public class CoffeeDetailService {
    
	CoffeeListDao coffeelistdao = new CoffeeListDao();
	
    public Coffee getCoffeeDetail(int coffeeListNo) throws SQLException {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            
            Coffee coffeeDetail = coffeelistdao.getCoffeeDetail(conn, coffeeListNo);
            return coffeeDetail;
        } finally {
            JdbcUtil.close(conn);
        }
    }
}