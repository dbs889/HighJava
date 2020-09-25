package kr.or.ddit.basic;

import kr.or.ddit.util.Aes256Util;
import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {

	
	public static void main(String[] args) throws Exception {
		
		
//		String testData =  "Hello,world";
		String testData =  "안녕 나는 유네야 유네 짝궁은 상구와 영섭군이지!!";
		
		
		System.out.println("MD5 : " + CryptoUtil.md5(testData));
		System.out.println("sha256 : " + CryptoUtil.sha256(testData));
		System.out.println("sha512 : " + CryptoUtil.sha512(testData));
		
		System.out.println("────────────────────────────────────────────");
		
		Aes256Util aes256 = new Aes256Util();
		
		
		//암호화 작업
		String str = aes256.encrypt(testData);
		System.out.println("AES256 암호화하기 전 : " + testData);
	
		System.out.println("AES256 암호화한 후  : " + str);
		
		//복호화 작업
		String deStr = aes256.decrypt(str); //암호화된 데이터를 넣어준다
		System.out.println("AES256 복호화한 후 : " + deStr);
		
		
		
	}
	
}
