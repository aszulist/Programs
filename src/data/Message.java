package data;

public class Message {
	
	private String title;
	private String text;
	
	public Message(String title, String text) {
		this.title = title;
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		return String.format("Title: %s, Text: %s", this.title, this.text);
	}
}
