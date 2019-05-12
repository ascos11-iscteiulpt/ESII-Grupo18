package interfaces;

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

public class ButtonEditorUtilizadores extends DefaultCellEditor {

    protected JButton button;
    private String label;
    private boolean isPushed;
    private ImageIcon pic3 = new ImageIcon("./images/trash.png");
	private GUIUtilizadores guiU;

    
    public ButtonEditorUtilizadores(JCheckBox checkBox, GUIUtilizadores gui) {
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
				
				System.out.println("No botão tem: "+guiU.getSelectedID());
//				ConnectionsAdmin.deleteVariavel(gui.getSelectedID());
				ConnectionsAdmin.deleteInvestigador(10);
				guiU.updateTable();
				guiU.repaintTable();
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
