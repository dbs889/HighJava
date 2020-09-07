package mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class simpleemail {
	
	public static void main(String[] args) throws EmailException {
		
		try {

			SimpleEmail email = new SimpleEmail();

			// setHostName에 실제 메일서버정보

			email.setCharset("euc-kr"); // 한글 인코딩

			email.setHostName("smtp.gmail.com"); // SMTP서버 설정

			email.setSmtpPort(465);

			email.setSSL(true);

			try {

				email.addTo("dbs8891@gmail.com", "이윤혜"); // 수신자 추가

			} catch (EmailException e) {

				e.printStackTrace();

			}

			try {

				email.setFrom("dbs8891@gmail.com",
						"유네짱"); // 보내는 사람

			} catch (EmailException e) {

				e.printStackTrace();

			}

			email.setSubject("Test message"); // 메일 제목

			email.setContent("simple 메일 Test입니다", "text/plain; charset=euc-kr");

			email.setAuthentication("dbs8891", "ddddddddddddd"); // 메일인증

			email.send();

		} catch (EmailException e) {

			e.printStackTrace();

		}
	}

}
