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
import java.util.ArrayList;
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
		ArrayList<String> accounts_list = XMLFile.list_account(Email);
		
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
		
	
		
		  Vector<String> headers = new Vector<String>();
	        headers.add("Serviços Associados");
	    	Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	        if(accounts_list.size()!=0) {
		for (int i = 0; i<accounts_list.size(); i++) {
			Vector<Object> row = new Vector<Object>();
			row.add(accounts_list.get(i));
	        data.add(row);
		}
	        }
	        else {
	        	Vector<Object> row = new Vector<Object>();
	        	row.add("Não tem serviços associados a esta conta.");
		        data.add(row);
	        }
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
