package interfacesInvestigador;

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
import database.ConnectionsInvestigador;

import javax.swing.JButton;

public class GUIInvestigador extends JFrame {

	private JPanel contentPane;
	private JPanel contentPaneAdmin;
	private JTextField emailField;
	private JPasswordField passwordField;
	private ConnectionsInvestigador connectInv;

	/**
	 * Create the frame.
	 */
	public GUIInvestigador(ConnectionsInvestigador connect) {
		this.connectInv = connect;
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

		JPanel emailPanel = new JPanel();
		emailPanel.setBackground(Color.WHITE);
		panel.add(emailPanel);

		ImageIcon user = new ImageIcon("./images/email.png");
		JLabel emailLabel = new JLabel();
		emailLabel.setIcon(user);
		emailLabel.setFont(new Font(emailLabel.getFont().getName(), Font.PLAIN, emailLabel.getFont().getSize()+2));	
		emailPanel.add(emailLabel);

		emailField = new JTextField();
		emailField.setFont(new Font(emailField.getFont().getName(), Font.PLAIN, emailField.getFont().getSize()+2));	
		emailPanel.add(emailField);
		emailField.setColumns(10);

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
				Boolean connected = connectInv.login(emailField.getText(), passwordField.getText());
				if(connected) {
					clearGUI();
					openInvGUI();
				}
			}
		});	
	}

	public void clearGUI() {
		this.getContentPane().removeAll();
		repaint();
		revalidate();
	}

	public void openInvGUI() {

		setSize(680, 380);
		contentPaneAdmin = new JPanel();
		contentPaneAdmin.setBorder(new EmptyBorder(50, 50, 50, 50));
		setContentPane(contentPaneAdmin);
		//		this.add(contentPaneAdmin);
		contentPaneAdmin.setAlignmentX(CENTER_ALIGNMENT);
		contentPaneAdmin.setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);

		ImageIcon img = new ImageIcon("./images/analytics.png");
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
		JButton culturasButton = new JButton("Gerir culturas");
		culturasButton.setFont(new Font(culturasButton.getFont().getName(), Font.PLAIN, culturasButton.getFont().getSize()+2));
		culturasButton.setPreferredSize(new Dimension(300, 40));
		aux.add(culturasButton);
//		centerPanel.add(aux);
		centerPanel.add(culturasButton);

		JPanel aux2 = new JPanel();
		JButton medicoesButton = new JButton("Gerir medições");
		medicoesButton.setFont(new Font(medicoesButton.getFont().getName(), Font.PLAIN, medicoesButton.getFont().getSize()+2));	
		medicoesButton.setPreferredSize(new Dimension(300, 40));
		aux2.add(medicoesButton);
//		centerPanel.add(aux2);
		centerPanel.add(medicoesButton);

		centerPanel.setBorder(BorderFactory.createEmptyBorder(70, 20, 70, 20));
		contentPaneAdmin.add(centerPanel, BorderLayout.CENTER);
		repaint();
		revalidate();

		culturasButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new GUICulturas(connectInv);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		medicoesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new GUIMedicoes(connectInv);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

	}

}
