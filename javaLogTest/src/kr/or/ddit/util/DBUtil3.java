package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;


//이 클래스는 JDBC드라이버를 로딩하고 DB시스템에 접속하여 Connection객체를 반환하는 역할을 수행하는 클래스이다


public class DBUtil3 {
	
	//1. Logger객체 만들기
	private static Logger logger = Logger.getLogger(DBUtil3.class);
	
	
	private static ResourceBundle bundle; //ResourceBundle객체 변수 선언
	
	
	//static초기화 블럭==> 드라이버 로딩을 수행한다
	static{
		
		
		bundle = ResourceBundle.getBundle("dbInfo");//ResourceBundle객체 생성
		logger.info("ResourceBundle객체 생성 - dbinfo.properties파일 읽기");
		
		try {
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			logger.info("DB 드라이버 로딩 성공 !!");
			
			
		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패");
//			e.printStackTrace();
			
			logger.error("드라이버 로딩 실패"  + e.getMessage());
		}
		
	}
	//DB시스템에 접속한 후 접속 정보를 갖는 Connection 객체를 반환하는 메서드
	public static Connection getConnection(){
		
		try {
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","une", "java");
			
			logger.info("DB에 연결하기 ==> Connection객체 생성!!");
			return DriverManager.getConnection(bundle.getString("url"),
					bundle.getString("user"), 
					bundle.getString("pass"));
			
		} catch (SQLException e) {
//			System.out.println("오라클 연결 실패");
			logger.error("DB에 연결 실패 ㅠㅠ ==> " + e.getMessage());
			return null;
		}
	}

}
