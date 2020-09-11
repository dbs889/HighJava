package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;


public class PhoneBookTest {

	private Map<String, Phone> phoneBook;
	private Scanner scan;
	private PdfMaker pm;
	private boolean isUpdated;
	
	
	private String PdfDirPath = "D:\\samples";
	private String PdfFileName = "phonebookList";
	
	
	public PhoneBookTest(){
		phoneBook = new HashMap<>();
		scan = new Scanner(System.in);
		
	}
	
	
	public static void main(String[] args) {
		
		new PhoneBookTest().run();
		
	}
	
	
	public void run(){
		
		init();
		
		while(true){
			
			printMenu();
			int num = Integer.parseInt(scan.next());
			
			switch (num) {
			case 1: add();   break;
			case 2: modify();break;
			case 3: delete();break;
			case 4: search();break;
			case 5: searchAll();break;
			case 6: save(); break;
			case 7: toPDF(); break;
			case 0: exit();break;

			default:
				System.out.println("입력이 잘못되었습니다.");
				break;
			}
		}
	}
	
	private void toPDF() {
		
		pm = new PdfMaker(phoneBook);
		try {
			File file = pm.createPdf(PdfDirPath, PdfFileName);
			pm.printPdf(file, phoneBook);
			System.out.println("\n" + PdfDirPath +"\n위 경로로 " + PdfFileName + ".pdf 파일의 출력이 완료되었습니다...");
			
		} catch (IOException e) {
		}
		
	}


	//전화번호 저장을 누르면 구현하기
	// 1 파일명은 'phoneData.dat'로 한다.
	// 2 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 셋팅한다.
	// 3 프로그램 종료할 때 map의 데이터가 변경되었거나 추가 또는 삭제되면 새로 저장한 후 종료되도록 한다.
	
	private void init() {
		
		File f = new File( PdfDirPath + "/phoneData.dat");
		if(!f.exists()){
			return;
		}
		
		try {
			FileInputStream fin = new FileInputStream(f);
			BufferedInputStream bin = new BufferedInputStream(fin);
			ObjectInputStream oin = new ObjectInputStream(bin);
			
			Object obj;
			
			while( (obj = oin.readObject())!=null){
				Phone hp = (Phone)obj;
				phoneBook.put(hp.getName(), hp);
			}
			
			oin.close();
		
		} catch (FileNotFoundException e) {
		}catch (Exception e) {
		}
	}


	private void save() {
		
		File f = new File("D:/samples/phoneData.dat");
		try {
			FileOutputStream fout = new FileOutputStream(f);
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			
			Set<String> key = phoneBook.keySet();
			
			System.out.println("전화번호 저장중");
			for(String k : key){
				oout.writeObject(phoneBook.get(k));
			}
			oout.close();
			
			isUpdated = false;
			System.out.println("전화번호 저장이 완료되었습니다.");
		} catch (Exception e) {
		}
	}


	private void printMenu(){
		
		System.out.println("\n다음 메뉴를 선택하세요.");
		System.out.println(" 1. 전화번호 등록");
		System.out.println(" 2. 전화번호 수정");
		System.out.println(" 3. 전화번호 삭제");
		System.out.println(" 4. 전화번호 검색");
		System.out.println(" 5. 전화번호 전체 출력");
		System.out.println(" 6. 전화번호 저장");
		System.out.println(" 7. PDF 출력");
		System.out.println(" 0. 프로그램 종료");
		System.out.println("---------------------------------");
	}
	
	
	private void add(){
		
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");

		System.out.print("이름 >>");
		String name = scan.next();
		
		
		if(phoneBook.containsKey(name)){
			System.out.println( name + "는(은) 이미 등록된 사람입니다.");
		}
		else{
			System.out.print("전화번호 >>");
			String hp = scan.next();
			
			System.out.print("나이 >>");
			int age = Integer.parseInt(scan.next());
			
			System.out.print("주소 >>");
			String addr = scan.next();
			
			phoneBook.put(name, new Phone(name,addr, age, hp));
			
			isUpdated = true;
			
			System.out.println( "'" + name +"'" + "전화번호 등록 완료!!");
		}
	}


