package mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	
	 protected PasswordAuthentication getPasswordAuthentication() {
		  String username = "dbs8891@gmail.com"; // gmail �����;
		  String password = "ehxebbqfarzvnefx";  // �н�����;
		  return new PasswordAuthentication(username, password);
	 }

}
