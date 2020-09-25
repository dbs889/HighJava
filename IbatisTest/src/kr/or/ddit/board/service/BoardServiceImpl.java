package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{
	
	private IBoardDao dao;
	
	private static BoardServiceImpl service;
	
	public static BoardServiceImpl getinstance(){
		if(service ==null) {
			service = new BoardServiceImpl();
		}
		return service;
	}

	
	//생성자
	private BoardServiceImpl() {

		dao = BoardDaoImpl.getinstance();
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		
		return dao.insertBoard(boardVo);
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		
		return dao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int board_no) {
		
		return dao.deleteBoard(board_no);
	}

	@Override
	public List<BoardVO> ListAll() {
		
		return dao.ListAll();
	}

//	@Override
//	public int getBoardCount(int board_no) {
//		
//		return dao.getBoardCount(board_no);
//	}

	@Override
	public List<BoardVO> selectBoard(String board_title) {
		// TODO Auto-generated method stub
		return dao.selectBoard(board_title);
	}

	@Override
	public int plusCnt(int board_no) {
		// TODO Auto-generated method stub
		return dao.plusCnt(board_no);
	}

	@Override
	public BoardVO readBoard(int board_no) {
		// TODO Auto-generated method stub
		return dao.readBoard(board_no);
	}

}
