package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		
		
		//Thread를 사용하는 방법
		
		/*방법1) Thread를 상속하는 방법
		
				Thread클래스를 상속한 Class를 작성하고 이 클래스의 인스턴스를 생성한 후
				이 인스턴스의 start()메서드를 호출해서 실행한다.
		*/
		
		
		MyThread1 th1 = new MyThread1();
		th1.start();
		
		/*
		  방법 2) Runnable 클래스를 사용하는 방법
		  		class 클래스이름 implements Runnable{}
		  
		  		Runnable 인터페이스를 구현된 Class를 작성하고, 이 클래스의 인스턴스를 생성하고
		  		이 인스턴스를 Thread 객체를 생성할 때 생성자의 매개값으로 넘겨준다
		  		이 때 생성된 Thread의 인스턴스의 start()메서드를 호출해서 실행한다
		  
		*/
		
		Runnable r1 = new Myrun(); ///Runnable메서드의 인스턴스를 생성한다
		Thread th2 = new Thread(r1); //Runnable객체를 Thread 객체를 생성할때 넣는다
		th2.start();
		
		/*
		 * 방벙 3)
		 		익명 구현체를 이용한다
		 		Runneabla 이름 = new Runnable(){}
		 */
		
		Runnable r2 = new Runnable() {
			
			@Override
			public void run() {
				
				for(int i =0; i<=200; i++){
					System.out.print("^");
					
					try {
						//Thread.sleep(시간) ==> 이 메서드는 주어진 '시간'동안 작업을 잠시 멈춘다
						//					==> 시간은 밀리세컨드 단위를 사용한다 (즉 1/1000초 단위)
						// 							1초 --> 1000
						
						Thread.sleep(100);
					} catch (Exception e) {
						
					}
				}
				
			}//run()
		}; //익명구현체
		Thread th3 = new Thread(r2);
		th3.start();
		
		
	}//main

}//class

//방법1)
class MyThread1 extends Thread{
	@Override
	public void run(){
		
				//run()메서드가 끝나면 Thread가 종료된다

		for(int i =0; i<=200; i++){
			System.out.print("*");
			
			try {
				//Thread.sleep(시간) ==> 이 메서드는 주어진 '시간'동안 작업을 잠시 멈춘다
				//					==> 시간은 밀리세컨드 단위를 사용한다 (즉 1/1000초 단위)
				// 							1초 --> 1000
				
				Thread.sleep(100);
			} catch (Exception e) {
				
			}
		}
		
	}
}

//방법2)
class Myrun implements Runnable{
	@Override
	public void run() {
	//쓰레드가 처리할 내용을 run()메서드를 사용해서 나타낸다
		
		for(int i =0; i<=200; i++){
			System.out.print("$");
			
			try {
				//Thread.sleep(시간) ==> 이 메서드는 주어진 '시간'동안 작업을 잠시 멈춘다
				//					==> 시간은 밀리세컨드 단위를 사용한다 (즉 1/1000초 단위)
				// 							1초 --> 1000
				
				Thread.sleep(100);
			} catch (Exception e) {
				
			}
		}
		
	}
}