package kr.or.ddit.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest01Une {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//File객체 만들기 
		File file = new File("d:/d_other/text.txt");
		
		//파일명
		System.out.println(file.getName());
		//경로
		System.out.println(file.getPath());
		//디렉토리인지 아닌지
		System.out.println(file.isDirectory());
		//파일인지 아닌지
		System.out.println(file.isFile());
		
		
		//파일의 크기
		System.out.println(file.length() + "bytes");
		//파일의 절대경로
		System.out.println(file.getAbsolutePath());
		
		
		
		
		//폴더 만들기(폴더 == 디렉토리)
		File f = new File("d:/d_other/유네슥호");
		
		//해당 폴더의 존재 여부
		System.out.println(f.exists());
		
		
		//존재하지 않으면 파일을 생성한다
		if(!f.exists()){
//			f.mkdir(); ==> File 객체의 경로 중 마지막 위치의 디렉토리를 만든다 중간부분 생략
			f.mkdirs(); //==> 중간부분의 경로가 없으면 중간 부분도 포함하여 만들어 준다
			
		}
		
		
	}
	
	//디렉토리를 매개변수로 받아 해당 폴더에 있는 모든 파일과 폴더 목록을 출력하는 메서드
	public void displayFileList(File dir){
		if(!dir.isDirectory()){
			System.out.println("디렉토리가 아닙니다"); return;
		}
		
		//해당 폴더에 있는 모든 파일과 디렉토리 목록을 구한다
		
		File[] files = dir.listFiles();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		for(int i = 0; i<files.length; i++){
			String filename = files[i].getName();
			String attr = "";
			String size ="";
			
			if(files[i].isDirectory()){
				attr ="<DIR>";
				
			}else{
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" :"";
				attr = files[i].canWrite() ? "W" :"";
				attr = files[i].isHidden() ? "H" :"";
				
			}
			System.out.printf("%s %5s %12s %s\n", df.format(new Date(files[i].lastModified())),attr,size,filename);
		}
		
	}
	

}
