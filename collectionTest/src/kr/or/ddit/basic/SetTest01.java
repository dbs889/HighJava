package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class SetTest01 {
	
	/*
	 * 
	 * set의 특징(List와 비교)
	 * 		1. List
	 * 			- 데이터의 순서(index)가 있다
	 * 			- 중복되는 데이터를 저장할 수 있다
	 * 		2. set
	 * 			- 데이터의 순서(index)가 없다
	 * 			- 중복되는 데이터를 저장할 수 없다
	 * 
	 */

	public static void main(String[] args) {
		
		
		HashSet hs1 = new HashSet<>(); //제네릭을 쓰지 않으면 어떤 데이터든 다 담을 수 ㅇ있다
		
		//데이터 추가 : add()메서드 사용
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("set의 개수 : " + hs1.size());
		System.out.println("set의 데이터 : " + hs1); //인덱스가 없으므로 입력한 순서와 데이터를 꺼내온 순서가 다르다
		System.out.println("---------------------------------------------");
		
		
		//set의 중복되는 데이터를 추가하면 false를 반환하고 데이터는 추가되지 않는다
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdd);
		System.out.println("set의 개수 : " + hs1.size());
		System.out.println("실제 set 데이터 : " + hs1);
		
		System.out.println("---------------------------------------------");
		isAdd = hs1.add("CC");
		System.out.println("중복되지 않을 때 : " + isAdd);
		System.out.println("set의 개수 : " + hs1.size());
		System.out.println("실제 set 데이터 : " + hs1);
		
		System.out.println("---------------------------------------------");
		
		/*set의 데이터를 수정하려면 수정 명령이 따로 없기 때문에 해당 자료를 삭제한 후 추가해 주는 방법을 사용한다
		
		-> 삭제하기 : remove(삭제할자료) ==> 반환값 : 삭제 성공(true), 삭제실패(false)
					clear() ==> 전체 삭제
		==> "FF" 문자열을 "EE" 문자열로 변경하기  
		 */
		
		hs1.remove("FF");
		System.out.println("삭제 후 데이터 : " + hs1);
		hs1.add("EE");
		System.out.println("추가"
				+ " 후 데이터 : " + hs1);
		
		System.out.println("---------------------------------------------");
//		hs1.clear(); //데이터 전체 삭제
//		System.out.println("clear 후 set : " + hs1);
		
		HashSet<Integer> intSet = new HashSet<>();
		
		intSet.add(10);
		intSet.add(7);
		intSet.add(9);
		intSet.add(3);
		intSet.add(5);
		
		//Q) set의 모든 데이터의 합계 구하기
	
		
		/*
		 *	Set의 데이터 순서(index)가 없기 때문에 List처럼 index로 데이터를 하나씩 불러 올 수가 없다
		 *	그래서 데이터를 하나씩 얻기 위해서는 Iterator형 객체로 변환해서 사용해야 한다
		 *	
		 *	- Set형의 데이터를 Iterator 객체로 변환해 부는 메서드 ==> Iterator()
		 *	
		 *
		 */
		
		Iterator<Integer> it = intSet.iterator(); //Set데이터를 Iterator로 변환하기
		
		//Iterator의 hashNext()메서드 ==> Iterator의 데이터를 가리키는 포인터(데이터를 가리키는 어떠한 값)를 다음 번째의 위치에
									// 데이터가 있으면 true,없으면 false를 반환한다.
		
		// hashNext() : 다음 데이터를 가르키는 포인터 역할을 한다 마지막에 데이터가 없을 때 false를 반환
		
		
		int sum =0;
		while(it.hasNext()){//iterator를 다음번?로 이동시켜라
			
			//Iterator의 next()메서드  : 포인터를 다음번째 위치로 이동한 후 그 자리의 데이터를 반환
			
			int num= it.next(); //it.next로 데이터를 꺼내온다
			System.out.println(num);
			sum += num;
		}
		System.out.println("---------------------------------------------");

		System.out.println("총 합계 : " + sum);
		
		//우리반 학생들 중 번호를 이용하여 추첨하는 프로그램을 작성해 보자
		//번호는 1번부터 25번까지 있고, 추첨할 인원은 3명이다.
		//당첨자를 출력해보자.
		
		//3개짜리 배열을 만들고 난수를 만들어 배열을 저장 (배열안에 값과 중복되면 안된다 배열안의 값고 난수가 같은값이 있으면 난수를 만들고 배열안의 값과난수가 다르면 난수를 저장)
		
//		int[] numArr = new int[3];
//		
//		for (int i = 0; i < numArr.length; i++) {
//			
//			numArr[i] = (int)(Math.random()*(25-1+1)+1); //-1은최소값 +1 
//			//최소값~ 최대값 사이의 난수 만들기
//			//(int)(Math.random()*(최대값-최소값+1)+최소값(=시작값))
//	
//			for (int j = 0; j < numArr.length; j++) {
//				if(numArr[i] == numArr[j]){
//					i--;
//					continue;
//					
//				}
//			}	
//			
//		}
//		System.out.println(Arrays.toString(numArr));
		
		HashSet<Integer> numSet = new HashSet<>();
		
		while(numSet.size()<3){
			numSet.add((int)(Math.random()*25+1));
				
			}
		System.out.println("당첨자 번호 : " + numSet);
		
		System.out.println("--------------------------------------------");
		
		
		// Set유형의 자료를 List형으로 변환하기
		ArrayList<Integer> testList = new ArrayList<>(numSet);
		
		System.out.println("List 데이터 출력 ");
		for (int i = 0; i < testList.size(); i++) {
			System.out.println(testList.get(i));
		}
		System.out.println("--------------------------------------------");
		
		
		
		
		
		
		
		
	
	}

}
