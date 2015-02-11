package data;

public enum GenderCategory {

	MALE("Male"),
	FEMALE("Female");
	
	private String text;
	
	private GenderCategory(String text) {
		this.text = text;
	}
	
	public String toString() {
		return this.text;
	}
}
