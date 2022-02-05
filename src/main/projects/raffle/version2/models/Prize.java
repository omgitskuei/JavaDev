package main.projects.raffle.version2.models;

public class Prize {
	
	// Fields
	private String name = "";
	
	// Constructor
	public Prize(String name) {
		this.setName(name);
	}
	
	// Accessor Methods
	public String getName() {
		return name;
	}
	public void setName(String owner) {
		this.name = owner;
	}
	
	// Printing Object as String
	@Override
	public String toString() {
		return "Prize[" + this.getName() + "]";
	}
}
