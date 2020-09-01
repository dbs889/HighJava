package kr.or.ddit.basic02;

//쓰레드의 상태를 출력하는 프로그램
public class ThreadTest10 {

	public static void main(String[] args) {
		StatePrintThread th = new StatePrintThread(new TargetThread());
		
		th.start();

	}

}

//쓰레드 상태의 대상이 되는 쓰레드
class TargetThread extends Thread{
	
	@Override
	public void run() {
		
		//시간지연용
		for (long i = 1L; i <= 20_000_000_000L; i++); 
		
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			
		
		for (long i = 1L; i <= 20_000_000_000L; i++);
		
		
	}
	
}

//검사 대상 쓰레드의 상태를 출력하는 쓰레드

class StatePrintThread extends Thread{
	
	private TargetThread target;
	
	//생성자
	public StatePrintThread(TargetThread target) {
	
		this.target = target;
	}
	
	@Override
	public void run() {
		
		while(true){
			
			//쓰레드의  현재 상태값 구하기
			
			Thread.State state = target.getState();
			System.out.println("TargetThread의 상태 값 : " + state);
			
			if(state == Thread.State.NEW){ //쓰레드의 상태가 New 상태이면 
				target.start(); //타겟 쓰레드 실행
			}
			
			if(state == Thread.State.TERMINATED){
				break;
			}
			

			try {
				Thread.sleep(500);
				
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
		
	}
	
	
}