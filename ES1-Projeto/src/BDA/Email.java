package BDA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

public class Email {

	public static ArrayList<String> getEmails(String email) {
		ArrayList<String> result = new ArrayList<String>();
		String password = XMLFile.getAttributteByEmail(email, "Password");

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
			Session session = Session.getDefaultInstance(properties, null);
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
				String from = inbox.getMessage(messageCount - i).getFrom()[0].toString();
				String subject = inbox.getMessage(messageCount - i).getSubject().toString();
				String content = getTextFromMessage(inbox.getMessage(messageCount - i));
				String date = inbox.getMessage(messageCount - i).getReceivedDate().toString();
				result.add("Email" + ";;" + date + ";;" + from + ";;" + subject );
		//		System.out.println("Mail Subject:- " + inbox.getMessage(messageCount - i).getSubject().toString());
		//		System.out.println("Mail From:- " + inbox.getMessage(messageCount - i).getFrom()[0].toString());
		//		System.out.println("Mail Content:- " + getTextFromMessage(inbox.getMessage(messageCount - i)));
		//		System.out.println("------------------------------------------------------------");
			}

			inbox.close(true);
			store.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	private static String getTextFromMessage(Message message) throws MessagingException, IOException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}
		
}
