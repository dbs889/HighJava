package kr.or.ddit.basic.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.basic.inf.RemoteInterface;
import kr.or.ddit.basic.vo.TestVo;
import kr.or.ddit.basic.vo.fileInfoVo;

public class RemoteClient {

	public static void main(String[] args) {
		
		
		//RMI용 객체를 서버에서 구해와서 사용하는 순서
		
		try {
			
			//1. 서버에서 등록한 RMI용 객체를 찾기 위해 Registry객체를 생성한다
			//	(서버의 IP주소와 제공하는 포트번호를 지정하여 생성한다)
			
			Registry reg = LocateRegistry.getRegistry("localhost", 9999);
			
			//2. 서버에서 등록한 '객체의 Alias'를 이용하여 객체를 불러온다
			
//			//형식 ) Registry 객체년수, lookup("객체의Alias");
//			RemoteInterface inf = (RemoteInterface) reg.lookup("rmiserver");
			
			RemoteInterface inf = (RemoteInterface) reg.lookup("rmiserver");
			
			
			
			//3. 이제부터 불러온 객체의 메서드를 호출해서 사용할 수 있다
			
			int a = inf.doRemotePrint("안녕하세요");
		
			System.out.println("서버의 반환값" + a);
			System.out.println();
			
			
			List<String> list = new ArrayList<>();
			list.add("복숭아");
			list.add("포도");
			list.add("딸기");
			list.add("대추");
			
			inf.doPrintList(list);
			System.out.println("List 객체 전송 끝");
			System.out.println();
			
			
			TestVo myvo = new TestVo();
			myvo.setName("홍길동");
			myvo.setNumber(123);
			
			inf.doPrintVo(myvo);
			System.out.println("vo객체 전송 끝");
			System.out.println();
			
			//파일 전송 처리 시작
			
			File file = new File("d:/d_other/항정이2.jpg");
			
			if(!file.exists()){
				System.out.println("복사할 원본 파일이 없습니다");
				return;
			}
			fileInfoVo fiv = new fileInfoVo();
			//파일 용량을 구햐서 FileInfoVo의 FileData 배열의 크기를 결정한다
			int len = (int) file.length();
			byte[] data = new byte[len]; 
			
			//파일을 읽어올 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);
			
			//파일의 내용을 읽어와 byte배열에 저장한다
			fin.read(data);
			
			//파일정보 Vo객체에 파일이름과 파일 내용을 셋팅한다
			fiv.setFileName(file.getName()); //파일 이름설정
			fiv.setFileData(data); //파일 데이터 설정
			
			
			//서버의 파일 전송용 메서드 호출
			inf.setFile(fiv);
			
			System.out.println("파일 전송 작업 끝....");
			
		} catch (RemoteException e) {
			// TODO: handle exception
		}catch (NotBoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
