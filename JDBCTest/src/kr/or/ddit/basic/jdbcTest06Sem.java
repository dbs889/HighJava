package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

/*
 * 회원을 관리하는 프로그램을 작성하시오.
 * (오라클 DB의 MYMEMBER테이블 이용)
 * 
 * 아래 메뉴의 기능을 모두 구현하시오. (CRUD 구현하기 연습)
 * 
 * 메뉴예시)
 * 		-- 작업 선택 --
 *    1. 자료 추가
 *    2. 자료 삭제
 *    3. 자료 수정
 *    4. 전체 자료 출력
 *    0. 작업 끝.
 *  ------------------
 *  원하는 작업 선택 >>  
 * 
 */



public class jdbcTest06Sem {
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new jdbcTest06Sem().startMember();
	}
	
	public void startMember(){
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1 :			// 추가
					insertMember();
					break;
				case 2 :			// 삭제
					deleteMember();
					break;
				case 3 :			// 수정
					updateMember();
					break;
				case 4 :			// 전체 출력
					displayMember();
					break;
				case 5 :			// 수정2
					updateMember2();
					break;
				case 0 :			// 종료
					System.out.println("작업을 마칩니다.");
					return;
				default : 
					System.out.println("작업 번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요.");
			}
		}
	}
	
	// 자료 삭제
	private void deleteMember(){
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		
		if(count==0){  // 없는 회원이면...
			System.out.println(memId + "은(는) 없은 회원ID입니다.");
			System.out.println("삭제 작업을 종료합니다.");
			return;
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + "회원의 정보가 삭제되었습니다.");
			}else{
				System.out.println(memId + "회원 정보 삭제 실패~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
		
	}
	
	// 자료 수정 ==> 원하는 컬럼만 선택해서 수정
	private void updateMember2(){
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count==0){  // 없는 회원이면...
			System.out.println(memId + "은(는) 없은 회원ID입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		// 수정할 항목 선택하기
		int num;
		String updateField = null;  // 수정 작업을 진행할 '컬럼명'이 저장될 변수
		String updateTitle = null;
		do{
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println("  1. 회원이름     2. 전화번호    3. 회원주소");
			System.out.println("-----------------------------------");
			System.out.print("수정할 항목 >> ");
			num = scan.nextInt();
			
			switch(num){
				case 1 : 
					updateField = "MEM_NAME";
					updateTitle = "회원이름"; break;
				case 2 : 
					updateField = "MEM_TEL";
					updateTitle = "전화번호"; break;
				case 3 : 
					updateField = "MEM_ADDR";
					updateTitle = "회원주소"; break;
				default : 
					System.out.println("수정 항목을 잘못 선택했습니다.");
					System.out.println("다시 선택하세요.");
			}
			
		}while(num<1 || num>3);
		
		// 수정할 데이터 입력 받기
		System.out.println();
		scan.nextLine();  // 버퍼비우기
		System.out.print("새로운 " + updateTitle + " >> ");
		String updateData = scan.nextLine();  
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "UPDATE MYMEMBER SET " + updateField + " = ? WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println("Update 성공!!");
			}else{
				System.out.println("Update 실패~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
		
		
	}
	
	
	// 자료 수정  ==> 전체 컬럼 수정(id제외)
	private void updateMember(){
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count==0){  // 없는 회원이면...
			System.out.println(memId + "은(는) 없은 회원ID입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		System.out.print("새로운 회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();  // 버퍼 비우기
		System.out.print("새로운 회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "UPDATE MYMEMBER SET "
					+ " MEM_NAME = ? , MEM_TEL = ? , MEM_ADDR = ? "
					+ " WHERE MEM_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + "회원 정보 수정 완료!!!");
			}else{
				System.out.println(memId + "회원 정보 수정 작업 실패~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
		
				
	}
	
	// 전체 자료 출력
	private void displayMember(){
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println(" ID   이름         전화번호        주소");
		System.out.println("---------------------------------");
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
//			conn = DBUtil.getConnection();
//			conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM MYMEMBER";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				System.out.print(rs.getString("mem_id") + "\t" );
				System.out.print(rs.getString("mem_name") + "\t" );
				System.out.print(rs.getString("mem_tel") + "\t" );
				System.out.println(rs.getString("mem_addr") );
			}
			System.out.println("---------------------------------");
			System.out.println("출력 작업 끝...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	// 추가 메서드
	private void insertMember(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println();
		System.out.println("새로운 회원 정보 추가하기");
		
		int cnt = 0;
		String memId;
		do{
			System.out.print("회원 ID >> ");
			memId = scan.next();
			
			cnt = getMemberCount(memId);
			if(cnt>0){
				System.out.println(memId + "은 이미 있는 ID입니다.");
				System.out.println("다른 회원ID로 다시 입력하세요.");
			}
		}while(cnt>0);
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.print("주     소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO MYMEMBER (MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR) "
					+ " VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int count = pstmt.executeUpdate();
			
			if(count > 0){
				System.out.println(memId + "회원 정보 추가 성공!!!");
			}else{
				System.out.println(memId + "회원 정보 추가 실패~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
		
	}
	
	// 매개변수로 회원ID를 받아서 해당 회원ID의 개수를 반환하는 메서드
	private int getMemberCount(String memId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt("CNT");
			}
			
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}
		
		return count;
	}
	
	
	// 메뉴 메서드
	private int displayMenu(){
		System.out.println();
		System.out.println(" -- 작업 선택 --");
		System.out.println(" 1. 자료 추가");
		System.out.println(" 2. 자료 삭제");
		System.out.println(" 3. 자료 수정");
		System.out.println(" 4. 전체 자료 출력");
		System.out.println(" 5. 자료 수정2");
		System.out.println(" 0. 작업 끝.");
		System.out.println("---------------");
		System.out.print(" 작업 선택 >> ");
		int num = scan.nextInt();
		return num;
	}

}







