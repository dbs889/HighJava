package kr.or.ddit.basic;

import java.io.Serializable;
import java.util.Map;
import java.util.Scanner;

public class HotelUneTest {
	
	
	//객실정보를 담을 Map
	Map<Integer, UneRoom> hmap;
	Scanner sc;
	
	

	public HotelUneTest() {

		sc = new Scanner(System.in);
		
		hmap = load();
		
		for(int i = 2; i<= 4; i++){
			
			int RoomNo;
			String RoomNM;
			
			for(int j=0; j<=9; i++){
				
				RoomNo = i *100 +j;
				if(i==2){
					RoomNM = "싱글룸";
					
				}else if(i==3){
					RoomNM = "더블룸";
					
				}else{
					RoomNM = "스위트룸";
				
				}//elseif
			
			
			}
			UneRoom room = new UneRoom(RoomNo,RoomNM);
			
			hmap.put(RoomNo, room);
		}//for
		
		
	}
	

	public static void main(String[] args) {	new HotelUneTest().startHotel(); }


	private void startHotel() {
			System.out.println("호텔문이 열렸습니다");
		
		System.out.println("");

		while(true){
			
			
			
			
			System.out.println("원하는 작업을 선택하세요");
			System.out.println("1.체크인 2. 체크아웃 3. 객실상태보기 4. 종료");
			int choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				checkRoom();
				break;
			case 4:
				System.out.println("호텔문을 닫습니다");
				return;

			default:
				System.out.println("잘못 누르셨습니다");
				break;
			}
			
			
		}
		
	}

}







class UneRoom implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int roomNo;
	private String roomNM;
	private String customer;
	
	
	
	
	public UneRoom(int roomNo, String roomNM) {
		super();
		this.roomNo = roomNo;
		this.roomNM = roomNM;
		this.customer = customer;
	}
	
	
	public void startHotel() {
		// TODO Auto-generated method stub
		
	}


	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomNM() {
		return roomNM;
	}
	public void setRoomNM(String roomNM) {
		this.roomNM = roomNM;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	
	
	
	
}