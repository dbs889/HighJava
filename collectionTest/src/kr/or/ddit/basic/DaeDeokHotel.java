package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DaeDeokHotel {

	// 1.
	private Map<Integer, Room> hotelMap;
	private Scanner sc;

	// 3.
	public DaeDeokHotel() {
		hotelMap = new HashMap<Integer, Room>();
		sc = new Scanner(System.in);

		// 4.객실 초기화
		for (int i = 3; i <= 4; i++) {
			String roomType = null;
			;
			switch (i) {
			case 2:
				roomType = "싱글룸";
				break;
			case 3:
				roomType = "더블룸";
				break;
			case 4:
				roomType = "스위트룸";
				break;
			}
			for (int j = 1; j <= 9; j++) {
				int roomNo = i * 100 + j;
				Room room = new Room(roomNo, roomType);
				hotelMap.put(roomNo, room);

			}
		}// 객실 초기화

	}

	// 5.
	private void hotelStart() {
		System.out.println();
		System.out.println("*************************************************");
		System.out.println("        호텔문을 열었습니다 어서요세요                 ");
		System.out.println("*************************************************");
		System.out.println();

		while (true) {
			int choice = displayMenu();
			switch (choice) {

			case 1:

				// 체크인

				checkIn();
				break;
			case 2:

				// 체크아웃

				break;
			case 3:

				// 객실상태

				showRoom();
				break;
			case 4:

				// 업무종료

				System.out.println("**************************************");
				System.out.println("             호텔문을 닫았습니다");
				System.out.println("**************************************");
				return;
			default:
				System.out.println("번호를 잘못입력하셨습니다");
			}
		}

	}

	// 7. 체크인메서드
	private void checkIn() {
		System.out.println();
		System.out.println("------------------------------");
		System.out.println("체크인");
		System.out.println("------------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~309 : 스위트룸");
		System.out.println("------------------------------");
		System.out.println("방 번호를 입력하세요 >");
		int num = sc.nextInt();

		// 입력한 방번호가 Map에 없으면 잘못입력한 방법호이다
		if (!hotelMap.containsKey(num)) {
			System.out.println(num + " 호 객실은 존재하지 않습니다");
			return;
		} else if (hotelMap.get(num).getGuestNm() != null) { // 해당 객실에 다른 투숙객이
																// 있는지 검사
			System.out.println(num + " 호 객실에는 이미 손님이 있습니다");

		} else {
			System.out.println("체크인 하실 분의 성함은 ? >");
			String name = sc.next();

			// 입력받은 투숙객 이름을 해당 객실의 투숙객 명단에 저장한다

			hotelMap.get(num).setGuestNm(name);

			System.out.println(name + " 씨가" + num + " 호 객실에 체크인 되었습니다");

		}

	}
	
	//8. 체크아웃
	private void checkOut(){
		
		System.out.println("------------------------------------");
		System.out.println("		체크아웃");
		System.out.println("------------------------------------");
		System.out.println("체크아웃 할 방법호를 입력하세요 >");
		int num = sc.nextInt();
		
		if(!hotelMap.containsKey(num)){
			System.out.println(num + "호 객실은 존재하지 않습니다");
			return;
		}
		
		if(hotelMap.get(num).getGuestNm() == null){
			
			System.out.println(num + "호 객실에는 체크인한 사람이 없습니다");
		return;
		}
		
		//객실의 투숙객 이름을 null로 변경한다
		String name = hotelMap.get(num).getGuestNm();
		 hotelMap.get(num).setGuestNm(null); //투숙객 이름을 null로 판단

		 System.out.println(num  + "호 객실의" + name + "님 체크아웃 완료" );
		 
	}

	// 8. 객실상태를 출력하는 메서그
	private void showRoom() {
		
		System.out.println();
		//방번호를 순서대로 출력하기 위해서 방번호(Map의 key값)만 List에 넣은 후 정렬하여 사용한다
		
		List<Integer> roomNmList = new ArrayList<>(hotelMap.keySet());
		
		Collections.sort(roomNmList); //방번호를 오름차순으로 정렬한다
		
		
		System.out.println("----------------------------------------");
		System.out.println("			현재 객실 상태");
		System.out.println("----------------------------------------");
		System.out.println("방번호		방종류		투숙객이름");
		System.out.println("----------------------------------------");
		
		
		//List에서 방번호를 하나씩 꺼내와 Map에서 해당 방번호에 해당하는 Room객체를 구해서 출력한다
		for(int roomNum : roomNmList){
			Room r = hotelMap.get(roomNum);
			System.out.print("   " + r.getRoomNo() + "\t\t" +  r.getRoomType()+ "\t\t" );
			String name =" -";
			if(r.getGuestNm()!= null){
				name = r.getGuestNm();
			}
			System.out.println(name);
		}
		System.out.println("----------------------------------------");
		System.out.println();
	}

	// 6. 메뉴 출력 및 작업번호를 입력받아 반환하는 메서그
	private int displayMenu() {

		System.out.println();
		System.out.println("------------------------------");
		System.out.println("어떤업무를 하시겠습니까");
		System.out.println("1.체크인 2. 체크아웃 3. 객실상태 4. 업무종료");
		System.out.println("------------------------------");
		System.out.println("선택 >");
		int num = sc.nextInt();

		return num;

	}

	public static void main(String[] args) {

		new DaeDeokHotel().hotelStart();

	}

}

// 2.
class Room {
	private int roomNo;
	private String roomType;
	private String guestNm;

	public Room(int roomNo, String roomType) {
		super();
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.guestNm = guestNm;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getGuestNm() {
		return guestNm;
	}

	public void setGuestNm(String guestNm) {
		this.guestNm = guestNm;
	}

}