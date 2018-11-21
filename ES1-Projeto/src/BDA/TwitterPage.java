package BDA;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
public class TwitterPage {

	/**
	 * Allows the user to enter new data for their accounts
	 */
	


	public void initialize(JFrame frame, String Email, JPanel panel) {
		frame.getContentPane().setBackground(UIManager.getColor("List.background"));
		frame.setBounds(100, 100, 863, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.window);
		menuBar.setBounds(0, 0, 881, 47);
		frame.getContentPane().add(menuBar);
		
		Image icone = new ImageIcon(this.getClass().getResource("/icone.png")).getImage();
		
		JButton button2 = new JButton("");
		button2.setBounds(774, 499, 59, 35);
		button2.setIcon(new ImageIcon(icone));
		menuBar.add(button2);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				Timeline tm= new Timeline();
				tm.initialize(frame, Email);
			}
		});
		
		
		
		JTextField textfield = new JTextField("");
		textfield.setBounds(200, 70, 400, 20);
		frame.add(textfield);
		JButton tweetar = new JButton("Publicar Tweet");
		tweetar.setBounds(620, 70, 150, 20);
		frame.add(tweetar);
		tweetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Twitter.tweet(textfield.getText(), Email);
			}
		});
		
		Image logout = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(130, 73, 603, 431);
		
		

		JButton button = new JButton("");
		button.setBounds(774, 499, 59, 35);
		frame.getContentPane().add(button);
		button.setIcon(new ImageIcon(logout));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 430, 557, -346);
		frame.getContentPane().add(scrollPane);
		
		Twitter twitter= new Twitter();
		ArrayList<String> list = twitter.getTweets(Email);
		ArrayList<Long> ids = Twitter.getTweetsId();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		 /**
		  * Inserts the posts in the table.
		 */
		 for (int i = 0; i<list.size(); i++) {
			 long indice= ids.get(i);
			 	String [] lineSplited = list.get(i).split(";;");
			   Vector<Object> row = new Vector<Object>();
			   row.add(lineSplited [0]);
			   row.add( lineSplited [1] );
			   row.add( lineSplited [2]);
			   row.add( lineSplited [3]);
			   JButton retweetar = new JButton("Retweetar");
			   retweetar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Twitter.retweet(indice, Email);
					}
				});
			   row.add(retweetar);
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
	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
