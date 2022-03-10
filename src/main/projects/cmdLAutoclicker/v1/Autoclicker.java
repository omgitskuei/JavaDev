package main.projects.cmdLAutoclicker.v1;

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
			System.out.println("Total number of clicks:");
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				maxClicks = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				System.out.println("IOException Error - please try again.");
			} catch (NumberFormatException ex) {
				System.out.println("NumberFormatException Error - please try again.");
			}
			if (maxClicks == 0 || maxClicks < 0) {
				maxClicks = 0;
				System.out.println("Must be at least 1 click.");
			}
		}

		// Collect user input for AutoClick frequencies (min, max)
		while (minWaitSeconds == 0) {
			try {
				System.out.println("Minimum rate of clicks (in seconds):");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				minWaitSeconds = Integer.parseInt(in.readLine());
				if (minWaitSeconds < 1) {
					minWaitSeconds = 0;
					System.out.println("Must be at least 1 second.");
				}
			} catch (IOException e) {
				System.out.println("IOException Error - please try again.");
			} catch (NumberFormatException ex) {
				System.out.println("NumberFormatException Error - please try again.");
			}
		}
		while (maxWaitSeconds == 0) {
			try {
				System.out.println("Maximum rate of clicks (in seconds):");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				maxWaitSeconds = Integer.parseInt(in.readLine());
				if (maxWaitSeconds <= minWaitSeconds) {
					maxWaitSeconds = 0;
					System.out.println("Must be greater than Minimum rate.");
				}
			} catch (IOException e) {
				System.out.println("IOException Error - please try again.");
			} catch (NumberFormatException ex) {
				System.out.println("NumberFormatException Error - please try again.");
			}
		}
		
		// Print planned total clicks, length of total runtime in seconds
		System.out.println("Autoclicker[Total Clicks:" + maxClicks
				+ ", Minimum Wait:" + minWaitSeconds + " seconds"
				+ ", Maxmimum Wait:" + maxWaitSeconds + " seconds"
				+ "]");
		System.out.println("Total Runtime: Between " + getTotalRuntimeMsg(maxClicks, minWaitSeconds, maxWaitSeconds));
		
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
		doClicks();
		// print exit message
		System.out.println("Autoclicker done");
		// exit program with no errors on termination
		System.exit(0);
	}

	
	private static String getTotalRuntimeMsg(int maxClicks2, int minWait, int maxWait) {
		// convert milliseconds into minutes
		double minRuntimeMinutes = minWait * maxClicks2 / 60;
		double maxRuntimeMinutes = maxWait * maxClicks2 / 60;
		
		String rtnStr = (minRuntimeMinutes>60 && maxRuntimeMinutes>61) ? 
				"~" + (int) Math.round(minRuntimeMinutes/60) 
				+ " and ~" + (int) Math.round(maxRuntimeMinutes/60) + " hours."
				: "~" + Double.toString(minRuntimeMinutes) 
				+ " and ~" + Double.toString(maxRuntimeMinutes) + " minutes.";
		
		return rtnStr;
	}

	private static void doClicks() {
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
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}