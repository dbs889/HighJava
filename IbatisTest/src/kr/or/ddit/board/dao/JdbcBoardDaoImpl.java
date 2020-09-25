package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.BulidSqlMapClient;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	private static JdbcBoardDaoImpl dao;
	private SqlMapClient smc;
	
	
	//생성자
	private JdbcBoardDaoImpl() { 
		smc = BulidSqlMapClient.getSqlMapClient();
	}
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null) {
			dao = new JdbcBoardDaoImpl();
		}
		return dao;
	}
	
	@Override
	public int insertBoard(JdbcBoardVO jBoardVo) {
		int cnt = 0;
		try {
			
			Object obj = smc.insert("jdbcBorardBySem.insertBoard",jBoardVo);
			if(obj == null) cnt =1;
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			
			cnt = smc.delete("jdbcBorardBySem.deleteBoard", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO jBoardVo) {
		int cnt = 0;
		try {
			
			cnt = smc.update("jdbcBorardBySem.updateBoard", jBoardVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		
		
		JdbcBoardVO jBoardVo = null;

		try {
			
			
			jBoardVo = (JdbcBoardVO) smc.queryForObject("jdbcBorardBySem.getBoard", boardNo);
			
		} catch (SQLException e) {
			jBoardVo = null;
			e.printStackTrace();
		} 
		
		return jBoardVo;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		List<JdbcBoardVO> boardList = null;
		try {

			boardList = smc.queryForList("jdbcBorardBySem.getAllBoardList");
			
			
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		
		return boardList;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String jBoardTitle) {
		
		
		List<JdbcBoardVO> boardList = null;
		
		
		if(jBoardTitle==null) {
			jBoardTitle = "";
		}
		try {
			
			boardList = smc.queryForList("jdbcBorardBySem.getSearchBoardList", jBoardTitle);
			
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		try {
			
			
			cnt = smc.update("jdbcBorardBySem.setCountIncrement", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

}
