package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.time.Duration;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;


import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

@WebListener
public class DBCPInitListener extends HttpServlet implements ServletContextListener {
     
	// poolConfig 읽고 JDBC드라이버 로딩, 커넥션풀 초기화
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String poolConfig =
				sce.getServletContext().getInitParameter("poolConfig");
		Properties prop = new Properties();
		try {
			prop.load(new StringReader(poolConfig));
		} catch (IOException e) {
			throw new RuntimeException("config load fail", e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);
	}
	

	/*
	 * Properties객체를 받아 "jdbcdriver"라는 key에 대한 value값을 이름으로 하는
	 * 클래스(JDBC드라이버)를 로딩
	 */
	private void loadJDBCDriver(Properties prop) {
		String driverClass = prop.getProperty("jdbcdriver");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}
	
	
	/*
	 * Properties객체를받아 DB주소, 계정id/pw를 string변수에 저장
	 * 받은 정보를 바탕으로 
	 */
	private void initConnectionPool(Properties prop) {
		try {
			String jdbcUrl = prop.getProperty("jdbcUrl");
			String username = prop.getProperty("dbUser");
			String pw = prop.getProperty("dbPass");
			
			ConnectionFactory connFactory = 
					new DriverManagerConnectionFactory(jdbcUrl, username, pw);
			//데이터베이스 연결을 생성
			
			PoolableConnectionFactory poolableConnFactory =
					new PoolableConnectionFactory(connFactory, null);
			//객체의 관리방법 결정. 연결하는 데이터베이스가 많다면 null 대신
			//키값을 통해 객체를 맵핑하여 풀을 관리
			
			String validationQuery = prop.getProperty("validationQuery");
			if (validationQuery != null && !validationQuery.isEmpty()) {
				poolableConnFactory.setValidationQuery(validationQuery);
			}
			
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRuns(Duration.ofMillis(1000L*60L*5L));
			poolConfig.setTestWhileIdle(true);
			int minIdle = getIntProperty(prop, "minIdle", 5);
			poolConfig.setMinIdle(minIdle);
			int maxTotal = getIntProperty(prop, "maxTotal", 50);
			poolConfig.setMaxTotal(maxTotal);
			//Pool 내  커넥션 관리의 설정
			
			GenericObjectPool<PoolableConnection> connectionPool =
					new GenericObjectPool<>(poolableConnFactory,poolConfig);
			//설정 들을 바탕으로 풀 생성
			poolableConnFactory.setPool(connectionPool);
			//커넥션을 풀에 세팅
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)
					DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			String poolName = prop.getProperty("poolName");
			driver.registerPool(poolName, connectionPool);
			//풀을 드라이버에 등록
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

    private int getIntProperty(Properties prop, String propName, int defaultValue) {
    	String value = prop.getProperty(propName);
    	if (value==null) return defaultValue;
		return Integer.parseInt(value); 
	}
    
    
    /*이 클래스는 ServletContextListener 인터페이스를 구현함.
     * ServletContextListener는 웹애플리케이션이 시작되거나 종료될 때 발생하는 
     * 이벤트를 처리하는 인터페이스임
     * contextDestroyed는 ServletContextListener에서 오버라이딩하는 메서드로
     * 애플리케이션 종료시 동작을 구현함.
     */
    @Override
	public void contextDestroyed(ServletContextEvent sce)  { 
    }
    

}
