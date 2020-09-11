package mail02;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.net.MalformedURLException;
import java.net.URL;

public class HtmlEmailTest {

    /*
        HTML 형식의 이메일 보내기
     */

    public static void main(String[] args) throws EmailException, MalformedURLException {

        // HtmlEmail 객체 생성
        HtmlEmail email = new HtmlEmail();

        // SMTP 설정
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("skygudanr89@gmail.com", "ogbjljodcjxghrnq"));
        email.setSSLOnConnect(true);

        // Charset 설정
        email.setCharset("utf-8");

        // 이메일 내용
        email.addTo("skygudanr@naver.com", "받는이");
        email.setFrom("skygudanr89@gmail.com", "보낸이");
        email.setSubject("The picture");

        // 이미지를 삽입하고 콘텐츠 ID를 가져옵니다.
        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
        String cid = email.embed(url, "Apache logo");
        
        System.out.println("cid : " + cid);

        // html 메시지 설정
        email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");

        // 대체 메시지 설정
        email.setTextMsg("Your email client does not support HTML messages");

        // 이메일 보내기
        email.send();

    }

}