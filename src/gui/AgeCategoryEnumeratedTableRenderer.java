package gui;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import data.AgeCategoryEnumerated;

public class AgeCategoryEnumeratedTableRenderer implements TableCellRenderer {
	
	private JComboBox<AgeCategoryEnumerated> comboBox;
	
	public AgeCategoryEnumeratedTableRenderer() {
		comboBox = new JComboBox<AgeCategoryEnumerated>(AgeCategoryEnumerated.values());
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		comboBox.setSelectedItem(value);
		
		return comboBox;
	}
}
