package kr.or.ddit.basic02;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest01 {

	public static void main(String[] args) {
		
		
		try {
			
			FileOutputStream fout = new FileOutputStream("D:/D_other/test.dat");
			
			//자료형 단위로 출력할 보조스티림 객체 새성(DataoutputStream)
			DataOutputStream dout = new DataOutputStream(fout);
			
			
			dout.writeInt(200); //정수형으로 데이터 출력
			dout.writeFloat(123.45f); // 실수형으로 데이터 출력
			dout.writeBoolean(false);
			dout.writeUTF("ABCDefghi"); //문자열 형식으로 출력
			System.out.println("출력 완료....");
			
			
			dout.close();
			
		
			// 위에서 출력한 자료 읽어오기
			FileInputStream fin = new FileInputStream("d:/d_other/test.dat");
			
			DataInputStream din = new DataInputStream(fin);
			
			//DataINputStream으로 자료를 읽어 올 때는 출력할 때의 순서와 같은 순서로 읽어야 한다
			System.out.println("정수형  : " + din.readInt());
			System.out.println("실수형 : " + din.readFloat());
			System.out.println("논리형 : " + din.readBoolean());
			System.out.println("문자열 : " + din.readUTF());
			
			System.out.println("읽기 작업 완료...");
			
			din.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
