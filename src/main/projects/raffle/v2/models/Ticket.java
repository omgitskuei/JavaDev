package main.projects.raffle.v2.models;

import java.util.UUID;

public class Ticket {

	// Fields
	private UUID id = UUID.randomUUID();

	public UUID getID() {
		return id;
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
					+ "]"
				: this.toString();
	}
}