	private void modify(){
		
		System.out.println("수정할 고객의 이름을 입력하세요.");
		System.out.print("이름 >>");
		String name = scan.next();
		
		if(!phoneBook.containsKey(name)){
			System.out.println( name + "는(은) 없는 고객입니다.");
		}
		else{
			System.out.print("전화번호 >>");
			String hp = scan.next();
			
			System.out.print("나이 >>");
			int age = Integer.parseInt(scan.next());
			
			System.out.print("주소 >>");
			String addr = scan.next();
			
			phoneBook.put(name, new Phone(name,addr, age, hp));
			
			isUpdated = true;
			
			System.out.println( "'" + name +"'" + "님의 전화번호 수정 완료!!");
		}
	}


	private void delete(){
		
		System.out.println("삭제할 고객의 이름을 입력하세요.");
		System.out.print("이름 >>");
		String name = scan.next();
		
		if(!phoneBook.containsKey(name)){
			System.out.println( name + "는(은) 없는 고객입니다.");
		}
		else{
			phoneBook.remove(name);
			isUpdated = true;
			System.out.println( "'" + name +"'" + "님의 전화번호 삭제 완료!!");
		}
		
	}
	

	private void search(){
		System.out.println("조회할 고객의 이름을 입력하세요.");
		System.out.print("이름 >>");
		String name = scan.next();
		
		if(!phoneBook.containsKey(name)){
			System.out.println( name + "는(은) 없는 고객입니다.");
		}
		else{
			
			System.out.println("이름 : \t" + name);
			System.out.println("나이 : \t" + phoneBook.get(name).getAge());
			System.out.println("번호 : \t" + phoneBook.get(name).getHp());
			System.out.println("주소 : \t" + phoneBook.get(name).getAddr());
			System.out.println("-----------------------------------------");
		}
		
	}
	
	
	private void searchAll(){
		
		
		if(!phoneBook.isEmpty()){
			System.out.println("=============================================");
			System.out.println("번호\t이름\t전화번호\t나이\t주소");
			System.out.println("=============================================");
			
			int count = 1;
			for(String key : phoneBook.keySet()){
				
				System.out.print((count++) + "\t" + key + "\t" + phoneBook.get(key).getHp() 
						                            + "\t" + phoneBook.get(key).getAge()
						                            + "\t" + phoneBook.get(key).getAddr());
				
				System.out.println();
			}
			System.out.println("\n=============================================");
			System.out.println("출력완료...");
		}
		else{
			System.out.println("전화번호부에 출력할 고객의 정보가 없습니다.");
		}
	}
	
	private void exit(){

		if(isUpdated) save();
		
		System.out.println("프로그램을 종료합니다...");
		System.exit(0);
	}
	
}


//pdfbox
class PdfMaker{
	
	
	Map<String, Phone> phoneBook;
	FileInputStream mainFont;
	
