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
import java.awt.Image;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Configurations {

	/**
	 * Allows the user to enter new data for their accounts
	 */
	
	private JPasswordField passwordField;
	private JTextField textFieldACK;
	private JTextField textFieldACKS;
	private JTextField textFieldAAT;
	private JTextField textFieldAATS;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Initialize the contents of the frame.
	 */

	public void initialize(JFrame frame, String Email) {
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
		
		/**
		 * Creates a form for entering twitter data
		 */
		
		JLabel AuthConsumerKey = new JLabel("AuthConsumerKey:");
		AuthConsumerKey.setBounds(261, 110, 150, 14);
		frame.getContentPane().add(AuthConsumerKey);
		
		JLabel AuthConsumerSecret = new JLabel("AuthConsumerSecret:");
		AuthConsumerSecret.setBounds(261, 140, 150, 14);
		frame.getContentPane().add(AuthConsumerSecret);
		
		JLabel AuthAccessToken = new JLabel("AuthAccessToken:");
		AuthAccessToken.setBounds(261, 170, 150, 14);
		frame.getContentPane().add(AuthAccessToken);
		
		JLabel AuthAccessTokenSecret = new JLabel("AuthAccessTokenSecret:");
		AuthAccessTokenSecret.setBounds(261, 200, 150, 14);
		frame.getContentPane().add(AuthAccessTokenSecret);
		
		textFieldACK = new JTextField();
		textFieldACK.setBounds(420, 110, 130, 20);
		frame.getContentPane().add(textFieldACK);
		textFieldACK.setColumns(10);
	
		textFieldACKS = new JTextField();
		textFieldACKS.setBounds(420, 140, 130, 20);
		frame.getContentPane().add(textFieldACKS);
		textFieldACKS.setColumns(10);

		textFieldAATS = new JTextField();
		textFieldAATS.setBounds(420, 170, 130, 20);
		frame.getContentPane().add(textFieldAATS);
		textFieldAATS.setColumns(10);

		textFieldAAT = new JTextField();
		textFieldAAT.setBounds(420, 200, 130, 20);
		frame.getContentPane().add(textFieldAAT);
		textFieldAAT.setColumns(10);
		
		
		
		/**
		 * Creates a form for entering facebook data
		 */
		
// FACEBOOK (555, 265, 158, 23);
		JLabel lblEmail = new JLabel("tokenAccess:");
		lblEmail.setBounds(261, 265, 150, 14);
		frame.getContentPane().add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setBounds(420, 265, 130, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		//EMAIL
		
		passwordField = new JPasswordField();
		passwordField.setBounds(420, 402, 130, 20);
		frame.getContentPane().add(passwordField);
		
	
		
		textField_2 = new JTextField();
		textField_2.setBounds(420, 371, 130, 20);
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
		btnAdicionarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XMLFile.changeAttributte(Email, "AuthConsumerKeyTwitter", textFieldACK.getText());
				XMLFile.changeAttributte(Email, "AuthConsumerSecretTwitter", textFieldACKS.getText());
				XMLFile.changeAttributte(Email, "AuthAccessTokenTwitter", textFieldAATS.getText());
				XMLFile.changeAttributte(Email, "AuthAccessTokenSecretTwitter", textFieldAAT.getText());
			
			}
		});
		
		JButton button_1 = new JButton("Adicionar Conta");
		button_1.setBounds(555, 265, 158, 23);
		frame.getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XMLFile.changeAttributte(Email, "TokenAccessFacebook", textField_1.getText());
			
			}
		});
		
		
		JButton button_2 = new JButton("Adicionar Conta");
		button_2.setBounds(555, 391, 158, 23);
		frame.getContentPane().add(button_2);
		
		
		
		JButton btnListarremoverContas = new JButton("Listar/Remover Contas");
		btnListarremoverContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().removeAll();
				frame.repaint();
				ConfigurationsRem nova = new ConfigurationsRem();
				nova.initialize(frame, Email);
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
