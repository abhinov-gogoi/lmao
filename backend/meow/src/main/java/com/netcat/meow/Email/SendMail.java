package com.netcat.meow.Email;

import com.netcat.meow.Utility.Literal;
import com.netcat.meow.Utility.Utility;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class SendMail {

    /**
     * Hold the SendMail service
     */
    private static SendMail instance;

    /**
     * @return
     */
    public static SendMail getInstance() {
        /**
         * Check for the Null
         */
        if (instance == null) {
            instance = new SendMail();
        }
        return instance;
    }

    public void send(String to, String template) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Sender's email ID needs to be mentioned
                    String from = "lmao.in@aol.com";

                    // Get system properties
                    Properties properties = System.getProperties();

                    // Setup mail server
                    properties.put("mail.smtp.host", "smtp.aol.com");
                    properties.put("mail.smtp.port", "465");
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.auth", "true");

                    // Get the Session object.// and pass username and password
                    Session session = Session.getInstance(properties, new Authenticator() {

                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("lmao.in@aol.com", "omcvryeejhauvehz");
                        }

                    });

                    // Used to debug SMTP issues ; set to true to print debug logs
                    session.setDebug(Utility.DEBUG_EMAIL);

                    // Create a default MimeMessage object.
                    MimeMessage message = new MimeMessage(session);

                    // Set From: header field of the header.
                    message.setFrom(new InternetAddress(from));

                    // Set To: header field of the header.
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                    // Set Subject: header field
                    message.setSubject(Literal.LMAO);
                    message.setContent(template, "text/html");

                    System.out.println("Sending email to ..." + to);
                    // Send message
                    Transport.send(message);
                    System.out.println("Sent message successfully to ...." + to);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}