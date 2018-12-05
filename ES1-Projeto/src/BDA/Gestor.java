package BDA;

import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * The Class Gestor.
 */

public class Gestor {

	private ArrayList<String> tweets;
	private ArrayList<String> fbPosts;
	private ArrayList<String> emails;
	private static ArrayList<String> allNotifications;
	private ArrayList<String> filteredPosts;
	private static ArrayList<String> atualist;
	private JTable table;
	private int selected;


	/**
	 * Filter by source.
	 *
	 * @param panel the panel
	 * @param Sources the sources
	 * @param frame the frame
	 * @param Email the email
	 */

	/**
	 * @param panel   parameter gives an instance of the panel of the main page.
	 * @param Email   is the email relative to the user.
	 * @param frame   parameter gives an instance of the frame of the main page.
	 * @param Sources gives a list of sources to filter.
	 */

	public void filterBySource(JPanel panel, ArrayList<String> Sources, Frame frame, String Email) {
		allNotifications = new ArrayList<String>();
		atualist = new ArrayList<String>();

		if (XMLFile.haveTwitter(Email)) {
			tweets = getTweets(Email);
			allNotifications.addAll(tweets);
		}
		if (XMLFile.haveFacebook(Email)) {
			fbPosts = getFBPosts(Email);
			allNotifications.addAll(fbPosts);
		}
		if (XMLFile.haveEmail(Email)) {
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
			JOptionPane optionPane = new JOptionPane("Não existe dados para mostrar!", JOptionPane.INFORMATION_MESSAGE);
			JDialog dialog = optionPane.createDialog("Alerta!");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}

	}

	/**
	 * Adds the rows.
	 *
	 * @param panel the panel
	 * @param list the list
	 * @param frame the frame
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
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selected = (int) table.getSelectedRow();
			}
		});
		panel.add(new JScrollPane(table));
		frame.add(panel);
		frame.repaint();

	}

	/**
	 * Selected row.
	 */

	public void selectedRow (){
		String res = atualist.get(selected);
		String[] lineSplited = res.split(";;");
		if(lineSplited[0].equals("Email")) {
			@SuppressWarnings("unused")
			Notification n = new Notification(lineSplited[0], lineSplited[1], lineSplited[2], lineSplited[3], lineSplited[4]);
		} else {
			@SuppressWarnings("unused")
			Notification res1 = new Notification(lineSplited[0], lineSplited[1], lineSplited[2], lineSplited[3], null);
		}
	}

	/**
	 * Write tweets file.
	 *
	 * @param Email the email
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
	 * Write facebook posts file.
	 *
	 * @param Email the email
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
	 * Gets the tweets.
	 *
	 * @param Email the email
	 * @return the tweets
	 */

	/**
	 * @param Email is the email relative to the user.
	 * @return a list of tweets.
	 */
	public ArrayList<String> getTweets(String Email) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			@SuppressWarnings("resource")
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
	 * Gets the FB posts.
	 *
	 * @param Email the email
	 * @return the FB posts
	 */

	/**
	 * @param Email is the email relative to the user.
	 * @return a list of facebook posts.
	 */
	public ArrayList<String> getFBPosts(String Email) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			@SuppressWarnings("resource")
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
	 * Checks if is online.
	 *
	 * @return true, if is online
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

	/**
	 * Checks if is host available.
	 *
	 * @param hostName the host name
	 * @return true, if is host available
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static boolean isHostAvailable(String hostName) throws IOException {
		boolean aux = false;
		try (Socket socket = new Socket()) {
			int port = 80;
			InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
			socket.connect(socketAddress, 3000);
			aux=true;
		} catch (UnknownHostException unknownHost) {
		}
		return aux;
	}

	/**
	 * Write emails file.
	 *
	 * @param email the email
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
	 * Gets the email.
	 *
	 * @param Email the email
	 * @return the email
	 */

	/**
	 * @param Email is the email relative to the user.
	 * @return a list of emails.
	 */

	public ArrayList<String> getEmail(String Email) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			@SuppressWarnings("resource")
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

	/**
	 * Write a txt file with the tweets wich are already retweeted.
	 */

	/** * @param    tweet is id of the tweet.
	 * @param   email is the email relative to the user.
	 */


