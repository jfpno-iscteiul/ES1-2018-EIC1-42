package BDA;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JPasswordField;

public class Configurations {

	/**
	 * Allows the user to enter new data for their accounts
	 */
	
	private JTextField txtEscrevaAquiA;
	private JTextField txtEscrevaAqui;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JTextField textFieldACK;
	private JTextField textFieldACKS;
	private JTextField textFieldAAT;
	private JTextField textFieldAATS;
	private JTextField textField_1;
	private JTextField textField_2;


	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize(JFrame frame) {
		frame.getContentPane().setBackground(UIManager.getColor("List.background"));
		frame.setBounds(100, 100, 863, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

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
		mnOrdenar.setForeground(SystemColor.windowBorder);
		menuBar.add(mnOrdenar);
		
		JMenuItem mntmAntigasPrimeiro = new JMenuItem("Antigas Primeiro");
		mntmAntigasPrimeiro.setFont(new Font("Calibri", Font.BOLD, 16));
		mnOrdenar.add(mntmAntigasPrimeiro);
		
		JMenuItem mntmRecentesPrimeiro = new JMenuItem("Recentes Primeiro");
		mntmRecentesPrimeiro.setFont(new Font("Calibri", Font.BOLD, 16));
		mnOrdenar.add(mntmRecentesPrimeiro);
		
		JMenu mnFiltrar = new JMenu("Filtrar");
		mnFiltrar.setForeground(SystemColor.windowBorder);
		mnFiltrar.setFont(new Font("Calibri", Font.BOLD, 18));
		menuBar.add(mnFiltrar);
		
		JMenu mnFonteDeInformao = new JMenu("Fonte de Informa\u00E7\u00E3o");
		mnFonteDeInformao.setFont(new Font("Calibri", Font.BOLD, 16));
		mnFiltrar.add(mnFonteDeInformao);
		
		JCheckBox chckbxFacebook = new JCheckBox("Facebook");
		chckbxFacebook.setFont(new Font("Calibri", Font.BOLD, 16));
		mnFonteDeInformao.add(chckbxFacebook);
		
		JCheckBox chckbxEmail = new JCheckBox("Email");
		chckbxEmail.setFont(new Font("Calibri", Font.BOLD, 16));
		mnFonteDeInformao.add(chckbxEmail);
		
		JCheckBox chckbxTwitter = new JCheckBox("Twitter");
		chckbxTwitter.setFont(new Font("Calibri", Font.BOLD, 16));
		mnFonteDeInformao.add(chckbxTwitter);
		
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
		
		JMenu mnConfiguraes = new JMenu("Configura\u00E7\u00F5es");
		mnConfiguraes.setFont(new Font("Calibri", Font.BOLD, 18));
		menuBar.add(mnConfiguraes);
		
		JMenuItem mntmASuaConta = new JMenuItem("A sua conta");
		mntmASuaConta.setFont(new Font("Calibri", Font.BOLD, 16));
		mnConfiguraes.add(mntmASuaConta);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setFont(new Font("Calibri", Font.BOLD, 16));
		mnConfiguraes.add(mntmLogout);
		Image logout = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(130, 73, 603, 431);
		
		
		
		
		

		JButton button = new JButton("");
		button.setBounds(774, 499, 59, 35);
		frame.getContentPane().add(button);
		button.setIcon(new ImageIcon(logout));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 430, 557, -346);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(91, 70, 130, 121);
		Image twitter = new ImageIcon(this.getClass().getResource("/twittericone.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(twitter));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("");
		Image facebook = new ImageIcon(this.getClass().getResource("/facebookicone.png")).getImage();
		label.setIcon(new ImageIcon(facebook));
		label.setBounds(91, 202, 130, 126);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("New label");
		Image email = new ImageIcon(this.getClass().getResource("/emailicone.png")).getImage();
		label_1.setIcon(new ImageIcon(email));
		label_1.setBounds(91, 339, 130, 126);
		frame.getContentPane().add(label_1);
		
		//  TWITTER
		
		JLabel AuthConsumerKey = new JLabel("AuthConsumerKey:");
		AuthConsumerKey.setBounds(261, 110, 76, 14);
		frame.getContentPane().add(AuthConsumerKey);
		
		JLabel AuthConsumerSecret = new JLabel("AuthConsumerSecret:");
		AuthConsumerSecret.setBounds(261, 140, 76, 14);
		frame.getContentPane().add(AuthConsumerSecret);
		
		JLabel AuthAccessToken = new JLabel("AuthAccessToken:");
		AuthAccessToken.setBounds(261, 170, 76, 14);
		frame.getContentPane().add(AuthAccessToken);
		
		JLabel AuthAccessTokenSecret = new JLabel("AuthAccessTokenSecret:");
		AuthAccessTokenSecret.setBounds(261, 200, 76, 14);
		frame.getContentPane().add(AuthAccessTokenSecret);
		
		textFieldACK = new JTextField();
		textFieldACK.setBounds(347, 110, 130, 20);
		frame.getContentPane().add(textFieldACK);
		textFieldACK.setColumns(10);
	
		textFieldACKS = new JTextField();
		textFieldACKS.setBounds(347, 1, 130, 20);
		frame.getContentPane().add(textFieldACKS);
		textFieldACKS.setColumns(10);

		textFieldAATS = new JTextField();
		textFieldAATS.setBounds(347, 1, 200, 20);
		frame.getContentPane().add(textFieldAATS);
		textFieldAATS.setColumns(10);

		textFieldAAT = new JTextField();
		textFieldAAT.setBounds(347, 1, 170, 20);
		frame.getContentPane().add(textFieldAAT);
		textFieldAAT.setColumns(10);
		
		
		
		
		
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(347, 402, 130, 20);
		frame.getContentPane().add(passwordField_2);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(347, 235, 130, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(347, 371, 130, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		

		
		
		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setBounds(261, 374, 76, 14);
		frame.getContentPane().add(lblEmail_1);
		
		
		JLabel label_3 = new JLabel("Password:");
		label_3.setBounds(261, 405, 65, 14);
		frame.getContentPane().add(label_3);
		
		
		
		JButton btnAdicionarConta = new JButton("Adicionar Conta");
		btnAdicionarConta.setBounds(555, 136, 158, 23);
		frame.getContentPane().add(btnAdicionarConta);
		
		JButton button_1 = new JButton("Adicionar Conta");
		button_1.setBounds(555, 265, 158, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Adicionar Conta");
		button_2.setBounds(555, 391, 158, 23);
		frame.getContentPane().add(button_2);
		
		
		
		JButton btnListarremoverContas = new JButton("Listar/Remover Contas");
		btnListarremoverContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.repaint();
				ConfigurationsRem nova = new ConfigurationsRem();
				nova.initialize(frame);
			}
		});
		btnListarremoverContas.setBounds(315, 499, 190, 23);
		frame.getContentPane().add(btnListarremoverContas);
		
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
}
