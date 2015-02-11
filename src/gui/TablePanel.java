package gui;

import gui.listeners.PersonTableListener;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import data.AgeCategoryEnumerated;
import data.EmploymentCategory;
import data.GenderCategory;
import data.Person;

public class TablePanel extends JPanel {
	
	private static final long serialVersionUID = -2100097416635813668L;
	
	private JTable table;
	private PersonTableModel tableModel;
	private JPopupMenu popupMenu;
	private PersonTableListener personTableListener;
	
	private EmploymentCategoryTableRenderer ectr;
	private AgeCategoryEnumeratedTableRenderer acetr;
	private GenderCategoryTableRenderer gctr;
	
	private EmploymentCategoryTableEditor ecte;
	private GenderCategoryTableEditor gcte;
	private AgeCategoryEnumeratedTableEditor acete;
	
	protected TablePanel() {
		
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
		popupMenu = new JPopupMenu();
		
		ectr = new EmploymentCategoryTableRenderer();
		acetr = new AgeCategoryEnumeratedTableRenderer();
		gctr = new GenderCategoryTableRenderer();
		
		table.setDefaultRenderer(EmploymentCategory.class, ectr);
		table.setDefaultRenderer(AgeCategoryEnumerated.class, acetr);
		table.setDefaultRenderer(GenderCategory.class, gctr);
		
		ecte = new EmploymentCategoryTableEditor();
		gcte = new GenderCategoryTableEditor();
		acete = new AgeCategoryEnumeratedTableEditor();
		
		table.setDefaultEditor(EmploymentCategory.class, ecte);
		table.setDefaultEditor(GenderCategory.class, gcte);
		table.setDefaultEditor(AgeCategoryEnumerated.class, acete);
		
		table.setRowHeight(20);
		
		JMenuItem removeItem = new JMenuItem("Delete Row");
	
		popupMenu.add(removeItem);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				int row = table.rowAtPoint(new Point(e.getPoint()));
				
				table.getSelectionModel().setSelectionInterval(row, row);
				
				if(e.getButton() == MouseEvent.BUTTON3) {
					popupMenu.show(table, e.getX(), e.getY());
				}
			}
		});
		
		removeItem.addActionListener((e) -> {
			
			int row = table.getSelectedRow();
			
			if(personTableListener != null) {
				personTableListener.rowDeleted(row);
				tableModel.fireTableRowsDeleted(row, row);
			}
		});
		
		setLayout(new BorderLayout());

		add(table, BorderLayout.CENTER);
		
		JScrollPane jsc = new JScrollPane(table);
		add(jsc);
	}
	
	public void setData(List<Person> peoples) {
		tableModel.setData(peoples);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
	
	public void setPersonTableListener(PersonTableListener listener) {
		this.personTableListener = listener;
	}
}
