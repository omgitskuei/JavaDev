package main.projects.raffle.v2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class RaffleUtils {
	
	protected Properties readConfig() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		File file = new File(System.getProperty("user.dir") 
				+ "\\src\\main\\projects\\raffle\\v2\\raffle.config");
		try (FileInputStream fis = new FileInputStream(file)) {
		    prop.load(fis);
		} 
		return prop;
	}
	
	protected ArrayList<String> readData(String localFileName) {
		File file = new File(System.getProperty("user.dir") 
			+ "\\src\\main\\projects\\raffle\\v2\\data\\" + localFileName);
		ArrayList<String> fileContent = new ArrayList<String>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				fileContent.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Failed to Read File: File URL was " + file.getAbsolutePath());
		} finally {
			if(null != scanner) {
				scanner.close();
			} else {
				System.out.println("Failed to close Scanner: Scanner was null, check console for exceptions.");
			}
		}
		return fileContent;
	}
	
	protected File writeFile(String localFileName, ArrayList<String> fileLines) {
		File file = new File(System.getProperty("user.dir") 
			+ "\\src\\main\\projects\\raffle\\v2\\output\\" + localFileName);
		
		StringBuilder sb = new StringBuilder();
		for (String line : fileLines) {
			sb.append(line + "\n");
		}
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
		} catch(IOException e) {
			System.out.println("Failed to ..."); //TODO
		} 
		try {
			writer.write(sb.toString());
		} catch(IOException e) {
			System.out.println("Failed to ..."); //TODO
		} 
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("Failed to ..."); //TODO
		}
		return file;
	}
}
