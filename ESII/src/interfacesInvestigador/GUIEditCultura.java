package interfacesInvestigador;

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
import database.ConnectionsInvestigador;
import interfacesAdministrador.GUIGerirInvestigadores;

public class GUIEditCultura extends JFrame{
	
	private JTextField descriptionField;
	private JPanel contentPane;
	private ConnectionsInvestigador connectInv;
	private GUICulturas gui;

	public GUIEditCultura(ConnectionsInvestigador connect, GUICulturas gui) {
		this.connectInv = connect;
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
		getContentPane().setBackground(Color.WHITE);
		contentPane.setLayout(new GridLayout(2, 0));

		JPanel fields = new JPanel();
		fields.setBackground(Color.WHITE);
		fields.setLayout(new FlowLayout());
		contentPane.add(fields);

		ImageIcon descriptionImg = new ImageIcon("./images/description.png");
		JLabel descricao = new JLabel();
		descricao.setIcon(descriptionImg);
		fields.add(descricao);

		descriptionField = new JTextField(gui.getSelectedDescricao());
		fields.add(descriptionField);
		descriptionField.setColumns(10);

		JPanel buttons = new JPanel();
		buttons.setBackground(Color.WHITE);
		contentPane.add(buttons);

		JButton insertV = new JButton("Atualizar");
		buttons.add(insertV);
		
		insertV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					connectInv.editCultura(descriptionField.getText(), gui.getSelectedID());
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
