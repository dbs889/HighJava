package mail02;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	
	 protected PasswordAuthentication getPasswordAuthentication() {
		  String username = "dbs8891@gmail.com"; //
		  String password = "ehxebbqfarzvnefx";  //
		  return new PasswordAuthentication(username, password);
	 }

}
