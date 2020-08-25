package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Hotel {

	HashMap<Integer, Room> roomM = new HashMap<>();

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		Hotel test = new Hotel();
		
		test.roomNumber();
		
		while (true) {
			System.out.println();
			System.out.println("*********************************************");
			System.out.println("\t호텔문을 열었습니다 어서오세요");
			System.out.println("*********************************************");

			System.out.println("---------------------------------------------");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1. 체크인 \t2. 체크아웃\t3. 객실상태 \t4. 업무종료");
			System.out.println("---------------------------------------------");
			System.out.println("선택 > ");
			
			
			int input = Integer.parseInt(sc.nextLine());

			switch (input) {
			case 1:

				test.checkIn();
				break;
			case 2:
				test.checkOut();
				break;
			case 3:
				
				test.roomCondition();
				break;
			case 4:

				System.out.println("감사합니다 또 오세요");
				System.exit(0);
				break;

			}

		}

	}
	
	private void roomNumber(){
		
		int roomNo = 200;
		
		while (roomNo < 409) {
			String roomCat = "";
			if (roomNo >= 200 && roomNo <= 209) {
				roomCat = "싱글룸";
			} else if (roomNo >= 301 && roomNo <= 309) {
				roomCat = "더블룸";
			} else if (roomNo >= 401 && roomNo <= 409) {
				roomCat = "스위트룸";
			}
			
			if (roomNo == 209) {
				roomNo = roomNo + 91;
			} else if (roomNo == 309) {
				roomNo = roomNo + 91;
			}
			
			++roomNo;
			Room r = new Room(roomNo, roomCat);
			roomM.put(roomNo, r);
			
		}
	}

	private void roomCondition() {

		System.out.println("----------------------------");
		System.out.println("현재 객실 상태");
		System.out.println("----------------------------");
		System.out.println("방번호\t방종류\t투숙객 이름");
		System.out.println("----------------------------");

		
		for (Integer key2 : roomM.keySet()) {
			Room value2 = roomM.get(key2);
		}

		ArrayList<Integer> list = new ArrayList<>(roomM.keySet());
		Collections.sort(list);
		
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)
					+ "\t "
					+ roomM.get(list.get(i)).getRoomCat()
					+ "\t "
					+ (roomM.get(list.get(i)).getVisitor() == null ? "-"
							: roomM.get(list.get(i)).getVisitor()));

		}

	}

	private void checkOut() {

		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.println("방 번호 입력 > ");
		int OutroomNo = Integer.parseInt(sc.nextLine());

		if (roomM.containsKey(OutroomNo) == false) {

			System.out.println(OutroomNo + "호 객실은 존재하지 않습니다.");

		} else if (roomM.containsKey(OutroomNo) == true) {
			System.out.println(OutroomNo + "호 객실의" + "\t"
					+ roomM.get(OutroomNo).getVisitor() + "님 체크아웃을 완료하였습니다");

					roomM.remove(OutroomNo).getVisitor();
		}

	}

	private void checkIn() {

		System.out.println("-----------------------------");
		System.out.println("체크인 작업");
		System.out.println("-----------------------------");
		System.out.println("*201~209 : 싱글룸");
		System.out.println("*301~309 : 더블룸");
		System.out.println("*401~409 : 스위트룸");
		System.out.println("-----------------------------");
		System.out.println("방 번호 입력 > ");
		int roomNO = Integer.parseInt(sc.nextLine());

		if (roomM.containsKey(roomNO) == false) {

			System.out.println("방번호를 잘못입력하셨습니다");

		} else {

			if (roomM.get(roomNO).getVisitor() != null) {
				System.out.println("이미 체크인한 방입니다. 다른방을 선택해주세요");
			} else {
				System.out.println("누구를 체크인 하시겠습니까?");
				System.out.println("이름입력 >  ");
				String name = sc.nextLine();
				roomM.get(roomNO).setVisitor(name);
				System.out.println("체크인이 완료되었습니다");
				System.out.println(roomM.get(roomNO));

			}

		}

	}

	class Room {

		private int roomNo;
		private String roomCat;
		private String visitor;

		public Room(int roomNo, String roomCat) {
			super();
			this.roomNo = roomNo;
			this.roomCat = roomCat;
		}

		public int getRoomNo() {
			return roomNo;
		}

		public void setRoomNo(int roomNo) {
			this.roomNo = roomNo;
		}

		public String getRoomCat() {
			return roomCat;
		}

		public void setRoomCat(String roomCat) {
			this.roomCat = roomCat;
		}

		public String getVisitor() {
			return visitor;
		}

		public void setVisitor(String visitor) {
			this.visitor = visitor;
		}

		@Override
		public String toString() {
			return "Room [roomNo=" + roomNo + ", roomCat=" + roomCat
					+ ", visitor=" + visitor + "]";
		}

	}
}