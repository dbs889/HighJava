package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 문제) 5명의 별명을 입력 받아 ArrayList에 저장한 후
 * 		이들 중 별명의 길이가 제일 긴 별명을 출력하시오
 * 			(단, 입력한 각각의 별명의 길이는 모두 다르가)
 * 
 * 문제2) 문제1에서 별명의 길이가 같은 것이 중복될 경우 처리하시오
 */

public class ArrayListTest03 {

	public static void main(String[] args) {


		
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nNameList = new ArrayList<>();
		
		for (int i = 0; i <5; i++) {
			System.out.println("별명을 입력하세요 > ");
			String nName = sc.nextLine();
			nNameList.add(nName);			
		}
		String max ="";
		for (int i = 0; i < nNameList.size(); i++) {
			 max = nNameList.get(i);
			for (int j = 0; j < nNameList.size(); j++) {
				if(max.length() < nNameList.get(j).length()){
					max = nNameList.get(j);
				}
			}
		}
		
		System.out.println(max);
		

	}

}
