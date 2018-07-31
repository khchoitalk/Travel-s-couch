package common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemplate {
	
	//tomcat 이 제공하는 DBCP 를 사용해서
	//DB 연결 처리
	public static Connection getConnection(){
		Connection con = null;
		
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:comp/env/jdbc/oraDB");
			con = ds.getConnection();
			con.setAutoCommit(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	
	/*public static Connection getConnection() {
		Connection con = null;
		Properties prop = new Properties();

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "student";
		String passwd = "student";		

		String driver = null;
		String url = null;
		String user = null;
		String passwd = null;
		
		try {
			//현재 클래스가 있는 폴더에서 properties 파일 읽어옴
			String currentPath = JDBCTemplate.class
					.getResource("./").getPath();
			prop.load(new FileReader(
					currentPath + "driver.properties"));
			
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			passwd = prop.getProperty("passwd");
			
			Class.forName(driver);
			con = DriverManager.getConnection(
					url, user, passwd);
			con.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}*/

	public static void close(Connection con) {
		try {
			if(con != null && !con.isClosed())
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed())
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection con) {
		try {
			if(con != null && !con.isClosed())
				con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection con) {
		try {
			if(con != null && !con.isClosed())
				con.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
