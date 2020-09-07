package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class TcpFileClient01 {
	
	//클라이언트가 서버에 접속하면 "d:/d_other"폴더에 있는 '호랑이.jpg'파일을 서버로 전송한다
	
	private Socket socket;
	private BufferedOutputStream bout;//용량이 큰건 buffer(바이트기반)를 사용 => 소켓전송용
	private BufferedInputStream bin; //파일의 내용을 읽어올 때 사용 =>파일 읽기용
	private DataOutputStream dout; //데이터 읽기용 =>문자전송용
	
	
	
	public void clientStart(){
		
		
		//1. 전송할 파일을 이용한 File 객체 생성
//			File file = new File("d:/d_other/항정이2.jpg");
//			
//			
//			
//		//2.파일 이름 선언
//			String fileNm = file.getName();
//			
//		//3.전송할 파일이 있는지 검사
//			
//			if(!file.exists()){
//				
//				System.out.println(fileNm + " 파일이 없습니다");
//				return;	
//			}
//			
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("d:/d_other"));
		
		String fileNm = null;
		File file = null;
		
		
		int result = fileChooser.showOpenDialog(new JPanel());
		
		if(result == JFileChooser.APPROVE_OPTION){
			file = fileChooser.getSelectedFile();
			fileNm = file.getName();
			
		}else{
			System.out.println("전송을 취소합2니다");
		}
		
		//4. 서버와 접속해서 접속이 되면 파일을 읽어 보낸다
			try {
				
				
				//4-1. 소켓 객체 생성 : 서버 접속
				socket = new Socket("localhost",7777);
				
				System.out.println("서버에 연결되었습니다");
				System.out.println("파일 전송 시작");
				
				//4-2. 파일이름 전송 : 처음 접속 되면 파일 이름을 전송한다
				dout = new DataOutputStream(socket.getOutputStream());
				dout.writeUTF(fileNm);
				
				//4-3.파일 이름이 전송되면 현재 파일의 내용을 읽어다가 파일을 전송한다
				
					//4-3-1. 파일 읽기용 스트림 객체 생성
					bin = new BufferedInputStream(new FileInputStream(file));
					
					//4-3-2. 서버로 전송할 스트림 객체 생성
					bout = new BufferedOutputStream(socket.getOutputStream());
					
					//4-3-3.서버로 데이터를 전송한다 => 바이트 배열을 이용
					
					byte[] temp = new byte[1024];
					
					int len =0;
					//4-3-4. 파일 내용을 읽어와 소켓 내용을 통해 전송한다
					while((len = bin.read(temp))>0){
						bout.write(temp,0,len);
					}
					//4-3-5.버퍼에 남아있는것 비우기
					bout.flush();
					
					System.out.println("파일 전송완료");
					
				
			} catch (Exception e) {
				//4-3-6. 예외가 발생하면 전송 실패
				System.out.println("파일전송실패");
			}finally{
				
				//사용한 스트림 닫기
				
				if(dout != null){try {dout.close();} catch (IOException e) {}
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
		
		new TcpFileClient01().clientStart();
		

	}

}
