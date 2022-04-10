package main.projects.macroEditor.v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.prefs.Preferences;

public class MacroEditorUtil {
	
	/**
	 * Read file given 
	 * @param path the local file path including the System.getProperty("user.dir") String
	 * @return fileContent an ArrayList<String> where each item is a single line in the file 
	 */
	protected ArrayList<String> readFile(String path) throws FileNotFoundException {
		File file = new File(path);
		ArrayList<String> fileContent = new ArrayList<String>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				fileContent.add(scanner.nextLine());
			}	
		} finally {
			if(null != scanner) {
				scanner.close();
			} else {
				System.out.println("Failed to close Scanner: Scanner was null, check console for exceptions.");
			}
		}
		return fileContent;
	}
	
	protected void logDebug(Preferences appConfig, String log) {
		if(appConfig.get("isDebug", "false").equals("true")) {
			System.out.println("[" + new Timestamp(System.currentTimeMillis()) + "]	" + log);
		}
	}
	
	protected HashMap<String, String> readFileAsMaps(String path) throws FileNotFoundException {
		HashMap<String, String> pairs = new HashMap<String, String>();
		ArrayList<String> configFileContents = readFile(path);
		for (String line : configFileContents) {
			String[] eachConfig = line.split(",", 2);
			String configKey = eachConfig[0];
			String configVal = eachConfig[1];
			pairs.put(configKey, configVal);
		}
		return pairs;
	}
}
