package kr.or.ddit.basic.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.basic.vo.TestVo;
import kr.or.ddit.basic.vo.fileInfoVo;


//원격지에서 호출할 수 있는 메서드를 선언하는 interface
//즉, RMI용 interface는 Remote를 상속해서 작성한다


public interface RemoteInterface extends Remote {

	//이 인터페이스에서 선언되는 모든 메서드들은 RemoteException을 throws하도록 작성해야한다
	
	//이 인터페이스에서 선언된 메서드의 파라미터 변수는 클라이언트에서 서버쪽으로 보내는 데이터가 저장되는 것이고, 
	//선언된 메서드의 반환값은 서버에서 처리한 클라이언트로 전달하는 데이터 이다
	
	public int doRemotePrint(String str) throws RemoteException;
	
	public void doPrintList(List<String> list ) throws RemoteException;
	
	
	public void doPrintVo(TestVo vo) throws RemoteException;
	
	//파일전송용 메서드선언
	public void setFile(fileInfoVo fileVo) throws RemoteException;
	
	
	
}
