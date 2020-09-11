package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


//이 클래스는 JDBC드라이버를 로딩하고 DB시스템에 접속하여 Connection객체를 반환하는 역할을 수행하는 클래스이다


public class DBUtil2 {
	
	
	private static Properties prop; //Properties객체 변수 선언
	
	
	
	//static초기화 블럭==> 드라이버 로딩을 수행한다
	static{
		prop = new Properties(); //properties객체 생성

		File f = new File("res/dbInfo.properties");
		
		
		FileInputStream fin = null;
	
		
		try {
			fin = new FileInputStream(f);
			
			prop.load(fin);
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
			
			
			
			
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
		
	}
	//DB시스템에 접속한 후 접속 정보를 갖는 Connection 객체를 반환하는 메서드
	public static Connection getConnection(){
		
		try {
			
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","une", "java");
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"), 
					prop.getProperty("pass"));
			
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
			return null;
		}
	}

}
