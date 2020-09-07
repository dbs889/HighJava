package mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class simpleemail {
	
	public static void main(String[] args) throws EmailException {
		
		try {

			SimpleEmail email = new SimpleEmail();

			//

			email.setCharset("euc-kr"); //

			email.setHostName("smtp.gmail.com"); // 

			email.setSmtpPort(465);

			email.setSSL(true);

			try {

				email.addTo("dbs8891@gmail.com", "Une"); 

			} catch (EmailException e) {

				e.printStackTrace();

			}

			try {

				email.setFrom("dbs8891@gmail.com",
						"Unene"); // 

			} catch (EmailException e) {

				e.printStackTrace();

			}

			email.setSubject("Test message"); //

			email.setContent("simple Test mail", "text/plain; charset=euc-kr");

			email.setAuthentication("dbs8891", "ddddddddddddd"); // 

			email.send();

		} catch (EmailException e) {

			e.printStackTrace();

		}
	}

}
