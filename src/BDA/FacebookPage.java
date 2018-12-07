package BDA;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FacebookPage {

	/**
	 * It allows the user to make facebook posts.
	 */

	JPanel panel;
	private JFrame frame;
	private String Email;
	private Facebook facebook;
	
	/** @param   frame parameter gives an instance of the frame of the main page.
	 * @param    Email is the email relative to the user.
	 */
	
	public FacebookPage(JFrame frame, String Email) {
		this.frame = frame;
		this.Email = Email;
		facebook = new Facebook();
		initialize();
		setVisible(true);
	}
	
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public void initialize() {
		
		JLabel labelBackground = new JLabel();
		//imagem retirada do site https://www.univercidade.net/wp-content/uploads/2015/12/Inaugurac%CC%A7a%CC%83o-EDIFI%CC%81CIO-CONVI%CC%81VIO_ISCTE-768x510.jpg
		Image imagem3 = new ImageIcon(this.getClass().getResource("/Imagens/iscte_wm_1.png")).getImage();
		labelBackground.setIcon(new ImageIcon(imagem3));
		labelBackground.setForeground(SystemColor.window);
		labelBackground.setBackground(SystemColor.activeCaption);
		labelBackground.setBounds(0, 48, 863, 594);
		Image imagem = new ImageIcon(this.getClass().getResource("/Imagens/frameImage.png")).getImage();
		frame.setIconImage(imagem);
		
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

		Image icone = new ImageIcon(this.getClass().getResource("/Imagens/icone.png")).getImage();
		
		JTextArea textarea = new JTextArea("");
		javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK);
		textarea.setBorder(BorderFactory.createCompoundBorder(border, 
		BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textarea.setBounds(200, 50, 400, 40);
		frame.getContentPane().add(textarea);
		JButton postar = new JButton("Publicar Post no grupo de ESI");
		postar.setBounds(620, 70, 220, 20);
		frame.add(postar);
		postar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facebook.postOnGroup(textarea.getText(),Email);
				JOptionPane optionPane = new JOptionPane("O post foi feito com sucesso!",
						JOptionPane.INFORMATION_MESSAGE);
				JDialog dialog = optionPane.createDialog("OK");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				textarea.setText("");
			}
		});

		
		JButton button2 = new JButton("");
		button2.setBounds(774, 499, 59, 35);
		button2.setIcon(new ImageIcon(icone));
		menuBar.add(button2);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				@SuppressWarnings("unused")
				Timeline tm = new Timeline(frame, Email);
			}
		});


		Image logout = new ImageIcon(this.getClass().getResource("/Imagens/logout.png")).getImage();

		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(130, 100, 603, 431);

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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 430, 557, -346);
		frame.getContentPane().add(scrollPane);

		ArrayList<String> list = facebook.getFBNotifications(Email);
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
		table.setDefaultEditor(Object.class, null);
		
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
					int selected = lsm.getMinSelectionIndex();
					String res = list.get(selected);
					String[] lineSplited = res.split(";;");
					@SuppressWarnings("unused")
					Notification n = new Notification(lineSplited[0], lineSplited[1], lineSplited[2], lineSplited[3], null);
				}
				
			}
		});
		panel.add(new JScrollPane(table));
		frame.add(panel);
		frame.getContentPane().add(labelBackground);

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
