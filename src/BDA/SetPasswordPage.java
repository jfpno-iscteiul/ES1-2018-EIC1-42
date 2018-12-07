package BDA;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SetPasswordPage {

	private JFrame frame;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private XMLFile xml = new XMLFile();
	/**
	 * Launch the application.
	*/

 
	/**
	 * Create the application.
	 */
	public SetPasswordPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		Image imagem = new ImageIcon(this.getClass().getResource("/Imagens/frameImage.png")).getImage();
		frame.setIconImage(imagem);
		JLabel lblAlterarPassword = new JLabel("Alterar Password");
		lblAlterarPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAlterarPassword.setBounds(149, 13, 165, 33);
		frame.getContentPane().add(lblAlterarPassword);
		
		JLabel email = new JLabel("Email:");
		email.setBounds(25, 61, 149, 33);
		frame.getContentPane().add(email);
		
		JLabel lblPasswordAntiga = new JLabel("Password Antiga:");
		lblPasswordAntiga.setBounds(25, 97, 149, 33);
		frame.getContentPane().add(lblPasswordAntiga);
		
		JLabel lblNovaPassword = new JLabel("Nova Password:");
		lblNovaPassword.setBounds(25, 143, 149, 23);
		frame.getContentPane().add(lblNovaPassword);
		
		JLabel lblConfirmaoPassword = new JLabel("Confirmar Password:");
		lblConfirmaoPassword.setBounds(25, 179, 149, 25);
		frame.getContentPane().add(lblConfirmaoPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(205, 108, 131, 22);
		frame.getContentPane().add(passwordField);
		
		JTextField emailText = new JTextField();
		emailText.setBounds(205, 61, 131, 22);
		frame.getContentPane().add(emailText);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(205, 143, 131, 22);
		frame.getContentPane().add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(205, 180, 131, 22);
		frame.getContentPane().add(passwordField_2);
		
		JButton alterar= new JButton ("Alterar");
		alterar.setBounds(180, 235, 80, 22);
		frame.getContentPane().add(alterar);
		alterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password1 = new String(passwordField.getPassword());
				String password2 = new String(passwordField_1.getPassword());
				String password3 =  new String(passwordField_2.getPassword());
				setPasswords(emailText.getText(),password1,password2,password3);
			}
		});
		frame.setBackground(new Color(51, 204, 204));
		frame.setVisible(true);
		
	}
	
	public void setPasswords(String emailText, String password1,String password2,String password3) {
		if(xml.getAttributteByEmail(emailText, "Password").equals(password1)) {
			if(password2.equals(password3)) {
				xml.changeAttributte(emailText, "Password", password2);
				JOptionPane optionPane = new JOptionPane("Palavra passe alterada com sucesso", JOptionPane.INFORMATION_MESSAGE);    
				JDialog dialog = optionPane.createDialog("Sucesso!");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				frame.dispose();
			}else {
				JOptionPane optionPane = new JOptionPane("As palavras pass não coincidem.", JOptionPane.ERROR_MESSAGE);    
				JDialog dialog = optionPane.createDialog("ERRO!");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
			}
		}else {
			JOptionPane optionPane = new JOptionPane("A password antiga que inseriu esta errada.", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog("ERRO!");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);
		}
	}

	public Frame getFrame() {
		return frame;
	}

}
