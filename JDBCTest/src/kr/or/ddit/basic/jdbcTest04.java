package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbcTest04 {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Connection con = null;
		Statement stmt = null;
		PreparedStatement psmt = null;
		
		//Statement와 PreparedStatement의 차이  stmt는 쿼리 문을 실행할때 해석하고 처리할 때 가져온다 psmt는 쿼리문에 대한 해석을 이미 하므로 똑같은 데이터를 가지고 해석을 해야할때 사용                                                                         
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","une", "java");
			
			System.out.println("계좌 번호 정보 추가하기");
			
			System.out.println("계좌번호 : ");
			String bankNo = scan.next();
			
			System.out.println("은 행 명 : ");
			String bankName = scan.next();
			
			System.out.println("예금주 명 : ");
			String bankUser = scan.next();
			
//			// 1. Statement객체를 이용하여 데이터 추가하기
//			String sql = "insert into bankinfo (bank_no,bank_name,bank_user_name,bank_date) " 
//			+ " values('" + bankNo + "', '" + bankName + "', '" + bankUser + "', sysdate ) ";
//			
//			stmt = con.createStatement();
//			
//			//실행할 SQL문이 select문일 경우에는 executeQuery()메서드를 사용하고,
//			//실행할 SQL문이 select문이 아닐 경우에는 executeUpdate()메서드를 사용한다
//			//executeUpdate()메서드의 반환 값 ==> 작업에 성공한 레코드 수 
//	
//			int cnt = stmt.executeUpdate(sql); 
			
			
			// 2. PreparedStatement 객체를 이용하여 데이터 추가하기
			
			// 2-1 SQL문에서 데이터가 들어갈 자리에 물음표(?)로 표시한다
			String sql = "insert into bankinfo (bank_no,bank_name,bank_user_name,bank_date)"
					+ "values (?,?,?,sysdate)";
			
			//2-2 PreparedStatement 객체 생성 ==> 이때 처리할 SQL문을 매개변수에 넘겨준다
			psmt = con.prepareStatement(sql);
			
			//2-3 SQL문의 ?자리에 데이터를 셋팅한다
			// 형식) psmt.set자료형이름(물음표위치번호, 데이터)
			
			psmt.setString(1, bankNo);
			psmt.setString(2, bankName);
			psmt.setString(3, bankUser);
			
			//데이터 셋팅이 완료되면 SQL문을 실행한다
			int cnt = psmt.executeUpdate();
			
			
			
			
			System.out.println("반환값 : " + cnt);
			
			
			
			
			
			
			
			
			
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(psmt != null)try{psmt.close();}catch(SQLException e){}
			if(con != null)try{con.close();}catch(SQLException e){}
			
		}
		
		
	}

}
