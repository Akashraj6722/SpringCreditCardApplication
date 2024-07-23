package com.chainsys.creditcard.dao;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
public class MailImpl {
	
	static Session newSession = null;

    public static void setProperties() {
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        newSession = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("iamakash6722@gmail.com", "ztdb otld cwza bvyy");
            }
        });
    }

    public static void setMailBody(String emailRecipient, String emailBody) throws MessagingException {
        String emailSubject = "Credit Card Approval";

        Message mimeMessage = new MimeMessage(newSession);

            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));

        mimeMessage.setSubject(emailSubject);
        mimeMessage.setText(emailBody);

        Transport.send(mimeMessage);
    }



}
