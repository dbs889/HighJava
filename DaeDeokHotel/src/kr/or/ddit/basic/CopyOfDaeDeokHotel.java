package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CopyOfDaeDeokHotel {

	private Scanner sc;
	//방 번호를 기본키로 하는 방 정보를 담는 Map 선언
	private Map<Integer, Room> hotelMap;
	
	private boolean dataChange = false;	
	//데이터가 변경되었는지 여부를 나타내는 변수
	//데이터가 변경되면 true가 된다

	
	//생성자
	public CopyOfDaeDeokHotel() {
		
		sc = new Scanner(System.in);
//		hotelMap = new HashMap<>();
		
		hotelMap = load();
		
		if(hotelMap == null){
			hotelMap = new HashMap<>();
		
		
			//객실 초기화
			for(int i = 2; i<= 4; i++){
				String roomType = null;
				
				switch(i){
				case 2: 
					roomType ="싱글룸";
					break;
				case 3: 
					roomType ="더블룸";
					break;
				case 4: 
					roomType ="스위트룸";
					break;
				}
			for (int j = 1; j <= 9; j++) {
						
					int roomNo = i * 100 + j;
					Room room = new Room(roomNo, roomType);
					hotelMap.put(roomNo, room);
				
					}
				
			}
		}
	
	}
	
	
	public static void main(String[] args) {
	 
		
		new CopyOfDaeDeokHotel().hotelStart();
		
	}

	//시작하는 메서드
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
				checkOUt();
				break;
			case 3:
				// 객실상태
				showRoom();
				break;
			case 4:
				//객실정보 등록
				hotelFile();
				break;
				
			case 5:
				// 업무 종료
				if(dataChange == true){
					System.out.println("변경된 내용을 저장한다");
					hotelFile();
				}
				
				System.out.println("호텔문을 닫습니다");
				System.exit(0);
			default:
				System.out.println("번호를 잘못 입력하셨습니다");

			}// switch
		}// while
	}// start

	
	private Map<Integer, Room> load() {

		HashMap<Integer, Room> hotelMap = null;

		File file = new File("d:/d_other/hotel_data.dat");

		if (!file.exists()) {

			return null;
		}

		try {

			ObjectInputStream oin = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));

			hotelMap = (HashMap<Integer, Room>) oin.readObject();
			
			

			oin.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return hotelMap;

	}


	private void hotelFile() {
		
		try {
			
			File file = new File("d:/d_other/hotel_data.dat");
			
			FileOutputStream fout = new FileOutputStream(file);
			
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			
	
			oout.writeObject(hotelMap);
			
			System.out.println("저장완료");
			
			
			oout.close();
			
			dataChange =false;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}


	private void showRoom() {


		System.out.println();
		
		//방번호를 순서대로 출력하기 위해서 방번호(Map의 key값)만 List에 넣은 후 정렬하여 사용한다
		
		List<Integer> roomList = new ArrayList<>(hotelMap.keySet());
		
		Collections.sort(roomList);
		
		System.out.println("----------------------------------------");
		System.out.println("			현재 객실 상태");
		System.out.println("----------------------------------------");
		System.out.println("방번호		방종류		투숙객이름");
		System.out.println("----------------------------------------");
		
		for(int roomNm : roomList){
			Room room = hotelMap.get(roomNm);
			System.out.print(" " + room.getRoomNo() + "\t\t" +  room.getRoomType() + "\t\t");
			String name = " -";
			if(room.getGuestNm() != null){
				name = room.getGuestNm();
			}
			System.out.println(name);
		}
		System.out.println("----------------------------------------");
		System.out.println();
	}


	private void checkOUt() {
		
		System.out.println("------------------------------------");
		System.out.println("		체크아웃");
		System.out.println("------------------------------------");
		System.out.println("체크아웃 할 방법호를 입력하세요 >");
		int num = sc.nextInt();
		
		if(hotelMap.containsKey(num)){
			System.out.println(num + "호 객실은 존재하지 않습니다");
			return;
		
		}
		if(hotelMap.get(num).getGuestNm() == null){
			System.out.println("해당 객실에는 체크인 한 사람이 없습니다");
			return;
	
		}
		
		String name = hotelMap.get(num).getGuestNm();
		hotelMap.get(num).setGuestNm(null);
		
		dataChange =true;
		System.out.println(num  + "호 객실의" + name + "님 체크아웃 완료");
		
		
		
	}


	private void checkIn() {
		
		
		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println("체크인");
		System.out.println("---------------------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~309 : 스위트룸");
		System.out.println("------------------------------");
		System.out.println("방 번호를 입력하세요 >");
		int num = Integer.parseInt(sc.nextLine());
		
		// 입력한 방 번호가 Map에 없으면 잘못입력한 방번호이다
		
		if(!hotelMap.containsKey(num)){
			System.out.println(num + " 호 객실은 존재하지 않습니다");
			return;
		}else if(hotelMap.get(num).getGuestNm() != null){
			System.out.println("해당 객실에는 손님이 있습니다");
		}else{
			System.out.println("체크인 하실 분으 성합은?? > ");
			String name = sc.nextLine();
			
			hotelMap.get(num).setGuestNm(name);
			

			dataChange =true;
			System.out.println((name + " 씨가" + num + " 호 객실에 체크인 되었습니다"));
		}
	}


	//메뉴 출력 및 작업 번호를 입력 받아 반환하는 메서드
	private int displayMenu() {
		
		
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인     2.체크아웃    3. 객실상태   4. 객실정보 등록  5. 업무종료");
		System.out.println("-----------------------------------");
		System.out.println("선택 >");
		int num = Integer.parseInt(sc.nextLine());

		
		return num;
	}


	public static void main(String[] args) {
		DaeDeokHotel
	}
	
	
}

class Room implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int roomNo;
	private String roomType;
	private String guestNm;
	
	
	public Room(int roomNo, String roomType) {
	
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
