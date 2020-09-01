package kr.or.ddit.basic02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 *  	한글이 저장된 파일 읽어오기
 *  
 *  	(한글의 인코딩 방식을 지정해서 읽어온다)
 * 
 * 	
 * 
 */

public class FileIOTest05 {

	public static void main(String[] args) {
		
//		File f = new File("D:/D_other/text_ansi.txt");
		File f = new File("D:/D_other/text_utf8.txt");
		
		try {
			
			// 보통의 FileReader 객체는 java파일의 인코딩에 맞춰서 데이터를 읽어 온다
			
			
			FileReader fr = new FileReader(f);
			
			
			
			//입출력할 파일의 인코딩 방식을 지정할 수 있는 스트림 객체는
			//InputStreamReader, OutputStreamWriter 이다
			FileInputStream fin = new FileInputStream(f);
			
			
			//기본 인코딩 방식
//			InputStreamReader isr = new InputStreamReader(fin);
			
			
			//인코딩 방식을 지정해서 읽어오기
			// 인코딩 지정 문자를 설정해서 객체를 생성한다
			//인코딩방식 지정 문자
			//- MS949 ==> window의 기본 한글 인코딩 방식(ANSI방식)
			//- UTF-8	==> 유니코드 UTF-8 인코딩 방식
			//- US-ASCII	==> 영문 전용 인코딩 방식
			InputStreamReader isr = new InputStreamReader(fin,"utf-8");	//대소문자의 구분이 없다
			
			
			int c;
			
			while((c = isr.read())!= -1){
				System.out.print((char)c);
				
			}
			
			isr.close();
			
			
			
		} catch (IOException e) {
			
		}

	}

}
