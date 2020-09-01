package kr.or.ddit.basic02;

/*
 * 
 * 
 * */

public class ThreadTest16 {

	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		TestThread th1 = new TestThread("Test1", sObj);
		TestThread th2 = new TestThread("Test2", sObj);
		
		
		th1.start();
		th2.start();
	}

}

class TestThread extends Thread{
	
	private ShareObject sObj;
	
	public TestThread(String name, ShareObject sObj){
		super(name); //쓰레드의 name 설정 --조상 클래스의 name값을 설정
		
		this.sObj = sObj;
		
	}
	
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			sObj.add();
		}
	}
}


//공통으로 사용할 쓰레드
class ShareObject{
	private int sum = 0;
	
	//동기화 처리 하기
	
	//방법 1) public synchronized void add(){ //synchronized : 동기화처리하는 방법 1 -> 메서드 자체에 동기화설정을 한다
	
	
	/*방법2)*/public void add(){ 
		
		synchronized (this) { //동기화 방법 2: 동기화 블럭으로 설정한다
			
			int n = sum;
			
			n+= 10; //10증가
			
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		}
		
		
		
		
	}
	
	
}