package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class uneTest01 {

	public static void main(String[] args) {
		// 문제 1 ) 5명의 이름을 입력 받아 김씨성만 출력하여라
		
		
		Scanner sc = new Scanner(System.in);
		
		ArrayList<String> list = new ArrayList<>();
		
		
		for (int i = 0; i <5; i++) {
			System.out.println("이름을 입력하세요");
			String name = sc.nextLine();
			list.add(name);
			
			
		}System.out.println(list);
		
		for (int i = 0; i < list.size(); i++) {
			
			if(list.get(i).substring(0, 1).equals("김")){
				System.out.println(list.get(i));
			}
			
		}
		System.out.println("============문제1끝============");
		
		// 문제 2) 5명의 별명을 입력 받아 별명의 길이가 가장 긴 별명을 출력하여라
		
		for (int i = 0; i <5; i++) {
			System.out.println("이름을 입력하세요");
			String alias = sc.nextLine();
			list.add(alias);
		}

		
		String longAlias = list.get(0);
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).length() > longAlias.length()){
				longAlias = list.get(i);
			}
		}System.out.println(longAlias);
		
		System.out.println();
		System.out.println("============문제2끝============");
			
		// 문제 3) 문제 2의 중복된 별명을 출력하여라
		
		
		ArrayList<String> temp = new ArrayList<>();
		for (int i = 0; i <list.size(); i++) {
			if(list.get(i).length()==longAlias.length()){
				temp.add(list.get(i));
			}
			
		}System.out.println(temp);

	}

}
