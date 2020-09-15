package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao{
	
	private static MemberDaoImpl memDao;
	private MemberDaoImpl(){}
	public static MemberDaoImpl getInstance(){
		if(memDao == null) memDao = new MemberDaoImpl();
		
		return memDao;
	}

	@Override
	public int insertMember(MemberVO memVo) {

		Connection con = null;
		PreparedStatement psmt = null;

		int cnt = 0;
		try {
			con = DBUtil3.getConnection();

			String sql = "insert into mymember values(?,?,?,?)";

			psmt = con.prepareStatement(sql);

			psmt.setString(1, memVo.getMem_id());
			psmt.setString(2, memVo.getMem_name());
			psmt.setString(3, memVo.getMem_tel());
			psmt.setString(4, memVo.getMem_addr());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("등록실패");
			e.printStackTrace();
		} finally {
			if (psmt != null)
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

		}

		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		Connection con = null;
		PreparedStatement psmt = null;
		int cnt = 0;
	
		try {
			con = DBUtil3.getConnection();	
			
			String sql = "delete from mymember where mem_id = ?";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1,memId);
			
			cnt = psmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(psmt != null)try{psmt.close();}catch(SQLException e){}
			if(con != null)try{con.close();}catch(SQLException e){}
			
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			
			con = DBUtil3.getConnection();
			
		
			String sql = "UPDATE MYMEMBER SET mem_id = ?, mem_name = ?, mem_tel = ?, mem_addr = ? where mem_id = ?";

			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, memVo.getMem_id());
			psmt.setString(2, memVo.getMem_name());
			psmt.setString(3, memVo.getMem_tel());
			psmt.setString(4, memVo.getMem_addr());
			
			
			cnt = psmt.executeUpdate();

			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(psmt != null)try{psmt.close();}catch(SQLException e){}
			if(con != null)try{con.close();}catch(SQLException e){}
		}
		
		
	
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		//select한 회원정보가 저장될 List선언
		List<MemberVO> allmember = new ArrayList<>();
				
				
				
		try {
			
		
			con = DBUtil3.getConnection();
			
			String sql = "select * from mymember";
			
			st = con.createStatement();
			
			rs = st.executeQuery(sql);
			
			while(rs.next()){
			//한 레코드의 정보를 vo에 저장하고 이 vo객체를 List에 추가한다 	
				
				MemberVO memvo = new MemberVO();
				
				
				memvo.setMem_id(rs.getString("mem_id"));
				memvo.setMem_id(rs.getString("mem_name"));
				memvo.setMem_id(rs.getString("mem_tel"));
				memvo.setMem_id(rs.getString("mem_addr"));
				
				allmember.add(memvo);
			
				
			}
			
			
			
		} catch (SQLException e) {
			
		}finally{
			
			if(rs != null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(st != null)try {st.close();} catch (SQLException e) {e.printStackTrace();}
			if(con != null)try {con.close();} catch (SQLException e) {e.printStackTrace();}
			
		}
		
		return allmember;
	}

	@Override
	public int getMemberCount(String memId) {
		
		//회원 ID를 매개변수로 받아 해당 회원의 개수를 반환하는 메서드
		//중복되는 걸 count하여 cotroller에게 넘겨 준다  controller에서 입력받은 값이 중복 되면 cnt가 0
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int cnt = 0;
		try {
			
			con = DBUtil3.getConnection();
			String sql = "select count(mem_id) cnt from mymember where mem_id =?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, memId);
			rs = psmt.executeQuery();
			while(rs.next()){
			  
				rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally{
			
			if(rs != null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(psmt != null)try {psmt.close();} catch (SQLException e) {e.printStackTrace();}
			if(con != null)try {con.close();} catch (SQLException e) {e.printStackTrace();}
			
		}
	
	
		
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// key 값  정보 : 회원 ID --> memid,  수정할 컬럼명 --> field, 수정할 데이터 -->data
		
		Connection con = null;
		PreparedStatement psmt = null;
		int cnt = 0;
		
			try {
				con = DBUtil3.getConnection();
				String sql = "update mymember set" + paramMap.get("field") + " = ? where mem_id = ?";
				psmt = con.prepareStatement(sql);
				psmt.setString(1, paramMap.get("data"));
				psmt.setString(2, paramMap.get("memid"));
				
				cnt = psmt.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO: handle exception
			}finally{
				if(psmt != null)try{psmt.close();}catch(SQLException e){}
				if(con != null)try{con.close();}catch(SQLException e){}
				
			}
		return cnt;
	}


	
	

}
