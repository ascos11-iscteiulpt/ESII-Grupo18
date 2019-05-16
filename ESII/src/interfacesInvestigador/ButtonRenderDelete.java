package interfacesInvestigador;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

class ButtonRenderer extends JButton implements TableCellRenderer {

	public ButtonRenderer() {
		//        setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
//			setForeground(table.getSelectionForeground());
//			setBackground(table.getSelectionBackground());
			ImageIcon pic3 = new ImageIcon("./images/trash.png");
			setIcon(pic3);
			setContentAreaFilled(false);
			setBorderPainted(false);
			setBorder(null);
			setFocusPainted(false);
		} else {
//			setForeground(table.getForeground());
//			setBackground(UIManager.getColor("Button.background"));
			ImageIcon pic3 = new ImageIcon("./images/trash.png");
			setIcon(pic3);
			setContentAreaFilled(false);
			setBorderPainted(false);
			setBorder(null);
			setFocusPainted(false);

		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}
