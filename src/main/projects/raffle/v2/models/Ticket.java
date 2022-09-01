package main.projects.raffle.v2.models;

import java.util.UUID;

public class Ticket {

	// Fields
	private String owner = "";
	private UUID id = UUID.randomUUID();
	private String prize = "";
	private int units = 0;

	// Constructor
	public Ticket(String owner) {
		this.setOwner(owner);
	}

	// Field-access methods
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public UUID getID() {
		return id;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	// Printing Object as String
	@Override
	public String toString() {
		return "Ticket[" + "Owner:\"" + this.getOwner() + "\", ID:\"" + this.getID().toString() + "\"" + ", Prize:\""
				+ this.getPrize() + "\"" + ", Units:\"" + String.valueOf(this.getUnits()) + "\"]\n";
	}

	public String toString(Boolean formatted, Boolean hideID) {
		return formatted
				? (hideID ?
						"Ticket[" 
						+ "\n" + "    Owner:\"" + this.getOwner() + "\","
						+ "\n" + "    Prize:\"" + this.getPrize() + "\"" + ","
						+ "\n" + "    Units:\""	+ String.valueOf(this.getUnits()) + "\"" + ","
						+ "\n" + "]" 
						: "Ticket[" 
						+ "\n" + "    Owner:\"" + this.getOwner() + ","
						+ "\n" + "    ID:   \"" + this.getID().toString() + "\"" + ","
						+ "\n" + "    Prize:\"" + this.getPrize() + "\"" + ","
						+ "\n" + "    Units:\""	+ String.valueOf(this.getUnits()) + "\"" + ","
						+ "\n" + "]")
				: this.toString();
	}
}
