package kr.or.ddit.basic;

public class ThreadTest01 {

	public static void main(String[] args) {

		//싱글 쓰레드 프로그램
		
		for(int i =0; i<=200; i++){
			System.out.print("*");
		}
		
		System.out.println();
		System.out.println();
		
		for(int i =0; i<=200; i++){
			System.out.print("$");
		}
		
		
		// 두 가지 작업을 동시에 할 수 없다
	}

}
