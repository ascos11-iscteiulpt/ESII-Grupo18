package interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ConnectionsAdmin;

public class GUIUtilizadores {
	
	private ConnectionsAdmin connectAdmin;
	private JFrame frame;
	private JButton insertButton;
	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel dtm;
	private int selectedID;
	
	public GUIUtilizadores(ConnectionsAdmin connect) throws SQLException {
		this.connectAdmin = connect;
		startGui();
		updateTable();
	}
	
	public void startGui() {
		frame = new JFrame("Gestão de utilizadores");
		frame.setLayout(new GridLayout(2, 1));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(200, 200, 800, 700);
		frame.setPreferredSize(new Dimension(800,500));
		frame.setLayout(new BorderLayout());

		insertButton = new JButton("Inserir nova variável");
		
		insertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	
		scroll = new JScrollPane(table);
		frame.add(scroll, BorderLayout.CENTER);
		frame.add(insertButton, BorderLayout.SOUTH);
	}
	
	
	
	public void updateTable() throws SQLException {
		frame.remove(scroll);
		frame.remove(insertButton);
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new String[]{"ID", "Email", "Nome", "Categoria", "Operacao"});

		PreparedStatement preparedStatement = connectAdmin.getConnectionAdmin().prepareStatement("SELECT * FROM investigador");
		preparedStatement.execute();
		ResultSet res = preparedStatement.getResultSet();
		ResultSetMetaData meta = res.getMetaData();

		int numberOfColumns = meta.getColumnCount();
		while (res.next())
		{
			Object [] rowData = new Object[numberOfColumns];
			for (int i = 0; i < rowData.length; ++i)
			{
				rowData[i] = res.getObject(i+1);
			}
			dtm.addRow(rowData);
		}
		dtm.fireTableDataChanged();    

		table = new JTable(dtm);
		table.getColumn("Operacao").setCellRenderer(new ButtonRenderer());
		table.getColumn("Operacao").setCellEditor(new ButtonEditorUtilizadores(new JCheckBox(), this));

		scroll = new JScrollPane(table);

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.getColumnModel().getColumn(0).setPreferredWidth(100);//so buttons will fit and not be shown butto..


		table.addMouseListener(new MouseAdapter() {
			  @Override
			    public void mouseClicked(final MouseEvent e) {
//			        if (e.getClickCount() == 1) {
//			            final JTable jTable= (JTable)e.getSource();
//			            final int row = table.getSelectedRow();
//			            final int column = table.getSelectedColumn();
//			            final int valueInCell = (Integer)table.getValueAt(row, 0);
//			            setSelectedID(valueInCell);
//			            System.out.println("Selecionou: "+valueInCell);
//			        }
			    }
		});
		
		
		frame.add(scroll, BorderLayout.CENTER);
		frame.add(insertButton, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();
		frame.pack();
		frame.setVisible(true);

	}
	
	public void repaintTable() {
		this.table.repaint();
	}
	
	public JTable getTable() {
		return this.table;
	}

	public int getSelectedID() {
		return selectedID;
	}
	
	public void setSelectedID(int selectedID) {
		this.selectedID = selectedID;
	}
	

}
