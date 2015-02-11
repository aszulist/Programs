package data;

public enum EmploymentCategory {

	EMPLOYED("Employed"),
	SELF_EMPLOYED("Self-employed"),
	UNEMPLOYED("Unemployed");
	
	private String text;
	
	private EmploymentCategory(String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
}
