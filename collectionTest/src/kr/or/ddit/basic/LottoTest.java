package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class LottoTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("=======================");
			System.out.println("로또프로그램");
			System.out.println("-----------------------");
			System.out.println(" 1. Lotto 구입\t2. 프로그램 종료");
			System.out.println("=======================");
			System.out.println("메뉴선택 >");
			int input = Integer.parseInt(sc.nextLine());
			switch (input) {

			case 1:
				System.out.println("Lotto 구입시작");
				System.out.println("1000원에 로또번호 하나입니다.");
				System.out.println("금액 입력 >");
				int money = Integer.parseInt(sc.nextLine());

				if (money <= 100000 && money >= 1000) {
					int lottocnt = money / 1000;
					int change = money % 1000;

					for (int i = 0; i < lottocnt; i++) {
						HashSet<Integer> ranNum = new HashSet<>();
						while (ranNum.size() < 6) {
							ranNum.add((int) (Math.random() * 45 + 1));

						}
						System.out
								.println("로또번호  " + (i + 1) + "\t :" + ranNum);
					}

					System.out.println("받은 금액은 " + money + "이고 거스름돈은" + change
							+ "원입니다");

				} else if (money < 1000) {
					System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");

				} else {
					System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");

				}

				break;
			case 2:
				System.out.println("감사합니다");
				System.exit(0);
				break;
			}

		}

	}

}
