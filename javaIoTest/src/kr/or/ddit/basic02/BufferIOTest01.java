package kr.or.ddit.basic02;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferIOTest01 {

	public static void main(String[] args) {
		//입출력의 성능 향상을 위해서 Buffered 스트림을 사용한다
		

		try {
			
			FileOutputStream fout = new FileOutputStream("D:/D_other/bufferTest.txt");
			
			//버퍼의 크기를 지정한 스트림 객체 생성(크기가 5인 객체)
			BufferedOutputStream bos = new BufferedOutputStream(fout,5);
			
																//출력을 할 때 곧바로 하는게 아니라 1부터 차례대로 버퍼에 쌓이다가 버퍼의 크기가 5일때 채워져서 출력된다 그런다음 버퍼가  비웠다가 
																//6,7,8,9를 출력해야 하는데  사이즈5를 채우지 못해 출력되지 못한다 ==> 해결책 : flush
			
			for(int i = '1'; i<='9'; i++){
				bos.write(i);
			}
			
			bos.flush(); //작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력한다
			
			//fout.close();
			bos.close();	//close에는 내부적으로 flush기능이 있다 하
							//보조 스트림을 닫으면 보조스트림에서 사용한 기반이 되는 스트림도 자동으로 닫힌다
			System.out.println("출력 작업 끝.....");
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
