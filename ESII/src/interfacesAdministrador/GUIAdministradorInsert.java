package interfacesAdministrador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.ConnectionsAdmin;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GUIAdministradorInsert extends JFrame{

	private JTextField nameField;
	private JTextField passField;
	private JPanel contentPane;
	private ConnectionsAdmin connectAdmin;
	private GUIGerirAdministradores gui;

	public GUIAdministradorInsert(ConnectionsAdmin connect, GUIGerirAdministradores gui) {
		this.connectAdmin = connect;
		this.gui = gui;
		openGui();
	}

	private void openGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 100, 80);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
		setContentPane(contentPane);
		contentPane.setAlignmentX(CENTER_ALIGNMENT);
		getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		getContentPane().setBackground(Color.WHITE);
		

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel fields = new JPanel();
		fields.setBackground(Color.WHITE);
		fields.setLayout(new GridLayout(3, 0));
		panel.add(fields);

		ImageIcon nameImg = new ImageIcon("./images/users.png");
		JLabel nome = new JLabel();
		nome.setIcon(nameImg);
		fields.add(nome);

		nameField = new JTextField();
		fields.add(nameField);
		nameField.setColumns(10);
		
		ImageIcon passImg = new ImageIcon("./images/key.png");
		JLabel pass = new JLabel();
		pass.setIcon(passImg);
		fields.add(pass);

		passField = new JTextField();
		fields.add(passField);
		passField.setColumns(10);

		JPanel buttons = new JPanel();
		buttons.setBackground(Color.WHITE);
		panel.add(buttons);

		JButton insertV = new JButton("Inserir");
		buttons.add(insertV);
		
		insertV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					connectAdmin.createAdministrador(nameField.getText(), passField.getText());
					gui.updateTable();
					gui.repaintTable();
					closeGUI();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		setVisible(true);
		pack();
		
	}
	
	public void closeGUI() {
		this.dispose();
	}


}
