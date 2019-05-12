package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import database.ConnectionsAdmin;

import javax.swing.JButton;

public class GUIAdministrador extends JFrame {

	private JPanel contentPane;
	private JPanel contentPaneAdmin;
	private JTextField usernameField;
	private JTextField passwordField;
	private ConnectionsAdmin connectAdmin;

	/**
	 * Create the frame.
	 */
	public GUIAdministrador(ConnectionsAdmin connect) {
		this.connectAdmin = connect;
		openLoginGUI();
	}
	
	public void openLoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
		setContentPane(contentPane);
		contentPane.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel usernamePanel = new JPanel();
		panel.add(usernamePanel);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font(usernameLabel.getFont().getName(), Font.PLAIN, usernameLabel.getFont().getSize()+2));	
		usernamePanel.add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font(usernameField.getFont().getName(), Font.PLAIN, usernameField.getFont().getSize()+2));	
		usernamePanel.add(usernameField);
		usernameField.setColumns(10);
		
		JPanel passwordPanel = new JPanel();
		panel.add(passwordPanel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font(passwordLabel.getFont().getName(), Font.PLAIN, passwordLabel.getFont().getSize()+2));	
		passwordPanel.add(passwordLabel);
		
		passwordField = new JTextField();
		passwordField.setFont(new Font(passwordField.getFont().getName(), Font.PLAIN, passwordField.getFont().getSize()+2));	
		passwordPanel.add(passwordField);
		passwordField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font(loginButton.getFont().getName(), Font.PLAIN, loginButton.getFont().getSize()+2));	
		panel.add(loginButton);
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean connected = connectAdmin.login(usernameField.getText(), passwordField.getText());
				if(connected) {
					//chamar novo método :c
					clearGUI();
					openAdminGUI();
				}
			}
		});	
	}
	
	public void clearGUI() {
		this.getContentPane().removeAll();
		repaint();
		revalidate();
	}
	
	public void openAdminGUI() {
		
		contentPaneAdmin = new JPanel();
		contentPaneAdmin.setBorder(new EmptyBorder(50, 50, 50, 50));
		setContentPane(contentPaneAdmin);
//		this.add(contentPaneAdmin);
		contentPaneAdmin.setAlignmentX(CENTER_ALIGNMENT);
		contentPaneAdmin.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Interface do administrador");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, title.getFont().getSize()+4));
		contentPaneAdmin.add(title, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		
		JButton variaveisButton = new JButton("Gerir variáveis");
		variaveisButton.setFont(new Font(variaveisButton.getFont().getName(), Font.PLAIN, variaveisButton.getFont().getSize()+2));
		centerPanel.add(variaveisButton);
		
		JButton utilizadoresButton = new JButton("Gerir utilizadores");
		utilizadoresButton.setFont(new Font(utilizadoresButton.getFont().getName(), Font.PLAIN, utilizadoresButton.getFont().getSize()+2));	
		centerPanel.add(utilizadoresButton);
		
		contentPaneAdmin.add(centerPanel, BorderLayout.CENTER);
		repaint();
		revalidate();
		
		variaveisButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					try {
						new GUIVariaveis(new ConnectionsAdmin());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
			}
		});
		
		utilizadoresButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new GUIUtilizadores(new ConnectionsAdmin());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		
	}

}
