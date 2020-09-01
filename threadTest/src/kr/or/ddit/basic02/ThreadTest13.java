package kr.or.ddit.basic02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;


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
public class ThreadTest13 {
	
	static List<Horse> list = new ArrayList<>();
	public static void main(String[] args) {
		
		Horse[] h = new Horse[]{
			new Horse("유네말"),	
			new Horse("상구말"),	
			new Horse("영섭말"),	
			new Horse("정혁말"),	
			new Horse("범영말"),	
			new Horse("병귭말"),	
			new Horse("승민말"),	
			new Horse("민규말"),	
			new Horse("동희말"),	
			new Horse("인혁말")	
			
		};
		
		
		
		
		for(Horse hh : h){
			hh.start();
		}
		for(Horse hh : h){
			try {
				list.add(hh);
				hh.join();
			} catch (InterruptedException e) {
				
			}
		}
		
		Collections.sort(list);
		
		System.out.println(" 경기결과");
		
		for(Horse hrank : list){
			System.out.println(hrank.getRank() + "등 " + hrank.getHsname());
		}
//		System.out.println(" 순위 : " + hose ) ;
	}

}



class Horse extends Thread implements Comparable<Horse>{

	public int loca = 0;
	private int rank = 0; // 말의 등수를 나타낸다
	private String hsname;
	public static int ranking = 0;
	
	public Horse(String hsname) {
		super();
		this.hsname = hsname;
	}

	public int getLoca() {
		return loca;
	}

	public void setLoca(int loca) {
		this.loca = loca;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getHsname() {
		return hsname;
	}

	public void setHsname(String hsname) {
		this.hsname = hsname;
	}

	@Override
	public int compareTo(Horse o) {
		
		return new Integer(this.rank).compareTo(o.rank);
	}

	@Override
	public void run() {

		for (int i = 1; i <= 50; i++) {

			try {
				loca = i;
				Thread.sleep((int) (Math.random() * 300 + 301));
			} catch (Exception e) {

			}
			System.out.print(hsname);
			for (int j = 1; j <= 50; j++) {
				System.out.print("-");

				if (loca == j) {
					System.out.print("🐧");
				}

			}
			System.out.println();

//			if (loca == 50) {
//				System.out.print(hsname + rank + "등");
//				break;
//			}

		}
		
		System.out.println(hsname + "도착!!");
		ranking++;
		
		setRank(ranking);
		if (rank == 1) {
			JOptionPane.showConfirmDialog(null, hsname + " 1등!!");
		}
	}
	
	

}
