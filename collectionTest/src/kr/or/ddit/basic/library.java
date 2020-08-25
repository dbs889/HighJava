package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class library {

	private Map<Integer, Book>  bkMap;
	private static Scanner sc;
	
	public library(){
		
		bkMap = new HashMap<Integer, Book>();
		sc = new Scanner(System.in);
	} //library생성자
	
	
	
	public static void main(String[] args) {
			library lb = new library();
			
			
			while(true){
				System.out.println("사용할 메뉴를 선택하세요");
				System.out.println("1. 도서 정보 등록");
				System.out.println("2. 도서 정보 수정");
				System.out.println("3. 도서 정보 삭제");
				System.out.println("4. 도서 정보 검색");
				System.out.println("5. 전체 도서 정보 출력");
				System.out.println("6. 도서 반납 및 대여(x)");
				System.out.println("0. 프로그램 종료");
				System.out.println("번호 입력 > ");
				int input = sc.nextInt();
				
				switch(input){
				
				case 1: 
					lb.insertBk();
					break;
				case 2: 
					
					lb.updateBk();
					break;
				case 3: 
					lb.deleteBk();
					break;
				case 4: 
					lb.searchBk();
					break;
				case 5: 
					lb.displayBk();
					break;
				case 6: 
					
					lb.borrowBk();
					break;
					
					
				case 0: 
					
					System.out.println("프로그램을 종료합니다");
					System.exit(0);
					
			
				
				
				
				
				}//스위치
				
				
				
			}//while
			
			
			
			
	} //main메서드


	private void borrowBk() {
		
		
		System.out.println(" 1.도서 대출 2.도서 반납");
		int input = sc.nextInt();
		
		
		ArrayList<Integer> list = new ArrayList<>(bkMap.keySet());
		Collections.sort(list);
		
		switch(input){
		
		
		case 1 :
			System.out.println("[대출가능도서목록]");
			System.out.println("───────────────────────────────────");
			System.out.println("도서번호\t제목\t지은이\t장르\t대출가능여부");
			System.out.println("───────────────────────────────────");
			
			//리스트에있는 도서번호 하나씩을 꺼내와 변수에 저장한다음 book 객체를 통해 구한다
			for(int key : list){
				Book b = bkMap.get(key);
				if(b.isYn() == true){
					
					System.out.println(b.getBookNm() + "\t" + b.getTitle() + "\t" 
							+ b.getWriter() + "\t" + b.getBookct() + "\t" + (b.isYn() ==true ?"가능" :"불가능"));
				}
		
			}
			
			
			System.out.println();
			System.out.println("대출할 도서번호를 입력하세요");
			
			int bkNum = sc.nextInt();
			
			
			if(!bkMap.containsKey(bkNum)){
				System.out.println("번호가 조회되지 않습니다 돌아가세요");
				
			}else{
				if(bkMap.get(bkNum).isYn() == true){
					System.out.println("대출성공!!");
					bkMap.get(bkNum).setYn(false);
				}else{
					System.out.println("이미 대출되었습니다");
					
				}
	
			}
			break;
			
		case 2 :
			System.out.println("[반납가능도서목록]");
			System.out.println("───────────────────────────────────");
			System.out.println("도서번호\t제목\t지은이\t장르\t대출가능여부");
			System.out.println("───────────────────────────────────");
		
			for(int key : list){
				Book b = bkMap.get(key);
				if(b.isYn() == false){
					
					System.out.println(b.getBookNm() + "\t" + b.getTitle() + "\t" 
							+ b.getWriter() + "\t" + b.getBookct() + "\t" + (b.isYn() ==true ?"가능" :"불가능"));
				}
				
			}
			
			System.out.println();
			System.out.println("반납할 도서번호를 입력하세요");
			
			int bkNum1 = sc.nextInt();
			
			
			if(!bkMap.containsKey(bkNum1)){
				System.out.println("번호가 조회되지 않습니다 돌아가세요");
				
			}else{
				if(bkMap.get(bkNum1).isYn() == false){
					System.out.println("반납성공!!");
					bkMap.get(bkNum1).setYn(true);
				}else{
					System.out.println("반납필요없음");
				}
				
			}
			break;
		}
		
	}



	private void displayBk() {
		
		
		System.out.println("───────────────────────────────────");
		System.out.println("도서번호\t제목\t지은이\t장르\t대출가능여부");
		System.out.println("───────────────────────────────────");
		
		ArrayList<Integer> list = new ArrayList<>(bkMap.keySet());
		Collections.sort(list);
		
		//리스트에있는 도서번호 하나씩을 꺼내와 변수에 저장한다음 book 객체를 통해 구한다
		for(int key : list){
			Book b = bkMap.get(key);
			System.out.println(b.getBookNm() + "\t" + b.getTitle() + "\t" 
			+ b.getWriter() + "\t" + b.getBookct() + "\t" + (b.isYn() ==true ?"가능" :"불가능"));
		}
		
		
		
		
	}



	private void searchBk() {
		
		
		System.out.println("조회할 책 번호를 입력하세요");
		int scNum = sc.nextInt();
		
		System.out.println("────────────────────────────────");
		System.out.println("책 번호 : " + bkMap.get(scNum).getBookNm());
		System.out.println("책 제목 : " + bkMap.get(scNum).getTitle());
		System.out.println("책 글쓴이 : " + bkMap.get(scNum).getWriter());
		System.out.println("책 장르 : " + bkMap.get(scNum).getBookct());
		System.out.println("대출 가능여부 : " + (bkMap.get(scNum).isYn() ==true? "가능" : "불가능"));
		
		System.out.println("────────────────────────────────");
	}



	private void deleteBk() {
	
		System.out.println("삭제할 책 번호를 입력하세요");
		
		System.out.println("도서번호 >");
		int bkNum = Integer.parseInt(sc.nextLine());
		
		
		if(!bkMap.containsKey(bkNum)){
			System.out.println(" 존재하는 도서번호가 없습니다 ");
			return;
		}else{
			
			if(bkMap.containsKey(bkNum) == true){
				
				bkMap.remove(bkNum);
				System.out.println("삭제되었습니다");
			}else{
				System.out.println("조회하는 번호가 일치하지 않습니다 삭제실패입니다");
				return;
			}
			
			
		}
		
		
		
		
		
	}



	private void updateBk() {
		//수정하는 메서드
		
		System.out.println();
		System.out.println("새롭게 수정할 책 정보를 입력하세요");
		
		System.out.println("도서번호 >");
		int bkNum = Integer.parseInt(sc.nextLine());
		if(!bkMap.containsKey(bkNum)){
			System.out.println(" 존재하는 도서번호가 없습니다 ");
			return;
		}
		
		System.out.println("제목");
		String bktitle = sc.nextLine();
		
		
		System.out.println("지은이");
		String bkwriter = sc.next();
		
		
		System.out.println("장르");
		String bkcat = sc.nextLine();

		
		bkMap.put(bkNum, new Book(bkNum, bktitle, bkwriter, bkcat));
		bkMap.get(bkNum).setYn(true);
		
		System.out.println(bktitle +"의 책이 등록되었습니다");
		
	}



	private void insertBk() {
		//등록하는 메서드
		
		System.out.println();
		System.out.println("새롭게 등록할 책 정보를 입력하세요");
		System.out.println("도서번호 >");
		int bkNum =  sc.nextInt();
		
		if(bkMap.containsKey(bkNum) == true){
			System.out.println("이미 존재하는 도서번호가 있습니다");
			return;
		}
		
		sc.nextLine();
		System.out.println("제목");
		String bktitle = sc.nextLine();
		
		
		//nextLine : 문장의 띄어쓰기 있을 때 필요한다 단, 위에서 생성해줘야 한다
		//next : 띄어쓰기가 없는 단어를 입력할 때 사용한다 
		
		System.out.println("지은이");
		String bkwriter = sc.nextLine();
		
		
		System.out.println("장르");
		String bkcat = sc.nextLine();
		
//		System.out.println("대출가능여부");
//		String yn = sc.next();
		
		bkMap.put(bkNum, new Book(bkNum, bktitle, bkwriter, bkcat));
		bkMap.get(bkNum).setYn(true);
		
		System.out.println(bktitle +"의 책이 등록되었습니다");
		
	}

}



class Book{
	private int bookNm;	//책번호
	private String  title;		//제목
	private String  writer; 	//지은이
	private String  bookct;  	//장르
	private boolean yn;		//대출 가능여부
	
	
	public Book(int bookNm, String title, String writer, String bookct) {
		super();
		this.bookNm = bookNm;
		this.title = title;
		this.writer = writer;
		this.bookct = bookct;
		this.yn = yn;
	}


	public int getBookNm() {
		return bookNm;
	}


	public void setBookNm(int bookNm) {
		this.bookNm = bookNm;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getBookct() {
		return bookct;
	}


	public void setBookct(String bookct) {
		this.bookct = bookct;
	}


	public boolean isYn() {
		return yn;
	}


	public void setYn(boolean yn) {
		this.yn = yn;
	}


	@Override
	public String toString() {
		return "Book [bookNm=" + bookNm + ", title=" + title + ", writer="
				+ writer + ", bookct=" + bookct + ", yn=" + yn + "]";
	}
	
	
	
	
}