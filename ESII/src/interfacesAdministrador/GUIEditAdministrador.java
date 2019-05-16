package interfacesAdministrador;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.ConnectionsAdmin;

public class GUIEditAdministrador extends JFrame{
	
	private JTextField nameField;
	private JPanel contentPane;
	private ConnectionsAdmin connectAdmin;
	private GUIGerirAdministradores gui;

	public GUIEditAdministrador(ConnectionsAdmin connect, GUIGerirAdministradores gui) {
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
		contentPane.setLayout(new GridLayout(2, 0));

		JPanel fields = new JPanel();
		fields.setBackground(Color.WHITE);
		fields.setLayout(new FlowLayout());
		contentPane.add(fields);

		ImageIcon nameImg = new ImageIcon("./images/users.png");
		JLabel nome = new JLabel();
		nome.setIcon(nameImg);
		fields.add(nome);

		nameField = new JTextField(gui.getSelectedName());
		fields.add(nameField);
		nameField.setColumns(10);

		JPanel buttons = new JPanel();
		buttons.setBackground(Color.WHITE);
		contentPane.add(buttons);

		JButton insertV = new JButton("Atualizar");
		buttons.add(insertV);
		
		insertV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					connectAdmin.editAdministrador(nameField.getText(), gui.getSelectedID());
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
