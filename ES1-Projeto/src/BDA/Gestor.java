package BDA;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Class responsible for managing the accounts and adding content to the timeline.
 */

public class Gestor {
	
	private static Twitter twitter = new Twitter();
	private ArrayList<String> content;
	private ArrayList<String >fbPosts;
	private ArrayList<String> allNotifications;
	private ArrayList<String> filteredPosts;
	
	
	/**
	 * Filters notifications according to their source
	 */
	
	public void filterBySource(JPanel panel,ArrayList<String> Sources, Frame frame, String Email) {
		writeTweetsFile (Email);
		writeFacebookPostsFile(Email);
		content=getTweets(Email);
		fbPosts= getFBPosts(Email);
		allNotifications = new ArrayList<String> ();
		allNotifications.addAll(content);
		allNotifications.addAll(fbPosts);
		filteredPosts= new ArrayList<String>();
		if(allNotifications!= null) {
			

			
		if(Sources.size()!=0) {
			for (int i = 0 ; i< Sources.size(); i++) {
				if (Sources.get(i).equals("Twitter")) {
					filteredPosts.addAll(content);
				}
				
				else if(Sources.get(i).equals("Facebook")) {
					filteredPosts.addAll(fbPosts);
				}
				else if (Sources.get(i).equals("Email")) {
					// colocar recurso do email
				}
			}
			System.out.println(filteredPosts);
			addRows(panel, filteredPosts, frame);
			
		}
			else {
				addRows(panel, allNotifications, frame);
			}
		}else {
			JOptionPane optionPane = new JOptionPane("Não há dados para mostrar!", JOptionPane.ERROR_MESSAGE);
			JDialog dialog = optionPane.createDialog("ERRO!");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
		
		
	}
	
	/**
	  * Inserts the posts in the table.
	 */
	
	public void addRows(JPanel panel, ArrayList<String> list, Frame frame) {
	
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	 
	panel.removeAll();
	panel.revalidate();
	panel.repaint();
	 for (int i = 0; i<list.size(); i++) {
		 	String [] lineSplited = list.get(i).split(";;");
		   Vector<Object> row = new Vector<Object>();
		   row.add(lineSplited [0]);
		   row.add( lineSplited [1] );
		   row.add( lineSplited [2]);
		   row.add( lineSplited [3]);
		   data.add(row);

	 }
	 
       Vector<String> headers = new Vector<String>();
       headers.add("Plataforma");
       headers.add("Data");
       headers.add("User");
       headers.add( "Notificação");


       JTable table = new JTable( data, headers );
       table.setDefaultEditor(Object.class, null);
  
       panel.add( new JScrollPane( table ));
       frame.add(panel);
       frame.repaint();
	
	}
	
	/**
	  * Write a file with the contents of each user related to twitter when it is offline.
	 */
	
	public static void writeTweetsFile (String Email) {
		ArrayList<String> tweets= twitter.getTweets(Email);
		if(tweets.size()!=0) {
			System.out.println("VOU APAGAR O FICHEIRO ANTIGO DOS TWEETS");
			File fold=new File("../src/Tweets/"+ Email +".txt");
			fold.delete();
			File fnew=new File("Tweets/"+ Email +".txt");
			FileWriter f2= null;
			try {
				f2 = new FileWriter(fnew, false);
			} catch (IOException e1) { e1.printStackTrace();}
			for(int i =0; i!=tweets.size();i++) {
				try {
					String text = tweets.get(i).replace("\n", "");
					f2.write(text + "\n");
				
				} catch (IOException e) {
					e.printStackTrace();
				}     
			}
		
			try {
				f2.close();
			} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	/**
	  * Write a file with the contents of each user related to facebook when it is offline.
	 */
	
	public static void writeFacebookPostsFile (String Email) {
		ArrayList<String> posts = Facebook.getFBNotifications(Email);
		
		if(posts.size()!=0) {
			System.out.println("VOU APAGAR O FICHEIRO ANTIGO DOS POSTS");
			File fold=new File("../src/FBPosts/"+ Email +".txt");
			fold.delete();
			File fnew=new File("FBPosts/"+ Email +".txt");
			FileWriter f2=null;
			try {
			f2 = new FileWriter(fnew, false);
			} catch (IOException e1) {e1.printStackTrace();}
				try {
					for(int i =0; i!=posts.size();i++) {
						String text = posts.get(i).replace("\n", "");
						f2.write(text + "\n");
					}
				} catch (IOException e1) {e1.printStackTrace();}
				try {
					f2.close();
				} catch (IOException e) {e.printStackTrace();}     
		}
		
	}
	


	/**
	 * Gets tweets from a particular user.
	*/
	public static ArrayList <String> getTweets (String Email) {
		ArrayList <String> result = new ArrayList <String>();
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner (new File("Tweets/"+ Email +".txt"));
			while(scanner.hasNextLine()){
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
	
	public static ArrayList<String> getFBPosts (String Email) {
		ArrayList <String> result = new ArrayList <String>();
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner (new File("FBPosts/"+ Email +".txt"));
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				result.add(line);
			}
		}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		return result;
	
		}
	

	/**
	 * Checks if a user is online.
	*/
	public static boolean isOnline(String Email) {
		ArrayList<String> posts = Facebook.getFBNotifications(Email);
		ArrayList<String> tweets= twitter.getTweets(Email);
		if (posts.size()== 0 && tweets.size()==0)
			return false;
		else
			return true;
	}
	

}
