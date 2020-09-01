package kr.or.ddit.basic;

import javax.swing.JOptionPane;

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

public class ThreadBySem {

		public static boolean inputCheck;
		
	public static void main(String[] args) {
		
		
		GameTimer gt = new GameTimer();
		
		//컴퓨터의 가위바위보 난수를 구한다
		String[] data = {"가위","바위","보"}; //0~2사이
		
		//배열에서 0~2사이의 값을 난수로 꺼내온다
		int index =(int)(Math.random()*3);
		
		//컴퓨터의 가위바위보를 저장한다
		String com = data[index];
		
		//카운트다운 시작
		gt.start();
		
		//사용자의 가위 바위 보 입력
		String user = null;
		do{
			user = JOptionPane.showInputDialog("가위 바위 보를 입력하세요");
		}while(user ==null || !user.equals("가위")&&!user.equals("바위")&&!user.equals("보"));
		
		
		inputCheck = true;
		
		// 결과를 판정하기
		String result = ""; //결과를 저장될 변수 선언 및 초기화
		
		if(user.equals(com)){
			result = "비겼습니다";
		}
		else if(user.equals("가위") && com.endsWith("보") || 
				user.equals("바위") && com.endsWith("가위") || 
				user.equals("보") && com.endsWith("바위")){
			result = "사용자가 이겼습니다";
			
		}
		else if(user.equals("가위") && com.endsWith("바위")|| 
				user.equals("바위") && com.endsWith("보")||
				user.equals("보") && com.endsWith("가위")){
			result = "당신이 졌습니다";
		}
		
		System.out.println("---결과---");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + user);
		System.out.println("결과 : " + result);
		
		

	}

}

class GameTimer extends Thread {

	@Override
	public void run() {

		for (int i = 10; i >= 1; i--) {

			if (ThreadBySem.inputCheck == true) {
				return;
			}

			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}

		}// 반복문

		System.out.println("지정된 시간이 경과했습니다. 프로그램을 종료합니다");
		System.exit(0);

	}

}
