package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LIstSortTest02 {

	
	public static void main(String[] args) {
		
		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "우럭회", "010-1111-1111"));
		memList.add(new Member(7, "광어회", "010-2222-2222"));
		memList.add(new Member(3, "참돔회", "010-3333-3333"));
		memList.add(new Member(8, "오징어회", "010-4444-4444"));
		memList.add(new Member(4, "소라", "010-5555-5555"));
		memList.add(new Member(2, "문어숙회", "010-6666-6666"));
		memList.add(new Member(6, "전복", "010-7777-7777"));
		memList.add(new Member(5, "해삼", "010-8888-8888"));
		
		
		System.out.println("정렬전...");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("---------------------------------------");
		
		
		Collections.sort(memList);
		System.out.println("정렬 후...");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("---------------------------------------");
		
		Collections.sort(memList,new TelDesc());
		for(Member mem : memList){
			System.out.println("정렬 후 : " + mem);
		}
		
		
		
	}
}

// 내부 정렬 기준을 포함할 클래스는 Comparable 인터페이스를 구현해야 한다
// (Collection에 추가되는 데이터 자체에 정렬 기준을 넣어주는 것을 말한다.)

//Comparable을 구현하는 클래스에서는 compareTo()메서드를 재정의해야 한다

class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;
	
	
	
	//생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}



	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}








	//Member의 이름을 오름차순으로 정렬하는 내부 정렬 기준 구현하기
//	@Override
//	public int compareTo(Member mem) {
//		
//		return this.name.compareTo(mem.getName());
//	}
	
	//Member의 번호를 내림차순으로 정렬하는 내부 정렬 기준 구현하기
//	@Override
//	public int compareTo(Member mem) {
//		if(this.num > mem.getNum()){
//			return -1; //음수를 반환하면 데이터 순서가 바뀌지 않는다
//			
//		}else if(this.num == mem.getNum()){
//			return 0;
//		}else{
//			return 1;
//		}
//	}
	//내부 정렬기준 구현
	@Override
	public int compareTo(Member mem) {
		
		
		//비교방법1
		if(this.num > mem.getNum()){
			return -1;
			
		}else if(this.num == mem.getNum()){
			return 0;
			
		}else{
			return 1;
		}
		
//		return new Integer(this.num);
	}
}
	
	//전화번호의 내림차순으로 정렬 할 수 있는 외부 정렬 기준을 만들고 정렬한 결과를 출력하시오

	
class TelDesc implements Comparator<Member>{
	private int num;
	private String name;
	private String tel;
	
	
	@Override
	public String toString() {
		return "TelDesc [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}


	@Override
	public int compare(Member mem1, Member mem2) {
		
		return mem1.getTel().compareTo(mem2.getTel())*-1;
	}
}

	