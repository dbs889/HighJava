package mail;

import javax.mail.MessagingException;

public class post {

	
	
	public static void main(String[] args) {
		  
		  String from = "from@gmail.com";    // ���� ������ ���
		  String to = "killmewild@naver.com";   // ���� �������
		  String cc = "cc@daum.net";     // ����
		  String subject = "�߰����� �׽�Ʈ ��~~~~~~~~~~";// ����
		  String content =
		   "�ȳ��ϼ���. �ݰ����ϴ�.\n G-Mail�� �̿��� ���� �߼� �����Դϴ�.\n �����մϴ�.";// ����
		  
		  if(from.trim().equals("")) {
		   System.out.println("������ ����� �Է����� �ʾҽ��ϴ�.");
		  }
		  else if(to.trim().equals("")) {
		   System.out.println("�޴� ����� �Է����� �ʾҽ��ϴ�.");
		  }
		  else {
		   try {
		    MailTest mt = new MailTest();
		    
		    // ���Ϻ�����
		    mt.sendEmail(from, to, cc, subject, content);
		    System.out.println("���� ���ۿ� �����Ͽ����ϴ�.");
		   }
		   catch(MessagingException me) {
		    System.out.println("���� ���ۿ� �����Ͽ����ϴ�.");
		    System.out.println("���� ���� : " + me.getMessage());
		   }
		   catch(Exception e) {
		    System.out.println("���� ���ۿ� �����Ͽ����ϴ�.");
		    System.out.println("���� ���� : " + e.getMessage());
		   }
		  }
		 }


}
