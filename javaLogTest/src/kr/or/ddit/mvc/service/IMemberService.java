package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.vo.MemberVO;

/**
 * Service 객체는 DAo에 설정된 메ㅔ서드를 원하는 작업에 맞게 호출하여 결과를 받아오고
 * 받아온 결과 자료를 Controller에게 보내주는 역할을 한다
 * 보통 DAO의 메서드와 구조를 같게한다(고급자바에 한정)
 * @author PC-08
 *
 */


public interface IMemberService {
	
	/**
	 * MemverVo에 담겨진 자료를 DB에 insert하는 메서드
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert 작업 성공 : 1, 작업 실패 : 0
	 */
	
	
	//회원 등록 
	public int insertMember(MemberVO memVo);
	
	
	/**
	 * 회원 ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원ID
	 * @return 작업성공 : 1, 작업 실패 : 0
	 */
	
	//회원 삭제
	public int deleteMember(String memId);
	
	/**
	 * MemberVO 자룔를 이용하여 DB에 update하는 메서드
	 * @param memVo update할 회원 정보가 들어있는 MemberVo객체
	 * @return 작업 성공 : 1 작업 실패 : 0
	 */
	//전체 업데이트
	public int updateMember(MemberVO memVo);
	
	
	/**
	 * Map에 저장된 정보를 이용해서 한개의 컬럼값을 수정하는 메서드 
	 * 
	 * @param paramMap update할 컬럼정보가 들어있는 Maprorcp 
	 * @return 작업 성공 : 1, 작업 실패  : 0
	 */
	
	public int updateMember2(Map<String, String> paramMap);
	
	
	
	/**
	 * 전체 회원정보를 DB에 가져와  List에 담아서 반환하는 메서드
	 * 
	 * @return 회원정보(MemberVO)가 저장될 List객체
	 */
	//전체자료 출력 (memverVO를 담는 List를 반환한다)
	public List<MemberVO> getAllMember();
	
	
	/**
	 * 회원 ID를 매개변수로 받아서 해당 회원의 개수를 반환하는 메서드
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원ID의 개수
	 */
	public int getMemberCount(String memId);
	
	

}
