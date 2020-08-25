package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 문제 2) 문제 1에서 별명의 길이가 같은 것이 중복될 경우를 처리하시오
 * 
 * 
 */
public class ArrayListTest04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nNameList = new ArrayList<>();
		
		for (int i = 0; i <5; i++) {
			System.out.println("별명을 입력하세요 > ");
			String nName = sc.nextLine();
			nNameList.add(nName);			
		}

		String max ="";
		ArrayList<String> temp = new ArrayList<>();

		for (int i = 0; i < nNameList.size(); i++) {
			 max = nNameList.get(i);
			for (int j = 0; j < nNameList.size(); j++) {
				if(max.length() < nNameList.get(j).length()){
					max = nNameList.get(j);
				}
			}
		}
		
		for (int i = 0; i < nNameList.size(); i++) {
			if (max.length() == nNameList.get(i).length()) {
				temp.add(nNameList.get(i));
			}
		}
		System.out.println(temp);
		
		
		//////////선생님 답변
		String maxAlias = nNameList.get(0);
		for (int i = 0; i < nNameList.size(); i++) {
			if(nNameList.get(i).length() > maxAlias.length()){
				maxAlias = nNameList.get(i);
			}
			
		}System.out.println("제일 긴 별명 : " + maxAlias);
		
		
		//선생님 답변 -- 제일 긴 글자수를 저장할 변수를 선언한 후 리스트의 첫번째 별명의 글자수로 초기화한다
		int maxLen = nNameList.get(0).length();
		for (int i = 0; i < nNameList.size(); i++) {
			if(nNameList.get(i).length() > maxLen){
				maxLen = nNameList.get(i).length();
		
			}
		}
		for (int i = 0; i < nNameList.size(); i++) {
			if(nNameList.get(i).length() == maxLen){
				System.out.println(nNameList.get(i));
				
			}
		}
				
		

	}

}
