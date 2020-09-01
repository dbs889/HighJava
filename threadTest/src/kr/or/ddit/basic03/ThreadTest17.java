package kr.or.ddit.basic03;

//(synchronized)를 이용한 동기화 처리예제
//은행의 입출금 작업을 쓰레드로 처리하는 예제


public class ThreadTest17 {

	
	private int balance; //잔액이 저장될 변수
	
	
	public synchronized int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	//입금을 처리하는 메서드
	public void deposit(int money){
		balance += money;
		
	}
	
	//출금을 처리하는 메서드
	//출금을 성공하면 true 실패 시 false
	//동기화 영역에서 호출하는 메서드도 동기화 처리를 해 주어야 안전하다
	
	public synchronized boolean withdraw(int money){
		if(balance >= money){ //잔액 확인
			for(int i =1; i<= 100000000; i++){} //시간 지연 용
			balance -= money;
			System.out.println("메서드 안에서 balance = " + balance);
			return true;
			
		}else{
			return false;
		}
	}

	public static void main(String[] args) {
		
		final ThreadTest17 acount = new ThreadTest17(); //익명구현체에서 지역변수를 사용하려면 final이어야 한다
		acount.setBalance(10000); //잔액을 10000원으로 설정
		
		
		//Runnable의 익명 구현체 시작
		Runnable r1 = new Runnable() {
			
			
			@Override
			public void run() {
				boolean result = acount.withdraw(6000); //6000원 출금
				System.out.println("쓰레드에서 result = " + result 
						+ ", balance : " + acount.getBalance());
				
			}
		};
		// 익명 구현체 끝.........
		
		Thread th1 = new Thread(r1);
		Thread th2 = new Thread(r1);
		
		th1.start();
		th2.start();
	}

}
