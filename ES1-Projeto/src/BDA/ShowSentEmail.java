package BDA;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ShowSentEmail {
	
	private String email;
	private JFrame frame;
	private Email mail;
	
	
	/**
	 * Instantiates a new show sent email.
	 *
	 * @param email the email
	 */
	public ShowSentEmail(String email) {
		this.email = email;
		mail=new Email();
		frame = new JFrame("Emails Enviados");
		initialize();
		setVisible(true);
	}

	
	/**
	 * Sets the visible.
	 *
	 * @param b the new visible
	 */
	private void setVisible(boolean b) {
		frame.setVisible(b);
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		frame.getContentPane().setBackground(UIManager.getColor("List.background"));
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		Image imagem = new ImageIcon(this.getClass().getResource("/frameImage.png")).getImage();
		frame.setIconImage(imagem);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(10, 10, 580, 430);
		
		ArrayList<String> list = mail.getSentEmails(email);
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		/**
		 * Inserts the posts in the table.
		 */
		for (int i = 0; i < list.size(); i++) {
			String[] lineSplited = list.get(i).split(";;");
			Vector<Object> row = new Vector<Object>();
			row.add(lineSplited[1]);
			row.add(lineSplited[2]);
			row.add(lineSplited[3]);
			data.add(row);

		}

		Vector<String> headers = new Vector<String>();
		headers.add("Data");
		headers.add("User");
		headers.add("Notifica��o");
		
		JTable table = new JTable(data, headers);
		panel.add(new JScrollPane(table));
		table.setDefaultEditor(Object.class, null);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel model = table.getSelectionModel();
		
		model.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					return;
				} 
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				
				if(lsm.isSelectionEmpty()) {
					return;
				} else {
					int selected = lsm.getMinSelectionIndex();
					String res = list.get(selected);
					String[] lineSplited = res.split(";;");
					Notification n = new Notification(lineSplited[0], lineSplited[1], lineSplited[2], lineSplited[3], lineSplited[4]);
				}
				
			}
		});
		
		frame.add(panel);
		
	}


	public JFrame getFrame() {
		return frame;
	}

}
