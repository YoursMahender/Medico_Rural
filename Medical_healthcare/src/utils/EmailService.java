//package utils;
//
//import jakarta.mail.*;
//import jakarta.mail.internet.*;
//import java.util.Properties;
//
//public class EmailService {
//
//    private static final String FROM_EMAIL = "yourgmail@gmail.com";
//    private static final String PASSWORD = "your_app_password"; // not your Gmail password!
//
//    public static void sendEmail(String toEmail, String subject, String body) {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");  
//        props.put("mail.smtp.port", "587");             
//        props.put("mail.smtp.auth", "true");            
//        props.put("mail.smtp.starttls.enable", "true"); 
//
//        Session session = Session.getInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(FROM_EMAIL));
//            message.setRecipients(
//                    Message.RecipientType.TO, InternetAddress.parse(toEmail));
//            message.setSubject(subject);
//            message.setText(body);
//
//            Transport.send(message);
//
//            System.out.println("Email sent to " + toEmail);
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//}