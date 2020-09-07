package kr.or.ddit.basic.tcp.une;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

/*
 * 문제 ) 클라이언트가 서버에 접속하면 "d:/d_other"폴더에 있는 '호랑이.jpg'파일을 서버로 전송한다
 * 		서버는 클라이언트가 보낸 파일을 받아서 "d:/d_other/download"폴더에 저장한다
 */



public class TcpFileClientUne {
	
	
	//클라이언트 문제 :  서버에 접속하면 d_other폴더에 있는 jpg파일을 서버로 전송한다
	
	//1. 변수 선언
	
	private Socket socket; //전송할 socket변수 선언
	private BufferedOutputStream bout; //파일을 내보낼거니깐 output
	private BufferedInputStream bin; ///파일을 읽어와야 하니깐 infut
	private DataOutputStream dout; //문자를 전송할 수 있으니깐(파일명을 먼저 전송하니깐) out
	
	
	
	



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TcpFileClientUne().start();
	}






	//메소드 안에서 구현 한다
	private void start() {
		
		//1. 전송할 file객체를 생성
		
		File file = new File("d:/d_other/항정이.jpg");
		
		//2. 파일 이름을 선언하고  해당 파일이 있는지 검사한다
		
		String fileNm = file.getName();
		
		if(!file.exists()){
			System.out.println("해당 파일이 존재하지 않습니다");
			return;
		}
		
		//3. 해당 파일이 있으면  서버와 접속한다
		try {
			
			//3-1  서버접속  - 소켓 객체생성
			socket = new Socket("localhost",7777);
			
			System.out.println("연결 되었습니다");
			
			//4. 파일이름을 전송할 객체 생성
			dout = new DataOutputStream(socket.getOutputStream());
			//4-1. dout에 파일이름을 저장한다
			dout.writeUTF(fileNm);
			
			//5. 바이트 배열을 이용하여 서버로 데이터를 전송한다
			byte[] temp = new byte[1024];
			
			int len = 0;
			
			//5-1. 반복문을 통해 파일 내용을 읽어와 소켓 내용을 통해 전송한다
			
			while((len = bin.read(temp)) >0){
				dout.write(temp,0,len);
			}
			
			
			//5-2. 버퍼에 남아있는 것을 비운다
			bout.flush();
			
			System.out.println("전송 완료");
			
			
		}catch (IOException e) {
			System.out.println("예외가 발생하여 전송 실패");
			e.printStackTrace();
		}finally{
			if(dout!= null){try {dout.close();} catch (IOException e) {}
				
			}
			
		}
		

		
		//4. 파일명을 내보낸다
		//5. 파일을 보낸다
		
	}

}
