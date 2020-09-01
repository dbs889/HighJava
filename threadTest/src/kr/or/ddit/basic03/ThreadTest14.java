package kr.or.ddit.basic03;

/*
 * 
 * Thread의 stop()메서드를 호출하면 쓰레드가 바로 멈춘다
 * 이 때 사용한던 자원을 정리하지 못하고 프로그램이 종료되어 나중에 실행된는 프로그램에 영향을 줄 수 있다. 
 * 그래서 stop()메서드는 비추천으로 되어 있다.  
 * 
 * */

public class ThreadTest14 {

	public static void main(String[] args) {
		/*ThreadStopTest1 th1 = new ThreadStopTest1();
		
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		
//		th1.stop();
		th1.setStop(true);
	}*/
		
		//Interrupt()메서드를 이용한 쓰레드 멈추기
		ThreadStopTest2 th2 = new ThreadStopTest2();
		
		th2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		}th2.interrupt();

}

}
//쓰레드를 멈추게 하는 연습용 쓰레드

class ThreadStopTest1 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop){
		this.stop = stop;
	}
	
	
	@Override
	public void run() {
		while(!stop){
			System.out.println("쓰레드 실행 중....");
		}
		System.out.println("자원 정리.....");
		System.out.println("쓰레드 종료.....");
	}
	
}

//interrupt()메서드를 이용하여 쓰레드를 멈추게하는 연습용 쓰레드


class ThreadStopTest2 extends Thread{
		
	@Override
		public void run() {
			/*//방법1) interrupt()메서드와 sleep()메서드를 이용하는 방법
		
			try {
				while(true){
					System.out.println("Thread 실행중...");
					Thread.sleep(1);
				}
			} catch (InterruptedException e) {
				
			}*/
		
		//방법2)
		while(true){
			System.out.println("쓰레드 실행.....중.....");
			//Interrupt(0메서드가 호출되었는지 여부를 검사한다
			
			//검사방법 1 ==>인스턴스 메서드 isInterrupted()메서드를 이용해서 검사한다.
			//				isInterrupted()메서드는 interrupt()메서드가 호출되면 true를 반환한다
			/*if(this.isInterrupted()){
				break;
			}
			*/
			//검사방법2 ==> Thread의 정적메서드인 interrupted()메서드를 이용해서 검사한다
				//   ==> interrupted()메서드가 호출되면 true를 반환한다
			
			if(Thread.interrupted()){
				break;
			}
			
			
		}
			System.out.println("자원 정리 중....");
			System.out.println("쓰레드 종료.....");

		}
	
}