package argTest;

public class ArgsTest {
	
	
	
	
	
	
	//1. 배열을 이용한 메서드 - 정수를 여러개 받아 합계를 구하는 메서드
	public int sumArr(int[] data){
		int total = 0;
		for(int i = 0; i<data.length; i++){
			total += data[i];
			
		}
		return total;
		
	}
	
	/*
	 * 가변형 인수  	- 메서드의 매개변수의 개수가 호출될 때마다 다를 경우에 사용한다
	 * 			- 이 가변형 인수는  메서드 내에서는 배열로 처리된다
	 * 			 : 필요에 따라 매개변수(인수)를 가변적으로 조정할 수 있는 기술이다
	 * 
	 * 			- 가변형 인수는 하나의 메서드에 하나만 사용할 수 있다. 
	 * */
	
	
	
	// 2. 정수형데이터를 가변형 인수로 받고 싶다- 가변형인수를 이용한 메서드
	public int sumArg(int...data){
		int total = 0;
		for(int i = 0; i<data.length; i++){
			total += data[i];
			
		}
			return total;
		
	}
	
	
	//3.하나의 메서드에 2개 이상의 가변형 변수 사용 불가
//	public void argTest(int...t,float...k){
//		
//	} 불가능하다
	
	
	//4.가변형 변수와 일반 변수를 같이 사용할 경우에는 가변형 변수를 제일 뒤에 배치해야 한다
	public void argTest(String name, int...data){
		
	}

	public static void main(String[] args) {

		ArgsTest test = new ArgsTest();
		
		int[] num ={1,2,3,4,5,6,7,8,9};
		
		
		int result = test.sumArr(num);
		System.out.println("result = " + result);
		
		
		
		//10,20,30,40의 데이터를 배열에 넣고 싶다
		System.out.println("───────────────────────────────────────────────────");
		System.out.println();
		System.out.println("[10,20,30,40의 합]");
		System.out.println(test.sumArr(new int[]{10,20,30,40}));
		
		System.out.println();
		System.out.println("───────────────────────────────────────────────────");
		
		System.out.println();
		System.out.println("[가변형 인수]");
		System.out.println(test.sumArg(1,2,3,4,5,6,7,8,9));
		System.out.println(test.sumArg(10,20,30,40));
		System.out.println("───────────────────────────────────────────────────");
		
		
	}

}
