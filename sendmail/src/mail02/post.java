package mail02;

import javax.mail.MessagingException;

public class post {

	public static void main(String[] args) {
		  
		  String from = "dbs8891@gmail.com";    //
		  String to = "dbs8891@gmail.com";   //
		  String cc = " ";     // 
		  String subject = "ohd";// 
		  String content =
		   "Oh Happy Day.";// 
		  
		  if(from.trim().equals("")) {
		   System.out.println("보내는 사람을 입력하지 않았습니다.");
		  }
		  else if(to.trim().equals("")) {
		   System.out.println("받는 사람을 입력하지 않았습니다");
		  }
		  else {
		   try {
			   newemail mt = new newemail();
		    
		    // 
		    mt.sendEmail(from, to, cc, subject, content);
		    System.out.println("메세지 보내기 성공.");
		   }
		   catch(MessagingException me) {
		    System.out.println("메세지 전송 실패.");
		    System.out.println("실패 이유 : " + me.getMessage());
		   }
		   catch(Exception e) {
		    System.out.println("메세지 전송실패.");
		    System.out.println("실패이유 : " + e.getMessage());
		   }
		  }
		 }


}
