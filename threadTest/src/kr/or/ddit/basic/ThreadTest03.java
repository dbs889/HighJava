package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
			
		
		
		//Thread가 수행되는 시간 체크해보기
		Thread th = new Thread(new MyRunner());
		
		
		
		//currentTimeMillis() : 1970년 01월 01일 0시0분0초로부터 경과한 시간을 밀리세컨드 단위로 반환
		long startTime = System.currentTimeMillis();
		th.start();
		
		/*
		 * startTime과 endTime의 시간이 매우 짦아 경과시간이 0으로 출력된다
		 * 정확한 경과시간을 구하려면 start()가 run()가 다 끝날 때까지 기다려야 한다 ==> join()사용
		 * */
		
		//끝났을 때(end) - 시작(start)시간 = 경과시간
		

		try {
			th.join(); // join() : 현재 실행중인 쓰레드에서 대상이되는 쓰레드(th)가 종료될때 까지 기다린다 // 제어가 멈춰있다가 th가 종료되면 start가 실행된다
		} catch (Exception e) {
		
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("경과시간 : " + (endTime-startTime) );
		
		

	}

}

class MyRunner implements Runnable{
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i = 1L; i<=1_000_000_000L;i++){
			sum += i;
		}
		
		System.out.println("합계 : " + sum);
	}
	
}