package gui;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import data.EmploymentCategory;

public class EmploymentCategoryTableRenderer implements TableCellRenderer {

	private JComboBox<EmploymentCategory> comboBox;
	
	public EmploymentCategoryTableRenderer() {
		comboBox = new JComboBox<EmploymentCategory>(EmploymentCategory.values());
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		comboBox.setSelectedItem(value);
		
		return comboBox;
	}
}
