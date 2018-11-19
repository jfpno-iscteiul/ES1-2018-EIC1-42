package BDA;


import java.awt.Frame;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Gestor {
	
	
	private ArrayList<String> content;
	private ArrayList<String >fbPosts;
	private ArrayList<String> allNotifications;
	private Twitter twitter = new Twitter();
	private Facebook facebook= new Facebook();
	private ArrayList<String> filteredPosts;
	
	
	public void filterBySource(JPanel panel,ArrayList<String> Sources, Frame frame) {
		content = twitter.getTweets();
		System.out.println(Sources.isEmpty());
		fbPosts= facebook.getFBNotifications();
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
	
}
