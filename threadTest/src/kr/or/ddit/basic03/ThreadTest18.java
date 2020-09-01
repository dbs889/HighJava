package kr.or.ddit.basic03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//(lock)를 이용한 동기화 처리예제
//은행의 입출금 작업을 쓰레드로 처리하는 예제
public class ThreadTest18 {
	
	
	
	private int balance; //잔액
	
	//Lock객체 생성 ==> 되도록이면 private final로 만든다
	private final Lock lock = new ReentrantLock();
	
	public int getBalance(){
		return balance;
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	//입금하는 메서드
	public void deposit(int money){
		
		lock.lock();
		
		balance += money;
		
		lock.unlock();
	}
	
	//출금하는 메서드
	public boolean withdraw(int money) {

		// 만약 tty-catch 블럭이 사용되는 부분에서 unlock()메서드를 호출할 때는 finally영역에서 호출하도록 한다
		boolean chk = false;
		try {

			lock.lock();
			if (balance >= money) { // 잔액 확인
				for (int i = 1; i <= 100000000; i++) {
				} // 시간 지연 용
				balance -= money;
				System.out.println("메서드 안에서 balance = " + balance);
				chk = true;

			} else {
				chk = false;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			lock.unlock();
		}
		return chk;
	}

	public static void main(String[] args) {
		//lock객체는 lock()메서드로 락을 설정하고
		//lock은 객체에 접근을 못하게 막아주는 역할을 한다
		//반드시 unlock()메서드로 락을 해제해주어여 한다
		
	
		
		
		final ThreadTest17 acount = new ThreadTest17(); 
		acount.setBalance(10000); 
		
		
	
		Runnable r1 = new Runnable() {
			
			
			@Override
			public void run() {
				boolean result = acount.withdraw(6000); 
				System.out.println("쓰레드에서 result = " + result 
						+ ", balance : " + acount.getBalance());
				
			}
		};
		
		
		Thread th1 = new Thread(r1);
		Thread th2 = new Thread(r1);
		
		th1.start();
		th2.start();

	}

}
