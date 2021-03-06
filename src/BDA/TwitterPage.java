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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TwitterPage {

	/**
	 * Allows the user to view and retweet tweets.
	 */

	private JPanel panel;
	private JFrame frame;
	private String Email;
	private Gestor gestor;
	private Twitter twitter;

	/**
	 * Instantiates a new twitter page.
	 *
	 * @param frame the frame
	 * @param Email the email
	 */
	public TwitterPage(JFrame frame, String Email) {
		this.frame = frame;
		this.Email = Email;
		twitter = new Twitter();
		initialize();
		setVisible(true);
	}

	/**
	 * Sets the visible.
	 *
	 * @param b the new visible
	 */
	public void setVisible(boolean b) {
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
	public void initialize() {
		gestor = new Gestor();
		JLabel labelBackground = new JLabel();
		//imagem retirada do site https://www.univercidade.net/wp-content/uploads/2015/12/Inaugurac%CC%A7a%CC%83o-EDIFI%CC%81CIO-CONVI%CC%81VIO_ISCTE-768x510.jpg
		Image imagem3 = new ImageIcon(this.getClass().getResource("/Imagens/iscte_wm_1.png")).getImage();
		labelBackground.setIcon(new ImageIcon(imagem3));
		labelBackground.setForeground(SystemColor.window);
		labelBackground.setBackground(SystemColor.activeCaption);
		labelBackground.setBounds(0, 48, 863, 594);

		
		//frame.getContentPane().setBackground(UIManager.getColor("List.background"));
		frame.setBounds(100, 100, 863, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		Image imagem = new ImageIcon(this.getClass().getResource("/Imagens/frameImage.png")).getImage();
		frame.setIconImage(imagem);
		
		panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(181, 100, 500, 431);
		

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.window);
		menuBar.setBounds(0, 0, 881, 47);
		frame.getContentPane().add(menuBar);

		Image icone = new ImageIcon(this.getClass().getResource("/Imagens/icone.png")).getImage();

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

		JTextArea textarea = new JTextArea("");
		javax.swing.border.Border border = BorderFactory.createLineBorder(Color.BLACK);
		textarea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textarea.setBounds(200, 50, 400, 40);
		frame.getContentPane().add(textarea);
		JButton tweetar = new JButton("Publicar Tweet");
		tweetar.setBounds(620, 70, 150, 20);
		frame.add(tweetar);
		tweetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				twitter.tweet(textarea.getText(), Email);
				JOptionPane optionPane = new JOptionPane("O tweet foi feito com sucesso!",
						JOptionPane.INFORMATION_MESSAGE);
				JDialog dialog = optionPane.createDialog("OK");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				frame.getContentPane().removeAll();
				frame.repaint();
				initialize();
			}
		});

		Image logout = new ImageIcon(this.getClass().getResource("/Imagens/logout.png")).getImage();
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

		ArrayList<String> list = twitter.getTweets(Email);
		ArrayList<Long> ids = twitter.getTweetsId();
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
		
		frame.add(panel);

		JButton retweetar = new JButton("Retweetar");
		retweetar.setBounds(734, 200, 100, 20);

		retweetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int i = table.getSelectedRow();
				long indice = ids.get(i);
				if (gestor.isRetweeted(indice, Email) == false) {
					twitter.retweet(indice, Email);
					JOptionPane optionPane = new JOptionPane("O retweet foi feito com sucesso!",
							JOptionPane.INFORMATION_MESSAGE);
					JDialog dialog = optionPane.createDialog("INFO");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
					gestor.writeRetweet(indice, Email);
				} else {
					JOptionPane fail = new JOptionPane("Este tweet já foi retweetado!",
							JOptionPane.ERROR_MESSAGE);
					JDialog ok = fail.createDialog("ERRO");
					ok.setAlwaysOnTop(true);
					ok.setVisible(true);
					gestor.writeRetweet(indice, Email);

				}
			}
		});
		frame.add(retweetar);
		frame.getContentPane().add(labelBackground);
	}

	/**
	 * Adds the popup.
	 *
	 * @param component the component
	 * @param popup     the popup
	 */
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
