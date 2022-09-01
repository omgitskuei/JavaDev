package main.projects.raffle.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import main.projects.raffle.RandomizerUtils;

public class Raffle {

	private RandomizerUtils rU = new RandomizerUtils();
	// Config
	private int minTicketNum = 1;
	private int maxTicketNum = 1000;
	private boolean debug = false;
	
	public static void main(String[] args) {
		// Initialize applet
		Raffle r = new Raffle();

		// Generate ticketOwners
		HashMap<String, Integer> allOwners = r.createOwners();
		
		// Generate tickets based on number of tickets per owner
		ArrayList<Ticket> allTickets = r.createTickets(allOwners);
		
		System.out.println(allTickets);
		
		ArrayList<String> allPrizes = r.createPrizes();
		
		// Raffle off each item
		
		for (String prize : allPrizes) {
			System.out.println("Raffling off:              " + prize);
			// Reset winner per prize
			Ticket winningTicket = new Ticket();
			int minDiff = 0;
			int winningTicketIndex = 0;
			// Generate random number for prize
			int prizeNum = r.rU.getRandomNumber(r.minTicketNum, r.maxTicketNum);
			if(r.debug) {
				System.out.println(", Prize Number: " + prizeNum);
			}
			// Find ticket with number closest to this prizeNum
			for (int i = 0; i < allTickets.size(); i++) {
				Ticket t = allTickets.get(i);
				if(r.debug) {
					System.out.println("Comparing ticket:" + t);
					System.out.println("Difference:      " + Math.abs(t.getNum()-prizeNum));
					System.out.println("Curr. Diff:      " + minDiff);
					System.out.println("New Winner:      " + (Math.abs(t.getNum()-prizeNum) < minDiff));
				}
				if (i == 0) {
					winningTicket = t;
					winningTicketIndex = i;
					minDiff = Math.abs(t.getNum()-prizeNum);
				} else {
					if ((Math.abs(t.getNum()-prizeNum) < minDiff) && (minDiff != 0)) {
						winningTicket = t;
						winningTicketIndex = i;
						minDiff = Math.abs(t.getNum()-prizeNum);
					} 
				}
			}
			System.out.println("Winner:                    " + winningTicket.getOwner());
			if(r.debug) {
				System.out.println("Winning Ticket:" + winningTicket);
				System.out.println("Winning Ticket Index: " + winningTicketIndex);
			}
//			allTickets.remove(winningTicketIndex);
		}
	}

	private ArrayList<String> createPrizes() {
		/**
		 * Prizes:
		 * 22 MC,
		 * 15 ascended cilantro,
		 * 10 spiritwood planks
		 * 20 raw gold
		 */
		ArrayList<String> prizes = new ArrayList<String>();
		for (int i = 0; i < 22; i++) {
			prizes.add("MC");
		}
		for (int i = 0; i < 15; i++) {
			prizes.add("Ascended cilantro");
		}
		for (int i = 0; i < 10; i++) {
			prizes.add("Spiritwood planks");
		}
		for (int i = 0; i < 20; i++) {
			prizes.add("Raw gold");
		}
		return prizes;
	}
	
	private ArrayList<Ticket> createTickets(HashMap<String, Integer> allOwners) {
		ArrayList<Ticket> allTickets = new ArrayList<Ticket>();
		for (Entry<String, Integer> owner : allOwners.entrySet()) {
            String ownerName = owner.getKey();
            Integer numOfTickets = owner.getValue();
            for (int i = 0; i < numOfTickets; i++) {
            	int ticketNum = rU.getRandomNumber(minTicketNum, maxTicketNum);
            	Ticket t = new Ticket();
        		t.setOwner(ownerName);
        		t.setNum(ticketNum);
            	allTickets.add(t);
			}
        }
		return allTickets;
	}
	

	private HashMap<String, Integer> createOwners() {
		// A list of map (key=owner : value=numberOfTickets)
		HashMap<String, Integer> allOwners = new HashMap<String, Integer>();
		allOwners.put("anderson.7594", 1);
		allOwners.put("AwildKuei.2013", 2);
		allOwners.put("BigBird.2547", 2);
		allOwners.put("Dan.5801", 2);
		allOwners.put("Generalkinseng.6795", 1);
		allOwners.put("Giddie.5028", 2);
		allOwners.put("JuzSuX.7394", 1);
		allOwners.put("Tyranno Legend.1958", 2);
		allOwners.put("VauxAxia.3278", 2);
		allOwners.put("Wildfire.9726", 2);
		return allOwners;
	}
}
