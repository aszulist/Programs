package data;

public enum AgeCategoryEnumerated {
	
	CHILD("Child"),
	ADULT("Adult"),
	SENIOR("Senior");
	
	private String text;
	
	private AgeCategoryEnumerated(String text) {
		this.text = text;
	}
	
	public String toString() {
		return this.text;
	}
}
