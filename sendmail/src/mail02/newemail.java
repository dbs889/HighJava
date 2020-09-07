package mail02;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class newemail {
	

	 public void sendEmail(String from, String to, String cc, String subject, String content)
	  throws Exception {
	  
	  // Propertie
	  Properties props = new Properties();
	  
	  // G-Mail SMTP ����
	  props.put("mail.transport.protocol", "smtp");// 
	  props.put("mail.smtp.host", "smtp.gmail.com");// 
	  props.put("mail.smtp.port", "465");//
	        props.put("mail.smtp.starttls.enable","true");
	      
	        props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	        // props.put("mail.smtp.user", from);
	        props.put("mail.smtp.auth", "true");//
	        
	  Authenticator auth = new SMTPAuthenticator();
	  Session mailSession = Session.getDefaultInstance(props, auth);
	  
	  // create a message
	  Message msg = new MimeMessage(mailSession);
	  
	  // set the from and to address
	  msg.setFrom(new InternetAddress(from));
	  msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));//
	  
	  if(!cc.trim().equals("")) {
	   msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));
	  }
	  
	  // Setting the Subject and Content Type
	  msg.setSubject(subject); // 
	  msg.setText(content);  // 
	  msg.setSentDate(new Date());// 
	  
	  Transport.send(msg);  //
	 }

}
