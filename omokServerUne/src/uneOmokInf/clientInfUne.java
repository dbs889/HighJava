package uneOmokInf;

import java.rmi.Remote;
import java.rmi.RemoteException;


/*
 *	RMI(Remote Method Invocation) 
 *		: 분산되어 존재하는 객체 간의 메서지 전송(메소드호출 포함)을 가능하게 하는 프로토콜 
 * 
 * 	RMI 구성
 * 		1. Server
 * 			- 공통 인터페이스
 * 			- 구현 클래스
 * 			- 스켈레톤 클래스
 * 			- 서버실행 클래스
 * 
 * 		2. Client
 * 			- 공통인터페이스
 * 			- 스텁 클래스
 * 			- 클라이언트 실행 클래스
 * 
 * 	RMI 주의점
 * 		1. server와 client,인터페이스의 패키지명은 모두 동일해야 한다
 * 		2. server와 client의 인터페이스는 모두 Remote클래스를 상속하여야 한다
 * 		3. server와 client에 선언된 메소드들은 모두 RemoteException 예외 클래스를 던져야 한다
 * 
 * 
 */

public interface clientInfUne extends Remote{ //server와 client의 인터페이스는 모두 Remote클래스를 상속하여야 한다

	//시작 메서드
	public void start() throws RemoteException;
	
	//출력용 메서드
	public void printMap() throws RemoteException;
	
	//흑돌 백돌 나타내는 메서드
	
	public void setMyTurn(boolean torf) throws RemoteException;
	//
	public void pMove(int color,int i, int j) throws RemoteException;
	
	public void pSelect(int[] xy,int color) throws RemoteException;
	
	
	
}
