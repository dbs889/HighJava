package kr.or.ddit.une;

public class ThreadTestUne01 {
	
	
	//싱글 쓰레드 프로그램
	
	public static void main(String[] args) {
		
//		for(int i = 0; i<=200; i++){
//			System.out.println("*");
//		}
//		
//		System.out.println();
//		System.out.println();
//
//		
//		for (int i = 0; i <= 200; i++) {
//			
//			System.out.println("$");
//		}
// 두 가지를 동시에 출력하고 싶을 때  Thread를 사용한다
		
		//1. thread 상속
		
		ThreadOne th = new ThreadOne();
		th.start();
		
		
		//2.Runnable 클래스 사용
		Runnable run = new ThreadTwo();
		Thread thr = new Thread(run);
		thr.start();
		
		
		//3. 익명구현체 이용
		
		Runnable run2 = new Runnable() {
			
			
			
			@Override
			public void run() {
				for (int i = 0; i <= 200; i++) {
					System.out.println("^");
					
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
			}
		};
		
		Thread th3 = new Thread(run2);
		th3.start();
		
		
	}

}


class ThreadOne extends Thread{
	
	@Override
	public void run() {

		for (int i = 0; i <= 200; i++) {
			System.out.println("*");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}


class ThreadTwo implements Runnable{

	@Override
	public void run() {

		for (int i = 0; i <=200; i++) {
			System.out.println("$");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		
	}
	
	
	
}