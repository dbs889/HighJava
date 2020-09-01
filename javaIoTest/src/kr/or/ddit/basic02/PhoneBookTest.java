package kr.or.ddit.basic02;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/*
 * 문제 ) 이름, 주소, 나이, 전화번호를 멤버 변수로 갖는 Phone클래스를 만들고 Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오
 * 
 * 
 * 			- 이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체 출력하는 기능이 있다
 * 			- 삭제와 검색기능은 '이름을' 입력받아 처리한다
 * 			-(Map의 구조는 key값으로 그 사람의 '이름'을 사용하고, value값으로는 ''Phone클래스의 인스턴스'로 한다)
 * 
 * 
 * 실행예시)
 * 
 * 1) 6. '전화번호 저장' 메뉴를 추가하고 구현한다
 * 		(저장 파일명 : phoneData.dat로 한다) 
 * 
 * 2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 셋팅한다
 * 			프로그램 파일이 있으면 map에다 셋팅하고 없으면 map객체를 새로 입력
 * 3) 프로그램을 종료할 때 Map의 데이터가 변경되었거나 추가 또는 삭제 되면 새로 저장한 후 종료되도록한다
 * 				수정한 데이터를 저장하고 종료되도록한다 만약 변경된 작업이 없을경우 저장을 하지 않는다
 * 
 * 		다음메뉴를 선택하세요
 * 		1. 전화번호 등록
 * 		2. 전화번호 수정
 * 		3. 전화번호 삭제
 * 		4. 전화번호 검색
 * 		5. 전화번호 전체 출력
 * 		6. 전화번호 저장
 * 		0. 프로그램 종료
 * ------------------------------------------
 * 	번호 입력 >>	1
 * 	
 * 	새롭게 등록할 전화번호 정보를 입력하세요
 * 	이름 >> 홍길동
 * 	전화번호 >> 010-2222-0000
 * 	나이 >> 20
 * 	주소 >> 대전시 중구 보문로 20번길34
 * 
 * 입력할 개체를 phone에 set한다음 map에 추가한다
 * =>결과 : '홍길동' 등록완료!!
 *	 
 *
 *	다음메뉴를 선택하세요
 * 		1. 전화번호 등록
 * 		2. 전화번호 수정
 * 		3. 전화번호 삭제
 * 		4. 전화번호 검색
 * 		5. 전화번호 전체 출력
 * 		0. 프로그램 종료
 * ------------------------------------------
 * 	번호 입력 >>	1
 * 
 * 새롭게 등록할 전화번호 정보를 입력하세요
 * 이름 >> 홍길동
 * 
 * '홍길동'은 이미 등록된 사람입니다
 * 
 * 다음메뉴를 선택하세요
 * 		1. 전화번호 등록
 * 		2. 전화번호 수정
 * 		3. 전화번호 삭제
 * 		4. 전화번호 검색
 * 		5. 전화번호 전체 출력
 * 		0. 프로그램 종료
 * ------------------------------------------
 * 	번호 입력 >>	5
 * 
 * =======================================================
 * 	번호	 	이름		전화번호			나이		주소	
 * =======================================================
 * 	1		홍길동	010-1111-1111	20		대전
 * 
 * -----------------------------------------
 * 출력 완료....
 * 
 * 
 * 다음메뉴를 선택하세요
 * 		1. 전화번호 등록
 * 		2. 전화번호 수정
 * 		3. 전화번호 삭제
 * 		4. 전화번호 검색
 * 		5. 전화번호 전체 출력
 * 		0. 프로그램 종료
 * ------------------------------------------
 * 번호 입력 >> 0
 * 
 * 프로그램을 종료합니다
 * 
 */
public class PhoneBookTest {

	HashMap<String, Phone> pbMap = new HashMap<>();

	static Scanner sc = new Scanner(System.in);
	static int cnt = 0;

	public static void main(String[] args) {

		PhoneBookTest pbt = new PhoneBookTest();

		while (true) {

			System.out.println("다음 메뉴를 선택하세요");
			System.out.println("1. 전화번호 등록");
			System.out.println("2. 전화번호 수정");
			System.out.println("3. 전화번호 삭제");
			System.out.println("4. 전화번호 검색");
			System.out.println("5. 전화번호 전체 출력");
			System.out.println("0. 프로그램 종료");
			System.out.println("-----------------------------------");
			int input = Integer.parseInt(sc.nextLine());
			switch (input) {
			case 1:
				pbt.insertPb();
				break;
			case 2:
				pbt.update();
				break;
			case 3:
				pbt.deletePb();
				break;
			case 4:
				pbt.searchPb();
				break;
			case 5:
				pbt.selectPb();
				break;
			case 0:
				System.out.println("프로그램이 종료됩니다");
				System.exit(0);
				break;

			}// switch문 끝나기

		} // while문 끝나기

	}

	private void selectPb() {

		System.out.println("===================================");
		System.out.println("번호\t이름\t전화번호\t\t나이");
		System.out.println("===================================");
		for (String key2 : pbMap.keySet()) {

			Phone value2 = pbMap.get(key2);
			System.out.println(++cnt + "\t" + key2 + "\t" + value2.getTel()
					+ "\t" + value2.getAge());
		}

	}

	private void searchPb() {

		System.out.println("조회할 이름을 검색하세요");
		String searNm = sc.nextLine();

		if (pbMap.containsKey(searNm) == true) {

			System.out.println("이름 : " + pbMap.get(searNm).getName());
			System.out.println("주소 : " + pbMap.get(searNm).getAddr());
			System.out.println("나이 : " + pbMap.get(searNm).getAge());
			System.out.println("전화번호 : " + pbMap.get(searNm).getTel());

		} else {
			System.out.println("해당 이름이 존재하지 않습니다");
		}

	}

	private void deletePb() {

		System.out.println("삭제할 이름을 입력하세요");
		String delNm = sc.nextLine();

		if (pbMap.containsKey(delNm) == true) {
			pbMap.remove(delNm);

		}

	}

	private void update() {
		// 수정하는 메서드
		System.out.println("새롭게 수정할 전화번호 정보를 입력하세요");

		System.out.println("이름 > ");
		String name = sc.nextLine();

		System.out.println("전화번호 > ");
		String tel = sc.nextLine();

		System.out.println("주소 > ");
		String addr = sc.nextLine();

		System.out.println("나이 > ");
		int age = Integer.parseInt(sc.nextLine());

		Phone ph = new Phone(name, addr, age, tel);

		pbMap.put(name, ph);

	}

	private void insertPb() {

		// 사용자가 등록한 정보를 리스트에 저장하는 메서드

		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요");

		System.out.println("이름 > ");
		String name = sc.nextLine();

		if (pbMap.containsKey(name) == true) {

			System.out.println(name + "은 이미 등록된 사람입니다");

		} else {

			System.out.println("전화번호 > ");
			String tel = sc.nextLine();

			System.out.println("주소 > ");
			String addr = sc.nextLine();

			System.out.println("나이 > ");
			int age = Integer.parseInt(sc.nextLine());

			Phone ph = new Phone(name, addr, age, tel);

			pbMap.put(name, ph);
		}

		// ph에있는 name이라는 key값을 가지고 value값을 저장하라

	}

	class Phone {

		@Override
		public String toString() {
			return "Phone [name=" + name + ", addr=" + addr + ", age=" + age
					+ ", tel=" + tel + "]";
		}

		private String name;
		private String addr;
		private int age;
		private String tel;

		public Phone(String name, String addr, int age, String tel) {
			super();
			this.name = name;
			this.addr = addr;
			this.age = age;
			this.tel = tel;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

	}

}