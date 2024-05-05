package coffeeList.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import coffeeList.dao.CoffeeListDao;
import coffeeList.dto.Coffee;
import coffeeList.dto.CoffeeListPage;
import connection.ConnectionProvider;
import favorite.dao.FavoriteDao;
import favorite.dto.Favorite;
import jdbc.JdbcUtil;
import member.dao.MemberDAO;

public class CoffeeListPageService {
	
	CoffeeListDao coffeeListDao = new CoffeeListDao();
	FavoriteDao favoriteDao = new FavoriteDao();
	MemberDAO memberDao = new MemberDAO();
	
	//리스트 페이지 회원일 때
	public CoffeeListPage getCoffeeList(String memberId, int page) throws SQLException {
		Connection conn = null;
		try {
			System.out.println("커피서비스 멤버 OK 도달");
			
			conn = ConnectionProvider.getConnection();
			// 한 페이지에 표시할 게시물 수
			int size = 12; 
			
			ArrayList<Coffee> coffeeList = coffeeListDao.CoffeeListView(conn, page, size);
			HashMap<Integer, Favorite> favMap = favoriteDao.getFavList(memberId, conn);
			int total = coffeeListDao.CoffeeListCount(conn);
			//하기 checkAdmin 메소드 호출
			boolean admin = checkAdmin(memberId);
			
			CoffeeListPage coffeeListPage = new CoffeeListPage(coffeeList, favMap, total, page, size);
			
			return coffeeListPage;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//리스트 페이지 비회원일 때
	public CoffeeListPage notAuthCoffeeList(int page) throws SQLException {
		Connection conn = null;
		try {
			System.out.println("커피서비스 비로그인");
			conn = ConnectionProvider.getConnection();
			
			// 한 페이지에 표시할 게시물 수
			int size = 12; 
			
			ArrayList<Coffee> coffeeList = coffeeListDao.CoffeeListView(conn, page, size);
			int total = coffeeListDao.CoffeeListCount(conn);
			
			CoffeeListPage coffeeListPage = new CoffeeListPage(coffeeList,total,page,size);
			
			return coffeeListPage;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//비회원 검색
	public CoffeeListPage searchCoffee(String searchType, String searchQuery, int page) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			ArrayList<Coffee> coffeeList = coffeeListDao.searchCoffees(conn, searchType, searchQuery, page, 12);
			int total = coffeeList.size();  // 검색 결과 수
			return new CoffeeListPage(coffeeList, total, page, 12);
		}
	}
	
	//회원 검색
	public CoffeeListPage searchAuthCoffee(String searchType, String memberId, String searchQuery, int page) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			ArrayList<Coffee> coffeeList = coffeeListDao.searchCoffees(conn, searchType, searchQuery, page, 12);
			HashMap<Integer, Favorite> favMap = favoriteDao.getFavList(memberId, conn);
			int total = coffeeList.size();  // 검색 결과 수
			return new CoffeeListPage(coffeeList, favMap,total, page, 12);
		}
	}
	
	//관리자 유무 확인
	public boolean checkAdmin(String memberId) throws SQLException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			String mrole = memberDao.adminOk(conn, memberId);
			
			return "A".equals(mrole);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}

//커넥션 null로 초기화
//예외 처리 메세지
//서비스에서 커넥션 넣기
//System.out.println("예외 메시지: " + e.getMessage());