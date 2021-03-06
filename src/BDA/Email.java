package BDA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.jsoup.Jsoup;

/**
 * The Class Email.
 */
public class Email {
	private XMLFile xml = new XMLFile();

	/**
	 * Gets the emails.
	 *
	 * @param email the email
	 * @return the emails
	 */
	public ArrayList<String> getEmails(String email) {
		ArrayList<String> result = new ArrayList<String>();
		String password = xml.getAttributteByEmail(email, "PassEmail");

		// set properties
		Properties properties = new Properties();
		// You can use imap or imaps , *s -Secured
		properties.put("mail.store.protocol", "imaps");
		// Host Address of Your Mail
		properties.put("mail.imaps.host", "imap.outlook.com");
		// Port number of your Mail Host
		properties.put("mail.imaps.port", "993");

		try {
			// create a session
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see javax.mail.Authenticator#getPasswordAuthentication()
				 */
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email, password);
				}
			});

			// SET the store for IMAPS
			Store store = session.getStore("imaps");

			System.out.println("Connection initiated......");
			// Trying to connect IMAP server
			store.connect(email, password);
			System.out.println("Connection is ready :)");

			// Get inbox folder
			Folder inbox = store.getFolder("inbox");
			// SET readonly format (*You can set read and write)
			inbox.open(Folder.READ_ONLY);

			// Inbox email count
			int messageCount = inbox.getMessageCount();
			System.out.println("Total Messages in INBOX:- " + messageCount);

			// Print Last 10 email information
			for (int i = 10; i > 0; i--) {
				String from = MimeUtility.decodeText(inbox.getMessage(messageCount - i).getFrom()[0].toString());
				String subject = inbox.getMessage(messageCount - i).getSubject().toString();
				String content = getText(inbox.getMessage(messageCount - i));
				if (content.isEmpty()) {
					content = "Erro! Ver no browser.";
				}
				String date = inbox.getMessage(messageCount - i).getReceivedDate().toString();
				result.add("Email" + ";;" + date + ";;" + from + ";;" + subject + ";;" + content);
			}

			inbox.close(true);
			store.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Gets the sent emails.
	 *
	 * @param email the email
	 * @return the sent emails
	 */
	public ArrayList<String> getSentEmails(String email) {
		ArrayList<String> result = new ArrayList<String>();
		String password = xml.getAttributteByEmail(email, "PassEmail");
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imaps.host", "imap.outlook.com");
		properties.put("mail.imaps.port", "993");
		try {
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see javax.mail.Authenticator#getPasswordAuthentication()
				 */
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email, password);
				}
			});

			Store store = session.getStore("imaps");

			System.out.println("Connection initiated......");
			store.connect(email, password);
			System.out.println("Connection is ready :)");
			Folder inbox = store.getFolder("sent items");
			inbox.open(Folder.READ_ONLY);

			int messageCount = inbox.getMessageCount();

			// Print Last 10 email information
			for (int i = 5; i > 0; i--) {
				String to = MimeUtility.decodeText(inbox.getMessage(messageCount - i).getAllRecipients()[0].toString());
				String subject = inbox.getMessage(messageCount - i).getSubject().toString();
				String content = getText(inbox.getMessage(messageCount - i));
				if (content.isEmpty()) {
					content = "Erro! Ver no browser.";
				}
				String date = inbox.getMessage(messageCount - i).getReceivedDate().toString();
				result.add("Email" + ";;" + date + ";;" + to + ";;" + subject + ";;" + content);
			}

			inbox.close(true);
			store.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Send email.
	 *
	 * @param email   the email
	 * @param to      the to
	 * @param subject the subject
	 * @param text    the text
	 */
	public void sendEmail(String email, String to, String subject, String text) {
		String password = xml.getAttributteByEmail(email, "PassEmail");
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see javax.mail.Authenticator#getPasswordAuthentication()
			 */
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the text.
	 *
	 * @param message the message
	 * @return the text
	 */
	private String getText(Message message) {
		String result = new String();
		if (message instanceof MimeMessage) {
			MimeMessage m = (MimeMessage) message;
			Object contentObject;
			try {
				contentObject = m.getContent();

				if (contentObject instanceof Multipart) {
					BodyPart clearTextPart = null;
					BodyPart htmlTextPart = null;
					Multipart content = (Multipart) contentObject;
					int count = content.getCount();
					for (int i = 0; i < count; i++) {
						BodyPart part = content.getBodyPart(i);
						if (part.isMimeType("text/plain")) {
							clearTextPart = part;
							break;
						} else if (part.isMimeType("text/html")) {
							htmlTextPart = part;
						}
					}

					if (clearTextPart != null) {
						result = (String) clearTextPart.getContent();
					} else if (htmlTextPart != null) {
						String html = (String) htmlTextPart.getContent();
						result = Jsoup.parse(html).text();
					}

				} else if (contentObject instanceof String) {
					result = (String) contentObject;
				}
			} catch (IOException | MessagingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
