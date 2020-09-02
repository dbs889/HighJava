package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URL;

public class urlTest01 {

	public static void main(String[] args) throws MalformedURLException {
		//url 클래스 ==> 인터넷에 연결된 컴퓨터의 자원에 접근할 수 있는 주소를 다루는 클래스
		
		//https://www.ddit.or.kr/index.html?ttt=123
		
		
		URL url = new URL("https://www.ddit.or.kr:80/index.html?ttt=123");
		
		System.out.println("protocol : " + url.getProtocol() );
		
		System.out.println("Hose : " + url.getHost());
		
		System.out.println("Port : " + url.getPort());
		System.out.println("File : " + url.getFile());
		System.out.println("path : " + url.getPath());
		System.out.println("Query : " + url.getQuery());
		System.out.println();
		
		System.out.println(url.toExternalForm());
	}

}



