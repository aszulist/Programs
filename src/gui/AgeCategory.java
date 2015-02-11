package gui;

public class AgeCategory {
	private int id;
	private String text;
	
	protected AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public int getId() {
		return id;
	}
}
