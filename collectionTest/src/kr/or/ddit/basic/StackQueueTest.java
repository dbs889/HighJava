package kr.or.ddit.basic;

import java.util.LinkedList;

public class StackQueueTest {

	/*
	 * Stack ==> LIFO(후입선출)방식의 자료구조
	 * 			 : 가장 나중에 삽입된 자료가 가장 먼저 삭제된다
	 * 
	 * Queue ==> FIFO(선입선출)방식의 자료구조
	 * 			: 가장 먼저 삽입된 자료가 가장 먼저 삭제 된다
	 * 
	 * 
	 * 	stack 과 queue는 LinkedList를 이용하여 사용할 수 있다
	 * 			LinkedList 데이터를 저장하는 공간이 하나씩 만들어져 있다
	 * 			
	 */
	
	public static void main(String[] args) {

		/*
		 * Stack 명령
		 * 
		 * 1. 자료 입력 : push(추가할 데이터);
		 * 2. 자료 출력 : pop() ==> 자료를 꺼내 온 후에 꺼내온 자료를 Stack에서 지운다
		 * 				peak() ==> 삭제 없이 자료를 꺼내온다
		 */
		
		LinkedList<String> stack = new LinkedList<>();
		
		stack.push("이윤혜");
		stack.push("영섭군");
		stack.push("용관장");
		stack.push("박병귭귭");
		
		System.out.println("──────────────────stack────────────────────────");
		System.out.println("현재 stack의 값 : " + stack); //내가 push한 순서의 역순으로 출력된다
		System.out.println("======================================");
		String data = stack.pop();
		System.out.println("꺼내온 값 : " + data);
		System.out.println("꺼내온 값 : " + stack.pop());
		System.out.println("현재 stack의 값 : " + stack); //내가 push한 순서의 역순으로 출력된다
		
		System.out.println("======================================");
		
		String temp = stack.peek();
		System.out.println("peek로 꺼내온 값 : " + stack.peek());
		System.out.println("현재 stack의 값 : " + stack); 
		
		System.out.println("======================================");
		
		stack.push("우럭회");
		System.out.println("추가한 후 stack : " + stack);
		
		System.out.println("======================================");
		
		System.out.println("꺼내온 값 : " + stack.pop());
		System.out.println("현재 stack의 값 : " + stack); 
		
		
		System.out.println();
		System.out.println("──────────────────queue────────────────────────");
		
		/*
		 * 	Queue의 명령
		 * 	1. 자료 입력 :  offer(추가할 데이터)
		 * 	2. 자료 출력 : poll() ==> 자료를 꺼내오고 꺼내온 자료를 Queue에서 삭제한다
		 * 				peek() ==> 삭제없이 자료를 꺼내든다
		 *  
		 */
		
		LinkedList<String> queue = new LinkedList<>();
		
		queue.offer("우럭회");
		queue.offer("광어회");
		queue.offer("연어회");
		queue.offer("참돔");
	
		System.out.println("현재 queue 값 : " + queue);
		
		System.out.println("======================================");
		
		data = queue.poll();
		System.out.println("꺼내온 값 : " + data);
		System.out.println("꺼내온 값 : " + queue.poll());
		System.out.println("현재 queue값 : " + queue);
		
		System.out.println("======================================");
		queue.offer("참치뱃살");
		System.out.println("현재 queue값 : " + queue);
		
		System.out.println("======================================");
	System.out.println("꺼내온 값 : " + queue.poll());
		System.out.println("현재 queue값 : " + queue);
		
		System.out.println("======================================");
		System.out.println("삭제없이 데이터 꺼내오기 : " + queue.peek());
		System.out.println("현재 queue값 : " + queue);
		
		
		

	}

}
