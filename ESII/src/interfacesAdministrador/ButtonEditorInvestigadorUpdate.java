package interfacesAdministrador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import database.ConnectionsAdmin;

public class ButtonEditorInvestigadorUpdate extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private ImageIcon pic3 = new ImageIcon("./images/edit.png");
	private GUIGerirInvestigadores gui;

    
    public ButtonEditorInvestigadorUpdate(JCheckBox checkBox, GUIGerirInvestigadores gui) {
        super(checkBox);
        this.gui = gui;
        
        button = new JButton(pic3);
        button.setIcon(pic3);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }
    
 
    
    public JButton getButton() {
    	return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
//            button.setForeground(table.getSelectionForeground());
//            button.setBackground(table.getSelectionBackground());
        } else {
//            button.setForeground(table.getForeground());
//            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            
			try {
				final int row = gui.getTable().getSelectedRow();
				final int valueInCell = (Integer)gui.getTable().getValueAt(row, 0);
				final String name = (String)gui.getTable().getValueAt(row, 2);
				gui.setSelectedID(valueInCell);
				gui.setSelectedName(name);
				System.out.println("No botão tem: "+gui.getSelectedID());
				System.out.println("Nome selecionado: "+gui.getSelectedName());
				new GUIEditInvestigador(gui.connectAdmin, gui);
				gui.updateTable();
				gui.repaintTable();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
