﻿package kr.or.ddit.basic02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PhoneBySem {
	
	
	//1.
	private Map<String, PhoneSem> pbm;
	private Scanner sc;
//	static String fname = "d:/d_other/phoneData.dat";


	
	
	
	public PhoneBySem(){
		pbm = new HashMap<String, PhoneSem>();
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
		load();
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
			case 6: // 전화번호 저장
				pbFile();
				break;
			case 0:
				// 프로그램 종료
				System.out.println("프로그램을 종료합니다");
				return;
			default:
				System.out.println("번호를 잘못입력했습니다. 다시 입력하세요");

			}

		}

	}
	
	private void load(){
		ObjectInputStream oin = null;
		File f = new File("d:/d_other/phone_data.dat");
		if(!f.exists()){
			return;
		}else{
			try {
				oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
				Object obj;
				
				while((obj = oin.readObject())!= null) {
					PhoneSem out =  (PhoneSem) obj;
				
					
					pbm.put(out.getName(), out);
					
					
				}	
			} catch (IOException e) {
				// TODO: handle exception
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
		
	private void pbFile() {
		
		try {
			File f = new File("d:/d_other/phone_data.dat");
			FileOutputStream fout = new FileOutputStream(f);
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);

			for (String key : pbm.keySet()) {
				oout.writeObject(pbm.get(key));
			}

			System.out.println("전화번호 저장 작업 완료 ");

			oout.close();
		} catch (IOException e) {
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
		System.out.println("  전화번호 : " + p.getTel());
		System.out.println("  나이 : " + p.getAge());
		System.out.println("  주소 : " + p.getAddr());
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
		
		
		
		

	}

	//6. 새로운 전화번호를 등록하는 메서드
	private void insert() {
		
		
	
		
		
			
			System.out.println();
			System.out.println("새롭게 등록할 전화번호 정보를 입력하세요");
			
			System.out.println("이름 > ");
			String name = sc.next();
			
			
			if(pbm.containsKey(name) == true){
				System.out.println(name + " 씨는 이미 등록된 사람입니다");
				return;
				
			}
			
			System.out.println("전화번호 > ");
			String tel = sc.next();
			
			System.out.println("나이 > ");
			int  age = sc.nextInt();
			
			System.out.println("주소 > ");
			String addr = sc.nextLine();
			
			
			pbm.put(name, new PhoneSem(name,tel,age,addr));
			
		
			
			System.out.println(name + " 씨의 전화번호가 등록 완료 되었습니다");
			
			
		
		
		
		
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
		new PhoneBySem().phoneStart();
		
		

	}

}

//2.
class PhoneSem implements Serializable{
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2996844346924968900L;
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