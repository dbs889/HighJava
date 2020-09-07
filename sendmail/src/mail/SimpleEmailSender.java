package mail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class SimpleEmailSender {
	
	private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;
    private static final boolean SSL_FLAG = true; 
 
    public static void main(String[] args) {
        SimpleEmailSender sender = new SimpleEmailSender();
        sender.sendSimpleEmail();
    }
 
    private void sendSimpleEmail() {
         
        String userName = "username@gmail.com";
        String password = "password";
         
        String fromAddress="username@gmail.com";
        String toAddress =  "quickprogrammer@gmail.com";
        String subject = "Test Mail";
        String message = "Hello from Apache Mail";
         
        try {
            Email email = new SimpleEmail();
            email.setHostName(HOST);
            email.setSmtpPort(PORT);
            email.setAuthenticator(new DefaultAuthenticator(userName, password));
            email.setSSLOnConnect(SSL_FLAG);
            email.setFrom(fromAddress);
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(toAddress);
            email.send();
        }catch(Exception ex){
            System.out.println("Unable to send email");
            System.out.println(ex);
        }
    }
}