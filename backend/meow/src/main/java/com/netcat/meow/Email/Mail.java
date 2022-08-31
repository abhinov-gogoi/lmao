package com.netcat.meow.Email;

import com.netcat.meow.Utility.Literal;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;
import com.netcat.meow.Utility.Utility;

public class Mail {
	/**
	 * Hold the properties
	 */
	private Properties props = System.getProperties();
	/**
	 * Hold Mail instance
	 */
	private static Mail instance;

	/**
	 * Create singleton object
	 */
	public static Mail getInstance() {
		/**
		 * Check for the Null
		 */
		if (instance == null) {
			instance = new Mail();
		}
		return instance;
	}

	/**
	 * Load mail property
	 */
	private Mail() {
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", Literal.PORT_465);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
	}

	/**
	 * 
	 * @param mailpushdatamodel
	 */
	public void sendEmail(MailPushDataModel mailpushdatamodel) {
		/**
		 * Choose the type Mail sending
		 */
		if (mailpushdatamodel.getAttachments() == null && mailpushdatamodel.getCc() == null) {
			this.sendEmail(mailpushdatamodel.getHtml_body_part(), mailpushdatamodel.getSubject(),
					mailpushdatamodel.getTo(),mailpushdatamodel.getFromName());
		} else if (mailpushdatamodel.getAttachments() == null && mailpushdatamodel.getCc() != null) {
			this.sendEmail(mailpushdatamodel.getHtml_body_part(), mailpushdatamodel.getSubject(),
					mailpushdatamodel.getTo(), mailpushdatamodel.getCc(),mailpushdatamodel.getFromName());
		} else if (mailpushdatamodel.getAttachments() != null && mailpushdatamodel.getCc() == null) {
			this.sendEmail(mailpushdatamodel.getHtml_body_part(), mailpushdatamodel.getAttachments(),
					mailpushdatamodel.getSubject(), mailpushdatamodel.getTo(),mailpushdatamodel.getFromName());
		} else if (mailpushdatamodel.getAttachments() != null && mailpushdatamodel.getCc() != null) {
			this.sendEmail(mailpushdatamodel.getHtml_body_part(), mailpushdatamodel.getAttachments(),
					mailpushdatamodel.getSubject(), mailpushdatamodel.getTo(), mailpushdatamodel.getCc(),mailpushdatamodel.getFromName());
		}
	}

	/**
	 * 
	 * @param html_body_part
	 * @param attachments
	 * @param subject
	 * @param to
	 * @param cc
	 */
	private void sendEmail(String html_body_part, List<String> attachments, String subject, String to, String cc,String fromname) {
		/**
		 * Create a Properties object to contain connection configuration information.
		 */
		try {
			/**
			 * Create a Session object to represent a mail session with the specified
			 */
			Session session = Session.getDefaultInstance(props);
			/**
			 * Create a message with the specified information.
			 */
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Literal.FROM, fromname));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
			message.setSubject(subject);
			/**
			 * Create a MULTIPART/alternative child container.
			 */
			MimeMultipart msg_body = new MimeMultipart("alternative");

