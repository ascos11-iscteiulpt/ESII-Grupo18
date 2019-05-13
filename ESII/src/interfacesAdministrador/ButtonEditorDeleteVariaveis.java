package interfacesAdministrador;

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

class ButtonEditorDeleteVariaveis extends DefaultCellEditor {

    private JButton button;
    private String label;
    private boolean isPushed;
    private GUIVariaveis guiVar;
    private ImageIcon pic3 = new ImageIcon("./images/trash.png");
	private GUIGerirInvestigadores guiU;

    public ButtonEditorDeleteVariaveis(JCheckBox checkBox, GUIVariaveis gui) {
        super(checkBox);
        this.guiVar = gui;
        
        button = new JButton(pic3);
        button.setIcon(pic3);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }
    
    public ButtonEditorDeleteVariaveis(JCheckBox checkBox, GUIGerirInvestigadores gui) {
        super(checkBox);
        this.guiU = gui;
        
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
				
				final int row = guiVar.getTable().getSelectedRow();
				final int valueInCell = (Integer)guiVar.getTable().getValueAt(row, 0);
				guiVar.setSelectedID(valueInCell);
				System.out.println("No botão tem: "+guiVar.getSelectedID());
				ConnectionsAdmin.deleteVariavel(guiVar.getSelectedID());
				guiVar.updateTable();
				guiVar.repaintTable();
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
