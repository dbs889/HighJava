package kr.or.ddit.basic03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class PhoneBySemIO {
	
	
	//1.
	private Map<String, PhoneSem> pbm;
	private Scanner sc;
	private String fileName = "phoneData.dat";  //사용할 파일을 저장할 장소
	private boolean dataChange = false;	//데이터가 변경되었는지 여부를 나타내는 변수
										//데이터가 변경되면 true가 된다
	
	/*
	 * next(), nextInt(), nextDouble(),.....
	 * 	=> 사이 띄기, tab키, Enter키를 구분 문자로 분리해서 분리된 자료만 읽어간다 
	 * 
	 * nextLine() ==>한 줄 단위로 입력한다 즉 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어간다 
	 * 
	 * Scanner는 사용자가 입력한 정보를 입력버 퍼에 저장된 후 입력버퍼에서 차례로 꺼내와 처리한다
	 * 
	 * 1) 6. 전화번호 저장 메서드를 구현
	 * 2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 셋팅
	 * 3)프로그램 종료시 Map의 데이터가 변경되거나 추가 또는 삭제 되면 새로 저장 후 종료된다
	 * 
	 * 
	 * */
	
	
	
	public PhoneBySemIO(){
//		pbm = new HashMap<String, PhoneSem>();
		
		
		
		pbm = load();//파일 내용을 읽어와 Map에 셋팅한다
		if(pbm == null){ //파일이 없거나 잘못되었을때-
			pbm = new HashMap<>();
		}
		
		sc = new Scanner(System.in);
	
		
		
	}
	//5.
	private int displayMenu(){
		
		System.out.println("다음 메뉴를 선택하세요");
		
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("6. 전화번호 저장");
		
		System.out.println("0. 전화번호 종료");
		
		
		System.out.println("===================================");
		System.out.println("번호입력 > ");
		
		int num = sc.nextInt();
		return num;
		
		
	}
	//4.
	private void phoneStart() {

		System.out.println("===================================");
		System.out.println("    ☎  전   화   번   호  관  리  프  로  그  램   ☎");
		System.out.println("===================================");
		System.out.println();

		while (true) {
			int choice = displayMenu(); // 메뉴 출력 및 번호 선택

			switch (choice) {

			case 1: // 등록
				insert();
				break;
			case 2: // 수정
				update();
				break;
			case 3: // 삭제
				delete();
				break;
			case 4: // 검색
				search();
				break;
			case 5: // 전체출력
				displayAll();
				break;
			case 6: // 전체출력
				save();
				break;
			case 0:
				// 프로그램 종료
				
				if(dataChange == true){//데이터가 변경되었으면
					
					System.out.println("변경된 내용을 저장한다");
					save();
					
				}
				System.out.println("프로그램을 종료합니다");
				return;
			default:
				System.out.println("번호를 잘못입력했습니다. 다시 입력하세요");

			}

		}

	}
	//전화번호 정보가 저장된 파일을 읽어 오는 메서드
	
	private Map<String, PhoneSem> load() {
		HashMap<String, PhoneSem> pMap = null; // 읽어온 Map객체가 저장될 변수 선언

		File file = new File("d:/d_other/" + fileName);

		if (!file.exists()) {// 저장된 파일이 없으면
//			pMap = new HashMap<>();// Map객체를 새로 생성한다
//			return pMap;
			
			return null;
		}

		// 저장된 파일이 있을 때 처리되는 곳...
		ObjectInputStream ois = null; // 읽어올 스트림 객체 변수 선언

		try {

			ois = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(file)));

			// 파일 내용을 읽어 와 Map객체 변수에 저장한다

			pMap = (HashMap<String, PhoneSem>) ois.readObject();

		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		} finally {
			try {

				// 사용한 스트림 닫기
				ois.close();

			} catch (IOException e) {
			}
		}
		return pMap;

	}
	
	
	// 전화번호 정보를 파일로 저장하는 메서드
	private void save() {
		
		ObjectOutputStream oos = null; //객체를 저장할 스트림 객체 변수 선언
		
		
		
		try {
			//객체 저장용 스트림 객체 생성
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/d_other/" + fileName)));
			
			//Map 객체를 파일로 저장한다
			oos.writeObject(pbm);
			
			System.out.println("저장 완료");
			
			dataChange = false; 
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
			//사용했던 스트림 객체 닫기
			try {
				oos.close();} catch (IOException e) {}
		}
		
		
	}
	//10.
	private void search() {
		
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요");
		
		System.out.println("이름 > ");
		String name = sc.next();

		// 입력한 사람이 등록되어 있지 않으면 수정작업을 진행할 수 없다
		if (!pbm.containsKey(name)) {
			System.out.println(name + " 씨의 전화번호 정보가 없습니다");
			return;

		}
		
		//해장 전화번호 정보가 있으면 그 정보를 구해온다
		PhoneSem p = pbm.get(name);
		System.out.println(name + "씨의 전호번호 정보");
		System.out.println("======================================");
		System.out.println("  이름 : " + p.getName());
		System.out.println("  이름 : " + p.getTel());
		System.out.println("  이름 : " + p.getAge());
		System.out.println("  이름 : " + p.getAddr());
	}

	//8.전화번호를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요");

		System.out.println("이름 > ");
		String name = sc.next();

		// 입력한 사람이 등록되어 있지 않으면 수정작업을 진행할 수 없다
		if (!pbm.containsKey(name)) {
			System.out.println(name + " 씨의 전화번호 정보가 없습니다");
			return;

		}

		System.out.println("새로운 전화번호 >");
		String tel = sc.next();

		System.out.println("새로운 나이 >");
		int age = sc.nextInt();

		System.out.println("새로운 주소 >");
		String addr = sc.nextLine();
		
		//같은 키값에 새로운 정보를 저장한다
		pbm.put(name, new PhoneSem(name,tel,age,addr));
		System.out.println(name + " 씨의 전화번호 정보를 변경하였습니다");
		dataChange = true;

	}
	//9. 
	private void delete() {
		
		
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요");
		System.out.println("이름을 입력하세요 > ");
		
		String name = sc.next();
		
		if(!pbm.containsKey(name)){
			System.out.println(name + "  씨의 전화번호 정보는 없습니다");
			return;	
		}
		//자료삭제
		pbm.remove(name);
		System.out.println(name + " 씨의 전화번호 정보를 삭제했습니다");
		
		
		dataChange = true;
		

	}

	//6. 새로운 전화번호를 등록하는 메서드
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요");
		
		System.out.println("이름 > ");
		String name = sc.next();
		
		//이미 등록되어있는 이름인지 검사
		
		if(pbm.containsKey(name) == true){
			System.out.println(name + " 씨는 이미 등록된 사람입니다");
			return;
			
		}
		
		System.out.println("전화번호 > ");
		String tel = sc.next();
		
		System.out.println("나이 > ");
		int  age = sc.nextInt();
		sc.nextLine();
		System.out.println("주소 > ");
		String addr = sc.nextLine();
	
		
		pbm.put(name, new PhoneSem(name,tel,age,addr));
		System.out.println(name + " 씨의 전화번호가 등록 완료 되었습니다");
		dataChange =true;
		
	}
	
	//7. 전화번호 정보 전체 출력하는 메서드
	private void displayAll() {

		System.out.println("================================================");
		System.out.println("번호\t이름\t전화번호\t나이\t주소");
		System.out.println("===============================================");

		if (pbm.size() == 0) {
			System.out.println(" 등록된 전화번호 정보가 없습니다");
		} else {
			int cnt = 0; // 번호를 출력

			Iterator<String> keyIt = pbm.keySet().iterator();

			while (keyIt.hasNext()) { // key값 구하기

				cnt++;
				String key = keyIt.next();
				PhoneSem p = pbm.get(key); //key값에 해당되는 value값 구학기
				System.out.println(cnt + "\t" + p.getName() + "\t" + p.getTel()
						+ "\t" + p.getAge() + "\t" + p.getAddr());

			}
		}

		System.out.println("=======================================");

	}

	//3.
	public static void main(String[] args) {
		new PhoneBySemIO().phoneStart();

	}

}

//2.
class PhoneSem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -179492210988822063L;
	private String name;
	private String addr;
	private int age;
	private String tel;
	
	
	
	
	public PhoneSem(String name, String tel, int age, String addr) {
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