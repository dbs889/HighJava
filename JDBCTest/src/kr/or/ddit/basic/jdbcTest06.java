package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;


/*
 * 회원을 관리하는 프로그램을 작성하시오
 * (오라클 DB의 MYMEMBER 테이블 이용)
 * 
 * 아래의 메뉴의 기능을 모두 구현하시오.  (CRUD 구현하기 연습)
 * 메뉴 예시)
 * 		-- 작업 선택 --
 * 		1. 자료 추가
 * 		2. 자료 삭제
 * 		3. 자료 수정
 * 		4. 전체 자료 출력
 * 		0. 작업 끝.
 *     --------------
 *     원하는 작업 선택 >>
 * 
 * 
 */
public class jdbcTest06 {
	
	static Connection con = null;
	static PreparedStatement psmt = null;
	static ResultSet rs = null;
	static Statement st = null; 
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		while (true) {

			System.out.println("-----------작업선택---------------");
			System.out.println("1. 자료추가");
			System.out.println("2. 자료삭제");
			System.out.println("3. 자료수정");
			System.out.println("4. 전체 자료 출력");
			System.out.println("5. 원하는 컬럼만 선택해서 수정");
			System.out.println("0. 작업 끝");
			System.out.println("-------------------------------");
			System.out.println("원하는 작업 선택 >>");

			int input = Integer.parseInt(sc.nextLine());

			switch (input) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				displayAll();
				break;
			case 5:
				memberUpdate2();
				break;
			case 0:
				System.out.println("작업 끝");
				System.exit(0);
				break;

			}

		}

	}

	
	//자료 수정 ==> 원하는 컬럼만 선택해서 수정
	private static void memberUpdate2() {
	
	try {
			
			con = DBUtil.getConnection();
			
		System.out.println("수정할 mem_id를 입력하세요");
		
			String memID = "";
			while(true){
				System.out.println("mem_id >> ");
				memID = sc.nextLine();
				
				String sql = "select * from mymember where mem_id =?";
				
				psmt = con.prepareStatement(sql);
				psmt.setString(1, memID);
				rs = psmt.executeQuery();
				if(rs.next()){
					psmt.close();
					break;
				}else{
					System.out.println("동일한 mem_id가 없습니다 다시 돌아가세요");
					return;
				}
			}
			
			
			//mem_name VARCHAR2(30) not null,
			System.out.println("mem_name >>");
			String memName = sc.nextLine();
			
			//mem_tel VARCHAR2(14),
			System.out.println("mem_Tel >>");
			String memTel = sc.nextLine();
			
			//mem_addr VARCHAR2(14),
			System.out.println("mem_Addr >>");
			String memAddr = sc.nextLine();
			
			
			
			String sql = "UPDATE MYMEMBER SET mem_id = ?, mem_name = ?, mem_tel = ?, mem_addr = ? where mem_id = ?";

			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, memID);
			psmt.setString(2, memName);
			psmt.setString(3, memTel);
			psmt.setString(4, memAddr);
			psmt.setString(5, memID);
			
			psmt.executeUpdate();
			System.out.println("수정되었습니다");
			
			int num;
			String updateField = null; //수정 작업을 진행할 '컬럼명'이 저장될 변수
			String updateTitle = null;
			do{
				System.out.println();
				System.out.println("수정할 항목을 선택하세요.");
				System.out.println("1. 회원이름 2. 전화번호 3. 회원주소 ");
				System.out.println("---------------------------");
				System.out.println("수정할 항목 >>");
				num = sc.nextInt();
				switch(num){
				case 1 :
					updateField = "mem_name";
					updateTitle = "회원이름"; break;
				case 2 :
					updateField = "mem_tel";
					updateTitle = "회원번호"; break;
				case 3 :
					updateField = "mem_addr";
					updateTitle = "회원주소"; break;
				default :
					System.out.println("수정 항목을 잘못 선택했습니다");
					System.out.println("다시 입력하세요");
					
					return;
				}
				
			}while(num<1 ||num>3);
			
			//수정할 데이터 입력 받기	
			System.out.println();
			sc.nextLine();
			System.out.println("새로운" + updateTitle + " >> ");
			String updateData = sc.nextLine();
			
			Connection con = null;
			PreparedStatement psmt = null;
			
				try {
					con = DBUtil3.getConnection();
					String sql2 = "update mymember set" + updateField + " = ? where mem_id = ?";
					psmt = con.prepareStatement(sql2);
				} catch (SQLException e) {
					// TODO: handle exception
				}
			

			
			
		} catch (SQLException e) {
			System.out.println("수정실패!!");
			e.printStackTrace();
		}finally{
			if(psmt != null)try{psmt.close();}catch(SQLException e){}
			if(con != null)try{con.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
			
		}
		
	}
	
	private static void displayAll() {


		System.out.println("================MyMember=============");
		System.out.println("mem_id\tmem_name\tmem_tel\tmem_addr");
		System.out.println("=====================================");
		
		try {
			
//			con = DBUtil2.getConnection();
			con = DBUtil3.getConnection();
			
			String sql = "select * from mymember";
			
			st = con.createStatement();
			
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				
				System.out.println(rs.getString("mem_id") + "\t" + rs.getString("mem_name")  + "\t" + rs.getString("mem_tel")  + "\t" + rs.getString("mem_addr"));
			
				
			}
			
		} catch (SQLException e) {
			
		}finally{
			
			if(rs != null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(st != null)try {st.close();} catch (SQLException e) {e.printStackTrace();}
			if(con != null)try {con.close();} catch (SQLException e) {e.printStackTrace();}
			
		}
		
	}

	private static void update() {
		
		try {
			
			con = DBUtil.getConnection();
			
		System.out.println("수정할 mem_id를 입력하세요");
		
			String memID = "";
			while(true){
				System.out.println("mem_id >> ");
				memID = sc.nextLine();
				
				String sql = "select * from mymember where mem_id =?";
				
				psmt = con.prepareStatement(sql);
				psmt.setString(1, memID);
				rs = psmt.executeQuery();
				if(rs.next()){
					psmt.close();
					break;
				}else{
					System.out.println("동일한 mem_id가 없습니다 다시 돌아가세요");
					return;
				}
			}
			
			
			//mem_name VARCHAR2(30) not null,
			System.out.println("mem_name >>");
			String memName = sc.nextLine();
			
			//mem_tel VARCHAR2(14),
			System.out.println("mem_Tel >>");
			String memTel = sc.nextLine();
			
			//mem_addr VARCHAR2(14),
			System.out.println("mem_Addr >>");
			String memAddr = sc.nextLine();
			
			
			
			String sql = "UPDATE MYMEMBER SET mem_id = ?, mem_name = ?, mem_tel = ?, mem_addr = ? where mem_id = ?";

			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, memID);
			psmt.setString(2, memName);
			psmt.setString(3, memTel);
			psmt.setString(4, memAddr);
			psmt.setString(5, memID);
			
			psmt.executeUpdate();
			System.out.println("수정되었습니다");
			
			
			
			
		} catch (SQLException e) {
			System.out.println("수정실패!!");
			e.printStackTrace();
		}finally{
			if(psmt != null)try{psmt.close();}catch(SQLException e){}
			if(con != null)try{con.close();}catch(SQLException e){}
			if(rs != null)try{rs.close();}catch(SQLException e){}
			
		}
		
		
		
		
	}

	private static void delete() {
		
		
		System.out.println();
		System.out.println("삭제할 member_id를 입력하세요");
		try {
			con = DBUtil.getConnection();
			
			
			String memId  ="";
			while(true){
				System.out.println("삭제할 member_id>");
				memId = sc.nextLine();
				
				String sql = "select * from mymember where mem_id = ?";
				psmt = con.prepareStatement(sql);
				
				psmt.setString(1, memId);
				
				rs = psmt.executeQuery();
				
				if(rs.next()){
					psmt.close();
					break;
				}else{
					System.out.println("존재하는 id값이 없습니다");
					psmt.close();
					return;
				}
				
				
				
			}
			
			String sql = "delete from mymember where mem_id = ?";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1,memId);
			psmt.executeUpdate();
			
			System.out.println("삭제 되었습니다");
		
		} catch (SQLException e) {
			System.out.println("삭제 실패");
			e.printStackTrace();
		}finally{
			if(psmt != null)try{psmt.close();}catch(SQLException e){}
			if(con != null)try{con.close();}catch(SQLException e){}
			
		}
		
		
	}

	private static void insert() {
		
		try {
			con = DBUtil.getConnection();
			
			System.out.println("테이블 정보 추가 하기 ");
			
			String memId ="";
			while(true){
				
				//mem_id VARCHAR2(15) 정보 입력
				System.out.println("mem_id >>");
				memId = sc.nextLine();
				
				String sql = "select * from mymember where mem_id = ?";
				
				psmt = con.prepareStatement(sql);
				
				psmt.setString(1, memId);
				
				rs = psmt.executeQuery();
				
				if(rs.next()){
					
					System.out.println("이미 mem_id값이 존재합니다");
					System.out.println("다시 등록하세요");
					psmt.close();
				}else{
					
					psmt.close();
					break;	
				}	
				
			}
				
			//mem_name VARCHAR2(30) not null,
			System.out.println("mem_name >>");
			String memName = sc.nextLine();
			
			//mem_tel VARCHAR2(14),
			System.out.println("mem_Tel >>");
			String memTel = sc.nextLine();
			
			//mem_addr VARCHAR2(14),
			System.out.println("mem_Addr >>");
			String memAddr = sc.nextLine();
			
			
			String sql = "insert into mymember values(?,?,?,?)";
				
				psmt = con.prepareStatement(sql);
				
				psmt.setString(1, memId);
				psmt.setString(2, memName);
				psmt.setString(3, memTel);
				psmt.setString(4, memAddr);
				
				psmt.executeUpdate();
				System.out.println("등록성공");
		 
			
			
			
		} catch (SQLException e) {
			System.out.println("등록실패");
			e.printStackTrace();
		}finally{
			if(psmt != null)try {psmt.close();} catch (SQLException e) {e.printStackTrace();}
			if(con != null)try {psmt.close();} catch (SQLException e) {e.printStackTrace();}
			if(rs != null)try {psmt.close();} catch (SQLException e) {e.printStackTrace();}
			
		}
		
	
		
	}

}
