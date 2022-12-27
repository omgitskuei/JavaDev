package main.projects.raffle.v2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import main.projects.raffle.RandomizerUtils;
import main.projects.raffle.v2.models.Draw;
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
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	ArrayList<Prize> prizes = new ArrayList<Prize>();
	ArrayList<Draw> draws = new ArrayList<Draw>();

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
		
		Raffle thisApp = new Raffle();

		System.out.println("RAFFLE v.2.5, omgitskuei, Dec 27 2022");
		System.out.println("----------------------------------------------");
		System.out.println("Change log");
		System.out.println("v.2.5 - 27/12/2022 - Aggregated results.");
		System.out.println("v.2.4 - 13/03/2022 - Added Ticket object UUID.");
		System.out.println("v.2.3 - 16/02/2022 - Added config file support");
		System.out.println("v.2.2 - 11/02/2022 - Randomize extra shuffles");
		System.out.println("v.2.1 - 09/02/2022 - Fixed output typos");
		System.out.println("v.2.0 - 06/02/2022 - Rewrote RAFFLE completely");
		System.out.println("----------------------------------------------");
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
				Ticket ticket = new Ticket(player.getUsername());
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
		
		
		// Randomize order of Players and Prizes
		thisApp.shuffle(thisApp.players, thisApp.prizes, thisApp.extraShuffles);
		System.out.println("-----------------------------------");
		if (thisApp.debug) {
			System.out.println("After shuffling players:");
			System.out.println(thisApp.players);
			System.out.println("After shuffling prizes:");
			System.out.println(thisApp.prizes);
			System.out.println("-----------------------------------");
		}

		// Differentiate into different ways of distributing prizes
		// depending on number of tickets and prizes to avoid
		// ArrayIndexOutOfBoundsException
		for (Player player : thisApp.players) {
			thisApp.tickets.addAll(player.getTickets());
		}
		
		for (int i = 0; i < thisApp.extraShuffles; i++) {
			Collections.shuffle(thisApp.tickets);
		}
			
		int ticketInd = 0;
		boolean repeatUseTickets = false;
		if (thisApp.prizes.size() > thisApp.tickets.size()) {
			if (thisApp.debug) {
				System.out.println("Note: #Prizes outnumber #Tickets");
				System.out.println("-----------------------------------");
			}
			repeatUseTickets = true;
		}
		for (Prize prize : thisApp.prizes) {
			if(repeatUseTickets) {
				if(!(ticketInd < thisApp.tickets.size())) {
					ticketInd = 0;
				}
			}
			Draw draw = new Draw();
			draw.setNum(1);
			draw.setPrize(prize);
			draw.setTicket(thisApp.tickets.get(ticketInd));
			draw.setWinner(thisApp.tickets.get(ticketInd).getOwner());
			
			thisApp.draws.add(draw);
			
			ticketInd++;
		}
		
		if (thisApp.debug) {
			System.out.println("Details of Each Draw:");
			thisApp.printEachDraw(thisApp.draws);
			System.out.println("-----------------------------------");
		}
		
		System.out.println("Tally of Prizes by Winner:");
		String contents = thisApp.prettyPrintDraws(thisApp.draws);
		
		thisApp.util.writeFile("RaffleResults.txt", contents);
	}
	
	private void printEachDraw(ArrayList<Draw> draws) {
		for (Draw draw : draws) {
			String line = "Prize: " + draw.getPrize().getName() + ", Winner: " + draw.getWinner() + ", Ticket ID: " + draw.getTicket().getID();
			System.out.println(line);
		}
	}
	
	private String prettyPrintDraws(ArrayList<Draw> draws) {
		HashMap<String, HashMap<String, String>> tally = new HashMap<String, HashMap<String, String>>();
		
		for (int j = 0; j < draws.size(); j++) {
			String prizeName = draws.get(j).getPrize().getName();
			int numberOfPrizes = draws.get(j).getNum();
			if(!tally.containsKey(draws.get(j).getWinner())) {
				HashMap<String, String> winnings = new HashMap<String, String>();
				winnings.put(prizeName, String.valueOf(numberOfPrizes));
				tally.put(draws.get(j).getWinner(), winnings);
			} else {
				HashMap<String, String> player = tally.get(draws.get(j).getWinner());
				if(player.containsKey(prizeName)) {
					player.put(prizeName, String.valueOf(Integer.valueOf(player.get(prizeName)) + numberOfPrizes));
				} else {
					player.put(prizeName, String.valueOf(numberOfPrizes));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (Entry<String, HashMap<String, String>> playerTally : tally.entrySet()) {
			sb.append("Winner: " + playerTally.getKey() + "\n");
			for(Entry<String, String> prize : playerTally.getValue().entrySet()) {
				String prizeName = prize.getKey();
				String units = prize.getValue();
				if(!units.equals("0")) {
					sb.append(prizeName + " : " + units + "\n");
				}
			}
		}
		System.out.println(sb);
		return sb.toString();
	}

	private void shuffle(ArrayList<Player> allPlayers, ArrayList<Prize> allPrizes, int extraShuffles) {
		if (extraShuffles > 0) {
			for (int i = 1; i <= extraShuffles + 1; i++) {
				System.out.println("Shuffling " + i + " time(s).");
				Collections.shuffle(allPlayers);
				Collections.shuffle(allPrizes);
			}
		} else {
			Collections.shuffle(allPlayers);
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
