package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class BaseBallTest {

	/*
	 * 문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오
	 * 
	 * 컴퓨터의 숫자는 난수를 이용하여 구한다(1~9사이의 서로 다른 난수 3개) (스트라이크 S, 볼은 B로 나타낸다)
	 * 
	 * 예시) 컴퓨터가 만든 난수 ==> 9 5 7
	 * 
	 * 실행예시) 사용자가 숫자를 입력 한줄에 세개씩 3,5,6 사용자가 입력한 숫자 3 5 6 ==> 1s 0B
	 * 
	 * 못맞추면 숫자입력을 반복한다
	 * 
	 * 난수 : set 스트라이크 볼 : list set에서 list에 넣고 한번 섞은 값을 난수로 한다
	 * 
	 * 
	 * 내가부른 숫자와 상대편 숫자를 비교하여 위치와 값이 같으면 스트라이크 값은 같은데 위치가 다르면 ball
	 * 
	 * 컴퓨터는 랜덤한 3개의 숫자를 만든다
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 1. 난수를 만든다
		HashSet<Integer> ranNum = new HashSet<>();
		while (ranNum.size() < 3) {
			ranNum.add((int) (Math.random() * 9 + 1));
		}
		// System.out.println("랜덤 번호 : " + ranNum);

		// 2. set에서 list에 넣고 한번섞은 값을 난수로 한다
		ArrayList<Integer> numList = new ArrayList<>(ranNum);

		Collections.shuffle(numList);
		// System.out.println("랜덤한 난수 : " + numList);

		// 4.랜덤한 숫자와 내 숫자가 같으면 스트라이크 위치만 같으면 ball
		int count = 0;
		while (true) {
			// 3. 3개의 숫자를 한번에 입력 받기
			System.out.print("3개의 숫자 입력 ");
			int num1 = Integer.parseInt(sc.nextLine());
			int num2 = Integer.parseInt(sc.nextLine());
			int num3 = Integer.parseInt(sc.nextLine());

			System.out.println("사용자가 입력한 숫자 : " + num1 + "," + num2 + ","
					+ num3);

			int strike = 0;
			int ball = 0;

			if (num1 == numList.get(0))
				strike++;
			if (num2 == numList.get(1))
				strike++;
			if (num3 == numList.get(2))
				strike++;

			if (num1 == numList.get(1) || num1 == numList.get(2)) {
				ball++;
			}
			if (num2 == numList.get(0) || num2 == numList.get(2)) {
				ball++;
			}
			if (num3 == numList.get(0) || num3 == numList.get(1)) {
				ball++;
			}
			System.out.println(strike + "S , " + ball + "B");
			System.out.println("축하합니다 당신은 " + ++count + " 번째에 맞췄습니다");
			if (strike == 3) {
				System.out.println("정답입니다");
				break;

			} else {
				System.out.println("다시 시도하세요");
			}
		}

	}

}
