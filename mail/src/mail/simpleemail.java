package mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class simpleemail {
	
	public static void main(String[] args) throws EmailException {
		
		try {

			SimpleEmail email = new SimpleEmail();

			// setHostName�� ���� ���ϼ�������

			email.setCharset("euc-kr"); // �ѱ� ���ڵ�

			email.setHostName("smtp.gmail.com"); // SMTP���� ����

			email.setSmtpPort(465);

			email.setSSL(true);

			try {

				email.addTo("dbs8891@gmail.com", "������"); // ������ �߰�

			} catch (EmailException e) {

				e.printStackTrace();

			}

			try {

				email.setFrom("dbs8891@gmail.com",
						"����¯"); // ������ ���

			} catch (EmailException e) {

				e.printStackTrace();

			}

			email.setSubject("Test message"); // ���� ����

			email.setContent("simple ���� Test�Դϴ�", "text/plain; charset=euc-kr");

			email.setAuthentication("dbs8891", "ddddddddddddd"); // ��������

			email.send();

		} catch (EmailException e) {

			e.printStackTrace();

		}
	}

}
