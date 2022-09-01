package main.projects.raffle.v2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import main.projects.raffle.RandomizerUtils;
import main.projects.raffle.v2.models.Player;
import main.projects.raffle.v2.models.Prize;
import main.projects.raffle.v2.models.Ticket;

public class Raffle {

	private RaffleUtils util = new RaffleUtils();
	private RandomizerUtils randomU = new RandomizerUtils();
	
	// Config
	private boolean debug;
	private boolean verbose;
	private int extraShuffles;
	private boolean randomizeExtraShuffles;
	
	// Data
	List<Player> players = new ArrayList<Player>();
	List<Prize> prizes = new ArrayList<Prize>();

	// Constructor
	public Raffle() {
		try {
			Properties prop = util.readConfig();
			this.debug = Boolean.valueOf(prop.getProperty("debug", "false"));
			this.verbose = Boolean.valueOf(prop.getProperty("verbose", "false"));
			int extraShuffles = Integer.valueOf(prop.getProperty("extraShuffles", "0"));
			this.randomizeExtraShuffles = Boolean.valueOf(prop.getProperty("randomizeExtraShuffles", "false"));
			if(this.randomizeExtraShuffles) {
				this.extraShuffles = randomU.getRandomNumber(0, extraShuffles);
			} else {
				this.extraShuffles = Integer.valueOf(prop.getProperty("extraShuffles", "0"));
			}
		} catch (IOException e) {
			System.out.println("Could not locate raffle.config in project Raffle.v2 folder,");
			System.out.println("starting Raffle() using default configurations.");
			this.debug = false;
			this.verbose = false;
			this.extraShuffles = 0;
			this.randomizeExtraShuffles = false;
		}
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
		
		Raffle thisApp = new Raffle();
		System.out.println("Configuration");
		System.out.println("Debug mode is turned " + (thisApp.debug ? "ON" : "OFF") + ".");
		System.out.println("Verbose mode is turned " + (thisApp.verbose ? "ON" : "OFF") + ".");
		System.out.println("Extra Shuffles is set to " + String.valueOf(thisApp.extraShuffles) + ".");
		System.out.println("Randomize number of Extra Shuffles is turned " + (thisApp.randomizeExtraShuffles ? "ON" : "OFF") + ".");
		System.out.println("----------------------------------------------");
		System.out.println("Data received");
		System.out.println("Players:");
		// Parse txt files for players, tickets, prizes data
		for (Entry<String, Integer> eachData : thisApp.parseOwners().entrySet()) {
			Player player = new Player(eachData.getKey());
			for(int index = 0; index < eachData.getValue(); index++) {
				Ticket ticket = new Ticket();
				player.addTicket(ticket);
			}
			thisApp.players.add(player);
		}
		System.out.println(thisApp.players);
		System.out.println("Prizes:");
		thisApp.prizes = thisApp.parsePrizes();
		System.out.println(thisApp.prizes);
		System.out.println("-----------------------------------");
		System.out.println("Shuffling ...");
		
		
//		// Randomize order of Prizes and Tickets
//		thisApp.shuffle(allTickets, thisApp.prizes, thisApp.extraShuffles);
//		System.out.println("-----------------------------------");
//		if (thisApp.debug) {
//			System.out.println("After shuffling tickets:");
//			System.out.println(allTickets);
//			System.out.println("After shuffling prizes:");
//			System.out.println(allPrizes);
//			System.out.println("-----------------------------------");
//		}
//
//		// Differentiate into different ways of distributing prizes
//		// depending on number of tickets and prizes to avoid
//		// ArrayIndexOutOfBoundsException
//		int index = 0;
//		if (allPrizes.size() > allTickets.size()) {
//			if (thisApp.debug) {
//				System.out.println("Prizes sample > Tickets sample");
//			}
//			// Assign each ticket to a prize
//			for (Ticket ticket : allTickets) {
//				// Note, not all prizes will be distributed, since each Ticket
//				// can only match one prize
//				Prize p = allPrizes.get(index);
//				ticket.setPrize(p.getName());
//				ticket.setUnits(1);
//				index = index + 1;
//			}
//		} else {
//			if (thisApp.debug) {
//				System.out.println("Prizes sample <= Tickets sample");
//			}
//			// Assign each prize to a ticket
//			for (Prize prize : allPrizes) {
//				Ticket t = allTickets.get(index);
//				t.setPrize(prize.getName());
//				t.setUnits(1);
//				index = index + 1;
//			}
//		}
//
//		thisApp.printResults(allTickets);
	}

	private void shuffle(List<Ticket> allTickets, ArrayList<Prize> allPrizes, int extraShuffles) {
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

	private HashMap<String, Integer> parseOwners() {
		HashMap<String, Integer> allPlayers = new HashMap<String, Integer>();
		// Read owners.txt file
		ArrayList<String> ownersFile = util.readData("players.txt");
		// Parse file content
		for (String line : ownersFile) {
			String[] eachOwner = line.split(",", 2);
			String playerName = eachOwner[0];
			String numTickets = eachOwner[1];
			// Add each line containing owner data "ownerName,numTickets" as an allOwners
			// map pair
			allPlayers.put(playerName, Integer.valueOf(numTickets));
		}
		return allPlayers;
	}

	private ArrayList<Prize> parsePrizes() {
		ArrayList<Prize> allPrizes = new ArrayList<Prize>();
		// Read prizes.txt file
		ArrayList<String> prizesFile = util.readData("prizes.txt");
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
