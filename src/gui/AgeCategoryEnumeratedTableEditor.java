package gui;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import data.AgeCategoryEnumerated;

public class AgeCategoryEnumeratedTableEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = -7456073522387041237L;
	
	private JComboBox<AgeCategoryEnumerated> comboBox;

	public AgeCategoryEnumeratedTableEditor() {
		comboBox = new JComboBox<AgeCategoryEnumerated>(AgeCategoryEnumerated.values());
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
