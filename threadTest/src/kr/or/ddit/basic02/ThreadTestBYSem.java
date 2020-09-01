package kr.or.ddit.basic02;

import java.util.Arrays;


/*
 * 
 * 10마리의 말들이 경주하는 경마 프로그램 작성하기
 * 
 * 말은 Horse라는 이름의 클래스로 구성한다(이 각각의 말들은 하나의 경기를 진행하는 쓰레드가 된다)
 * 
 * 이 클래스는ㅁ 말이름(String), 등수(int) ,말의 현재위치(int)를 멤버변수로 갖는다
 * 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다. (Comparable 인터페이스 구현하기)
 * 
 * 경기 구간은 1~50 구간으로 되어 있다
 * 
 * 경기 중 중간 중간에 각 말들의 위치를 나타내시오.
 * 
 * 
 * 예) 
 * 말 이름1 ------->-----
 * 말 이름2 ---->--------
 * 말 이름3 ---------->--
 * 
 * 
 */
public class ThreadTestBYSem {

	public static void main(String[] args) {
		
		HorseSem[] horseArr = new HorseSem[] { new HorseSem("1번말"),
				new HorseSem("2번말"), new HorseSem("3번말"), new HorseSem("4번말"),
				new HorseSem("5번말"), new HorseSem("6번말"), new HorseSem("7번말"),
				new HorseSem("8번말"), new HorseSem("9번말"), new HorseSem("10번말") };

		GameState gs = new GameState(horseArr);
		System.out.println("경기 시작.....");
		
		
		//각각의 말들의 쓰레드를 작동
		for (HorseSem horse : horseArr) {
			horse.start();

		}
		
		
		gs.start();// 경기 상황을 출력하는 쓰레드

		for (HorseSem horse : horseArr) {

			try {
				horse.join();
			} catch (InterruptedException e) {}
		}//for
		
		try {
			gs.join();
		} catch (InterruptedException e) {
			
		}
		System.out.println("경기 끝");
		System.out.println();
		System.out.println("경기결과");
		
		//배열의 데이터들을 정렬하는 메서드 : Arrays.sort()
		Arrays.sort(horseArr); //등수의 오름차순으로 정렬하기
		for(HorseSem h : horseArr){
			System.out.println(h);
		}
		
	}

}


class HorseSem extends Thread implements Comparable<HorseSem>{
	
	public static int currentRank = 0; //말들의 등수를 결정할 변수 선언
	
	
	private String hsname;
	private int rank;
	private int position;
	
	
	
	public HorseSem(String hsname){
		this.hsname = hsname;
	}



	public String getHsname() {
		return hsname;
	}



	public void setHsname(String hsname) {
		this.hsname = hsname;
	}



	public int getRank() {
		return rank;
	}



	public void setRank(int rank) {
		this.rank = rank;
	}



	public int getPosition() {
		return position;
	}



	public void setPosition(int position) {
		this.position = position;
	}



	@Override
	public String toString() {
		return "경주마" + hsname + "는(은)" + rank + "등 입니다";
	}

	
	
	
	@Override
	public int compareTo(HorseSem h) {
		
		return new Integer(this.rank).compareTo(h.getRank());
	}



	@Override
	public void run() {
		
		for(int i=1; i <= 50; i++){
			this.position = i;
			
			try {
				Thread.sleep((int)(Math.random()*500));
			} catch (InterruptedException e) {}
		
		}
		//한 마리의 말이 경주가 끝나면 등수를 구해서 설정한다
		currentRank++;
		this.rank = currentRank;
		
	}
	
}


//경기 진행 사항을 출력하는 메서드
/* 예) 
 * 말 이름1 ------->-----
 * 말 이름2 ---->--------
 * 말 이름3 ---------->--

*/

class GameState extends Thread{
	//말들의 정보가 들어가 있는 배열
	private HorseSem[] horseArr; // 경기에 참여하는 말들이 저장된 배열이 저장될 변수 선언

	public GameState(HorseSem[] horseArr) {
		
		this.horseArr = horseArr;
	}
	
	@Override
	public void run() {
		while (true) {
			// 10마리가 다 완주했을 때 반복문을 빠져 나간다
			if (HorseSem.currentRank == horseArr.length) {
				break;
			}

			// 여러줄의 빈줄 출력하기

			for (int i = 0; i <= 20; i++) {
				System.out.println();
			}
			// 경기에 참여한 말의 개수만큼 반복
			for (int i = 0; i < horseArr.length; i++) {
				System.out.print(horseArr[i].getHsname() + " : ");// 말 이름 출력

				// 구간을 나타내는 반복문
				for (int j = 1; j <= 50; j++) {
					if (j == horseArr[i].getPosition()) {
						System.out.print(">");
					} else {
						System.out.print("-");
					}

				}
				System.out.println();
			}// for 문

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}

		}// while문의 끝
	}
	
	
	
}