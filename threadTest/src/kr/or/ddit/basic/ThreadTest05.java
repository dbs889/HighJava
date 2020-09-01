package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {
	
	public static void main(String[] args) {
		
		
		//사용자로부터 데이터 입력 받기
		
		String str = JOptionPane.showInputDialog("유네는 짱이에요");
		
		System.out.println("입력값 : " + str );
		
		//카운트다운
		for(int i = 10; i>= 0; i--){
			System.out.println(i);
			
			try {
				
				//1초 동안 잠시 멈춘다
				Thread.sleep(1000);
				
				
			} catch (Exception e) {
				
			}
		}
	}

}
