package main.projects.raffle.v2.models;

public class Prize {
	
	// Fields
	private String name = "";
	
	// Constructor
	public Prize(String name) {
		this.setName(name);
	}
	
	// Getter/Setter Methods
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Printing Object as String
	@Override
	public String toString() {
		return "Prize[" + this.getName() + "]";
	}
}
