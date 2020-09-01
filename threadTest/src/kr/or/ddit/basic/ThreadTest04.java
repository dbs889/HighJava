package kr.or.ddit.basic;


/*
 		1~20억까지의 합계를 구하는 프로그램을 하나의 쓰레드가 단독으로 처리할 때와 
 		여러개의 쓰레드가 협력해서 처리할 때의 경과시간을 비교해 본다
  
 * */

public class ThreadTest04 {
	
	
	
	
	
	public static void main(String[] args) {
		
		//4.단독으로 처리하는 쓰레드
		
		SumThread sm = new SumThread(1L, 2_000_000_000L);
		
		//5.여럿이 협력해서 처리하는 쓰레드
		//배열을 만들어 배열에 담는다
		SumThread[] sumArr = new SumThread[]{
				new SumThread(1L, 500_000_000L),
				new SumThread(500_000_000L, 1_000_000_000L),
				new SumThread(1_000_000_000L, 1_500_000_000L),
				new SumThread(1_500_000_000L, 2_000_000_000L)
		};
		
		//4-2.단독으로 처리하기
		long startTime = System.currentTimeMillis();
		sm.start();
		
		try {
			sm.join();
		} catch (Exception e) {
		
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("단독으로 처리했을 때 경과시간 : 유네슼은 처리못함  " + (endTime - startTime));
		System.out.println("-------------------------------------------");
		System.out.println();
		
		startTime = System.currentTimeMillis();		
		//5-2.멀티쓰레드
		for(int i = 0; i< sumArr.length; i++){
			sumArr[i].start();
			
		}
		
		for(SumThread sms : sumArr){
			try {
				sms.join();
			} catch (Exception e) {
			
			}
		}
		endTime = System.currentTimeMillis();
		System.out.println("멀티쓰레드를 사용했을 때 경과 시간" + (endTime - startTime));

	}

}

class SumThread extends Thread{
	
	//1. 합계를 구할 영역의 시작값과 종료값이 저장될 변수 선언
	private long start,end;

	
	//2.생성자를 만들어 변수의 값을 초기화
	public SumThread(long start, long end) {
		
		this.start = start;
		this.end = end;
	} 
	
	//3.run()메서드
	@Override
	public void run() {
		long sum = 0L;
		for(long i = start; i<=end; i++){
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	
	
}

