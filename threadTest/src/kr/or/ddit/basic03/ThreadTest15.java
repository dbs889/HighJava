package kr.or.ddit.basic03;

import java.util.Calendar;


//쓰레드에서 객체를 공통으로 사용하는 예제 연습

/*
 * 	원주율을 계산만 하는 쓰레드와 원주율을 출력만 하는 쓰레드가 있다
 * 
 * 	(각각의 데이터가 원주율을 공통으로 사용함)
 * 
 * 	원주율을 저장하는 객체가 필요하다
 * 	이 객체를 두 쓰레드에서 공통으로 사용해서 처리한다
 * 
 * */

public class ThreadTest15 {

	public static void main(String[] args) {
	
			//공통으로 사용할 객체 생성
			ShareData sd = new ShareData();
			
			//각각의 쓰레드 객체를 생성할 때 공통으로 사용할 객체를 쓰레드에 주입한다
			calcPIThread calc = new calcPIThread(sd);
			
			PrintPIThread print = new PrintPIThread(sd);
			
			calc.start();
			print.start();
	}

}

// 원주율을 관리하는 클래스 작성(공통으로 사용할 클래스)

class ShareData{
	
	public double result; //계산된 원주율이 저장될 변수
	
	public  boolean isOk; //계산이 완료되었는지 여부를 나타내는 변수(계산이 완료되면 true)

	//volatile ==> 이 키워드가 붙은 변수는 컴파일러의 최적화 대상에서 제외된다.
	// 				즉, 값이 변경되는 즉시 변수에 적용시킨다
	//				
	
	//

}

//원주율을 계산만 하는 쓰레드

class calcPIThread extends Thread {

	private ShareData sd;

	public calcPIThread(ShareData sd) {

		this.sd = sd;
	}

	@Override
	public void run() {

		/*
		 * 원주율 =(1/1 - 1/3 + 1/5 - 1/7 + 1/9 - ...)*4; 1 - 3 + 5 - 7 + 9 0 1 2 3
		 * 4
		 */

		double sum = 0.0;
		for (int i = 1; i <= 1_000_000_000; i += 2) {
			if ((i / 2) % 2 == 0) {
				sum += (1.0 / i);
			}// if
			else {
				sum -= (1.0 / i);
			}
		}// for
		sd.result = sum * 4; // 계산된 결과를 저장
		sd.isOk = true;
		System.out.println("계산완료");
	}

}

// 원주율 계산이 완료되면 출력만 하는 쓰레드
class PrintPIThread extends Thread {

	private ShareData sd;

	public PrintPIThread(ShareData sd) {

		this.sd = sd;
	}

	@Override
	public void run() {

		while (true) {

			if (sd.isOk == true) {
				break;
			}

		}
		// 계산된 원주율 출력하기
		System.out.println();
		System.out.println("결과 : " + sd.result);
		System.out.println("pi : " + Math.PI);
	}

}// printPIThread