			/**
			 * Create a wrapper for the HTML and text parts.
			 */
			MimeBodyPart wrap = new MimeBodyPart();
			/**
			 * Define the HTML part.
			 */
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(html_body_part, "text/html; charset=UTF-8");
			msg_body.addBodyPart(htmlPart);
			/**
			 * Add the child container to the wrapper object.
			 */
			wrap.setContent(msg_body);
			/**
			 * Create a multipart/mixed parent container.
			 */
			MimeMultipart msg = new MimeMultipart("mixed");
			/**
			 * Add the parent container to the message.
			 */
			message.setContent(msg);
			/**
			 * Add the multipart/alternative part to the message.
			 */
			msg.addBodyPart(wrap);
			/**
			 * Define the attachment
			 */
			attachments.forEach(s -> {
				try {
					MimeBodyPart att = new MimeBodyPart();
					DataSource fds = new FileDataSource(s);
					att.setDataHandler(new DataHandler(fds));
					att.setFileName(fds.getName());
					/**
					 * Add the attachment to the message.
					 */
					msg.addBodyPart(att);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			});
			/**
			 * Create a transport.
			 */
			Transport transport = session.getTransport();
			/**
			 * Send the message.
			 */
			try {
				System.out.println("Sending...");
				transport.connect(Literal.HOST, Literal.SMTP_USERNAME, Literal.SMTP_PASSWORD);
				/**
				 * Send the email.
				 */
				transport.sendMessage(message, message.getAllRecipients());
				System.out.println("Email sent!");
			} catch (Exception ex) {
				System.out.println("The email was not sent.");
				System.out.println("Error message: " + ex.getMessage());
				Utility.printStackTrace(ex, this.getClass().getName());
			} finally {
				/**
				 * Close and terminate the connection.
				 */
				transport.close();
			}
		} catch (Exception e) {
			Utility.printStackTrace(e, this.getClass().getName());
		}
	}

	/**
	 * 
	 * @param attachments
	 * @param subject
	 */
	private void sendEmail(String html_body_part, List<String> attachments, String subject, String to,String fromname) {
		try {
			/**
			 * Create a Session object to represent a mail session with the specified
			 */
			Session session = Session.getDefaultInstance(props);
			/**
			 * Create a message with the specified information.
			 */
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Literal.FROM, fromname));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			/**
			 * Create a MULTIPART/alternative child container.
			 */
			MimeMultipart msg_body = new MimeMultipart("alternative");

			/**
			 * Create a wrapper for the HTML and text parts.
			 */
			MimeBodyPart wrap = new MimeBodyPart();
			/**
			 * Define the HTML part.
			 */
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(html_body_part, "text/html; charset=UTF-8");
			msg_body.addBodyPart(htmlPart);
			/**
			 * Add the child container to the wrapper object.
			 */
			wrap.setContent(msg_body);
			/**
			 * Create a multipart/mixed parent container.
			 */
			MimeMultipart msg = new MimeMultipart("mixed");
			/**
			 * Add the parent container to the message.
			 */
			message.setContent(msg);
			/**
			 * Add the multipart/alternative part to the message.
			 */
			msg.addBodyPart(wrap);
			/**
			 * Define the attachment
			 */
			attachments.forEach(s -> {
				try {
					MimeBodyPart att = new MimeBodyPart();
					DataSource fds = new FileDataSource(s);
					att.setDataHandler(new DataHandler(fds));
					att.setFileName(fds.getName());
					/**
					 * Add the attachment to the message.
					 */
					msg.addBodyPart(att);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			});
			/**
			 * Create a transport.
			 */
			Transport transport = session.getTransport();
			/**
			 * Send the message.
			 */
			try {
				System.out.println("Sending...");
				transport.connect(Literal.HOST, Literal.SMTP_USERNAME, Literal.SMTP_PASSWORD);
				/**
				 * Send the email.
				 */
				transport.sendMessage(message, message.getAllRecipients());
				System.out.println("Email sent!");
			} catch (Exception ex) {
				System.out.println("The email was not sent.");
				System.out.println("Error message: " + ex.getMessage());
				Utility.printStackTrace(ex, this.getClass().getName());
			} finally {
				/**
				 * Close and terminate the connection.
				 */
				transport.close();
			}
		} catch (Exception e) {
			Utility.printStackTrace(e, this.getClass().getName());
		}
	}

	/**
	 * 
	 * @param html_body_part
	 * @param subject
	 * @param to
	 */
	private void sendEmail(String html_body_part, String subject, String to,String from_name) {
		try {
			/**
			 * Create a Session object to represent a mail session with the specified
			 */
			Session session = Session.getDefaultInstance(props);
			/**
			 * Create a message with the specified information.
			 */
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Literal.FROM, from_name));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			/**
			 * Create a MULTIPART/alternative child container.
			 */
			MimeMultipart msg_body = new MimeMultipart("alternative");

			/**
			 * Create a wrapper for the HTML and text parts.
			 */
			MimeBodyPart wrap = new MimeBodyPart();
			/**
			 * Define the HTML part.
			 */
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(html_body_part, "text/html; charset=UTF-8");
			msg_body.addBodyPart(htmlPart);
			/**
			 * Add the child container to the wrapper object.
			 */
			wrap.setContent(msg_body);
			/**
			 * Create a multipart/mixed parent container.
			 */
			MimeMultipart msg = new MimeMultipart("mixed");
			/**
			 * Add the parent container to the message.
			 */
			message.setContent(msg);
			/**
			 * Add the multipart/alternative part to the message.
			 */
			msg.addBodyPart(wrap);
			/**
			 * Create a transport.
			 */
			Transport transport = session.getTransport();
			/**
			 * Send the message.
			 */
			try {
				System.out.println("Sending...");
				transport.connect(Literal.HOST, Literal.SMTP_USERNAME, Literal.SMTP_PASSWORD);
				/**
				 * Send the email.
				 */
				transport.sendMessage(message, message.getAllRecipients());
				System.out.println("Email sent!");
			} catch (Exception ex) {
				System.out.println("The email was not sent.");
				System.out.println("Error message: " + ex.getMessage());
				Utility.printStackTrace(ex, this.getClass().getName());
			} finally {
				/**
				 * Close and terminate the connection.
				 */
				transport.close();
			}
		} catch (Exception e) {
			Utility.printStackTrace(e, this.getClass().getName());
		}
	}

	/**
	 * 
	 * @param html_body_part
	 * @param subject
	 * @param to
	 * @param cc
	 */
	private void sendEmail(String html_body_part, String subject, String to, String cc,String fromname) {
		try {
			/**
			 * Create a Session object to represent a mail session with the specified
			 */
			Session session = Session.getDefaultInstance(props);
			/**
			 * Create a message with the specified information.
			 */
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Literal.FROM, fromname));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
			message.setSubject(subject);
			/**
			 * Create a MULTIPART/alternative child container.
			 */
			MimeMultipart msg_body = new MimeMultipart("alternative");

			/**
			 * Create a wrapper for the HTML and text parts.
			 */
			MimeBodyPart wrap = new MimeBodyPart();
			/**
			 * Define the HTML part.
			 */
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(html_body_part, "text/html; charset=UTF-8");
			msg_body.addBodyPart(htmlPart);
			/**
			 * Add the child container to the wrapper object.
			 */
			wrap.setContent(msg_body);
			/**
			 * Create a multipart/mixed parent container.
			 */
			MimeMultipart msg = new MimeMultipart("mixed");
			/**
			 * Add the parent container to the message.
			 */
			message.setContent(msg);
			/**
			 * Add the multipart/alternative part to the message.
			 */
			msg.addBodyPart(wrap);
			/**
			 * Create a transport.
			 */
			Transport transport = session.getTransport();
			/**
			 * Send the message.
			 */
			try {
				System.out.println("Sending...");
				transport.connect(Literal.HOST, Literal.SMTP_USERNAME, Literal.SMTP_PASSWORD);
				/**
				 * Send the email.
				 */
				transport.sendMessage(message, message.getAllRecipients());
				System.out.println("Email sent!.......");
			} catch (Exception ex) {
				System.out.println("The email was not sent.");
				System.out.println("Error message: " + ex.getMessage());
				Utility.printStackTrace(ex, this.getClass().getName());
			} finally {
				/**
				 * Close and terminate the connection.
				 */
				transport.close();
			}
		} catch (Exception e) {
			Utility.printStackTrace(e, this.getClass().getName());
		}
	}
}
