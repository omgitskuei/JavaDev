package main.projects.raffle.v2.models;

import java.util.UUID;

public class Ticket {

	// Fields
	private UUID id = UUID.randomUUID();
	private String owner = "";
	
	// Constructor
	public Ticket(String owner) {
		this.setOwner(owner);
	}

	public UUID getID() {
		return id;
	}

	public String getOwner() {
		return owner;
	}
	
	private void setOwner(String owner) {
		this.owner = owner;
	}
	
	// Printing Object as String
	@Override
	public String toString() {
		return "Ticket[" + "ID:\"" + this.getID().toString() + "\"]";
	}

	public String toString(Boolean formatted) {
		return formatted ? 
				"Ticket[" + "\n" 
					+ "ID:   \"" + this.getID().toString() + "\"" + "\n"
					+ "Owner:   \"" + this.getOwner().toString() + "\"" + "\n"
					+ "]"
				: this.toString();
	}
}
