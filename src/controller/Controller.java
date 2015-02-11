package controller;

import gui.FormEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import data.AgeCategoryEnumerated;
import data.Database;
import data.EmploymentCategory;
import data.GenderCategory;
import data.Person;
import data.listeners.CntChangeListener;

public class Controller {
	
	private Database db;

	public Controller() {
		db = new Database();
	}
	
	public void addPerson(FormEvent e) {
		
		String name = e.getName();
		String occupation = e.getOccupation();
		int ageCatID = e.getAge();
		String employment = e.getEmployment();
		boolean ispl = e.isPlCitizen();
		String docID = e.getDocID();
		String gender = e.getGender();
		String phoneNumber = e.getPhoneNumber();
	
		//AGE
		AgeCategoryEnumerated ageCategoryEnum = null;
	
		switch(ageCatID) {
		
		case 0 : {
			ageCategoryEnum = AgeCategoryEnumerated.CHILD;
			break;
		}
		case 1 : {
			ageCategoryEnum = AgeCategoryEnumerated.ADULT;
			break;
		}
		case 2 : {
			ageCategoryEnum = AgeCategoryEnumerated.SENIOR;
			break;
		}}
		
		//EMPLOYMENT
		EmploymentCategory empCatEnum = null;
		
		if(employment.equals("Employed")) {
			empCatEnum = EmploymentCategory.EMPLOYED;
			
		} else if(employment.equals("Self-employed")) {
			empCatEnum = EmploymentCategory.SELF_EMPLOYED;
			
		} else if(employment.equals("Unemployed")) {
			empCatEnum = EmploymentCategory.UNEMPLOYED;
		}
		
		//GENDER
		GenderCategory genCatEnum = null;
		
		if(gender.equals("Male")) {
			genCatEnum = GenderCategory.MALE;
			
		} else if(gender.equals("Female")) {
			genCatEnum = GenderCategory.FEMALE;
		}

		db.addPerson(new Person(name, occupation, ageCategoryEnum, empCatEnum, ispl, docID, genCatEnum, phoneNumber));
	}
	
	public List<Person> getPeople() {
		return db.getPeople();
	}
	
	public void saveToFile(File file) throws FileNotFoundException, IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		db.loadFromFile(file);
	}
	
	public void removePerson(int index) {
		db.removePerson(index);
	}
	
	public void connect() throws Exception {
		db.connect();
	}
	
	public void disconnect() {
		db.disconnect();
	}
	
	public void save() throws SQLException {
		db.save();
	}
	
	public void load() throws SQLException {
		db.load();
	}
	
	public void setCntChangeListener(CntChangeListener listener) {
		db.setCntChangeListener(listener);
	}
}
