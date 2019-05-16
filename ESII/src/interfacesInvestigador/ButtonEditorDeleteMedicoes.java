package interfacesInvestigador;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import database.ConnectionsAdmin;
import database.ConnectionsInvestigador;

class ButtonEditorDeleteMedicoes extends DefaultCellEditor {

    private JButton button;
    private String label;
    private boolean isPushed;
    private GUIMedicoes gui;
    private ImageIcon pic3 = new ImageIcon("./images/trash.png");

    public ButtonEditorDeleteMedicoes(JCheckBox checkBox, GUIMedicoes gui) {
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
				final int valueInCell = (Integer)gui.getTable().getValueAt(row, 2);
				gui.setSelectedID(valueInCell);
				System.out.println("No botão tem: "+gui.getSelectedID());
				gui.connectInv.deleteMedicao(gui.getSelectedID());
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
