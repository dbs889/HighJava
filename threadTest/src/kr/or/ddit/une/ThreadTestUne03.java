package kr.or.ddit.une;

import javax.swing.JOptionPane;

public class ThreadTestUne03 {
	
	/*
	 
	 
	 컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오

	 컴퓨터에 가위 바위 보는 난수를 이용해서 구하고
	 
	 사용자의 가위 바위 보는 showinputDialog()를 이용해서 입력 받는다 
	 
	 입력시간은 5초로 제한하고 카운트 다운을 진행한다
	 
	 5초 안에 입력이 없으면 게임 진것으로 처리한다

	 5초안에 입력이 완료되면 승패를 구해서 출력한다

	 결과 예시)
	 		-- 결 과 --
	 		컴퓨터 : 가위
	 		사용자 : 바위
	 		결과 : 당신이 이겼습니다
	 			  당신이 졌습니다
	 			  비겼습니다
	 */
		public static void main(String[] args) {
			
			Thread th1 = new userGame();
			Thread th2 = new countThread();
			
			th1.start();
			th2.start();
			
		}
	
	
	 
 	
	
}

class userGame extends Thread{
	public static boolean inputck;
	
	@Override
	public void run() {
		
		String str = JOptionPane.showInputDialog("가위 바위 보를 입력하시오");
		
		
		inputck = true;
		
		//컴퓨터의 난수를 만든다
		String[] computer = {"가위","바위","보"};
		String com = computer[(int) (Math.random()*2)];
		
		
		System.out.println("사용자 입력 : " + str);
		System.out.println("컴퓨터 입력 : " + com);
		if(str.equals(com)){
			System.out.println("비겼습니다");
		}
		else if(str.equals("보") && com.equals("가위")){
			System.out.println("당신이 졌습니다");
		}
		else if(str.equals("보") && com.equals("바위")){
			System.out.println("당신이 이겼습니다");
		}
		else if(str.equals("주먹") && com.equals("가위")){
			System.out.println("당신이 이겼습니다");
		}
		else if(str.equals("주먹") && com.equals("보")){
			System.out.println("당신이 졌습니다");
		}
		else if(str.equals("가위") && com.equals("바위")){
			System.out.println("당신이 졌습니다");
		}
		else if(str.equals("가위") && com.equals("보")){
			System.out.println("당신이 이겼습니다");
		}else{
			System.out.println("잘못 입력");
		}
		
		
		
		
		
	}
	
}

//카운트 다운하는 클래스
class countThread extends Thread{
	
	
	@Override
	public void run() {
	
		for(int i = 5; i>=0; i--){
			System.out.println(i);
			
			if(userGame.inputck == true){
				return;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("시간 초과되었습니다");
		
		
	}
}
