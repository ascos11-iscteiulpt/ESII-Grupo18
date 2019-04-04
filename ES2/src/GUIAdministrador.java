//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class GUIAdministrador {
	public static void main(String[] args) {
		new GUIAdministrador();
	}
	public GUIAdministrador()
	{
		JFrame guiFrame = new JFrame();
		//make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("GUI Teste");
		guiFrame.setSize(300,250);
		//This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);
		//Options for the JComboBox
		String[] tabelaAConsultar = {"investigador", "medições", "variáveis"
				,"cultura", "sistema", "variáveis medidas", "medições luminosidade", "medições temperatura"};
		//The first JPanel contains a JLabel and JCombobox
		final JPanel comboPanel = new JPanel();
		JLabel comboLbl = new JLabel("Tabelas:");
		JComboBox fruits = new JComboBox(tabelaAConsultar);
		comboPanel.add(comboLbl);
		comboPanel.add(fruits);
		//Create the second JPanel. Add a JLabel and JList and
		//make use the JPanel is not visible.
		final JPanel listPanel = new JPanel();
		listPanel.setVisible(false);
		JButton vegFruitBut = new JButton( "Consultar");
		vegFruitBut.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
		
				listPanel.setVisible(!listPanel.isVisible());
				comboPanel.setVisible(!comboPanel.isVisible());
			}
		});
		guiFrame.add(comboPanel, BorderLayout.NORTH);
		guiFrame.add(listPanel, BorderLayout.CENTER);
		guiFrame.add(vegFruitBut,BorderLayout.SOUTH);
		//make sure the JFrame is visible
		guiFrame.setVisible(true);
	}
}