package kr.or.ddit.util;

import java.lang.Character.Subset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


//단방향 암호화하기
public class CryptoUtil{

	//byte배열을 Hex(16진수) 문자열로 변환하는 메서드
	public static String byteToHexString(byte[] data){
		
		StringBuilder sb = new StringBuilder();
		for(byte b : data){
			// 16진수 2자리의 문자열로 만든다
			//(b& 0xFF) ==> 0a ---> a
			//(b& 0xFF) + 0x100 ==> 10a -->(Integer.toHexString) ==> "10a" -->"0a'
			//Integer.toHexString : 정수를 16잔수 문자열로 바꿔준다
			
			sb.append(Integer.toHexString((b & 0xFF) + 0x100).substring(1));
		}
		
		return sb.toString();
	}
	
	//문자열을 MDS방식으로 암호화하는 메서드 ==>32byte
	public static String md5(String msg) throws NoSuchAlgorithmException{
		
		
		//MD5로 암호화할 수 있는 객체 선언
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		
		md.update(msg.getBytes());
		
		return CryptoUtil.byteToHexString(md.digest());
	}
	//문자열을 SHA-256방식으로 암호화하는 메서드 ==> 64byte
	public static String sha256(String msg) throws NoSuchAlgorithmException{
		
		
		//MD5로 암호화할 수 있는 객체 선언
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		
		md.update(msg.getBytes());
		
		return CryptoUtil.byteToHexString(md.digest());
	}
	//문자열을 SHA-256방식으로 암호화하는 메서드 ==> 128byte
	public static String sha512(String msg) throws NoSuchAlgorithmException{
		
		
		//MD5로 암호화할 수 있는 객체 선언
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		
		
		md.update(msg.getBytes());
		
		return CryptoUtil.byteToHexString(md.digest());
	}
	
	
}
