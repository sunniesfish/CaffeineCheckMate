package favorite.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import coffeeList.dao.CoffeeListDao;
import connection.ConnectionProvider;
import favorite.dao.FavoriteDao;
import favorite.dto.Favorite;
import jdbc.JdbcUtil;

public class FavoriteService {
	
	FavoriteDao favoriteDao = new FavoriteDao();
	CoffeeListDao coffeeListDao = new CoffeeListDao();
	
	public boolean addFavorite(String memberId ,int coffeeNo) {
		System.out.println("addfavorite service");
		int affectedRow = 0;
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			affectedRow = favoriteDao.AddFav(memberId, coffeeNo, conn);
			
			if(affectedRow != 0) {
				coffeeListDao.plusFav(coffeeNo, conn);
				conn.commit();
				return true;				
			} else {
				conn.commit();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			return false;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public void deleteFav(String memberId, int coffeeNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			if (favoriteDao.delete(memberId, coffeeNo, conn)) {				
				coffeeListDao.minusFav(coffeeNo, conn);
			}
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
	
	public HashMap getFavAsync(String memberId) {
		Connection conn = null;
		HashMap<Integer, Favorite> favMap = null;
		try {
			conn = ConnectionProvider.getConnection();
			favMap = favoriteDao.getFavList(memberId, conn);	
		}catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
		return favMap;
	}
	
	public HashMap deleteFavAsync(String memberId, int coffeeNo) {
		Connection conn = null;
		HashMap<Integer, Favorite> favMap = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			if (favoriteDao.delete(memberId, coffeeNo, conn)) {				
				coffeeListDao.minusFav(coffeeNo, conn);
			}
			favMap = favoriteDao.getFavList(memberId, conn);
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
		return favMap;
	}

	public HashMap addFavAsync(String memberId, Integer coffeeNo) {
		Connection conn = null;
		HashMap favlist = null;
		int affectedRow = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			affectedRow = favoriteDao.AddFav(memberId, coffeeNo, conn);
			
			if(affectedRow != 0) {
				coffeeListDao.plusFav(coffeeNo, conn);
			}			
			favlist = favoriteDao.getFavList(memberId, conn);
			System.out.println("favlist fs : "+favlist.toString());
			conn.commit();
			return favlist;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
		return favlist;
	}
}
/*
 *  - 서비스에서 커넥션을 생성하면 한 트랜잭션 안에서 그 서비스에서 사용하는 DAO의 메서드를 관리할 수 있음
 * 
 *  - 트랜잭션이 DB에 영향을 주는 경우(insert / update / delete를 포함하는 경우)
 *   1. 커넥션 생성
 *   2. 오토커밋을 끔 (안끄는 경우 자동으로 커밋돼서 롤백할 수 없음)
 *   3. 문제가 생겼을 시 catch에서 rollback
 *   4. 문제가 없을 경우 commit
 *   5. 마지막으로 트랜잭션의 모든 작업이 완료되면 커넥션 종료
 *   
 *   - 트랜잭션이 DB에 영향을 주지 않는 경우 (select만 수행하는 경우)
 *   DB에 영향이 없기 때문에 롤백이 필요한 경우가 없음 => 오토커밋을 끌 필요 없음
 *   1. 커넥션 생성
 *   2. 마지막으로 트랜잭션의 모든 작업이 완료되면 커넥션 종료
 *   
 *   ResultSet(rs), PreparedStatement(pstmt), Statement(stmt)는 각각의 메서드에서 종료해줘야함
 */

