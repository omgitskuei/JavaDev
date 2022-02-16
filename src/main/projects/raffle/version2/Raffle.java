package main.projects.raffle.version2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import main.projects.raffle.RandomizerUtils;
import main.projects.raffle.version2.models.Prize;
import main.projects.raffle.version2.models.Ticket;

public class Raffle {

	private RaffleUtils util = new RaffleUtils();
	private RandomizerUtils randomU = new RandomizerUtils();
	
	// Config
	private boolean debug;
	private int extraShuffles;
	private boolean randomizeExtraShuffles;

	// Constructor
	public Raffle() {
		try {
			Properties prop = util.readConfig();
			this.debug = Boolean.valueOf(prop.getProperty("debug", "false"));
			int extraShuffles = Integer.valueOf(prop.getProperty("extraShuffles", "0"));
			if(Boolean.valueOf(prop.getProperty("randomizeExtraShuffles", "true"))) {
				this.extraShuffles = randomU.getRandomNumber(1, extraShuffles);
				this.randomizeExtraShuffles = Boolean.valueOf(prop.getProperty("randomizeExtraShuffles", "false"));
			} else {
				this.extraShuffles = Integer.valueOf(prop.getProperty("extraShuffles", "0"));
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.debug = false;
			this.extraShuffles = 3;
			this.randomizeExtraShuffles = false;
		}
	}

	public Raffle(Boolean debug, int extraShuffles) {
		this.debug = debug;
		this.extraShuffles = extraShuffles;
		this.randomizeExtraShuffles = false;
	}

	public static void main(String[] args) {
		System.out.println("RAFFLE v.2.2, omgitskuei, Feb 11 2022");
		System.out.println("----------------------------------------------");
		System.out.println("Change log");
		System.out.println("v.2.3 - 16/02/2022 - Added config file support");
		System.out.println("v.2.2 - 11/02/2022 - Randomize extra shuffles");
		System.out.println("v.2.1 - 09/02/2022 - Fixed output typos");
		System.out.println("v.2.0 - 06/02/2022 - Rewrote RAFFLE completely");
		System.out.println("----------------------------------------------");
		// Initialize applet
//		Raffle r = new Raffle(true, 5);	// Note: 5 EXTRA shuffles, meaning 6 shuffles total
		Raffle r = new Raffle();
		System.out.println("Configuration");
		System.out.println("Debug mode is turned " + (r.debug ? "ON" : "OFF") + ".");
		System.out.println("extraShuffles is set to " + String.valueOf(r.extraShuffles) + ".");
		System.out.println("randomizeExtraShuffles is turned " + (r.randomizeExtraShuffles ? "ON" : "OFF") + ".");
		System.out.println("----------------------------------------------");

		// Parse txt files for all owners' names, and number of tickets each owner
		HashMap<String, Integer> allOwners = r.parseOwners();
		System.out.println("All Owners, Number of tickets:");
		System.out.println(allOwners);
		// Generate tickets from allOwners
		ArrayList<Ticket> allTickets = new ArrayList<Ticket>();
		for (Entry<String, Integer> owner : allOwners.entrySet()) {
			Integer numTickets = owner.getValue();
			for (int i = 0; i < numTickets; i++) {
				Ticket t = new Ticket(owner.getKey());
				allTickets.add(t);
			}
		}
		if (r.debug) {
			System.out.println("All Tickets:");
			System.out.println(allTickets);
		}
		System.out.println("Total tickets:" + allTickets.size());
		
		// Generate prizes objects from txt file
		ArrayList<Prize> allPrizes = r.parsePrizes();
		System.out.println("All Prizes:");
		System.out.println(allPrizes);
		System.out.println("Total prizes:" + allPrizes.size());
		System.out.println("-----------------------------------");
		// Randomize order of Prizes and Tickets
		r.shuffle(allTickets, allPrizes, r.extraShuffles);
		System.out.println("-----------------------------------");
		if (r.debug) {
			System.out.println("After shuffling tickets:");
			System.out.println(allTickets);
			System.out.println("After shuffling prizes:");
			System.out.println(allPrizes);
			System.out.println("-----------------------------------");
		}

		// Differentiate into different ways of distributing prizes
		// depending on number of tickets and prizes to avoid
		// ArrayIndexOutOfBoundsException
		int index = 0;
		if (allPrizes.size() > allTickets.size()) {
			if (r.debug) {
				System.out.println("Prizes sample > Tickets sample");
			}
			// Assign each ticket to a prize
			for (Ticket ticket : allTickets) {
				// Note, not all prizes will be distributed, since each Ticket
				// can only match one prize
				Prize p = allPrizes.get(index);
				ticket.setPrize(p.getName());
				ticket.setUnits(1);
				index = index + 1;
			}
		} else {
			if (r.debug) {
				System.out.println("Prizes sample <= Tickets sample");
			}
			// Assign each prize to a ticket
			for (Prize prize : allPrizes) {
				Ticket t = allTickets.get(index);
				t.setPrize(prize.getName());
				t.setUnits(1);
				index = index + 1;
			}
		}

		r.printResults(allTickets);
	}

	private void shuffle(ArrayList<Ticket> allTickets, ArrayList<Prize> allPrizes, int extraShuffles) {
		if (extraShuffles > 0) {
			for (int i = 1; i <= extraShuffles + 1; i++) {
				System.out.println("Shuffling " + i + " time(s).");
				Collections.shuffle(allTickets);
				Collections.shuffle(allPrizes);
			}
		} else {
			Collections.shuffle(allTickets);
			Collections.shuffle(allPrizes);
		}
	}

	private void printResults(ArrayList<Ticket> tickets) {
		for (Ticket ticket : tickets) {
			System.out.println(ticket.toString(true, true));
		}
	}

	private HashMap<String, Integer> parseOwners() {
		HashMap<String, Integer> allOwners = new HashMap<String, Integer>();
		// Read owners.txt file
		ArrayList<String> ownersFile = util.readFile("owners");
		// Parse file content
		for (String line : ownersFile) {
			String[] eachOwner = line.split(",", 2);
			String ownerName = eachOwner[0];
			String numTickets = eachOwner[1];
			// Add each line containing owner data "ownerName,numTickets" as an allOwners
			// map pair
			allOwners.put(ownerName, Integer.valueOf(numTickets));
		}
		return allOwners;
	}

	private ArrayList<Prize> parsePrizes() {
		ArrayList<Prize> allPrizes = new ArrayList<Prize>();
		// Read prizes.txt file
		ArrayList<String> prizesFile = util.readFile("prizes");
		// Parse file content
		for (String line : prizesFile) {
			String[] eachPrize = line.split(",", 2);
			String prizeName = eachPrize[0];
			String totalStr = eachPrize[1]; // total num of this prize
			int total = 0;
			try {
				total = Integer.parseInt(totalStr);
			} catch (NumberFormatException e) {
				System.out.println("Threw NumberFormatException during parsePrizes(...)," + "setting prize ["
						+ prizeName + "] total to 0.");
			}
			// Add the same prize 'total' amount of times
			for (int i = 0; i < total; i++) {
				allPrizes.add(new Prize(prizeName));
			}
		}
		return allPrizes;
	}
}
