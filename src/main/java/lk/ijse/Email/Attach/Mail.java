package lk.ijse.Email.Attach;

import java.util.ArrayList;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

    public static boolean sendEmial(String reciever, ArrayList<String> attrachment, String body) {

        final String username = "USE YOUR EMAIl ADDRESS "; // USE YOUR EMAIl ADDRESS
        final String password = "USE YOUR PASSWORD"; //USE YOUR PASSWORD

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("harshika.nayomal@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(reciever));
            message.setSubject("Inform about Monthly Fee");
            MimeBodyPart textPart = new MimeBodyPart();

            textPart.setText(body);

            if (attrachment !=null) {
                Multipart multipart = new MimeMultipart();

                for (String attachment : attrachment) {
                    System.out.println(attachment);

                    MimeBodyPart messageBodyPart = new MimeBodyPart();

                    DataSource source = new FileDataSource(attachment);

                    messageBodyPart.setDataHandler(new DataHandler(source));

                    messageBodyPart.setFileName("Images of Bills");

                    multipart.addBodyPart(messageBodyPart);

                }
                MimeBodyPart htmlPart = new MimeBodyPart();

                htmlPart.setContent(body, "text/html; charset=utf-8");


                Multipart multipart2 = new MimeMultipart();
                message.setContent(multipart2);
                multipart.addBodyPart(htmlPart);
                message.setContent(multipart);
            }
            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Done");
            return true;


        } catch (MessagingException e) {

            e.printStackTrace();
            return  false;
        }

    }
}