package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;


	/*
	 * lprod 테이블에 새로운 데이터 추가하기
	 * 
	 *lprod_gu 와 lprod_nm은 직접입력받아거 처리하고
	 *lprod_id는 현재의 iprod_id값 중 제일 큰 값보다 1크기한다 9보다큰값  select max(lprod_id) from lprod
	 *그리고, 입력받은 lprod_gu가 이미등록되어 있으면 다시 입력한다  select count(*) from lprod where lprod_gu ='p109'; 셀프조인
	 */
public class jdbcTest05 {

	public static void main(String[] args) {
		
		
		
		Scanner scan = new Scanner(System.in);
		
		Connection con = null;
		Statement stmt = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","une", "java");
			
			con = DBUtil.getConnection();
			
			System.out.println("lprod테이블 정보 추가하기");
			
			String lprodGU ="";
			String lprodNm ="";
			
			while(true){
				
				System.out.println("lprod_gu 입력 >");
				lprodGU = scan.next();
				
				System.out.println("lprod_nm 입력 >");
				lprodNm = scan.next();
				
				//중복여부 체크
				String sql = "select * from lprod where LPROD_gu = ?";
				
				
				psmt = con.prepareStatement(sql);
				
				psmt.setString(1, lprodGU);
				
				rs = psmt.executeQuery();
				
				if(rs.next()){
					System.out.println("lprod_gu가 이미 존재합니다");
					psmt.close();
					
				}else{
					psmt.close(); //다음 PrepareStatemet실행을 위해 닫아준다
					break;
				}
			}
			
		
			
			

			
			String sql2 = "insert into lprod values((select max(lprod_id)+1 from lprod),?,?)";
			
			psmt = con.prepareStatement(sql2);
			
			psmt.setString(1, lprodGU);
			psmt.setString(2, lprodNm);
			
			psmt.executeUpdate();
			System.out.println("등록성공");
//			int cnt = psmt.executeUpdate();
//			System.out.println("가나다");
//			
//			if(cnt == 1){
//				System.out.println("등록됐다");
//			}else{
//				System.out.println("등록실패!!");
//				
//				
//			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
//		catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		finally{
			
			if(stmt != null)try{stmt.close();}catch(SQLException e){}
			if(psmt != null)try{psmt.close();}catch(SQLException e){}
			if(con != null)try{con.close();}catch(SQLException e){}
			
			
		}
		
	
	}

}
