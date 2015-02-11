package data;

import java.io.Serializable;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 7862907502368768298L;

	private static int cnt = 1;
	
	private int id;
	private String name;
	private String occupation;
	private AgeCategoryEnumerated age;
	private EmploymentCategory employment;
	private boolean isPLCitizen;
	private String docID;
	private GenderCategory gender;
	private String phoneNumber;
	
	public Person() {
		this.id = cnt;
		cnt++;
	}

	public Person(String name, String occupation,
			AgeCategoryEnumerated age, EmploymentCategory employment,
			boolean isPLCitizen, String docID, GenderCategory gender,
			String phoneNumber) {
		
		this();
		this.name = name;
		this.occupation = occupation;
		this.age = age;
		this.employment = employment;
		this.isPLCitizen = isPLCitizen;
		this.docID = docID;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
	}
	
	public Person(int id, String name, String occupation,
			AgeCategoryEnumerated age, EmploymentCategory employment,
			boolean isPLCitizen, String docID, GenderCategory gender,
			String phoneNumber) {
		
		this(name, occupation, age, employment, isPLCitizen, docID, gender, phoneNumber);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getOccupation() {
		return occupation;
	}

	public AgeCategoryEnumerated getAge() {
		return age;
	}

	public EmploymentCategory getEmployment() {
		return employment;
	}

	public boolean isPLCitizen() {
		return isPLCitizen;
	}

	public String getDocID() {
		return docID;
	}

	public GenderCategory getGender() {
		return gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public static int getCnt() {
		return cnt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public void setAge(AgeCategoryEnumerated age) {
		this.age = age;
	}

	public void setEmployment(EmploymentCategory employment) {
		this.employment = employment;
	}

	public void setPLCitizen(boolean isPLCitizen) {
		this.isPLCitizen = isPLCitizen;
	}

	public void setDocID(String docID) {
		this.docID = docID;
	}

	public void setGender(GenderCategory gender) {
		this.gender = gender;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public static void setCnt(int cnt) {
		Person.cnt = cnt;
	}

	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", occupation="
				+ occupation + ", age=" + age + ", employment=" + employment
				+ ", isPLCitizen=" + isPLCitizen + ", docID=" + docID
				+ ", gender=" + gender + ", phoneNumber=" + phoneNumber + "]";
	}
}
