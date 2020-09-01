package argTest;

/*
 * 제네릭 클래스 만드는 방법 	

		형식) class 클래스명<제네릭타입글자>{
		
				제네릭타입글자 변수명; 			//변수 선언에 제네릭을 사용할 경우
				....
				
				제네릭타입글자 메서드명(파라미터변수들...){			// 반환값이 있는 메서드에서 사용할 경우
							..
							return 처리할값;
				}
					
					반환값타입 메서드명(제네릭타입글자 변수명, ...){			// 메서드의 파라미터변수에 사용할 경우
								...
					}
		}
		제네릭 사용 이유 : 형변환에 따른 오류를 발견할 수 있다

 */

//제네릭을 사용하지 않는 클래스 작성

class NoneGenericClass{
	
	private Object val;
	
	public void setVal(Object val){
		this.val =val;
	}
	
	public Object getVal(){
		return val;
	}
	
	
	
}

//제네릭을 사용하는 클래스 작성
class MyGeneric<T>{ 				//T는 MyGeneric을생성할 때 선언하겠다
	
		private T val; //변수 선언
		
		public void setVal(T val){		 //메서드의 파라미터 변수에 사용
			this.val = val;
			
		}
		
		public T getVal(){		 //메서드의 반환값 타입에 사용
			return val;
		}
	
}
public class GenericTest {

	
	
	public static void main(String[] args) {
		
		NoneGenericClass ngc1 = new NoneGenericClass();
		
		ngc1.setVal("가나다라");
		
		NoneGenericClass ngc2 = new NoneGenericClass();
		
		ngc2.setVal(123);
		
		String rtnStr = (String) ngc1.getVal(); 
		
//		int rtnStr = (int) ngc1.getVal(); 
		/*반환값타입이 Object이니깐 형변환하는데는 컴파일에러가 나지 않지만 출력(실행)할때 문자열을 정수로 바꾸려고 하니깐 오류가 난다  ==> 제네릭 사용이유 */ 
		
		
		System.out.println("문자열 반환값 : " + rtnStr);
		
		int rtnInt = (int) ngc2.getVal();
		System.out.println("숫자 반환 값 : " + rtnInt);
		
		
		//---------------------------------------------------------------------------//
		System.out.println("──────────────────────────────────────────");
		System.out.println("[제네릭을 사용하는 클래스]");
		
		
		MyGeneric<String>  mgc1 = new MyGeneric<>();
		mgc1.setVal("유네생일");
		rtnStr = mgc1.getVal();  	//mgc1의 타입을 String으로 정해줘서 형변환이 필요없다
		
		//객체형을 기본자료형으로 바꿔주는 것 Unboxing
		System.out.println("제네릭 문자열 반환값 : " + rtnStr);
		
		MyGeneric<Integer> mgc2 = new MyGeneric<>();
		mgc2.setVal(1023);
		rtnInt = mgc2.getVal();
		System.out.println("제네릭 숫자 반환값 : " + rtnInt);
		

		

	}

}
