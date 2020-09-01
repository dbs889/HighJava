package kr.or.ddit.basic02;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileIOTest06 {

	public static void main(String[] args) {
		
		
		try {

			
			InputStreamReader isr = new InputStreamReader(System.in);

			FileOutputStream fout = new FileOutputStream("D:/D_other/outTest.txt");
			
			
			//기본 인코딩 방식으로 저장
//			OutputStreamWriter osw = new OutputStreamWriter(fout);
//			OutputStreamWriter osw = new OutputStreamWriter(fout,"MS949");
			OutputStreamWriter osw = new OutputStreamWriter(fout,"utf-8");
			

			
			
			System.out.println("파일에 저장할 내용을 입력 하세요");
			System.out.println("입력의 마지막은'ctrl' + 'z'");

			int c;

			// 콘솔에서 데이터를 입력할 때 입력의 끝은 'ctrl' + 'z' 키를 누른다
			while ((c = isr.read()) != -1) {
				osw.write(c);
			}

			// 스트림 닫기
			osw.close();
			isr.close();

		} catch (IOException e) {
			
		}

	}

}
