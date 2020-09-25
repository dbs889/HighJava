package kr.or.ddit.une;

import java.io.DataInputStream;

import javax.swing.JOptionPane;

public class ThreadTestUne02 {
	
	public static void main(String[] args) {
		
		
		Thread th = new DataInputThread();
		Thread th2 = new CountThread();
		
		th.start();
		th2.start();
		
		
		
		
	}




	
	
}
//데이터를 입력 받는 쓰레드
class DataInputThread extends Thread{
	
	
	//입력 여부를 나타낼 변수 선언
	public static boolean inputcheck;
	
	@Override
	public void run() {

			String str = JOptionPane.showInputDialog("유네Test");
			
			inputcheck = true;
			
			System.out.println("입력값 : " + str);
	}
	
	
}

//카운트 다운을 진행하는 쓰레드

class CountThread extends Thread{
	
	@Override
	public void run() {

			for(int i = 10; i>=0; i--){
				if(DataInputThread.inputcheck ==true){
					return;
				}
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	}
}