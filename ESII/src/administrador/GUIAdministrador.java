package administrador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;

public class GUIAdministrador extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField passwordField;

	/**
	 * Create the frame.
	 */
	public GUIAdministrador() {
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
		usernamePanel.add(usernameLabel);
		
		usernameField = new JTextField();
		usernamePanel.add(usernameField);
		usernameField.setColumns(10);
		
		JPanel passwordPanel = new JPanel();
		panel.add(passwordPanel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordPanel.add(passwordLabel);
		
		passwordField = new JTextField();
		passwordPanel.add(passwordField);
		passwordField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		panel.add(btnNewButton);
	}

}
