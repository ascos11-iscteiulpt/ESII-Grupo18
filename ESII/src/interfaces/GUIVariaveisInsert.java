package interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.ConnectionsAdmin;

import javax.swing.JButton;

public class GUIVariaveisInsert extends JFrame{

	private JTextField variavel;
	private JPanel contentPane;
	private ConnectionsAdmin connectAdmin;
	private GUIVariaveis gui;

	public GUIVariaveisInsert(ConnectionsAdmin connect, GUIVariaveis gui) {
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

		JPanel campos = new JPanel();
		contentPane.add(campos);

		JLabel nomeVariavel = new JLabel("Nome da variável a inserir: ");
		campos.add(nomeVariavel);

		variavel = new JTextField();
		campos.add(variavel);
		variavel.setColumns(10);

		JPanel botoes = new JPanel();
		contentPane.add(botoes);

		JButton insertV = new JButton("Inserir");
		botoes.add(insertV);
		
		insertV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					connectAdmin.createVariavel(variavel.getText());
					gui.updateTable();
					gui.repaintTable();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		setVisible(true);
		pack();
		
	}

}
