package main.projects.Autoclicker.v1;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

public class Autoclicker {

	public static boolean running = true;
	public static int totalClicks = 0;
	public static int minWaitSeconds = 0;
	public static int maxWaitSeconds = 0;
	public static int maxClicks = 0;

	public static void main(String[] args) {
		// Collect user input for total # of clicks in this run
		while (maxClicks == 0) {
			maxClicks = promptUserInt("Total number of clicks:", false, false);
		}

		// Collect user input for AutoClick frequencies (min, max)
		// Prompt user input for minimum interval between clicks
		while (minWaitSeconds == 0) {
			minWaitSeconds = promptUserInt("Minimum rate of clicks (in seconds):", false, false);
		}
		// Prompt user input for maximum interval between clicks
		while (maxWaitSeconds == 0) {
			maxWaitSeconds = promptUserInt("Maximum rate of clicks (in seconds):", false, false);
			if (maxWaitSeconds <= minWaitSeconds && maxWaitSeconds != 0) { // don't show message if == 0, already showing a message
				System.out.println("Must be greater than Minimum rate. - Please try again.");
				maxWaitSeconds = 0;
			}
		}
		
		// Print planned total clicks, length of total runtime in seconds
		System.out.println("Autoclicker starting: [Total Clicks:" + maxClicks
			+ ", Minimum Wait:" + minWaitSeconds + " seconds"
			+ ", Maxmimum Wait:" + maxWaitSeconds + " seconds"
			+ "]");
		System.out.println("Total Runtime between " 
			+ getTotalTime(maxClicks, minWaitSeconds) 
			+ " ~ " 
			+ getTotalTime(maxClicks, maxWaitSeconds));
		
		// 3 second count down to begin
		try {
			System.out.print("Starting in: 3");
			Thread.sleep(1000);
			System.out.print(", 2");
			Thread.sleep(1000);
			System.out.println(", 1.");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// do clicks
		try {
			Robot robot = new Robot();
			while (running == true) {
				int randomNum = ThreadLocalRandom.current().nextInt(minWaitSeconds * 1000, maxWaitSeconds * 1000);
				System.out.println(
						"Current Rate: " + randomNum / 1000 + " seconds (~" + randomNum / 1000 / 60 + " minutes)");
				Thread.sleep(randomNum);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				totalClicks++;
				System.out.println("Amount Clicked So Far: " + totalClicks);

				if (totalClicks == maxClicks) {
					running = false;
				}
			}
		} catch (AWTException e) {
			System.out.println("AWTException - Exiting Program");
			System.exit(-1);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException - Exiting Program");
			System.exit(-1);
		}
		
		// print exit message
		System.out.println("Autoclicker done");
		// exit program with no errors on termination
		System.exit(0);
	}
	
	/**
	 * Prompts int value from user for variable
	 * @param message
	 * @param acceptZero
	 * @param acceptNegatives
	 */
	private static int promptUserInt(String message, boolean acceptZero, boolean acceptNegatives) {
		int variable = 0;
		try {
			System.out.print(message);
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			variable = Integer.parseInt(in.readLine());
			if(!acceptZero && variable == 0) {
				throw new NumberFormatException();
			}
			if(!acceptNegatives && variable < 0) {
				throw new NumberFormatException();
			}
		} catch (IOException e) {
			System.out.println("IOException - Exiting Program");
			System.exit(-1);
		} catch (NumberFormatException ex) {
			System.out.print("Must enter a");
			if(!acceptNegatives) {
				System.out.print(" positive");
			}
			System.out.print(" whole number");
			if(!acceptZero) {
				System.out.print(" larger than 0");
			}
			System.out.println(" - Please try again.");
			variable = 0;
		}
		return variable;
	}
	
	/**
	 * Take number of clicks and wait interval, return String of total duration;
	 * duration = clicks * interval;
	 * @param clicks
	 * @param waitSeconds
	 * @return String "## hours ## minutes"
	 */
	private static String getTotalTime(int clicks, int waitSeconds) {
		double seconds = waitSeconds * clicks;
		double hours = Math.floor(seconds/ 60.0 / 60.0);
		double minsR = (seconds/ 60.0 / 60.0 - hours) * 60;
		double mins = Math.floor(minsR);
		double secsR = Math.round((minsR - mins)* 60);
		return (((hours<1)? "" : String.format("%.0f", hours) + " hours ") 
				+ ((mins<1)? "" : String.format("%.0f", mins) + " minutes ")
				+ ((secsR<1)? "" : String.format("%.0f", secsR) + " seconds"));
	}
}