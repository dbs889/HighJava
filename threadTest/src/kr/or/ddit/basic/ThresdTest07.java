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

public class ThresdTest07 {

	public static void main(String[] args) {
	
			
			Thread ui = new UserInput();
			
			Thread ct = new CntThread();
			
			ui.start();
			ct.start();
			
			
		
		
	}

}

//사용자가 데이터를 입력하는 쓰레드

class UserInput extends Thread{
	
	public static boolean inputck;
	
	@Override
	public void run() {
		
		String str = JOptionPane.showInputDialog("가위 바위 보 중에 입력하세요");
		inputck = true;
		
		//컴퓨터의 난수를 만든다
		
		String[] computer = {"가위","바위","보"};
		
		String c = computer[(int) (Math.random()*2)];
		
		System.out.println("---결과---");
		System.out.println("컴퓨터 : " + c);
		System.out.println("사용자: " + str);
		if(c.equals(str)){
			System.out.println("비겼습니다");
		}else if(c.equals("보")&& str.equals("가위")){
			System.out.println("당신이 이겼습니다");
		}else if(c.equals("보")&& str.equals("바위")){
			System.out.println("당신이 졌습니다");
		}
		else if(c.equals("가위")&& str.equals("바위")){
			System.out.println("당신이 이겼습니다");
		}
		else if(c.equals("가위")&& str.equals("보")){
			System.out.println("당신이 졌습니다");
		}
		else if(c.equals("바위")&& str.equals("보")){
			System.out.println("당신이 이겼습니다");
		}
		else if(c.equals("바위")&& str.equals("가위")){
			System.out.println("당신이 졌습니다");
		}else{
			System.out.println("잘못입력하셨습니다");
		}
		
			
	}
	
}

class CntThread extends Thread{
	
	@Override
	public void run() {
		for(int i = 5; i>= 0; i--){
			if(UserInput.inputck == true){
				return;
			}
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("지정된 시간이 경과했습니다");
		System.exit(0);
	}
}
