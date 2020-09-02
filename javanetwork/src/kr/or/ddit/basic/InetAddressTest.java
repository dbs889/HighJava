package kr.or.ddit.basic;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.UnexpectedException;



public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException{
		// InetAddress 클래스 => IP주소를 다루기 위한 클래스
		
		
		//www.naver.com 사이트의 IP정보 가져오기
		
		InetAddress naverIP = InetAddress.getByName("www.naver.com");
		
		System.out.println("Host name : " + naverIP.getHostName());
		System.out.println("Host Address : " + naverIP.getHostAddress());
		System.out.println();
		
		//자신의 컴퓨터의 IP정보 가져오기
		
		InetAddress localIP = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host name : " + localIP.getHostName());
		System.out.println("내 컴퓨터의 Host Address : " + localIP.getHostAddress());
		System.out.println();
		
		//IP주소가 여러개인 호스트의 정보 가져온다
		InetAddress[] ipArr = InetAddress.getAllByName("www.naver.com");
		for(InetAddress ip : ipArr){
			System.out.println(ip.toString());
		}
		
		

	}

}
