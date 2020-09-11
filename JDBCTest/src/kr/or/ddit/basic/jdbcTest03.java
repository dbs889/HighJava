package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcTest03 {
	
	//문제 2) lprod_id값을 2개 입력받아서 두 값중 작은 값부터 큰 값 사이의 자료들을 출력하시오. 새로운 클래스

	public static void main(String[] args) {
		
		
		
		
Connection con = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		try {
			
			
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("id 1을 입력하세요 > ");
			int id1 = Integer.parseInt(sc.nextLine());
			System.out.println("id 2을 입력하세요 > ");
			int id2 = Integer.parseInt(sc.nextLine());
			
			
			if(id1 > id2){
				
			
				int start = id1;
				id1 = id2;
				id2 = start;
		
			}
			
			
			//1. 드라이버 로딩을 한다
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.DB접속하기
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","une","java");
			
			
			
			//3.SQL문을 작성한다
			String sql = "select * from lprod where lprod_id between " + id1 +  " and " + id2  ;
			
			//4. 질의를 수행하는 객체 생성 --> 실행하는 객체 생성
			stmt = con.createStatement();
			
			//5. sql문을 DB의 서버로 보내 결과를 얻는다
			
			rs = stmt.executeQuery(sql);
		
			
			//6.  결과 가져오기
			System.out.println(" == SQL문의 처리 결과 == ");
			while(rs.next()){
				System.out.println("Lprod id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("lprod_nm"));
				
				System.out.println("───────────────────────────────────────────────────");
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs != null)try {rs.close();} catch (SQLException e) {}
			if(stmt != null)try {stmt.close();} catch (SQLException e) {}
			if(con != null)try {con.close();} catch (SQLException e) {}
			
		}

	}

}
