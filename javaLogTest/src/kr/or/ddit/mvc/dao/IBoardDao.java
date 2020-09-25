package kr.or.ddit.mvc.dao;

import java.util.List;

import kr.or.ddit.mvc.vo.BoardVO;

/*
 * 전체 목록 출력, 새글작성, 수정, 삭제, 검색 기능
 * 
 * 
 */
public interface IBoardDao {
	
	
	/**
	 * BoardVO에 담겨진 자료를 DB에 담아 insert하는 메서드
	 * @param boardVo
	 * @return 작업성공시 1 , 실패시 0
	 */
	
	//새글작성
	public int insertBoard(BoardVO boardVo);
	
	/**
	 * BoardVO 자룔를 이용하여 DB에 update하는 메서드
	 * @param boardVo
	 * @return 작업성공시 1 , 실패시 0
	 */
	
	//수정
	public int updateBoard(BoardVO boardVo);
	
	
	/**
	 *  board_no를 매개변수로 받아서 해당 게시글 정보를 삭제하는 메서드
	 * @param board_no
	 * @return 작업성공시 1 , 실패시 0
	 */
	//삭제
	public int deleteBoard(int board_no);
	
	
	/**
	 *  전체 게시글 정보를 DB에 가져와  List에 담아서 반환하는 메서드
	 * @return BoardVO가 저장될 List객체
	 */
	public List<BoardVO> ListAll();
	
	/**
	 * 게시글 번호 를 매개변수로 받아서 해당 회원의 개수를 반환하는 메서드
	 * @param board_no 검색할 게스글 번호
	 * @return 검색된 게시글의 개수
	 */
	public int getBoardCount(int board_no);
	
	/**
	 * 게스글 제목을 매개변수로 받아 해당 개시글을 조회하는 메서드
	 * @param board_title
	 * @return 작업 성공시 1, 실패시 0
	 */
	public List<BoardVO> selectBoard(String  board_title);
	
	/**
	 * 게시글 번호를 매개변수로 받아 조회수를 플러스하는 메서드
	 * @param board_no
	 * @return 작업 성공시 1, 실패시 0
	 */
	public int plusCnt(int board_no);
	
	
	public BoardVO readBoard(int board_no);
}
