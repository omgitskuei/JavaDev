package main.projects.raffle.v2.models;

public class Draw {
	private Ticket ticket = null;
	private Prize prize = null;
	private Player winner = null;
	private int num = 0;
	
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Prize getPrize() {
		return prize;
	}

	public void setPrize(Prize prize) {
		this.prize = prize;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	@Override
	public String toString() {
		return "Draw["
				+ winner.toString() + ","
				+ ticket.toString() + ","
				+ prize.toString() + ","
				+ String.valueOf(num)
				+ "]";
	}
	
	public String toString(boolean formatted) {
		return "Draw[" 						+ ((formatted) ? "\n" : "")
				+ winner.toString() + "," 	+ ((formatted) ? "\n" : "")
				+ ticket.toString() + "," 	+ ((formatted) ? "\n" : "") 
				+ prize.toString() + "," 	+ ((formatted) ? "\n" : "") 
				+ String.valueOf(num) + "," + ((formatted) ? "\n" : "") 
				+ "]";
	}
}
