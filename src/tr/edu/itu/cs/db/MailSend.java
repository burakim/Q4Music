package tr.edu.itu.cs.db;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSend {

    private String pass = "his1-wolfs";
    private String email = "q4musicnotify@gmail.com";
    private String server = "smtp.gmail.com";
    private String port = "587";
    private Message message;

    public MailSend() {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", server);
        props.put("mail.smtp.user", email);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, pass);
                    }
                });
        message = new MimeMessage(session);
    }

    public Boolean sendCode(String username, String code, String email,
            String to) {
        try {

            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Q4Music Challenge Request From " + to);
            message.setText("Hey My Friend, "
                    + to
                    + "\n\n Hey I am "
                    + username
                    + " and I have just completed Music quiz that is provided by Q4Music. I think, I am better than you about music knowledge and I challenge you \n"
                    + "I send this link, just click and challenge \n"
                    + "Challenge Link \n"
                    + " http://q4music.mybluemix.net?code=" + code);

            Transport.send(message);

            System.out.println("Done");
            return true;

        } catch (MessagingException e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