	public void writeRetweet(long tweet , String Email) {
		try(FileWriter fw = new FileWriter("Retweets/" + Email + ".txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
		{
			out.println(tweet);
		} catch (IOException e) {
		}
	}

	/**
	 * Verify if one tweet is already retweeted.
	 */

	/** * @param    tweet is id of the tweet.
	 * @param   email is the email relative to the user.
	 * @return if a twitter has already been retweet.
	 */

	public static boolean isRetweeted (long tweet, String Email) {
		boolean res=false;
		Scanner scanner;
		String myString = Long.toString(tweet);
		try {
			scanner = new Scanner(new File("Retweets/" + Email + ".txt"));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if( line.equals(myString)) {
					res= true;
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return res;

	}

	/**
	 * Filters the results by a given word.
	 */

	/**
	 * @param list is the list to be filtered.
	 * @param word is the word to be searched.
	 * @return a list of filtered notifications by word.
	 */

	public static  ArrayList<String> filterByWord(ArrayList<String> list, String word){
		ArrayList<String> result = new ArrayList<String>();
		for(int i =0;i!= list.size();i++) {
			if (list.get(i).toLowerCase().contains(word.toLowerCase())) {
				result.add(list.get(i));
			}
		}
		atualist=result;
		return result;
	}

	public static ArrayList<String> filterByDate(String searchDate){
		if (!searchDate.isEmpty()) {
			ArrayList<String> result= new ArrayList <String>();
			String [] dateForSearch = searchDate.split("-");
			String day = dateForSearch[0];
			String month = dateForSearch[1];
			String year = dateForSearch[2];

			for(int i =0; i!= allNotifications.size();i++) {
				String [] data = allNotifications.get(i).split(";;");
				String datas=data[1];
				String [] date2= datas.split(" ");
				String month2 = auxDate(date2[1]);
				if(date2[2].equals(day)&& date2[5].equals(year) && month.equals(month2)) {
					result.add(allNotifications.get(i));
				}
			}
			atualist = result;
			return result;
		}
		return allNotifications;
	}

		/**
		 * Returns a String relative to the month
		 */

		/**
		 * @param string is the string to be changed.
		 * @return a month.
		 */

		public static String auxDate(String string) {
			String result="";
			if(string.equals("Jan")) {
				result="01";
			}else if(string.equals("Feb")) {
				result="02";
			}else if(string.equals("Mar")) {
				result="03";
			}else if(string.equals("Apr")) {
				result="04";
			}else if(string.equals("May")) {
				result="05";
			}else if(string.equals("Jun")) {
				result="06";
			}else if(string.equals("Jul")) {
				result="07";
			}else if(string.equals("Aug")) {
				result="08";
			}else if(string.equals("Sep")) {
				result="09";
			}else if(string.equals("Oct")) {
				result="10";
			}else if(string.equals("Nov")) {
				result="11";
			}else if(string.equals("Dec")) {
				result="12";
			}
			return result;
		}

		
		/**
		 * Returns an ordered list by date.
		 */

		/**
		 * @return an ordered list by date.
		 */

		
		public static ArrayList<String> orderByDate(){
			ArrayList<Data> datesList= new ArrayList<Data>();
			ArrayList<String> orderedList= new ArrayList<String>();
			for(int i=0;i!= atualist.size();i++) {
				String [] datas= atualist.get(i).split(";;");
				String listDate= datas[1];
				String [] data= listDate.split(" ");
				int dia=Integer.parseInt(data[2]);
				int mes=Integer.parseInt(auxDate(data[1]));
				int ano=Integer.parseInt(data[5]);
				String [] horas =data[3].split(":");
				int hora=Integer.parseInt(horas[0]); 
				int min=Integer.parseInt(horas[1]); 
				int seg=Integer.parseInt(horas[2]); 
				Data finalData= new Data(dia,mes,ano,hora,min,seg);
				datesList.add(finalData);
			}
			
			datesList.sort(new ComparadorDatas());
			
			
			for(int i=0;i!= atualist.size();i++) {
				String [] datas= atualist.get(i).split(";;");
				String listDate= datas[1];
				String [] data= listDate.split(" ");
				int dia=Integer.parseInt(data[2]);
				int mes=Integer.parseInt(auxDate(data[1]));
				int ano=Integer.parseInt(data[5]);
				String [] horas =data[3].split(":");
				int hora=Integer.parseInt(horas[0]); 
				int min=Integer.parseInt(horas[1]); 
				int seg=Integer.parseInt(horas[2]); 
				Data finalData= new Data(dia,mes,ano,hora,min,seg);
				for(int k=0;k!= datesList.size();k++) {
					if(finalData.toString().equals(datesList.get(k).toString())) {
						orderedList.add(atualist.get(i));
					}
				}
			}
			return orderedList;
		}


		/**
		 * @return an actual list of notifications.
		 */
		public static ArrayList<String> getAtualist(){
			return atualist;
		}

	}
