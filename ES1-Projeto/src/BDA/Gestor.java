package BDA;

import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Class responsible for managing the accounts and adding content to the
 * timeline.
 */

public class Gestor {

	private ArrayList<String> tweets;
	private ArrayList<String> fbPosts;
	private ArrayList<String> emails;
	private ArrayList<String> allNotifications;
	private ArrayList<String> filteredPosts;
	private ArrayList<String> atualist;
	private JTable table;
	/**
	 * Filters notifications according to their source
	 */

	/**
	 * @param panel   parameter gives an instance of the panel of the main page.
	 * @param Email   is the email relative to the user.
	 * @param frame   parameter gives an instance of the frame of the main page.
	 * @param Sources gives a list of sources to filter.
	 */

	public void filterBySource(JPanel panel, ArrayList<String> Sources, Frame frame, String Email) {
		allNotifications = new ArrayList<String>();

		if (XMLFile.haveTwitter(Email)) {
			// writeTweetsFile(Email);
			tweets = getTweets(Email);
			allNotifications.addAll(tweets);
		}
		if (XMLFile.haveFacebook(Email)) {
			// writeFacebookPostsFile(Email);
			fbPosts = getFBPosts(Email);
			allNotifications.addAll(fbPosts);
		}
		if (XMLFile.haveEmail(Email)) {
			// writeEmailsFile(Email);
			emails = getEmail(Email);
			allNotifications.addAll(emails);
		}

		filteredPosts = new ArrayList<String>();
		if (allNotifications != null) {
			if (Sources.size() != 0) {
				for (int i = 0; i < Sources.size(); i++) {
					if (Sources.get(i).equals("Twitter")) {
						filteredPosts.addAll(tweets);
					}
					if (Sources.get(i).equals("Facebook")) {
						filteredPosts.addAll(fbPosts);
					}
					if (Sources.get(i).equals("Email")) {
						filteredPosts.addAll(emails);
					}
				}
				atualist.addAll(filteredPosts);
				addRows(panel, filteredPosts, frame);
			} else {
				atualist.addAll(allNotifications);
				addRows(panel, allNotifications, frame);
			}
		} else {
			JOptionPane optionPane = new JOptionPane("N�o h� dados para mostrar!", JOptionPane.ERROR_MESSAGE);
			JDialog dialog = optionPane.createDialog("ERRO!");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}

	}

	/**
	 * Inserts the posts in the table.
	 */

	/**
	 * @param panel parameter gives an instance of the panel of the main page.
	 * @param frame parameter gives an instance of the frame of the main page.
	 * @param list  gives a list of Strings to add to the table.
	 */
	public void addRows(JPanel panel, ArrayList<String> list, Frame frame) {

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		for (int i = 0; i < list.size(); i++) {
			String[] lineSplited = list.get(i).split(";;");
			Vector<Object> row = new Vector<Object>();
			row.add(lineSplited[0]);
			row.add(lineSplited[1]);
			row.add(lineSplited[2]);
			row.add(lineSplited[3]);
			data.add(row);

		}

		Vector<String> headers = new Vector<String>();
		headers.add("Plataforma");
		headers.add("Data");
		headers.add("User");
		headers.add("Notificação");

		
		table = new JTable(data, headers);
		table.setDefaultEditor(Object.class, null);

		panel.add(new JScrollPane(table));
		frame.add(panel);
		frame.repaint();

	}
	/**
	 * Show the notification in a new window.
	 */


	public void selectedRow (){

		int i = table.getRowCount();
		System.out.println(i);
		String res = atualist.get(i-1);
		System.out.println(res);
		String[] lineSplited = res.split(";;");
		Notification res1 = new Notification(lineSplited[0], lineSplited[1], lineSplited[2], lineSplited[3]);
		
	}

	/**
	 * Write a file with the contents of each user related to twitter when it is
	 * offline.
	 */

	/**
	 * @param Email is the email relative to the user.
	 */

	public static void writeTweetsFile(String Email) {
		ArrayList<String> tweets = Twitter.getTweets(Email);
		File fold = new File("../src/Tweets/" + Email + ".txt");
		fold.delete();
		File fnew = new File("Tweets/" + Email + ".txt");
		FileWriter f2 = null;
		try {
			f2 = new FileWriter(fnew, false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i != tweets.size(); i++) {
			try {
				String text = tweets.get(i).replace("\n", "");
				f2.write(text + "\n");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			f2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Write a file with the contents of each user related to facebook when it is
	 * offline.
	 */

	/**
	 * @param Email is the email relative to the user.
	 */

	public static void writeFacebookPostsFile(String Email) {
		ArrayList<String> posts = Facebook.getFBNotifications(Email);
		File fold = new File("../src/FBPosts/" + Email + ".txt");
		fold.delete();
		File fnew = new File("FBPosts/" + Email + ".txt");
		FileWriter f2 = null;
		try {
			f2 = new FileWriter(fnew, false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			for (int i = 0; i != posts.size(); i++) {
				String text = posts.get(i).replace("\n", "");
				f2.write(text + "\n");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			f2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets tweets from a particular user.
	 */

	/**
	 * @param Email is the email relative to the user.
	 * @return a list of tweets.
	 */
	public ArrayList<String> getTweets(String Email) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Scanner scanner = new Scanner(new File("Tweets/" + Email + ".txt"));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.add(line);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Gets Facebook posts from a particular user.
	 */

	/**
	 * @param Email is the email relative to the user.
	 * @return a lits of facebook posts.
	 */
	public ArrayList<String> getFBPosts(String Email) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Scanner scanner = new Scanner(new File("FBPosts/" + Email + ".txt"));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * Checks if a user is online.
	 */

	/**
	 * @return true if the user is online and false otherwise.
	 * @throws IOException
	 */

	public static boolean isOnline() {
		try {
			return isHostAvailable("google.com") || isHostAvailable("outlook.com") || isHostAvailable("facebook.com")
					|| isHostAvailable("twitter.com");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean isHostAvailable(String hostName) throws IOException {
		try (Socket socket = new Socket()) {
			int port = 80;
			InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
			socket.connect(socketAddress, 3000);
			return true;
		} catch (UnknownHostException unknownHost) {
			return false;
		}
	}

	/**
	 * Write a file with the contents of each user related to email when it is
	 * offline.
	 */

	/**
	 * @param Email is the email relative to the user.
	 */
	
	public static void writeEmailsFile(String email) {
		ArrayList<String> emails = Email.getEmails(email);
		File fold = new File("../src/Emails/" + email + ".txt");
		fold.delete();
		File fnew = new File("Emails/" + email + ".txt");
		FileWriter f2 = null;
		try {
			f2 = new FileWriter(fnew, false);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			for (int i = 0; i != emails.size(); i++) {
				String text = emails.get(i).replace("\n", "");
				f2.write(text + "\n");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			f2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Gets emails from a particular user.
	 */

	/**
	 * @param Email is the email relative to the user.
	 * @return a lits of emails.
	 */
	
	public ArrayList<String> getEmail(String Email) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Scanner scanner = new Scanner(new File("Emails/" + Email + ".txt"));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

}
