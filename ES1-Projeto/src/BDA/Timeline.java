package BDA;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class Timeline {

	/**
	 * This is where you can view the content of your accounts.
	 */

	private JTextField txtEscrevaAquiA;
	private JTextField txtEscrevaAqui;
	private Gestor gestor;
	private ArrayList<String> sourceFilters;
	private JPanel panel;
	private JFrame frame;
	private String Email;

	public Timeline(JFrame frame, String Email) {
		this.frame = frame;
		this.Email = Email;
		initialize();
		setVisible(true);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	/**
	 * if the user turns offline.
	 */
	
	@SuppressWarnings("static-access")
	private void writeFiles() {
		if(XMLFile.haveTwitter(Email)) {
			gestor.writeTweetsFile(Email);
		}
		if(XMLFile.haveFacebook(Email)) {
			gestor.writeFacebookPostsFile(Email);
		}
		if(XMLFile.haveEmail(Email)) {
			gestor.writeEmailsFile(Email);
		}
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	@SuppressWarnings("static-access")
	void initialize() {
		gestor = new Gestor();
		writeFiles();
		
		frame.getContentPane().setBackground(UIManager.getColor("List.background"));
		frame.setBounds(100, 100, 863, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		sourceFilters = new ArrayList<String>();

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.window);
		menuBar.setBounds(0, 0, 881, 47);
		frame.getContentPane().add(menuBar);

		JLabel lblNewLabel = new JLabel(" ");
		Image icone = new ImageIcon(this.getClass().getResource("/icone.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(icone));
		menuBar.add(lblNewLabel);

		JMenu mnOrdenar = new JMenu("Ordenar");
		mnOrdenar.setFont(new Font("Calibri", Font.BOLD, 18));
		menuBar.add(mnOrdenar);

		JMenuItem mntmAntigasPrimeiro = new JMenuItem("Antigas Primeiro");
		mntmAntigasPrimeiro.setFont(new Font("Calibri", Font.BOLD, 16));
		mnOrdenar.add(mntmAntigasPrimeiro);

		JMenuItem mntmRecentesPrimeiro = new JMenuItem("Recentes Primeiro");
		mntmRecentesPrimeiro.setFont(new Font("Calibri", Font.BOLD, 16));
		mnOrdenar.add(mntmRecentesPrimeiro);

		JMenu mnFiltrar = new JMenu("Filtrar");
		mnFiltrar.setFont(new Font("Calibri", Font.BOLD, 18));
		menuBar.add(mnFiltrar);

		JMenu mnFonteDeInformao = new JMenu("Fonte de InformaÃ§Ã£o");
		mnFonteDeInformao.setFont(new Font("Calibri", Font.BOLD, 16));
		mnFiltrar.add(mnFonteDeInformao);

		JCheckBox chckbxFacebook = new JCheckBox("Facebook");
		chckbxFacebook.setFont(new Font("Calibri", Font.BOLD, 16));
		mnFonteDeInformao.add(chckbxFacebook);
		chckbxFacebook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxFacebook.isSelected()) {
					sourceFilters.add("Facebook");
					gestor.filterBySource(panel, sourceFilters, frame, Email);
				} else {
					sourceFilters.remove("Facebook");
					gestor.filterBySource(panel, sourceFilters, frame, Email);

				}
			}
		});

		JCheckBox chckbxEmail = new JCheckBox("Email");
		chckbxEmail.setFont(new Font("Calibri", Font.BOLD, 16));
		mnFonteDeInformao.add(chckbxEmail);
		chckbxEmail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxEmail.isSelected()) {
					sourceFilters.add("Email");
					gestor.filterBySource(panel, sourceFilters, frame, Email);
				} else {
					sourceFilters.remove("Email");
					gestor.filterBySource(panel, sourceFilters, frame, Email);

				}
			}
		});

		JCheckBox chckbxTwitter = new JCheckBox("Twitter");
		chckbxTwitter.setFont(new Font("Calibri", Font.BOLD, 16));
		mnFonteDeInformao.add(chckbxTwitter);
		chckbxTwitter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxTwitter.isSelected()) {
					sourceFilters.add("Twitter");
					gestor.filterBySource(panel, sourceFilters, frame, Email);
				} else {
					sourceFilters.remove("Twitter");
					gestor.filterBySource(panel, sourceFilters, frame, Email);

				}

			}
		});

		JMenu mnPalavraChave = new JMenu("Palavra Chave");
		mnPalavraChave.setFont(new Font("Calibri", Font.BOLD, 16));
		mnFiltrar.add(mnPalavraChave);

		txtEscrevaAquiA = new JTextField();
		txtEscrevaAquiA.setText("Escreva aqui");
		mnPalavraChave.add(txtEscrevaAquiA);
		txtEscrevaAquiA.setColumns(10);

		JMenu mnData = new JMenu("Data");
		mnData.setFont(new Font("Calibri", Font.BOLD, 16));
		mnFiltrar.add(mnData);

		txtEscrevaAqui = new JTextField();
		txtEscrevaAqui.setText("Escreva aqui");
		mnData.add(txtEscrevaAqui);
		txtEscrevaAqui.setColumns(10);

		JMenu mnConfiguraes = new JMenu("A minha conta");
		mnConfiguraes.setFont(new Font("Calibri", Font.BOLD, 18));
		menuBar.add(mnConfiguraes);

		JMenuItem mntmASuaConta = new JMenuItem("ConfiguraÃ§Ãµes");
		mntmASuaConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				@SuppressWarnings("unused")
				Configurations conf = new Configurations(frame, Email);
			}
		});
		mntmASuaConta.setFont(new Font("Calibri", Font.BOLD, 16));
		mnConfiguraes.add(mntmASuaConta);

		if (gestor.isOnline()) {

			if (XMLFile.haveTwitter(Email)) {
				Image icone11 = new ImageIcon(this.getClass().getResource("/twitterm.png")).getImage();

				JButton button2 = new JButton("");
				button2.setBounds(774, 499, 59, 35);
				button2.setIcon(new ImageIcon(icone11));
				menuBar.add(button2);
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().removeAll();
						frame.repaint();
						@SuppressWarnings("unused")
						TwitterPage tp = new TwitterPage(frame, Email);
					}
				});
			}

			if (XMLFile.haveFacebook(Email)) {
				Image icone2 = new ImageIcon(this.getClass().getResource("/facebookm.png")).getImage();

				JButton button3 = new JButton("");
				button3.setBounds(774, 499, 59, 35);
				button3.setIcon(new ImageIcon(icone2));
				menuBar.add(button3);
				button3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().removeAll();
						frame.repaint();
						@SuppressWarnings("unused")
						FacebookPage fbp = new FacebookPage(frame, Email);
					}
				});
			}

			if (XMLFile.haveEmail(Email)) {
				Image icone4 = new ImageIcon(this.getClass().getResource("/emailm.png")).getImage();

				JButton button5 = new JButton("");
				button5.setBounds(774, 499, 59, 35);
				button5.setIcon(new ImageIcon(icone4));
				menuBar.add(button5);
				button5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().removeAll();
						frame.repaint();
						EmailPage email = new EmailPage(frame, Email);
					}
				});
			}
		}

		Image logout = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();

		panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(130, 73, 603, 431);

		JLabel lblListaDeNotificaes = new JLabel("Lista de NotificaÃ§Ãµes");
		lblListaDeNotificaes.setBounds(205, 5, 187, 27);
		lblListaDeNotificaes.setFont(new Font("Calibri", Font.BOLD, 22));
		panel.add(lblListaDeNotificaes);
		gestor.filterBySource(panel, sourceFilters, frame, Email);

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

	public JFrame getFrame() {
		return frame;
	}
}
