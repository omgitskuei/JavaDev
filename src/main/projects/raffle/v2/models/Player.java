package main.projects.raffle.v2.models;

import java.util.Collections;
import java.util.List;

public class Player {
	private String username = "";
	private List<Ticket> tickets = Collections.emptyList();
	
	// Constructor
	public Player(String username) {
		this.username = username;
	}
	
	// Getter/Setter
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	// Add/Remove a Ticket from Tickets
	public void addTicket(Ticket ticket) {
		this.tickets.add(ticket);
	}
	
	public void removeTicket(Ticket ticket) {
		for (int index = 0; index < this.tickets.size(); index++) {
			if(tickets.get(index).getID().equals(ticket.getID())) {
				this.tickets.remove(index);
			}
		}
	}

	@Override
	public String toString() {
		return "Player["
				+ username + ","
				+ tickets
				+ "]";
	}
	
	public String toString(boolean formatted) {
		return "Draw[" 						+ ((formatted) ? "\n" : "")
				+ username + "," 			+ ((formatted) ? "\n" : "") 
				+ printFormattedListTickets(tickets) + "," + ((formatted) ? "\n" : "") 
				+ "]";
	}
	
	private String printFormattedListTickets(List<Ticket> tickets) {
		StringBuilder sb = new StringBuilder();
		for (Ticket ticket : tickets) {
			sb.append(ticket.toString(true));
		}
		return sb.toString();
	}
}
