package kr.or.ddit.mvc.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.mvc.service.BoardServiceImpl;
import kr.or.ddit.mvc.service.IBoardService;
import kr.or.ddit.mvc.vo.BoardVO;

public class BoardController {
	
		private IBoardService service;
		private Scanner sc;
		
		//생성자
		public BoardController(){
			service = new BoardServiceImpl();
			sc = new Scanner(System.in);
		
		}
		
		public static void main(String[] args) {
			new BoardController().start();
		}

		private void start() {
			
			
			while(true){
				
				int choice = displayMenu();
				switch(choice){
				case 1: //새글작성
					insertBoard();
					break;
				case 2://게시글보기
					readBoard();
					break;
				case 3://검색
					searchBoard();
					break;
				case 0://작업끝
					System.out.println("게시판을 종료합니다");
					return;
				default : 
					System.out.println("작업 번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요.");
				
				
				}
				
			}
			
		}
		private void searchBoard() {
		
			
			System.out.println();
			System.out.println("검색 작업");
			System.out.println("--------------------------------------------");
			System.out.println("- 검색할 제목 입력 : ");
			String title = sc.nextLine();
			
			
			System.out.println();
			System.out.println("-------------------------------------------------------------");
			System.out.println(" No	        제 목            작성자 	                조회수   ");
			System.out.println("-------------------------------------------------------------");
			
		
			
			if(title != null){ //제목을 입력하면 (엔터가 아니면)
			
				
				List<BoardVO> bList = service.selectBoard(title);
				
				if(bList == null || bList.size() ==0){
					System.out.println("동일한 제목의 게시글이 존재하지 않습니다");
				}else{
					for(BoardVO boardVo : bList){
						System.out.print(boardVo.getBoard_no() + "\t");
						System.out.print(boardVo.getBoard_title() + "\t");
						System.out.print(boardVo.getBoard_writer() + "\t");
						System.out.println(boardVo.getBoard_cnt() + "\t");
						
					}
				}
			
			}else{
				
				List<BoardVO> bList = service.ListAll();
				
				for(BoardVO boardVo : bList){
					System.out.print(boardVo.getBoard_no() + "\t");
					System.out.print(boardVo.getBoard_title()+ "\t");
					System.out.print(boardVo.getBoard_writer() + "\t");
					System.out.print(boardVo.getBoard_cnt() + "\t");
					
				}
			}
			
			System.out.println("-------------------------------------------------------------");
			
			System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
			System.out.print(" 작업 선택 >> ");
			
			int num = Integer.parseInt(sc.nextLine());
			switch(num){
			case 1:
				insertBoard();
				break;
			case 2:
				readBoard();
				break;
			case 3:
				searchBoard();
				break;
			case 0:
				System.out.println("작업이 끝났습니다");
				System.exit(0);
				
			
			}
			
			
		}

		private void readBoard() {
			System.out.println();
			System.out.println("보기를 원하는 게시물 번호 입력 >> ");
			int b_no = Integer.parseInt(sc.nextLine());
			
			BoardVO boardVO = service.readBoard(b_no);
			service.plusCnt(b_no);
			if(b_no == boardVO.getBoard_no()){
				System.out.println();
				System.out.println(b_no + "번글 내용");
				System.out.println("------------------------------------------------------------");
				System.out.println("- 제  목 : " + boardVO.getBoard_title());
				System.out.println("- 작성자 : " + boardVO.getBoard_writer());
				System.out.println("- 내  용 : " + boardVO.getBoard_content());
				System.out.println("- 작성일 : " + boardVO.getBoard_date());
				System.out.println("- 조회수 : "+ (int)(boardVO.getBoard_cnt()+1));
				
				System.out.println("------------------------------------------------------------");
				
				System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
				System.out.print(" 작업 선택 >> ");
				
				int num = Integer.parseInt(sc.nextLine());
				switch(num){
				case 1:
					updateBoard(b_no);
					break;
				case 2:
					deleteBoard(b_no);
					break;
				case 3:
					displayMenu();
					break;
					
					
				}
				
			}else{
				System.out.println("해당게시물이 없습니다");
				return;
				
			}
			
			
			
		}
		
		//삭제하는 메서드
		private void deleteBoard(int b_no) {

	
			int cnt = service.deleteBoard(b_no); // Service의 삭제용 메서드 호출
			
			if (cnt > 0) {
				System.out.println(b_no + "번글이 삭제되었습니다.");
			} else {
				System.out.println(b_no + "번글이 삭제 실패!!!.");
			}
					
				}

//수정하는 메서드
		private void updateBoard(int b_no) {
		
			System.out.println();
			System.out.println("수정 작업하기");
			System.out.println("-----------------------------------");
			System.out.print("- 제  목 : ");
			String title = sc.next();
			System.out.print("- 내  용 : ");
			sc.nextLine();
			String content = sc.nextLine();
			
			BoardVO boardVo = new BoardVO();
			boardVo.setBoard_title(title);
			boardVo.setBoard_content(content);
			boardVo.setBoard_no(b_no);
			
			int cnt = service.updateBoard(boardVo);
			
			if(cnt>0){
				System.out.println( b_no + "번글이 수정되었습니다.");
			}else{
				System.out.println("게시글 수정 작업 실패~~~");
			}
			
			
			System.out.println("");
			
		}

	//추가메서드
	private void insertBoard() {

		System.out.println();
		System.out.println("게시글 등록하기");
		int cnt = 0;

		System.out.println("-----------------------------------");
		System.out.println("- 제  목 : ");
		String title = sc.next();
		System.out.println("- 작성자 : ");
		String writer = sc.next();

		sc.nextLine(); // 입력 버퍼 비우기
		System.out.println("- 내 용 : ");
		String content = sc.nextLine();

		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);

		int count = service.insertBoard(boardVo);

		if (count > 0) {
			System.out.println("새글이 추가되었습니다");

		} else {
			System.out.println("새글 등록 실패!!");
		}

	}

		//메뉴메서드
		private int displayMenu() {
			
			
			List<BoardVO> bList = service.ListAll();
			
			System.out.println();
			System.out.println("-------------------------------------------------------------");
			System.out.println("No	        제 목            작성자 	               조회수   ");
			System.out.println("-------------------------------------------------------------");
			
			if(bList == null || bList.size() ==0){
				System.out.println("등록된 게시글이 하나도 없습니다");
			}else{
				for(BoardVO boardVo : bList){
					System.out.print(boardVo.getBoard_no() + "\t");
					System.out.print(boardVo.getBoard_title()+ "\t");
					System.out.print(boardVo.getBoard_writer() + "\t");
					System.out.println(boardVo.getBoard_cnt() + "\t");
					
				}
				
			}
			
			
			System.out.println("-------------------------------------------------------------");
			
			System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
			System.out.print(" 작업 선택 >> ");
			int num = Integer.parseInt(sc.nextLine());
			return num;
		}

}
