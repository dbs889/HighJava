package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	public static void main(String[] args) {
		
		
		Thread th1 = new DataInputThread();
		Thread th2 = new CountdownThread();
		
		th1.start();
		th2.start();
		

	}

}


//데이터를 입력하는 쓰레드
class DataInputThread extends Thread{
	
	
	//입력 여부를 확인하기 위한 변수 선언 ==> 쓰레드에서 공통으로 사용할 변수이다
	public static boolean inputcheck;
	
	
	@Override
	public void run() {

		String str = JOptionPane.showInputDialog("유네는 짱이에요??");
		
		//입력완료되면 inputcheck가 true
		inputcheck = true;
		
		System.out.println("입력값 : " + str );
	
	}
	
}

//카운트다운을 진행하는 쓰레드
class CountdownThread extends Thread{
	
	@Override
	public void run() {

		for(int i = 10; i>= 1; i--){
			
			
			//입력이 완료되었는지 여부를검사해서 입력이 완료되면 쓰레드를 종료시킨다
			if(DataInputThread.inputcheck == true){
				return; //run()메서드가 종료되면 쓰레드도 같이 동료된다
			}

			System.out.println(i);
			
			
			try {
				
				//1초 동안 잠시 멈춘다
				Thread.sleep(1000);
				
				
			} catch (Exception e) {
				
			}
		}//반복문
		
		System.out.println("지정된 시간이 경과했습니다. 프로그램을 종료합니다");
		System.exit(0);
		
		
	}
	
}