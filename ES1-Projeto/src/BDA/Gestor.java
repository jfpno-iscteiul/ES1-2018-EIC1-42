package BDA;
import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Gestor {
	
	private static Twitter twitter = new Twitter();
	private ArrayList<String> content;
	private ArrayList<String >fbPosts;
	private ArrayList<String> allNotifications;
	private Facebook facebook= new Facebook();
	private ArrayList<String> filteredPosts;
	
	
	public void filterBySource(JPanel panel,ArrayList<String> Sources, Frame frame, String Email) {
		content=twitter.getTweets(Email);
		fbPosts= facebook.getFBNotifications(Email);
		allNotifications = new ArrayList<String> ();
		allNotifications.addAll(content);
		allNotifications.addAll(fbPosts);
		filteredPosts= new ArrayList<String>();
		if(allNotifications!= null) {
			if(Sources.isEmpty()==false) {
				for (int i = 0; i<allNotifications.size(); i++) {
					String [] lineSplited = allNotifications.get(i).split(";;");
					for(int j=0; j!= Sources.size();j++) {
						if(lineSplited[0]== Sources.get(j)) {
							filteredPosts.add(allNotifications.get(i));
						}
					}
				}
			}else {
				filteredPosts=allNotifications;
				addRows(panel, filteredPosts, frame);
			}	
			
			if(filteredPosts!=null) {
				addRows(panel, filteredPosts, frame);
			}
	
		}else {
			JOptionPane optionPane = new JOptionPane("Não há dados para mostrar!", JOptionPane.ERROR_MESSAGE);
			JDialog dialog = optionPane.createDialog("ERRO!");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
		
	}
	
	public void addRows(JPanel panel, ArrayList<String> list, Frame frame) {
		
	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	 /**
	  * Inserts the posts in the table.
	 */
	 
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
       panel.add( new JScrollPane( table ));
       frame.add(panel);
	
	}
	
	public static void writeTweetsFile (String Email){
		ArrayList<String> tweets= twitter.getTweets(Email);
		File fold=new File("../src/Tweets/"+ Email +".txt");
		fold.delete();
		File fnew=new File("Tweets/"+ Email +".txt");
		for(int i =0; i!=tweets.size();i++) {

			try {
				FileWriter f2 = new FileWriter(fnew, false);
				f2.write(tweets.get(i));
				f2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}       
		}
		
	}

}
