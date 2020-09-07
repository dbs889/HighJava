package mail02;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class simpleMail {

	// SMTP 서버 변수를 선언
	// 1. 서버 주소 smtp.gmail.com
	private static final String HOST = "smtp.gmail.com";
	// SSL용 인증 포트 :465
	private static final int SSL_PORT = 465;
	// ssl 필요 yes
	private static final boolean SSL_FLAG = true;
	// TLS전용 포트 :587
	private static final int TSL_PORT = 587;
	// TLS 필요 : 예
	private static final boolean TLS_FLAG = true;

	// 간단한 텍스트만 보내는 메서드
	private void sendSimpleMail() {
		String userName = "dbs8891@gmail.com";
		String password = "ehxebbqfarzvnefx";

		try {
			Email email = new SimpleEmail();

			// 한글 인코딩
			email.setCharset("euc-kr");

			// smtp서버
			email.setHostName(HOST);
			email.setSmtpPort(SSL_PORT);
			// 사용자 추가

			/*
			 * 공용 클래스 DefaultAuthenticator는 javax.mail.Authenticator를 확장합니다. 이것은 기본 userName
			 * 및 암호 유형 인증이 필요한 모든 전송에 사용할 수있는 매우 간단한 인증 개체입니다. DefaultAuthenticator public
			 * DefaultAuthenticator ( 문자열 사용자 이름, 문자열 비밀번호) 기본 생성자. 매개 변수 : userName -인증 요청시
			 * 사용할 사용자 이름 password -인증 요청시 사용할 비밀번호
			 * 
			 * 
			 */
			email.setAuthentication(userName, password);
			// ssl연결 필요여부
			email.setSSLOnConnect(SSL_FLAG);

			// 보내는 사람 주소
			email.setFrom(userName);

			// 제목 내용작성
			email.setSubject("오늘은 토요일");
			email.setMsg("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\n"
					+ "zzzzzzzzzzzzzzzzzzzzzzzzzzzzz\nzzzzzzzzzzzzzzzzzzzzz");

			// 받는 사람 주소 입력
			email.addTo("dbs889@naver.com");

			// 메일 보내기

			email.send();
			System.out.println("메일 보내기 성공!!");


		} catch (EmailException e) {
			System.out.println("이멜보내기 실패");
			e.printStackTrace();
		}

	}
	
	private void sendAttachMail() {
		String userName = "dbs8891@gmail.com";
		String password = "ehxebbqfarzvnefx";
		try {
			//첨부할 attachment 정보를 생성합니다
			EmailAttachment att = new EmailAttachment();
			
			//첨부파일 경로
			att.setPath("C:/Users/user/Desktop/기타/Smileyface.png");
			att.setDisposition(EmailAttachment.ATTACHMENT);
			//첨부 파일 관련 텍스트
			att.setDescription("웃는 이미지");
			//첨부파일 이미지
			att.setName("Smileyface.png");
			
			
			MultiPartEmail email = new MultiPartEmail();
			email.setCharset("euc-kr");
			//smtp 주소 정보
			
			email.setHostName(HOST);
			email.setAuthentication(userName, password);
			// ssl연결 필요여부
			email.setSSLOnConnect(SSL_FLAG);
			email.setSmtpPort(SSL_PORT);
			email.addTo("leeys9423@gmail.com");
			email.setFrom(userName);
			email.setSubject("이미지파일보내기");
			email.setMsg("웃는이미지테스트 파일");
			
			//생성한 첨부파일을 추가
			email.attach(att);
			
			email.send();
			System.out.println("메일 보내기 완료");
			
			
		} catch (EmailException e) {
			System.out.println("메일보내기 실패");
			e.printStackTrace();
		}
	}

	//URL통해 첨부하기
	private void sendURLMail() {
		
		String userName = "dbs8891@gmail.com";
		String password = "ehxebbqfarzvnefx";
		try {
			//첨부할 attachment 정보를 생성합니다
			EmailAttachment att = new EmailAttachment();
			
			//첨부파일 경로
			att.setURL(new URL("https://commons.apache.org/proper/commons-email/"));
			att.setDisposition(EmailAttachment.ATTACHMENT);
			//첨부 파일 관련 텍스트
			att.setDescription("아파치 메일 사이트");
			//첨부파일 이미지
			att.setName("commons mail");
			
			
			MultiPartEmail email = new MultiPartEmail();
			email.setCharset("euc-kr");
			//smtp 주소 정보
			
			email.setHostName(HOST);
			email.setAuthentication(userName, password);
			// ssl연결 필요여부
			email.setSSLOnConnect(SSL_FLAG);
			email.setSmtpPort(SSL_PORT);
			email.addTo("dbs8891@gmail.com");
			email.setFrom(userName);
			email.setSubject("URL파일보내기");
			email.setMsg("URL테스트 파일");
			
			//생성한 첨부파일을 추가
			email.attach(att);
			
			email.send();
			System.out.println("메일 보내기 완료");
			
			
		} catch (EmailException e) {
			System.out.println("메일보내기 실패");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void sendHTMLMail() {
		

		String userName = "dbs8891@gmail.com";
		String password = "ehxebbqfarzvnefx";
		try {
			HtmlEmail email = new HtmlEmail();
		
			email.setCharset("euc-kr");
//			//smtp 주소 정보
//			
			email.setHostName(HOST);
			email.setAuthentication(userName, password);
			// ssl연결 필요여부
			email.setSSLOnConnect(SSL_FLAG);
			email.setSmtpPort(SSL_PORT);
			email.setHostName(HOST);
			email.addTo("cor_dial_09@naver.com");
			email.setFrom(userName);
			email.setSubject("다용이가 좋아할 사진");
			
			
			URL url = new URL("https://pbs.twimg.com/profile_images/1260824952095141888/TelAyEAr_400x400.jpg");
			String cid = email.embed(url,"apache logo");
			
			email.setHtmlMsg("지뇽이사진");
			email.send();
			System.out.println("메일 보내기 완료");
			
			
		} catch (EmailException e) {
			System.out.println("메일보내기 실패");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {

		// 메일을 보내는 클래스
		simpleMail sendEmail = new simpleMail();
//		sendEmail.sendSimpleMail();
//		sendEmail.sendAttachMail();
//		sendEmail.sendURLMail();
		sendEmail.sendHTMLMail();
	}

}
