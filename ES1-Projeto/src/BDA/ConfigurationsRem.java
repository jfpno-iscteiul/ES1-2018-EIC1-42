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
import java.util.Vector;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class ConfigurationsRem {
	
	/**
	 * Allows the user to view a list of their accounts and remove them if they see fit.
	 */

	private JTextField txtEscrevaAquiA;
	private JTextField txtEscrevaAqui;

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(JFrame frame, String Email) {

		frame.getContentPane().setBackground(UIManager.getColor("List.background"));
		frame.setBounds(100, 100, 863, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/**
		 * Creates the menu for this window
		 */
		
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
		panel.setBounds(50, 73, 603, 431);
		

		JButton button = new JButton("");
		button.setBounds(774, 499, 59, 35);
		frame.getContentPane().add(button);
		button.setIcon(new ImageIcon(logout));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 430, 557, -346);
		frame.getContentPane().add(scrollPane);
	
	
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(675, 420, 158, 23);
		frame.getContentPane().add(btnAlterar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(675, 454, 158, 23);
		frame.getContentPane().add(btnRemover);
		
	
		
		
		
		 Vector<Vector<Object>> data = new Vector<Vector<Object>>();

	        Vector<Object> row = new Vector<Object>();
	        row.add( "facebook");
	        row.add( "jose@iscte-iul.pt");
	        data.add(row);

	        Vector<Object> otherRow = new Vector<Object>();
	        otherRow.add( "Twitter");
	        otherRow.add( "@Ana");
	        data.add(otherRow);

	        Vector<String> headers = new Vector<String>();
	        headers.add("Plataforma");
	        headers.add("Username/Email");


	        JTable table_1 = new JTable( data, headers );

			table_1.setBounds(82, 137, 441, 321);
	        panel.add( new JScrollPane( table_1 ));
	        
	        frame.getContentPane().add(panel);

	        
	        
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
