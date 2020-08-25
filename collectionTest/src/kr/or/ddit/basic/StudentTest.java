package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


 	//문제 ) 학번 이름 국어점수 영어점수 수학점수 총점 등수를 멤버로 갖는 Student 클래스를 만든다
	/*
	 * 이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화를 처리한다
	 * 
	 * 이 student 객체는 Lisdt에 저장하여 관리한다
	 * 
	 * List에 저장된 데이터들을 학번의 오름차순 정렬이 될 수 있는 내부 정렬 기준을 구현하고, 
	 * 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스를 작성하여 정렬된 결과를 출력
	 * 
	 * (등수는 List에 전체 데이터가 추가된 후에 저장 되도록 한다)
	 */
public class StudentTest {
	
	//등수를 구하는 메서드
	public void setRanking(List<Student> stuList){
		for(Student std1 : stuList){ //등수를 구할 반복문
			int rank =1; //처음에는 1등으로 설정해 놓고 시작한다
			for(Student std2 : stuList){ //비교대상을 나타내기 위한 반복문
				
				
				//등수를 구할 데이터보다 큰 값을 만나면 rank를 증가시킨다
				if(std1.getSumGrade() <std2.getSumGrade()){
					rank++;
					
					
				}
			}
			//구해진 등수를 Student 객체의 rank변수에 저장한다
			std1.setRank(rank);
			
			
		}
	}

	public static void main(String[] args) {
		
		
		StudentTest stdTest = new StudentTest();
		
		ArrayList<Student> stuList = new ArrayList<>();
		stuList.add(new Student(201510080,"a",80,40,20));
		stuList.add(new Student(201510028,"ab",80,30,50));
		stuList.add(new Student(201410081,"abc",100,60,40));
		stuList.add(new Student(201410053,"d",80,100,80));
		stuList.add(new Student(201910032,"ea",40,90,95));
		stuList.add(new Student(202010082,"eaf",50,50,60));
		stuList.add(new Student(202010043,"g",80,95,85));
		
		System.out.println("정렬 전..");
		for(Student stu : stuList){
			System.out.println(stu);
		}
		System.out.println("------------------------------");
		Collections.sort(stuList);
		System.out.println("정렬 후...");
		for(Student stu : stuList){
			System.out.println(stu);
		}
		
		
		
		for(int i = 0; i< stuList.size(); i++){
			int count =1;
			for (int j = 0; j <stuList.size(); j++) {
				if(stuList.get(i).getSumGrade()<stuList.get(j).getSumGrade()){
					count++;
					stuList.get(i).setRank(count);
					
				}
				
			}
			
		}
	
		
		
		//선생님답 - 등수를 구하는 메서드 호출하기
		stdTest.setRanking(stuList);
		System.out.println("정렬전...");
		for(Student std : stuList){
			System.out.println(std); //--> std만 써도 자동으로 .toString생략되어 있다
//			System.out.println(std.toString()); --> 위와 같다
		}
		
		
		//학번의 오름차순 정렬하기
		Collections.sort(stuList);
		System.out.println("정렬후...");
		for(Student std : stuList){
			System.out.println(std);
		}
		//총점의 역순으로 정렬하기
		Collections.sort(stuList,new sumGradeDesc());
		System.out.println("정렬후...");
		for(Student std : stuList){
			System.out.println(std);
		}
		
		
		
		
		
		
		//유네답
		for (Student stu : stuList) {
			int count =1;
			for(Student stu1 : stuList){
				if(stu.getSumGrade() < stu1.getSumGrade()){
					count++;
					stu.setRank(count);
				}
			}
			
		}
		System.out.println("------------------------------");
		Collections.sort(stuList);
		System.out.println("정렬 후...");
		for(Student stu : stuList){
			System.out.println(stu);
		}
		
//		System.out.println("------------------------------");
//		Collections.sort(stuList, new sumGradeDesc());
//		for(Student stu : stuList){
//			System.out.println("정렬 후 : " + stu);
//		}
		
		
//		System.out.println("------------------------------");
//		Collections.sort(stuList,new sumGradeDesc());
//		for(int i =0 ; i< stuList.size(); i++){
//			Student stu = stuList.get(i);
//			System.out.println("알아보자 " + stu);
//		}
//		
//		for(Student stu : stuList){
//			
//		}
//		
		
		
		
	

	}

}

//1. Student 클래스 만든다
class Student implements Comparable<Student>{
	
	//2. 변수를 선언
	private int stuNo;
	private String name;
	private int koGrade;
	private int engGrade;
	private int maGrade;
	private int sumGrade;
	private int rank;
	
	
	//3. 생성자를 만들어 변수를 초기화한다
	public Student(int stuNo, String name, int koGrade, int engGrade,
			int maGrade) {
		super(); //object의 상속을 받아 super
		this.stuNo = stuNo;
		this.name = name;
		this.koGrade = koGrade;
		this.engGrade = engGrade;
		this.maGrade = maGrade;
		this.sumGrade = koGrade + engGrade + maGrade;
		this.rank = 1;
		
		
	}
	//4. get,set 만들기
	public int getSumGrade() {
		return sumGrade;
	}

	public void setSumGrade(int sumGrade) {
		this.sumGrade = sumGrade;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getStuNo() {
		return stuNo;
	}

	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKoGrade() {
		return koGrade;
	}

	public void setKoGrade(int koGrade) {
		this.koGrade = koGrade;
	}

	public int getEngGrade() {
		return engGrade;
	}

	public void setEngGrade(int engGrade) {
		this.engGrade = engGrade;
	}

	public int getMaGrade() {
		return maGrade;
	}

	public void setMaGrade(int maGrade) {
		this.maGrade = maGrade;
	}

	
	//5. toString 만들기
	@Override
	public String toString() {
		return "Student [stuNo=" + stuNo + ", name=" + name + ", koGrade="
				+ koGrade + ", engGrade=" + engGrade + ", maGrade=" + maGrade
				+ ", sumGrade=" + sumGrade + ", rank= " + rank +"]";
	}
	//6. 학번의 오름차순으로 정렬할 수 있는 기준 설정
	@Override
	public int compareTo(Student stu) {
		
		
		
		//선생님 답 
		return new Integer(this.stuNo).compareTo(stu.getStuNo());
			
		//유네의 답
//		if( this.stuNo >stu.stuNo){
//			return 1;
//		}else if(this.stuNo == stu.stuNo){
//			return 0;
//		}else{
//			
//			return -1;
//		}
	}
	

	
	
	
}

// 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되도록하는 외부 정렬기준 만들기
class sumGradeDesc implements Comparator<Student>{

	@Override
	public int compare(Student stu1, Student stu2) {
		
		
		//선생님답
		if(stu1.getSumGrade() ==stu2.getSumGrade()){
			return stu1.getName().compareTo(stu2.getName());
		}else{
			return new Integer(stu1.getSumGrade()).compareTo(stu2.getSumGrade()*-1);
		}
		
		//유네답
//		if(stu1.getSumGrade()>stu2.getSumGrade()){
//			return -1;
//		}else if(stu1.getSumGrade()==stu2.getSumGrade()){
//			return stu1.getName().compareTo(stu2.getName())*0;
//		}else{
//			return 1;
//		}
		
	}

}
