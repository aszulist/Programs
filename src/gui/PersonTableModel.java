package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import data.AgeCategoryEnumerated;
import data.EmploymentCategory;
import data.GenderCategory;
import data.Person;

public class PersonTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -644898091938774026L;

	private List<Person> data;
	
	private String[] colNames = {"ID", "Name", "Occupation", "Age Category", "Employment", "Polish Citizen", "Document ID", "Gender", "Phone Number"};
	
	public PersonTableModel(){
	}
	
	public boolean isCellEditable(int row, int col) {
		
		if(col == 0) {
			return false;
		} else if (col == 6 && (!((Person)data.get(row)).isPLCitizen())) {
			return false;
		} else {
			return true;
		}
	}

	public void setValueAt(Object value, int row, int col) {
		
		if(data == null) {
			return;
		}
		
		switch(col) {
		case 0 : {
			return;
		} case 1 : {
			data.get(row).setName((String)value);
			return;
		} case 2 : {
			data.get(row).setOccupation((String)value);
			return;
		} case 3 : {
			data.get(row).setAge((AgeCategoryEnumerated)value);
			return;
		} case 4 : {
			data.get(row).setEmployment((EmploymentCategory)value);
			return;
		} case 5 : {
			data.get(row).setPLCitizen((boolean)value);
			return;
		} case 6 : {
			data.get(row).setDocID((String)value);
			return;
		} case 7 : {
			data.get(row).setGender((GenderCategory)value);
			return;
		} case 8 : {
			data.get(row).setPhoneNumber((String)value);
			return;
		} default : {
			return;
		}
		}
	}
	
	public Class<?> getColumnClass(int col) {
		
		switch(col) {
		case 0: {
			return Integer.class;
		}
		case 1: {
			return String.class;
		}
		case 2: {
			return String.class;
		}
		case 3: {
			return AgeCategoryEnumerated.class;
		}
		case 4: {
			return EmploymentCategory.class;
		}
		case 5: {
			return Boolean.class;
		}
		case 6: {
			return String.class;
		}
		case 7: {
			return GenderCategory.class;
		}
		case 8: {
			return String.class;
		}
		default : {
			return null;
		}
		}
	}

	public void setData(List<Person> data) {
		this.data = data;
	}
	
	public int getColumnCount() {
		return 9;
	}

	public int getRowCount() {
		return data.size();
	}

	public String getColumnName(int column) {
		return colNames[column];
	}

	public Object getValueAt(int row, int col) {

		Person person = data.get(row);
		
		switch(col) {
		case 0: {
			return person.getId();
		}
		case 1: {
			return person.getName();
		}
		case 2: {
			return person.getOccupation();
		}
		case 3: {
			return person.getAge();
		}
		case 4: {
			return person.getEmployment();
		}
		case 5: {
			return person.isPLCitizen();
		}
		case 6: {
			return person.getDocID();
		}
		case 7: {
			return person.getGender();
		}
		case 8: {
			return person.getPhoneNumber();
		}
		}
		
		return null;
	}

}
