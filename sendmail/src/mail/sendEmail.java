package mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage.RecipientType;

public class sendEmail {


	
	public static void sendemail(String toUser, String subject, String messageBody){
		
		 Properties props = new Properties();    
		 props.put("mail.smtp.host", "smtp.gmail.com");
		 props.put("mail.smtp.socketFactory.port", "465");
		 props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.port", "465");
		 props.put("mail.smtp.socketFactory.fallback", "false");
		 
		 Session session = Session.getInstance(props, new  javax.mail.Authenticator(){protected PasswordAuthentication getPasswordAuthentication(){return new PasswordAuthentication("dbs8891@gmail.com", "jrxqsszartgwlbwq");}});
//		 props.put("mail.transport.protocol", "smtp");
//		    props.put("mail.smtp.quitwait", "false");
		
		 
		try {
			
			
			 Message message = new MimeMessage(session);
			 Address address = new InternetAddress("dbs8891@gmail.com");
			 message.setFrom(address);
			 Address toAddress = new InternetAddress(toUser);
			 message.addRecipient(Message.RecipientType.TO, toAddress);
			 message.setSubject(subject);
			 MimeBodyPart mTextPart = new MimeBodyPart();
			 mTextPart.setContent(messageBody,"text/html");
			 Multipart mParts = new MimeMultipart();
			 mParts.addBodyPart(mTextPart);
			message.setContent(mParts);
			 
			Transport transport = session.getTransport("smtp");
			transport.connect();
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			
			 
			 
		      		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
