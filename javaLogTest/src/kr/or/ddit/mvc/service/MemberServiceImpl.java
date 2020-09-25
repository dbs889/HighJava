package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	//service는 dao를 부르기 때문에 dao객체가 필요한다
	
	private IMemberDao dao;
	
	
	//싱글톤 패턴 1. 참조값이 저장될 변수를 private static으로 선언한다
	private static MemberServiceImpl service;
	
	//싱글톤 패턴 3. 자신 class의 인스턴스를 생성하고 반환하는 메서드를 public static으로 한다 
	public static MemberServiceImpl getInstance(){
		
		if(service ==null) service = new MemberServiceImpl();
		
		return service;
	}
	
	
	//싱글톤 패턴 2. 생성자의 접근제한자를 private로 한다
	private MemberServiceImpl() {
		
		dao = MemberDaoImpl.getInstance();
	}
	//--------------------------------------------------------------------
	@Override
	public int insertMember(MemberVO memVo) {
		
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
	
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMember() {
		
		return dao.getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
	
	
}
