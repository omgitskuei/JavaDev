package main.projects.raffle.version2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RaffleUtils {
	
	protected ArrayList<String> readFile(String localFileName) {
		File file = new File(System.getProperty("user.dir") + "\\src\\main\\projects\\raffle\\version2\\resources\\" + localFileName);
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
}
