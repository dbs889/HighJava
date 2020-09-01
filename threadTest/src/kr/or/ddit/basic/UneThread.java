package kr.or.ddit.basic;

import javax.swing.JOptionPane;

//thread를 상속받는 메서드
public class UneThread extends Thread {
	
	public static boolean check;
	
	@Override
	public void run() {
		
		//사용자가 데이터를 입력하는 스레드
		String inputStr = JOptionPane.showInputDialog("가위 바위 보 중에 하나를 입력하세요");
		check = true;
		
		//컴퓨터가 내는 난수를 만들어라
		//1. 배열에 가위바위보를 저장
		String[] computer = {"가위","바위","보"};
		
		//2. 배열에 저장된 값을 하나씩 꺼내와 새로운 String 변수에 저장
		
		String ranCom = computer[(int)(Math.random()*2)];
		System.out.println("---결과---");
		System.out.println("사용자 : " + inputStr);
		System.out.println("컴퓨터 : " + ranCom);
		
		
		if(inputStr.equals(ranCom)){
			System.out.println("비겼습니다");
		}
		else if(inputStr.equals("보") && ranCom.equals("가위")){
			System.out.println("사용자가 이겼습니다");
		}
		else if(inputStr.equals("바위") && ranCom.equals("보")){
			System.out.println("사용자가 이겼습니다");
		}
		else if(inputStr.equals("가위") && ranCom.equals("바위")){
			System.out.println("사용자가 이겼습니다");
		}
		else if(inputStr.equals("가위") && ranCom.equals("보")){
			System.out.println("사용자가 졌습니다");
		}
		
		else if(inputStr.equals("바위") && ranCom.equals("가위")){
			System.out.println("사용자가 졌습니다");
		}
		
		else if(inputStr.equals("보") && ranCom.equals("바위")){
			System.out.println("사용자가 졌습니다");
		}else{
			System.out.println("입력이 올바르지 않습니다");
		}
			
	}//run

}//가위바위보 쓰레드

class countThr extends Thread{
	@Override
	public void run() {
		
		//5초 동안 카운트 다운 하는 쓰레드 만들기
		for (int i = 5; i >= 0; i--) {
			
			
			if(UneThread.check == true){
				return;
				
				//사용자의 입력이 완료되면 카운트다운을 종료시킨다
			}
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
		System.out.println("지정된시간이 경과되었습니다");
		System.exit(0);
	}
}
