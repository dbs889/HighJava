package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	//service는 dao를 부르기 때문에 dao객체가 필요한다
	private IMemberDao dao;
	
	
	
	
	public MemberServiceImpl() {
		
		dao = new MemberDaoImpl();
	}

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
