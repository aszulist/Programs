package gui;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import data.EmploymentCategory;

public class EmploymentCategoryTableEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = -7373440460883286418L;
	
	private JComboBox<EmploymentCategory> comboBox;

	public EmploymentCategoryTableEditor() {
		comboBox = new JComboBox<EmploymentCategory>(EmploymentCategory.values());
	}
	
	public Object getCellEditorValue() {
		
		return comboBox.getSelectedItem();
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		
		comboBox.setSelectedItem(value);
		comboBox.addActionListener((e) -> {
			fireEditingStopped();
		});
		return comboBox;
	}

	public boolean isCellEditable(EventObject e) {
		return true;
	}
}
