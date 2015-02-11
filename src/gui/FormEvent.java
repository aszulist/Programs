package gui;

import java.util.EventObject;

public class FormEvent extends EventObject {

	private static final long serialVersionUID = 6409245800344985837L;

	private String name;
	private String occupation;
	private int age;
	private String employment;
	private boolean plCitizen;
	private String docID;
	private String gender;
	private String phoneNumber;

	public FormEvent(Object source) {

		super(source);
	}

	public FormEvent(Object source, String name, String occupation, int age,
			String employment, boolean plCitizen, String nrDow, String gender, String phoneNumber) {
		
		super(source);

		this.name = name;
		this.occupation = occupation;
		this.age = age;
		this.employment = employment;
		this.plCitizen = plCitizen;
		this.docID = nrDow;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public String getOccupation() {
		return occupation;
	}

	public int getAge() {
		return age;
	}

	public String getEmployment() {
		return employment;
	}

	public boolean isPlCitizen() {
		return plCitizen;
	}

	public String getDocID() {
		return docID;
	}

	public String getGender() {
		return gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

	public void setPlCitizen(boolean plCitizen) {
		this.plCitizen = plCitizen;
	}

	public void setDocID(String docID) {
		this.docID = docID;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
