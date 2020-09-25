package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.mvc.vo.BoardVO;
import kr.or.ddit.util.DBUtil3;

public class BoardDaoImpl implements IBoardDao{
	

	@Override
	public int insertBoard(BoardVO boardVo) {
		 
		Connection con = null;
		PreparedStatement psmt = null;
		int cnt = 0;
		
		try {
			con = DBUtil3.getConnection();
			String sql = "insert into jdbc_board(board_no,board_title,board_writer,board_date,board_cnt,board_content)"
							+ " values(board_seq.NEXTVAL,?,?,sysdate,0,?)";
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, boardVo.getBoard_title());
			psmt.setString(2, boardVo.getBoard_writer());
			psmt.setString(3, boardVo.getBoard_content());
			
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(psmt!=null) try{ psmt.close(); }catch(SQLException e){}
			if(con!=null) try{ con.close(); }catch(SQLException e){}
			
			
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {

		Connection con = null;
		PreparedStatement psmt = null;
		int cnt = 0;
		
		try {
			con = DBUtil3.getConnection();
			String sql = "update jdbc_board set board_title = ?, board_content = ? where board_no = ?";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, boardVo.getBoard_title());
			psmt.setString(2, boardVo.getBoard_content());
			psmt.setInt(3, boardVo.getBoard_no());
			
			
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(psmt!=null) try{ psmt.close(); }catch(SQLException e){}
			if(con!=null) try{ con.close(); }catch(SQLException e){}
			
			
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		
		Connection con = null;
		PreparedStatement psmt = null;
		int cnt = 0;
		
		try {
			con = DBUtil3.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setInt(1, board_no);
			
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(psmt!=null) try{ psmt.close(); }catch(SQLException e){}
			if(con!=null) try{ con.close(); }catch(SQLException e){}
			
			
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> ListAll() {
		Connection con =null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BoardVO> bList = null;
		
		try {
			
			con = DBUtil3.getConnection();
			// order by board_no DESC
			String sql = "select * from jdbc_board order by board_no DESC";
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			bList = new ArrayList<>();
			
			while(rs.next()){
				
				BoardVO boardVo = new BoardVO();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getDate("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
				bList.add(boardVo);
			
			}
			
			
			
		} catch (SQLException e) {
			bList =null;
			e.printStackTrace();
		}finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(con!=null) try{ con.close(); }catch(SQLException e){}
		}
		
		return bList;
	}

	@Override
	public int getBoardCount(int board_no) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			con = DBUtil3.getConnection();
			
			String sql = "SELECT COUNT(*) CNT FROM jdbc_board WHERE board_no = ? ";
			
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, board_no);
			
			rs = psmt.executeQuery();
			
			if(rs.next()){
				cnt = rs.getInt("CNT");
			}
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(psmt!=null) try{ psmt.close(); }catch(SQLException e){}
			if(con!=null) try{ con.close(); }catch(SQLException e){}
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> selectBoard(String board_title) {
		
		
	
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		 List<BoardVO> bList = null;
		try {
			
			
			con = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM jdbc_board WHERE board_title LIKE ? ";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, "%"+board_title+"%");
			
			rs = psmt.executeQuery();
			bList = new ArrayList<>();
			
			while(rs.next()){
				
				BoardVO boardVo = new BoardVO();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getDate("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
				bList.add(boardVo);
				
			}
			
		} catch (SQLException e) {
			bList =null;
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(psmt!=null) try{ psmt.close(); }catch(SQLException e){}
			if(con!=null) try{ con.close(); }catch(SQLException e){}
		}
		
		return bList;
	}

	
	@Override
	public int plusCnt(int board_no) {
		Connection con = null;
		PreparedStatement psmt = null;
		int cnt = 0;
		
		try {
			con = DBUtil3.getConnection();
			
			String sql = "update jdbc_board set board_cnt = board_cnt+1 WHERE board_no = ? ";
			
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, board_no);
			
			cnt = psmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(psmt!=null) try{ psmt.close(); }catch(SQLException e){}
			if(con!=null) try{ con.close(); }catch(SQLException e){}
		}
		
		return cnt;
	}

	@Override
	public BoardVO readBoard(int board_no) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		BoardVO boardVo = new BoardVO();
	
		
		try {
			
			
			con = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM jdbc_board WHERE board_no = ? ";
			
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, board_no);
			
			rs = psmt.executeQuery();
			if(rs.next()){
				
				boardVo.setBoard_no(rs.getInt(1));
				boardVo.setBoard_title(rs.getString(2));
				boardVo.setBoard_writer(rs.getString(3));
				boardVo.setBoard_date(rs.getDate(4));
				boardVo.setBoard_cnt(rs.getInt(5));
				boardVo.setBoard_content(rs.getString(6));
				
			}
			
		} catch (SQLException e) {
			boardVo =null;
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(psmt!=null) try{ psmt.close(); }catch(SQLException e){}
			if(con!=null) try{ con.close(); }catch(SQLException e){}
		}
		
		return boardVo;
	}

}
