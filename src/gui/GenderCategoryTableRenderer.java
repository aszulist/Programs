package gui;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import data.GenderCategory;

public class GenderCategoryTableRenderer implements TableCellRenderer {
	
	private JComboBox<GenderCategory> comboBox;
	
	 public GenderCategoryTableRenderer() {

		comboBox = new JComboBox<GenderCategory>(GenderCategory.values());
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		comboBox.setSelectedItem(value);
		
		return comboBox;
	}
}
