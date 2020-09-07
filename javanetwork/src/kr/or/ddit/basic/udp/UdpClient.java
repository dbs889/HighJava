package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		
		//송신용, 수신용 패킷변수 선언
		DatagramPacket outPacket, inPacket;
		
		
		//수신받은 데이터가 저장될 byte형 배열 선언
		
		byte[] bMsg = new byte[1024];
		
		try {
			//접속을 할 때는 포트번호를 지정하지 않고
			DatagramSocket socket = new DatagramSocket();
			
			//접속할 서버의 주소를 생성한다
			InetAddress address = InetAddress.getByName("localhost"); //IP주소를 입력해도 된다
			
			while(true){
				//전송할 메시지를 입력 받는다
				System.out.println("보낼 메시지 입력 : ");
				 String msg = scan.nextLine();
				 
				 
				 //전송할 패킷 객체를 생성
				 outPacket = new DatagramPacket(msg.getBytes(),msg.getBytes().length,address,8888);
				 
				 
				 socket.send(outPacket);
				 
				 if("/end".equals(msg)){ //'end'가 입력되면 접속 종료
					 break;
				 }
				 
				 //서버에서 온 데이터를 받아서 출력하기
				 //수신용 패킷객체 생성
				 inPacket = new DatagramPacket(bMsg, bMsg.length);
				 
				 
				 //데이터 수신
				 socket.receive(inPacket);
				 
				 //출력
				System.out.println("서버의 응답 데이터 : " + new String(bMsg,0,inPacket.getLength()));
				
				
				
				
				//while문
				System.out.println("통신 끝....");
				
			
				
			}//while끝
			
			System.out.println("서버 종료");
			socket.close();
				
			
			try {
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
