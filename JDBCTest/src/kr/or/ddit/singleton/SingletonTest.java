package kr.or.ddit.singleton;


public class SingletonTest {

	public static void main(String[] args) {


//		MySingleton test1 = new Mysingleton; 외부에서 new명령으로 객체생성 불가
		
		MySingleton test2 = MySingleton.getInstance(); //메서드를 사용한다
		MySingleton test3 = MySingleton.getInstance(); //객체생성을 한번만 한다
		
		System.out.println("test2 => " + test2.toString());
		System.out.println("test3 => " + test3.toString());
		System.out.println(test2 == test3);
		
		test2.displayTest();

	}

}
