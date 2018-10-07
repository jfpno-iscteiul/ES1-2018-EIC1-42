package initial;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class InitialWindow {

	public static void main(String[] args) {
		NewFrame();
	}

	public static void NewFrame() {
		User user = new User();
		 JFrame frame = new JFrame("Bom Dia Academia");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);

		frame.setLayout(new GridLayout(2, 1));
		
		JButton login = new JButton("Iniciar Sessão");
		JButton signup = new JButton("Registar");
		
		class Listener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == login) {
					System.out.println("Carregou no botão LOGIN.");
					
				}
				else if(e.getSource() == signup) {
					System.out.println("Carregou no botão REGISTAR .");
					user.SignUp();
					
				}
				
			} 


		}
		
		Listener listener = new Listener();
		login.addActionListener(listener);
		signup.addActionListener(listener);
		frame.add(login); 
		frame.add(signup);
		
	}
	
	

	
}