package BDA;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class EmailPage {

	private JFrame frame;
	private String email;
	private int selected;
	private Email mail;
	/**
	 * Instantiates a new email page.
	 *
	 * @param frame the frame
	 * @param email the email
	 */
	public EmailPage(JFrame frame, String email) {
		this.email = email;
		this.frame = frame;
		mail = new Email();
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
	 * Gets the frame.
	 *
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		frame.getContentPane().setBackground(UIManager.getColor("List.background"));
		frame.setBounds(100, 100, 863, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.setResizable(false);
		frame.setIconImage((new ImageIcon("Imagens/frameImage.png").getImage()));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(130, 90, 603, 431);

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
			
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				@SuppressWarnings("unused")
				Timeline tm = new Timeline(frame, email);
			}
		});
		
		JButton b = new JButton("Enviar email");
		b.setBounds(200, 50, 150, 30);
		frame.add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendEmailPage s = new SendEmailPage(email);
			}
		});
		
		JButton bu = new JButton("Email Enviados");
		bu.setBounds(500, 50, 150, 30);
		frame.add(bu);
		bu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowSentEmail s = new ShowSentEmail(email);
			}
		});
		
		
		ArrayList<String> list = mail.getEmails(email);
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
		headers.add("Notificação");
		
		JTable table = new JTable(data, headers);
		panel.add(new JScrollPane(table));
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel model = table.getSelectionModel();
		table.setDefaultEditor(Object.class, null);
		
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
					selected = lsm.getMinSelectionIndex();
					String res = list.get(selected);
					String[] lineSplited = res.split(";;");
					Notification n = new Notification(lineSplited[0], lineSplited[1], lineSplited[2], lineSplited[3], lineSplited[4]);
				}
				
			}
		});

		Image logout = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		JButton button = new JButton("");
		button.setBounds(774, 499, 59, 35);
		frame.getContentPane().add(button);
		button.setIcon(new ImageIcon(logout));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainWindow.main(null);
			}
		});
		frame.add(panel);
	}
	
	/**
	 * Adds the popup.
	 *
	 * @param component the component
	 * @param popup the popup
	 */
	@SuppressWarnings("unused")
	private void addPopup(Component component, final JPopupMenu popup) {
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
