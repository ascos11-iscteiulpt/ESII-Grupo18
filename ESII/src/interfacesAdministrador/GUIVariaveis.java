package interfacesAdministrador;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.mysql.cj.jdbc.MysqlDataSource;

import database.ConnectionsAdmin;

public class GUIVariaveis{

	private ConnectionsAdmin connectAdmin;
	private JFrame frame;
	private JTable table;
	private JButton insertButton;
	private DefaultTableModel dtm;
	private JScrollPane scroll;
	private int selectedID;


	public GUIVariaveis(ConnectionsAdmin connect) throws SQLException {
		this.connectAdmin = connect;	
		startGui();
		updateTable();
	}

	public void startGui() {
		frame = new JFrame("Gestão de variáveis");
		frame.setLayout(new GridLayout(2, 1));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(200, 200, 800, 700);
		frame.setPreferredSize(new Dimension(800,500));
		frame.setLayout(new BorderLayout());

		insertButton = new JButton("Inserir nova variável");

		insertButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new GUIVariaveisInsert(connectAdmin, GUIVariaveis.this);
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
		dtm.setColumnIdentifiers(new String[]{"ID", "Nome", "Eliminar"});

		PreparedStatement preparedStatement = connectAdmin.getConnectionAdmin().prepareStatement("SELECT * FROM variaveis");
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
		table.getColumn("Eliminar").setCellRenderer(new ButtonRenderer());
		table.getColumn("Eliminar").setCellEditor(new ButtonEditorDeleteVariaveis(new JCheckBox(), this));


		scroll = new JScrollPane(table);

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.getColumnModel().getColumn(0).setPreferredWidth(100);//so buttons will fit and not be shown butto..




		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				if (e.getClickCount() == 1) {

					final JTable jTable= (JTable)e.getSource();
					final int row = table.getSelectedRow();
					final int column = table.getSelectedColumn();
					final int valueInCell = (Integer)table.getValueAt(row, 0);
					setSelectedID(valueInCell);
					System.out.println("Selecionou: "+valueInCell);
				}
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



