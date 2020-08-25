package kr.or.ddit.basic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest01 {
	
		
	
	public static void main(String[] args) {
		
		
		ArrayList<String> list = new ArrayList<>();
		
		list.add("우럭회");
		list.add("광어회");
		list.add("연어회");
		list.add("참돔회");
		list.add("오징어회");
		
		System.out.println("정렬 전 : " + list);
		
		// 정렬은 Collection.sort()메서드를 이용하여 정렬한다
		
		// 		==> 보통 정렬은 기본적으로 오름차순 정렬을 수행한다
		
		
		Collections.sort(list);
		System.out.println("정렬 후 : " + list );
		
		
		Collections.shuffle(list); //자료를 섞어 준다
		System.out.println("자료섞기 후  : " + list );
		
		
		//외부 정렬 기준 클래스를 이용하여 정렬하기
		Collections.sort(list,new Desc());
		System.out.println("정렬 후 : " + list );

	}

}

		// 정렬 방식을 정해주는 class를 만든다 (== 외부 정렬 기준 클래스)
		// Comparator 인터페이스를 구현해서 작성해야 한다
		// Comparator 인터페이스를 구현한 클래스는 compare()라는 메서드를 재정의해서
		// 정렬기준을 설정한다

class Desc implements Comparator<String>{
	
		//compare() 메서드의 매개변수는 2개이고 정수형 데이터를 반환한다 
	
		//compare() 메서드의 반환값
		//1. 두 값이 같을 경우 0을 반환하도록 한다
		//2. 양수를 반환하면 앞 뒤 데이터의 순서가 바뀐다
		//3. 음수를 반환하면 앞, 뒤 데이터의 순서가 바뀌지 않는다
	
		// 예 ) 오름차순일 경우 ==> 앞의 값이 크면 상수, 같으면 0, 앞의 값이 작으면 음수를 반환하도록 작성한다
	
		//String 객체에는 정렬을 위해서 compareTo()메서드가 구현되어 있는데
		//이 메서드의  반환값은 오름차순에 맞게 반환되도록 구현되어 있다
		//(Wrapper 클래스와 Data, File클래스에도 구현되어 있다)
	
	
		@Override
		public int compare(String o1, String o2) {
			//내림차순으로 정렬하도록 구현하기
			if(o1.compareTo(o2)<0){
				return 1;
			}else if(o1.compareTo(o2)==2){
				return 0;
			}else{
				return -1;
			} //매개변수의 순서를 정해주는 것이 반환값
		}
	}
