package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


//클라이언트가 서버에 접속하면 "d:/d_other"폴더에 있는 '호랑이.jpg'파일을 서버로 전송한다
//서버는 클라이언트가 보낸 파일을 받아서 "d:/d_other/download"폴더에 저장한다
public class TcpFileServer01 {
	
	private ServerSocket server;
	private Socket socket;
	
	private DataInputStream din; //데이터 읽기용 =>문자수신용
	private BufferedInputStream bin; //데이터 수신용
	private BufferedOutputStream bout; //파일 저장용
	
	
	private void serverStart(){
		
		//1. 저장할 파일객체 생성
			File savedir = new File("d:/d_other/download");
			
		//2. 저장할 폴더가 없으면 저장할 폴더를 새로 만들어 준다
			if(!savedir.exists()){
				savedir.mkdirs();
			}
			
		//3. 
			try {
				
				//3-1. 서버 소켓을 만든다
				server = new ServerSocket(7777);
				System.out.println("서버가 준비되었습니다");
				
				
				//3-2.요청을 기다린다
				Socket socket = server.accept();
				
				System.out.println("파일 다운로드 시작");
				
				//3-3.소켓을 이용해서 파일을 받는다
				
					//3-3-1 클라이언트가 처음으로 보내는 데이터 받기 => 파일 이름이 처음에 전송되어 온다
					din = new DataInputStream(socket.getInputStream());
					
					String fileNm = din.readUTF();
					
				//3-4. 저장된 파일 위치와 파일 이름을 지정하여 file객체 생성
					File saveFile = new File(savedir,fileNm);
					
				//3-5.수신용 스트림 객체 생성
					bin = new BufferedInputStream(socket.getInputStream());
					
				//3-6 파일저장용 스트림 객체 생성
					bout = new BufferedOutputStream(new FileOutputStream(saveFile));
					
				//3-7 배열로 담아서 받는다
					
					byte[] temp = new byte[1024];
					
					int len = 0;
					
					
				//3-8. 소켓으로 수신된 데이터를 파일로 저장한다
					while((len = bin.read(temp)) >0){
						
						bout.write(temp,0,len);
					}
					
				//3-9.
					bout.flush();
					System.out.println("파일 다운로드 완료");
		
				
			} catch (Exception e) {
				//3-1. 예외처리가 발생하면
				System.out.println("파일 다운로드 실패");
				e.printStackTrace();
			}finally{

				if(din != null){try {din.close();} catch (IOException e) {}
				}
				if(bin != null){try {bin.close();} catch (IOException e) {}
				}
				if(bout != null){try {bout.close();} catch (IOException e) {}
				}
				if(socket != null){try {socket.close();} catch (IOException e) {}
				}
				
				
			}
			
		
		
	}

	public static void main(String[] args) {
		
		new TcpFileServer01().serverStart();
		
		
	}

}