	public PdfMaker(Map<String, Phone> phoneBook) {
		this.phoneBook = phoneBook;
		try {
			mainFont = new FileInputStream("c:/windows/fonts/malgunbd.ttf");
		} catch (FileNotFoundException e) {
		}
	}
	
	
	public File createPdf(String dirPath, String fileName) throws IOException{
		
		File dir = new File(dirPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		if(!dir.isDirectory()){
			System.out.println("입력이 잘못되었습니다. 화면으로 돌아갑니다.");
			return null;
		}
		
		fileName = URLEncoder.encode(fileName, "UTF-8");
		
		//pdf 생성
		
		File pdfFile = new File(dirPath + File.separator + fileName + ".pdf");
		if(pdfFile.exists()){
			pdfFile.delete();
		}
		
		//pdf
		PDDocument doc = new PDDocument();
		doc.save(pdfFile);
		doc.close();
		
		return pdfFile;
	}
	
	
	public void printPdf(File file, Map<String, Phone> phoneBook) throws IOException{
		
		PDDocument doc = new PDDocument();
		
		doc.addPage(new PDPage(PDRectangle.A4));
		
		PDPageContentStream cs = new PDPageContentStream(doc, doc.getPage(0), PDPageContentStream.AppendMode.APPEND, false);

		drawTable(doc, cs);
		
		doc.save(file);
		doc.close();
	}
	
	
	
	
	private void drawTable(PDDocument doc, PDPageContentStream cs){
		
		int fontSize = 12;
		
		float startX = PDRectangle.A4.getWidth()/8;
		float startY = PDRectangle.A4.getHeight()/8 * 7;
		
		float w[] = { 0, 75, 75, 150, 75, 75 };	//테이블 가로 간격 
		float h = 25;							//테이블 세로 간격 
		float padding = (h - fontSize) / 2; 	//테이블 내용 여백
		
		float len = h * (phoneBook.size() + 1); //row 개수
		float temp = startX;
		
		List<String> key = new ArrayList<>(phoneBook.keySet());
		Collections.sort(key);
		
		for (int i = 0; i < 6; i++) {	//테이블 세로줄 그리기
			temp = temp + w[i];
			drawLine(cs, temp, startY, temp, startY - len);
		}
		
		for (int i = 0; i < phoneBook.size() + 2; i++) {	//테이블 가로줄 그리기
			drawLine(cs, startX, startY - h * i, temp, startY - h * i);
		}
		
		try {
			cs.beginText();
			PDType0Font fontmalgun = PDType0Font.load(doc, mainFont);
			cs.setFont(fontmalgun, fontSize); 
			
			cs.newLineAtOffset(startX + padding, startY - h + padding);	//텍스트 시작 위치설정
			temp = startX;
			
			for (int j = 0; j < 5; j++){	//처음 인덱스는 속성명
				temp = temp + w[j];
				cs.newLineAtOffset(w[j], 0);
				switch (j) {
					case 0: cs.showText("번호");break;
					case 1: cs.showText("이름");break;
					case 2: cs.showText("핸드폰번호");break;
					case 3: cs.showText("나이");break;
					case 4: cs.showText("주소");break;
				}
			}
			
			cs.newLineAtOffset(startX-temp, 0);
			
			for (int i = 0; i < phoneBook.size(); i++) {	//이후 인덱스는 전화번호부 회원정보 값
				temp = startX;
				cs.newLineAtOffset(0, -h);
				for (int j = 0; j < 5; j++) {
					temp = temp + w[j];
					cs.newLineAtOffset(w[j], 0);
					switch (j) {
						case 0: cs.showText(String.valueOf(i+1));break;
						case 1: cs.showText(String.valueOf(phoneBook.get(key.get(i)).getName()));break;
						case 2: cs.showText(String.valueOf(phoneBook.get(key.get(i)).getHp()));break;
						case 3: cs.showText(String.valueOf(phoneBook.get(key.get(i)).getAge()));break;
						case 4: cs.showText(String.valueOf(phoneBook.get(key.get(i)).getAddr()));break;
					}
				}
				cs.newLineAtOffset(startX-temp, 0);
			}
			cs.endText();
			cs.close();

		} catch (IOException e) {
		}
		
	}
	
	private void drawLine(PDPageContentStream cs , float x1, float y1, float x2, float y2){
		try {
			cs.moveTo(x1, y1);
			cs.lineTo(x2, y2);
			cs.stroke();
		} catch (IOException e) {
		}
		
	}
	
	
}



class Phone implements Serializable{
	
	private static final long serialVersionUID = 5659742456495887072L;
	
	private String addr;
	private int age;
	private String hp;
	private String name;
	
	public Phone(String name, String addr, int age, String hp) {
		this.name = name;
		this.addr = addr;
		this.age = age;
		this.hp = hp;
	}
	
	public String getAddr() {
		return addr;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	public String getHp() {
		return hp;
	}
	
}