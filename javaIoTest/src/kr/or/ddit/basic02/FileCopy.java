package kr.or.ddit.basic02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/*
 * 	d:/d_other/항정이2.jpg 파일을 
 * 	d:/d_other/연습용 폴더에 '항정이2_복사본.jpg 파일로 저장하시오
 * 
 * 
 */
public class FileCopy {

	public static void main(String[] args) {
		
		//-------------------------선생님
		
		
		String sourceFile = "d:/d_other/항정이2.jpg";
		String targetFile = "d:/d_other/연습용/항정이2_복사본.jpg";
		
		File file = new File(sourceFile);
		if(!file.exists()){	//파일이 있는지 없는지를 검사한다
			
			
			System.out.println(file.getPath() + "파일이 없습니다");
			System.out.println("복사 작업을 중지합니다");
			
			return;
		}
		
		try {
			
			//복사할 원본 파일
			FileInputStream fi = new FileInputStream(file);
			
			BufferedInputStream bin= new BufferedInputStream(fi);
			
			//복사될 대상 파일
			FileOutputStream fo = new FileOutputStream(targetFile);
			BufferedOutputStream bos = new BufferedOutputStream(fo);
			
			int data;
			
			byte[] temp = new byte[1024];
			int len = 0;
			System.out.println("복사시작.....");
			
			//1byte씩 복사할때
//			while((data = fi.read()) != -1){
//				fo.write(data);
//			}
//			
//			fo.flush();
			
			//배열을 이용해서 복사
//			while((len = fi.read(temp)) > 0){
//				fo.write(temp,0,len);
//			}
//			
//			fo.flush();
			
			
			while((data = bin.read()) > 0){
				bos.write(data);
			}
			
			bos.flush();
			System.out.println("복사 완료.....");
			
			bin.close(); 
			bos.close();
	
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		
		//-------------------------------유네 
//		try {
//			
//			FileInputStream fis = new FileInputStream("d:/d_other/항정이2.jpg");
//			
//			
//			
//			FileOutputStream fos = new FileOutputStream("d:/d_other/연습용/항정이2_복사본.jpg");
//			
//			
//			int c;
//			
//			while((c=fis.read())!= -1){
//				fos.write(c);
//			}
//			
//			
//			fis.close();
//			fos.close();
//			
//			
//		} catch (IOException e) {
//			
//		}

		

	}

}
