package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 문제) 5명의 사람이름을 입력 받아 ArrayList에 저장
 * 		이들 중 '김'씨 성의 이름을 모두 출력 하시오
 * 		(단, 입력은 Scanner객체를 사용 한다)
 */

public class ArrayListTest02 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ArrayList<String> nameList = new ArrayList<>();
		//5명의 이름을 입력
		for (int i = 0; i < 5; i++) {
			System.out.println("이름 입력>");
			String name = sc.nextLine();
			nameList.add(name);
			

		}
		//김씨 성을 가진 사람을 찾기
//		for (int i = 0;  i< nameList.size();i++) {
//			String str = nameList.get(i).substring(0, 1);
//			if (str.contains("김")) {
//				System.out.println(nameList.get(i));
//				
//			}
//		}
		
		for (int i = 0;  i< nameList.size();i++) {
			if (nameList.get(i).substring(0, 1).contains("김")) {
				System.out.println(nameList.get(i));
				
			}
		}
		

		for (int i = 0;  i< nameList.size();i++) {
			if (nameList.get(i).substring(0, 1).equals("김")) {
				System.out.println(nameList.get(i));
				
			}
		}
		
		for (int i = 0;  i< nameList.size();i++) {
			if (nameList.get(i).indexOf("김") == 0) {
				System.out.println(nameList.get(i));
				
			}
		}
		

		for (int i = 0;  i< nameList.size();i++) {
			if (nameList.get(i).startsWith("김")) {
				System.out.println(nameList.get(i));
				
			}
		}
		
		


	}

}
