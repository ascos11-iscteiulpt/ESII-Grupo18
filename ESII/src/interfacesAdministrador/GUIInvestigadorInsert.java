package interfacesAdministrador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.ConnectionsAdmin;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GUIInvestigadorInsert extends JFrame{

	private JTextField nicknameField;
	private JTextField nameField;
	private JTextField passField;
	private JPanel contentPane;
	private ConnectionsAdmin connectAdmin;
	private GUIGerirInvestigadores gui;

	public GUIInvestigadorInsert(ConnectionsAdmin connect, GUIGerirInvestigadores gui) {
		this.connectAdmin = connect;
		this.gui = gui;
		openGui();
	}

	private void openGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
		setContentPane(contentPane);
		contentPane.setAlignmentX(CENTER_ALIGNMENT);
		getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		getContentPane().setBackground(Color.WHITE);

		JPanel campos = new JPanel();
		campos.setLayout(new GridLayout(3,0));
		campos.setBackground(Color.WHITE);
		contentPane.add(campos);

		ImageIcon userImg = new ImageIcon("./images/arroba.png");
		JLabel nickname = new JLabel();
		nickname.setIcon(userImg);
		campos.add(nickname);

		nicknameField = new JTextField();
		campos.add(nicknameField);
		nicknameField.setColumns(10);
		
		ImageIcon nameImg = new ImageIcon("./images/users.png");
		JLabel nome = new JLabel();
		nome.setIcon(nameImg);
		campos.add(nome);

		nameField = new JTextField();
		campos.add(nameField);
		nameField.setColumns(10);
		
		ImageIcon passImg = new ImageIcon("./images/key.png");
		JLabel pass = new JLabel();
		pass.setIcon(passImg);
		campos.add(pass);

		passField = new JTextField();
		campos.add(passField);
		passField.setColumns(10);

		JPanel botoes = new JPanel();
		botoes.setBackground(Color.WHITE);
		contentPane.add(botoes);

		JButton insertV = new JButton("Inserir");
		botoes.add(insertV);
		
		insertV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					connectAdmin.createInvestigador(nicknameField.getText(), nameField.getText(), passField.getText());
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
