package interfacesInvestigador;

import java.awt.Color;
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
import interfacesAdministrador.GUIVariaveis;

public class GUICulturasInsert extends JFrame{

	private JTextField cultura;
	private JTextField descricao;
	private JPanel contentPane;
	private ConnectionsInvestigador connectInv;
	private GUICulturas gui;

	public GUICulturasInsert(ConnectionsInvestigador connect, GUICulturas gui) {
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
		getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		getContentPane().setBackground(Color.WHITE);

		JPanel campos = new JPanel();
		campos.setLayout(new GridLayout(2,0));
		campos.setBackground(Color.WHITE);
		contentPane.add(campos);

		JPanel aux1 = new JPanel();
		aux1.setBackground(Color.WHITE);
		ImageIcon culturaImg = new ImageIcon("./images/science.png");
		JLabel nomeCultura = new JLabel();
		nomeCultura.setIcon(culturaImg);
		aux1.add(nomeCultura);

		cultura = new JTextField();
		aux1.add(cultura);
		cultura.setColumns(10);
		
		JPanel aux2 = new JPanel();
		aux2.setBackground(Color.WHITE);
		ImageIcon descricaoImg = new ImageIcon("./images/description.png");
		JLabel descricaoCultura = new JLabel();
		descricaoCultura.setIcon(descricaoImg);
		aux2.add(descricaoCultura);

		descricao = new JTextField();
		aux2.add(descricao);
		descricao.setColumns(10);
		campos.add(aux1);
		campos.add(aux2);

		JPanel botoes = new JPanel();
		botoes.setBackground(Color.WHITE);
		contentPane.add(botoes);

		JButton insertV = new JButton("Inserir");
		botoes.add(insertV);
		
		insertV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					connectInv.createCultura(cultura.getText(), descricao.getText());
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
