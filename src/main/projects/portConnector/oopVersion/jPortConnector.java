package main.projects.portConnector.oopVersion;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.prefs.Preferences;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class jPortConnector {
	
	//
	private Preferences config;
	
	// Constructor
	jPortConnector() {
		this.config = Preferences.userNodeForPackage(main.projects.portConnector.oopVersion.jPortConnector.class);
	}
	
	// Main
	public static void main(String[] args) {
		jPortConnector app = new jPortConnector();
		
		app.loadConfig();
		app.loadLookFeel();
		
	}
	
	
	
	private void loadConfig() {
		ArrayList<String> fileLines = new ArrayList<String>();
		// Open configuration file
		File file = new File(System.getProperty("user.dir") 
				+ "\\src\\main\\projects\\portConnector\\oopVersion\\resources\\portConnectorConfig.txt");
		// Read all lines from file
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				fileLines.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(null != scanner)
			{
				scanner.close();
			}
		}
		// Validate options and selected option
		int index = 0;
		while (index < fileLines.size()) {
			Config config = new Config();
			
			config.setName(fileLines.get(index));
			config.setOptions(
					new ArrayList<>(
							Arrays.asList(fileLines.get(index + 1).split(","))));
			config.setSelectedOption(fileLines.get(index + 2));
			
			System.out.println(config); //XXX
			
			index = index + 4;
		}
		
		
		
	}

	
	
	
	
	private void loadLookFeel() {
		try {
			// Set Look&Feel to current System's
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			outputDebugMsg("Successfully set UIManager look&feel to current system's look&feel ["+UIManager.getSystemLookAndFeelClassName()+"].", config);
		} catch (UnsupportedLookAndFeelException e) {
			// Default to CrossPlatformLookAndFeel if System's Feel fails to apply
			System.err.println("An UnsupportedLookAndFeelException error occurred while setting LookAndFeel to current sysL&F.");
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (UnsupportedLookAndFeelException e1) {
				System.err.println("An UnsupportedLookAndFeelException error occurred while setting LookAndFeel to crossPlatformL&F.");
			}  catch (ClassNotFoundException e1) {
				System.err.println("An ClassNotFoundException error occurred while setting LookAndFeel to crossPlatformL&F.");
			} catch (InstantiationException e1) {
				System.err.println("An InstantiationException error occurred while setting LookAndFeel to crossPlatformL&F.");
			} catch (IllegalAccessException e1) {
				System.err.println("An IllegalAccessException error occurred while setting LookAndFeel to crossPlatformL&F.");
			}
		} catch (ClassNotFoundException e) {
			System.err.println("An ClassNotFoundException error occurred while setting LookAndFeel to current sysL&F.");
		} catch (InstantiationException e) {
			System.err.println("An InstantiationException error occurred while setting LookAndFeel to current sysL&F.");
		} catch (IllegalAccessException e) {
			System.err.println("An IllegalAccessException error occurred while setting LookAndFeel to current sysL&F.");
		}
	}
	
	private void outputDebugMsg(String message, Preferences config) {
		if (message != null && message.length() > 0) {
			if (config.get("isDebug", "false").equals("true")) {
				System.out.println("[" + new Timestamp(System.currentTimeMillis()) + "] " + message);
			}
		}
	}
}
