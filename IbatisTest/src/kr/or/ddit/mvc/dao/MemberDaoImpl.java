package kr.or.ddit.mvc.dao;

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
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.mvc.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {

	private static MemberDaoImpl memDao;
	private SqlMapClient smc;

	
	//싱글톤 메서드
	public static MemberDaoImpl getInstance(){
		if(memDao == null) memDao = new MemberDaoImpl();
		
		return memDao;
	}

	//생성자
	private MemberDaoImpl(){
		
		
		try {
			//1-1. 문자 인코딩 캐릭터 셋 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			//1-2.환경 설정파일을 읽어오기
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			
			//1-3. 위에서 읽어온 Reader 객체를 이용하여 실제 환경설정을 완성한 후 
			//SQL문을 호출해서 실행할 수 있는 객체를 생성한다
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();//스트림 닫기
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	
	@Override
	public int insertMember(MemberVO memVo) {
		
		int cnt = 0;
		
		try {
			
			Object obj = smc.insert("mymember.insertMember", memVo);
			if(obj == null) cnt = 1;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		try {
			
			cnt = smc.delete("mymember.deleteMember",memId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		
		int cnt = 0;
		try {
		
			cnt = smc.update("mymember.updateMember", memVo);
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
	
		
		List<MemberVO> memList = null;  // select한 회원정보가 저장될 List 변수 선언
		
		try {
			
			memList = smc.queryForList("mymember.memListAll"); // List객체 생성
	
			
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		} 
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		
		int cnt = 0;
		
		try {

			cnt =  (int) smc.queryForObject("mymember.getMember", memId);
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// key값 정보 ==> 회원ID --> memid,  수정할컬럼명 --> field,  수정할데이터 --> data


		int cnt = 0;
		try {
//		
				cnt = smc.update("mymember.updateMember2", paramMap);
				

			
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		
		return cnt;
	}

}
