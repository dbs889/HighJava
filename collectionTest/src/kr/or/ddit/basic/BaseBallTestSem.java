package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BaseBallTestSem {
	
	ArrayList<Integer> numList; //난수가 저장될 리스트 변수
	ArrayList<Integer> userList; //사용자가 입력한 값이 저장될 리스트 변수
	
	
	int strike; //스트라이크개수
	int ball; // 볼의 개수
	
	
	Scanner sc =new Scanner(System.in);
	
	
	//게임이 시작되는 메서드
	public void gameStart(){
		System.out.println("숫자야구게임을 시작합니다");
		
		
		getNum(); //난수를 만드는 메서드 호출
		 
		int cnt =0; //몇번만에 맞췄는지를 저장하는 변수
		
		do{
			
			cnt++;
			//사용자 입력 메서드 호출
			inputNum();
			
			//볼카운트 구하는 메서드 호출
			ballCount();
			
		}while(strike !=3); // 3스트라이크가 될때까지 반복
		
		System.out.println();
		System.out.println("축하합니다!! 당신은 " + cnt + "번째만에 맞췄습니다");
		
		
		
	}
	
	//1~9사이의 서로 다른 난수 3개를 만들어서 리스트에 저장하는 메서드
	
	public void getNum(){
		
		
		Set<Integer> numSet = new HashSet<>();
		
		
		//1~9사이의 난수 3개 만들기
		while(numSet.size()<3){
			numSet.add((int)(Math.random()*9+1));
			
			
			//만들어진 난수 List에 저장하기
			numList = new ArrayList<>(numSet);
			
			
			//List의 데이터를 섞어준다
			Collections.shuffle(numList);
			
			
		}
		
		System.out.println("컴퓨터 난수: " + numList);
		
		
	}
	//사용자로부터 3개의 서로 다른 정수 3개를 입력 받아 리스트에 저장하는 메서드
	
	
	public void inputNum(){
		int n1,n2,n3;
		
		do{
			System.out.print("숫자입력 >");
			n1 = sc.nextInt();
			n2 = sc.nextInt();
			n3 = sc.nextInt();
			
			if(n1==n2 ||n2==n3||n1==n3){
			System.out.println("중복되는 숫자는 입력할 수 없습니다");	
			}
			
		}while(n1==n2 ||n2==n3||n1==n3);
		
		
		//입력한 값을 리스트에 저장한다
		userList = new ArrayList<>();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}
	
	
	//스트라이크와 볼을 판정하고 결과를 출력하는 메서드
	public void ballCount(){
		strike =0;
		ball =0;
		
		for (int i = 0; i < userList.size(); i++) {
				for (int j = 0; j < numList.size(); j++) {
					if(userList.get(i) == numList.get(j)){
						if(i ==j){
							strike++;
						}else{
							ball++;
						}
					}
				}
		}
		
		//볼카운터의 결과를 출력한다
		System.out.println(userList.get(0) + "," 
		+ userList.get(1) + "," + userList.get(2) + "==> " + strike + "S" + ball + "B");
		
	}
	
	public static void main(String[] args) {
		
		BaseBallTestSem test = new BaseBallTestSem();
		test.gameStart();
		
	}//메인메서드 끝
	
}
