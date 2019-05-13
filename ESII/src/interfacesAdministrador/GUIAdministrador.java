package interfacesAdministrador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

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
	private JPasswordField passwordField;
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
		getContentPane().setBackground(Color.WHITE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel usernamePanel = new JPanel();
		usernamePanel.setBackground(Color.WHITE);
		panel.add(usernamePanel);

		ImageIcon user = new ImageIcon("./images/users.png");
		JLabel usernameLabel = new JLabel();
		usernameLabel.setIcon(user);
		usernameLabel.setFont(new Font(usernameLabel.getFont().getName(), Font.PLAIN, usernameLabel.getFont().getSize()+2));	
		usernamePanel.add(usernameLabel);

		usernameField = new JTextField();
		usernameField.setFont(new Font(usernameField.getFont().getName(), Font.PLAIN, usernameField.getFont().getSize()+2));	
		usernamePanel.add(usernameField);
		usernameField.setColumns(10);

		JPanel passwordPanel = new JPanel();
		passwordPanel.setBackground(Color.WHITE);
		panel.add(passwordPanel);

		ImageIcon pass = new ImageIcon("./images/key.png");
		JLabel passwordLabel = new JLabel();
		passwordLabel.setIcon(pass);
		passwordLabel.setFont(new Font(passwordLabel.getFont().getName(), Font.PLAIN, passwordLabel.getFont().getSize()+2));	
		passwordPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font(passwordField.getFont().getName(), Font.PLAIN, passwordField.getFont().getSize()+2));	
		passwordPanel.add(passwordField);
		passwordField.setColumns(10);

		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font(loginButton.getFont().getName(), Font.PLAIN, loginButton.getFont().getSize()+2));	
		loginButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(loginButton);

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean connected = connectAdmin.login(usernameField.getText(), passwordField.getText());
				if(connected) {
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

		setSize(680, 380);
		contentPaneAdmin = new JPanel();
		contentPaneAdmin.setBorder(new EmptyBorder(50, 50, 50, 50));
		setContentPane(contentPaneAdmin);
		//		this.add(contentPaneAdmin);
		contentPaneAdmin.setAlignmentX(CENTER_ALIGNMENT);
		contentPaneAdmin.setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);

		ImageIcon img = new ImageIcon("./images/admin-3.png");
		JLabel title = new JLabel();
		title.setIcon(img);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setAlignmentY(CENTER_ALIGNMENT);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, title.getFont().getSize()+4));
		contentPaneAdmin.add(title, BorderLayout.WEST);

		JPanel centerPanel = new JPanel();
		//		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setLayout(new GridLayout(3, 0));
		centerPanel.setBackground(Color.WHITE);

		JPanel aux = new JPanel();
		JButton variaveisButton = new JButton("Gerir variáveis");
		variaveisButton.setFont(new Font(variaveisButton.getFont().getName(), Font.PLAIN, variaveisButton.getFont().getSize()+2));
		variaveisButton.setPreferredSize(new Dimension(300, 40));
		aux.add(variaveisButton);
//		centerPanel.add(aux);
		centerPanel.add(variaveisButton);

		JPanel aux2 = new JPanel();
		JButton investigadoresButton = new JButton("Gerir investigadores");
		investigadoresButton.setFont(new Font(investigadoresButton.getFont().getName(), Font.PLAIN, investigadoresButton.getFont().getSize()+2));	
		investigadoresButton.setPreferredSize(new Dimension(300, 40));
		aux2.add(investigadoresButton);
//		centerPanel.add(aux2);
		centerPanel.add(investigadoresButton);

		JPanel aux3 = new JPanel();
		JButton administradoresButton = new JButton("Gerir administradores");
		administradoresButton.setFont(new Font(administradoresButton.getFont().getName(), Font.PLAIN, administradoresButton.getFont().getSize()+2));	
		administradoresButton.setPreferredSize(new Dimension(300, 40));
		aux3.add(administradoresButton);
//		centerPanel.add(aux3);
		centerPanel.add(administradoresButton);

		centerPanel.setBorder(BorderFactory.createEmptyBorder(70, 20, 70, 20));
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

		investigadoresButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new GUIGerirInvestigadores(new ConnectionsAdmin());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		administradoresButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new GUIGerirAdministradores(new ConnectionsAdmin());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});


	}

}
