package BDA;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
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
import javax.swing.JTable;

public class ConfigurationsRem {

	/**
	 * Allows the user to view a list of their accounts and remove them if they see
	 * fit.
	 */
	
	private JFrame frame;
	private String Email;
	private int selected;
	private XMLFile xml;
	
	/** @param   frame parameter gives an instance of the frame of the main page.
	 * @param    Email is the email relative to the user.
	 */
	
	public ConfigurationsRem(JFrame frame, String Email) {
		this.frame = frame;
		this.Email = Email;
		xml=new XMLFile();
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
		Image imagem3 = new ImageIcon(this.getClass().getResource("/iscte_wm_1.png")).getImage();
		labelBackground.setIcon(new ImageIcon(imagem3));
		labelBackground.setForeground(SystemColor.window);
		labelBackground.setBackground(SystemColor.activeCaption);
		labelBackground.setBounds(0, 48, 863, 594);

		frame.setBounds(100, 100, 863, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setIconImage((new ImageIcon("Imagens/frameImage.png").getImage()));
		ArrayList<String> accounts_list = xml.list_account(Email);

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
				@SuppressWarnings("unused")
				Timeline tm = new Timeline(frame, Email);
			}
		});

		Image logout = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(50, 73, 603, 431);

		Vector<String> headers = new Vector<String>();
		headers.add("Serviços Associados");
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		if (accounts_list.size() != 0) {
			for (int i = 0; i < accounts_list.size(); i++) {
				Vector<Object> row = new Vector<Object>();
				row.add(accounts_list.get(i));
				data.add(row);
			}
		} else {
			Vector<Object> row = new Vector<Object>();
			row.add("N�o tem serviços associados a esta conta.");
			data.add(row);
		}
		JTable table_1 = new JTable(data, headers);

		table_1.setBounds(82, 137, 441, 321);
		table_1.setDefaultEditor(Object.class, null);
		
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selected = (int) table_1.getSelectedRow();
			}
	    });
		
		panel.add(new JScrollPane(table_1));

		frame.getContentPane().add(panel);

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

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String service = accounts_list.get(selected);
				if (service.equals("Facebook")) {
					xml.changeAttributte(Email, "TokenAccessFacebook", "vazio");
					JOptionPane optionPane = new JOptionPane("Facebook removido com sucesso!", JOptionPane.INFORMATION_MESSAGE);    
					JDialog dialog = optionPane.createDialog("Alerta!");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else if (service.equals("Twitter")) {
					xml.changeAttributte(Email, "AuthConsumerKeyTwitter", "vazio");
					xml.changeAttributte(Email, "AuthConsumerSecretTwitter", "vazio");
					xml.changeAttributte(Email, "AuthAccessTokenTwitter", "vazio");
					xml.changeAttributte(Email, "AuthAccessTokenSecretTwitter", "vazio");
					JOptionPane optionPane = new JOptionPane("Twitter removido com sucesso!", JOptionPane.INFORMATION_MESSAGE);    
					JDialog dialog = optionPane.createDialog("Alerta!");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} else if (service.equals("Email")) {
					xml.changeAttributte(Email, "PassEmail", "vazio");
					JOptionPane optionPane = new JOptionPane("Email removido com sucesso!", JOptionPane.INFORMATION_MESSAGE);    
					JDialog dialog = optionPane.createDialog("Alerta!");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				accounts_list.remove(selected);
				frame.getContentPane().removeAll();
				frame.repaint();
				initialize();

			}
		});
		btnRemover.setBounds(675, 454, 158, 23);
		frame.getContentPane().add(btnRemover);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainWindow.main(null);
			}
		});
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
