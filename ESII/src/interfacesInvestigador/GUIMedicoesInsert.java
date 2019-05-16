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

public class GUIMedicoesInsert extends JFrame{

	private JTextField variavel;
	private JTextField cultura;
	private JPanel contentPane;
	private ConnectionsInvestigador connectInv;
	private GUIMedicoes gui;
	private JTextField valor;

	public GUIMedicoesInsert(ConnectionsInvestigador connect, GUIMedicoes gui) {
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
		campos.setLayout(new GridLayout(3,0));
		campos.setBackground(Color.WHITE);
		contentPane.add(campos);

		JPanel aux1 = new JPanel();
		aux1.setBackground(Color.WHITE);
		ImageIcon variavelImg = new ImageIcon("./images/science.png");
		JLabel variavelLabel = new JLabel();
		variavelLabel.setIcon(variavelImg);
		aux1.add(variavelLabel);

		variavel = new JTextField();
		aux1.add(variavel);
		variavel.setColumns(10);
		
		JPanel aux2 = new JPanel();
		aux2.setBackground(Color.WHITE);
		ImageIcon culturaImg = new ImageIcon("./images/sunflower.png");
		JLabel culturaLabel = new JLabel();
		culturaLabel.setIcon(culturaImg);
		aux2.add(culturaLabel);

		cultura = new JTextField();
		aux2.add(cultura);
		cultura.setColumns(10);

		JPanel aux3 = new JPanel();
		aux3.setBackground(Color.WHITE);
		ImageIcon valorImg = new ImageIcon("./images/input.png");
		JLabel valorLabel = new JLabel();
		valorLabel.setIcon(valorImg);
		aux3.add(valorLabel);

		valor = new JTextField();
		aux3.add(valor);
		valor.setColumns(10);
		
		campos.add(aux1);
		campos.add(aux2);
		campos.add(aux3);

		JPanel botoes = new JPanel();
		botoes.setBackground(Color.WHITE);
		contentPane.add(botoes);

		JButton insertV = new JButton("Inserir");
		botoes.add(insertV);
		
		insertV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					connectInv.createMedicao(Integer.parseInt(variavel.getText()), Integer.parseInt(cultura.getText()), Double.parseDouble(valor.getText()));
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
