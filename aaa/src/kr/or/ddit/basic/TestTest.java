package kr.or.ddit.basic;

public class TestTest {

	public static void main(String[] args) {


		int i =0;
		int j = 0;
		for( i = 2; i<=4; i++){
			for(j = 5; j<=7; j++){
			}
		}
		System.out.println(j+ " * " + i + " = " + j*i);

		int x =100; 
		int y=10;
		do{
			x -= y;
			System.out.print(x + "\t");
			System.out.println(y++);
		
			
		}while(x>30);
	
	
	
	
	System.out.println("──────────────────────────────────────");
	
	int[] a = {90,100,80,70,60,50,30};
	int hap =0;
	float avg;
	for(int aa : a){
		hap = hap + aa;
		avg = (float)hap /a.length;
		System.out.println(hap + "," + avg);
	}
	
	int[] aa = new int[5];
	int b =1;
	int sum = 0;
	
	for( i = 4; i>-1;i--){
		a[i] = b;
		b *= 3;
	}
	for(i = 4; i>-1;i-=2)
		sum += *(a+i);
	System.out.println(sum);
	
	
	
	}



}
