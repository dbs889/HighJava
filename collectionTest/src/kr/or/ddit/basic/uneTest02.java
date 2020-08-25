package kr.or.ddit.basic;

import java.awt.Container;


public class uneTest02 {
	
	public static void main(String[] args) {
	
		
		
				Pair<String,Integer> pair = new Pair<>("홍길동",35);
				Integer age = Util.getValue(pair,"홍길동");
				System.out.println(age);
				
				childPair<String,Integer> childpair = new childPair<>("홍길동",35);
				Integer childage = Util.getValue(childpair,"홍삼순");
				System.out.println(childage);
				
				/*otherPair<String,Integer> otherpair = new otherPair<>("홍길동",35);
				Integer otherage = Util.getValue(otherpair,"홍길동");
				System.out.println(otherage);*/
				
				
		
		
		
		
		
	}
	


	

}

class Pair<K,V>{
	private K key;
	private V value;
	
	
	
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey(){
		return key;
	}
	
	public V getValue(){
		return value;
	}
}

class childPair<K,V> extends Pair<K, V>{
	
	public childPair(K key, V value){
		super(key,value);
	}
}

class otherPair<K,V>{
	private K key;
	private V value;
	
	
	
	public otherPair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey(){
		return key;
	}
	
	public V getValue(){
		return value;
	}
	
}


class Util{
	public static <K,V> V getValue(Pair<K,V> p, K k){
		
		if(p.getKey() ==k){
			return p.getValue();
		}else{
			return null;
		}
	}
}

//class Container<K,V>{
//	private K key;
//	private V value;
//	
//	
//	
//	public Container(K key, V value) {
//		this.key = key;
//		this.value = value;
//	}
//
//
//
//	public K getkey() {
//		return key;
//	}
//
//
//	public V getvalue() {
//		return value;
//	}
//
//
//
//	public void set(K key,V value) {
//		this.key = key;
//		this.value = value;
//	}
//	
//	
//
//	
//	
//	
//}
