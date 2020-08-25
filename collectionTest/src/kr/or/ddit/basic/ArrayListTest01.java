package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {

	public static void main(String[] args) {
		
		//객체생성
		 ArrayList list = new ArrayList();
//		 System.out.println("size : " + list.size()); ==> list의 size는 0
		
		 //데이터 추가 : add(추가 할 데이터) ==> 반환값 : 성고하면 true 실패하면 false
		 list.add("abc");
		 list.add(new Integer(123));
		 list.add(111); //자동형변환한다
		 list.add('a'); //char형으로 저장
		 list.add(true);
		 list.add(3.14f);
		 
		 System.out.println("size : " + list.size());
		 System.out.println("현재 List값 : " + list);
		 
		 //데이터 추가2 : add(index,추가할 데이터)
		 // ==> index번째에 '추가할 데이터'를 끼워 넣는다
		 list.add(3,"CCC");
		 
		 System.out.println("List => " + list);
		 System.out.println("--------------------------------------------");
		 
		 //데이터 변경 : set(index,새로운데이터)
		 // ==> index번째 데이터를 '새로운데이터'로 덮어쓴다
		 // ==> 반환값 : 원래의 데이터 
		 
		 String temp = (String) list.set(0,"zzzz"); //list가 object 타입이므로 컴파일 에러가 나타난다
		 System.out.println("변경된 List => " + list);
		 System.out.println("원래의 데이터 : " + temp);
		
		 System.out.println("--------------------------------------------");
		 //데이터 삭제 : remove(index)
		 //==> index번째의 데이터를 삭제한다
		 // ==> 반환값: 삭제된 데이터
		  temp = (String) list.remove(0);
		  System.out.println("삭제 후 list ==> " + list);
		  System.out.println("삭제된 데이터 " + temp);
		  
		  System.out.println("--------------------------------------------");
		 
		 //데이터 삭제2 : remove(삭제할 데이터)
		  // ==> '삭제할 데이터'를 찾아서 삭제한다
		  // ==> 삭제할 데이터가 list안에 여러개가 있을 경우 앞에서부터 삭제된다.
		  // ==> '삭제할 데이터'가 '정수형' 이거나 'char형'일 경우에는 반드시  객체로 변환해서 사용해야 한다 
		  // ==> 반환값 : 삭제 성공이면 true 실패이면 false
		  
		  list.remove("CCC");
		  System.out.println("CCC 삭제 후 list ==> " + list);
		  
//		  list.remove(111); //오류가 나타남 --> why :위에서 remove할때 index를 사용해서 삭제하므로 오류 --> 객체로 변환해야 한다
		  list.remove(new Integer(111)); //==>정수형 데이터는 객체형으로 변환해서 사용한다
		  System.out.println("111 삭제 후 list ==> " + list);
		  
//		  list.remove('a'); //오류가 나타남 --> why : 'a'는 데이터가 저장될때 코드값으로 저장된다(코드값 = A :65 a :97) 
		  list.remove(new Character('a')); //char형 데이터는 객체형으로 변환해서 사용한다
		  System.out.println("'a' 삭제 후 list ==> " + list);
		  
//		  list.remove(123);
		  list.remove(true);
		  list.remove(3.14f);
		  System.out.println("삭제 후 list ==> " + list);
		  System.out.println("--------------------------------------------");
		  
		  // 전체 삭제 : clear();
		  list.clear();
		  System.out.println("size =>" + list.size());
		  System.out.println("clear 후 list =>" + list);
		  
		  
		  System.out.println("--------------------------------------------");
		  list.add("AAAAA");
		  list.add("BBBBB");
		  list.add("CCCCC");
		  list.add("DDDDD");
		  System.out.println("List => " + list );
		  //데이터 꺼내오기 : get(index)
		  // ==> index번째 데이터를 꺼내서 반환한다
		  String data = (String) list.get(1);
		  System.out.println("1번째 데이터 : " + data);
		  
		  //----------------------------------------------------------------------
		  /*
		   * 제네릭 타입(Generic Type) ==>  객체를 선언할 때 <> 안에 그 Collection이 사용할 데이터의 타입을 정해주는 것을 말한다
		   * 							이런식으로 선언하게 되면 그 데이터 타입 이외의 다른 데이터는 저장할 수 없다
		   * 							그리고, 제네릭으로 선언될 수 있는 데이터 타입은 클래스형 이어야 한다
		   * 							(예 : int =>Integer, boolean => Boolean, char => Character 등)
		   * 
		   * 			제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요 없다
		   */
		  
		  ArrayList<String> list2 = new ArrayList<>(); //String만 저장할 수 있는 list
		  
		  
		  list2.add("안녕");
//		  list2.add(123);//list2는 String이므로 컴파일 에러 ==> 다른 종류의 데이터를 저장할 수 없다
		  
		  String temp2 = list2.get(0);
		  System.out.println("--------------------------------------------");
		  System.out.println("꺼내온 데이터 : " + temp);
		  
		  list2.clear();
		  
		  list2.add("AAA");
		  list2.add("BBB");
		  list2.add("CCC");
		  list2.add("DDD");
		  list2.add("EEE");
		  list2.add("FFF");
		  
		  ArrayList<String> list3 = new ArrayList<>();
		  list3.add("BBB");
		  list3.add("DDD");
		  list3.add("EEE");
		  
		  System.out.println("--------------------------------------------");
		  System.out.println("List2 => " + list2);
		  System.out.println("List3 => " + list3);
		  
		  //데이터 삭제: removeAll(Collection 객체)
		  // ==> 전체 데이터 중 'Collection 객체'가 가지고 있는 데이터 전체를 삭제한다
		  list2.removeAll(list3);
		  System.out.println("--------------------------------------------");
		  System.out.println("삭제 후 list2 : " + list2);
		  
		  list2.clear();
		  
		  
		  list2.add("AAA");
		  list2.add("BBB");
		  list2.add("CCC");
		  list2.add("DDD");
		  list2.add("EEE");
		  list2.add("FFF");
		  
		  //list의 데이터를 순서대로 차례로 가져와 사용할 경우에는 반복문을 사용한다
		  
		  System.out.println("-------------------------------------------");
		  for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + "번째 자료 : " + list2.get(i));

			}
		  	System.out.println("-------------------------------------------");
		  	
		  //향상된 for문 --인덱스 안에서 데이터만 필요할 때 사용
		  for(String str : list2){
			  
			  System.out.println(str);
			  
		  }
		  System.out.println("-------------------------------------------");
		  
		  //데이터가 있는지 여부 검사 : contains(검색할 데이터)
		  // ==> 검색할 데이터가 있으면 true, 없으면 false 변환
		  System.out.println("DDD값 : " + list2.contains("DDD"));
		  System.out.println("RRR값 : " + list2.contains("RRR")); //FALSE
		  
		  //검색할 데이터의 위치 구하기  : index of(검색할 데이터)
		  // ==> 리스트에 '검색할데이터'가 있으면 '검색할 데이터'가 있는 index값을 반환하고 없으면 -1을 반환한다
		  System.out.println("-------------------------------------------");
		  System.out.println("DDD의 위치 값 : " + list2.indexOf("DDD"));
		  System.out.println("RRR의 위치 값 : " + list2.indexOf("RRR")); //-1을 반환한다 => 해당데이터가 LIST에 없다
		  
		  
		  //list의 데이터를 배열로 변환하기
		  // 방법1) toArray() ==> List안의 데이터를 Object형 배열로 변환해서 반환한다
		  // 방법2) toArray(new 제네릭타입자료형[0]); ==> 제네릭 타입의 배열로 변환해서 반환한다
		  
		  Object[] objArr = list2.toArray();
		 
		  System.out.println("-------------------------------------------");
		  
			 //방법1) 
		  
			  System.out.println("배열의 개수 : " + objArr.length);
			 for (int i = 0; i < objArr.length; i++) {
				String test = (String) objArr[i];
				 System.out.println( i + "번? 자료 : " + objArr[i]);
			}
			 
		 System.out.println("-------------------------------------------");
		 
		 
		 	//방법 2)
		 
			 String[] strArr = list2.toArray(new String[0]);
			 for(String str : strArr){
				 System.out.println(str);
			 }
		 
		 
		 
		 
		  
		  
		 
		 
		 
		 
		 
		 
		 
		 
		 
		

	}

}
