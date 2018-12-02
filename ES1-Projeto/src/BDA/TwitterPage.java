package BDA;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class TwitterPage {

	/**
	 * Allows the user to view and retweet tweets.
	 */
	
	private JPanel panel;
	private JFrame frame;
	private String Email;
	
	public TwitterPage(JFrame frame, String Email) {
		this.frame = frame;
		this.Email = Email;
		initialize();
		setVisible(true);
	}
	
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void initialize() {
		frame.getContentPane().setBackground(UIManager.getColor("List.background"));
		frame.setBounds(100, 100, 863, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(130, 73, 603, 431);

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
				@SuppressWarnings("unused")
				Timeline tm= new Timeline(frame, Email); 
			}
		});
		
		
		
		JTextArea textarea = new JTextArea("");
		javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK);
		textarea.setBorder(BorderFactory.createCompoundBorder(border, 
		BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textarea.setBounds(200, 50, 400, 40);
		frame.getContentPane().add(textarea);
		JButton tweetar = new JButton("Publicar Tweet");
		tweetar.setBounds(620, 70, 150, 20);
		frame.add(tweetar);
		tweetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Twitter.tweet(textarea.getText(), Email);
				JOptionPane optionPane = new JOptionPane("O tweet foi feito com sucesso!", JOptionPane.INFORMATION_MESSAGE);    
				JDialog dialog = optionPane.createDialog("OK");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				textarea.setText("");
				frame.repaint();
			}
		});
		
		Image logout = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(130, 100, 603, 431);
		
		

		JButton button = new JButton("");
		button.setBounds(774, 499, 59, 35);
		frame.getContentPane().add(button);
		button.setIcon(new ImageIcon(logout));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 430, 557, -346);
		frame.getContentPane().add(scrollPane);
		
		ArrayList<String> list = Twitter.getTweets(Email);
		ArrayList<Long> ids = Twitter.getTweetsId();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		 /**
		  * Inserts the posts in the table.
		 */
		 for (int i = 0; i<list.size(); i++) {
		//	 long indice= ids.get(i);
			 	String [] lineSplited = list.get(i).split(";;");
			   Vector<Object> row = new Vector<Object>();
			   row.add( lineSplited [1] );
			   row.add( lineSplited [2]);
			   row.add( lineSplited [3]);
	//		   JButton retweetar = new JButton("Retweetar");
//			   retweetar.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						Twitter.retweet(indice, Email);
//					}
//				});
//			   row.add(retweetar);
			   data.add(row);

		 }
		 
		 
	       Vector<String> headers = new Vector<String>();
	       headers.add("Data");
	       headers.add("User");
	       headers.add("Notificação");

	       JTable table = new JTable( data, headers );
	       //table.setBounds(x, y, width, height);
	       panel.add( new JScrollPane( table ));
	       
	       frame.add(panel);
	     
	       JButton retweetar = new JButton("Retweetar");
	       retweetar.setBounds(734, 200, 100, 20);
	       
		   retweetar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i = table.getRowCount();
					long indice= ids.get(i+1);
					Twitter.retweet(indice, Email);
					JOptionPane optionPane = new JOptionPane("O retweet foi feito com sucesso!", JOptionPane.INFORMATION_MESSAGE);    
					JDialog dialog = optionPane.createDialog("OK");
				}
			});
		   frame.add(retweetar);
	//	   row.add(retweetar);
		
		
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
