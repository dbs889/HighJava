package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {

	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();

	}
	
	//클라이언트가 시작하는 메서그
	private void clientStart(){
		
		Socket socket =null;
		
		try {
			String severIp = "192.168.43.35";
			socket = new Socket(severIp,7777);
			
			System.out.println("서버에 연결되었습니다");
			System.out.println();
			
			ClientSender sender = new ClientSender(socket);
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}//clientStart끝

	//메시지 전송용 쓰레드
	
	class ClientSender extends Thread {

		private Socket socket;

		private DataOutputStream dout;
		private DataInputStream din; // 피드백을 받기 위해서 필요
		private String name;
		private Scanner scan;

		// 생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);

			try {
				// 전송용 스트림 객체 생성
				dout = new DataOutputStream(socket.getOutputStream());

				// feedback 수신용 스트림 객체 생성
				din = new DataInputStream(socket.getInputStream());

				if (dout != null) {
					// 클라이언트 프로그램이 처음 실행되면 자신의 대화명을 입력 받아 서버로 전송하고
					// 대화명의 중복 여부를 feedback으로 받아서 확인한다

					System.out.println("대화명 : ");
					String irum = scan.nextLine();

					while (true) {

						// 대화명 서버로 전송한다
						dout.writeUTF(irum);

						// 대화명의 중복여부를 서버로부터 받는다 : 서버가 대화명을 보내면 중복여부를 검사해서 서버가
						// 클라이언트에게 보낸다음 클라이언트가 받는다
						String feedback = din.readUTF();

						System.out.println(feedback);
						if ("이름 중복".equals(feedback)) { // 대화명이 중복 될 때

							System.out.println(irum + "은 이름이 중복됩니다");
							System.out.println("다른 대화명을 입력하세요");
							System.out.println("대화명  : ");
							irum = scan.nextLine();

						} else { // 이름이 중복되지 않을 때...
							name = irum;
							System.out.println(irum + " 님이 대화방에 입장했습니다");
							break; // 반복문 탈출!!

						}

					}// while

				}

			} catch (Exception e) {

			}

		}// 생성자

		@Override
		public void run() {

			try {
				// 전송용 쓰레드 작업 메세지를 입력 받아 서버에게 보낸다

				while (dout != null) {

					// 대화 내용을 서버로 전송한다
					dout.writeUTF("[" + name + "]" + scan.nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}//메세지 전송용 쓰레드 종료
	
	//메서지 수신용 쓰레드
	
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream din;
		
		
		public ClientReceiver(Socket socket) {
			
			this.socket = socket;
			
			try {
				din = new DataInputStream(socket.getInputStream());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		@Override
		public void run() {
			try {

				while (din != null) {
					// 서버에서 받은 메시지를 화면에 출력한다

					System.out.println(din.readUTF());
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}//run메소드ㅡㅌ
		
		
		
	}
	
	
}
