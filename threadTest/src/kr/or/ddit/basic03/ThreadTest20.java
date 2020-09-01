package kr.or.ddit.basic03;


/*
 * wait(), notify()메서드를 이용하여 두 쓰레드가 번갈아 한번씩 실행되는 예제
 * wait(), notify(), notifyAll()메서드는 동기화 영역에서만 사용 가능하다
 */
public class ThreadTest20 {

	public static void main(String[] args) {
		WorkObject workobj = new WorkObject();
		
		Thread thA = new ThreadA(workobj);
		Thread thB = new ThreadB(workobj);
		
		thA.start();
		thB.start();
		

	}

}


//공통으로 사용할 객체의 클래스
class WorkObject{
	public synchronized void test1(){
		System.out.println("test1()메서드 실행중.....");
		
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			
		}//catch
		
	}//test1
	
	
	public synchronized void test2(){
		System.out.println("test2()메서드 실행중.....");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
		
		}//catch
	}//test2
}


//WorkObject의 test1()메서드만 호출하는 쓰레드

class ThreadA extends Thread{
	private WorkObject workobj;

	public ThreadA(WorkObject workobj) {
	
		this.workobj = workobj;
	}

	
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workobj.test1();
			
		}
		
		synchronized (workobj) {
			workobj.notify(); //반복문이 끝나야하니깐 깨워줄때 사용
		}
	}
	
}


//WorkObject의 test2()메서드만 호출하는 쓰레드

class ThreadB extends Thread{
	private WorkObject workobj;
	
	public ThreadB(WorkObject workobj) {
		
		this.workobj = workobj;
	}
	
	
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workobj.test2();
			
		}
		
		synchronized (workobj) {
			workobj.notify(); //반복문이 끝나야하니깐 깨워준다
		}
	}
	
}


