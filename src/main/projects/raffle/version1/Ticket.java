package main.projects.raffle.version1;

public class Ticket {
	
	private String owner = "";
	private int num = 0;
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public String toString() {
		return "Ticket[" + this.getOwner() + "," + this.getNum() + "]";
	}
}
