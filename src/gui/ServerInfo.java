package gui;

public class ServerInfo {

	private int id;
	private String name;
	private boolean isChecked;
	
	protected ServerInfo(int id, String name, boolean isChecked) {
		
		this.id = id;
		this.name = name;
		this.isChecked = isChecked;
	}

	public int getId() {
		return this.id;
	}

	public String toString() {
		return this.name;
	}
	
	public boolean isChecked() {
		return this.isChecked;
	}
	
	public void setIsChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
}
