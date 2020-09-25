package kr.or.ddit.board.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao{
	
	
	private static BoardDaoImpl boardDao;
	private SqlMapClient smc;
	
	private BoardDaoImpl() {
		
		try {
			
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static BoardDaoImpl getinstance(){
		if(boardDao == null){
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	

	@Override
	public int insertBoard(BoardVO boardVo) {
		 
		
		int cnt = 0;
		
		try {
		
			Object obj = smc.insert("jdbcBorard.insertBoard", boardVo);
			if(obj == null) cnt =1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {

		
		int cnt = 0;
		
		try {
			
			cnt = smc.update("jdbcBorard.updateBoard", boardVo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		
		int cnt = 0;
		
		try {
			
			cnt = smc.delete("jdbcBorard.deleteBoard", board_no);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> ListAll() {
		
		List<BoardVO> bList = null;
		
		try {
			
		
			bList =  smc.queryForList("jdbcBorard.boardListAll");
	
			
		} catch (SQLException e) {
			bList =null;
			e.printStackTrace();
		}
		return bList;
	}

	

	@Override
	public List<BoardVO> selectBoard(String board_title) {
		
		
		
		 List<BoardVO> bList = null;
		try {
			
			bList = smc.queryForList("jdbcBorard.searchBoard", board_title);
			
		} catch (SQLException e) {
			bList =null;
			e.printStackTrace();
		} 
		return bList;
	}

	
	@Override
	public int plusCnt(int board_no) {
	
		int cnt = 0;
		
		try {
			
			cnt = smc.update("jdbcBorard.plusCnt", board_no);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public BoardVO readBoard(int board_no) {
		
		BoardVO boardVo = new BoardVO();
	
		
		try {
			
			boardVo = (BoardVO) smc.queryForObject("jdbcBorard.readBoard", board_no);
		
		} catch (SQLException e) {
			boardVo =null;
			e.printStackTrace();
		} 
		
		return boardVo;
	}

}
