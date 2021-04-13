package main.notes.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import main.projects.timeElapse.version2.TimeElapseAppData;

public class readFile {

	public static void main(String[] args) throws FileNotFoundException {
		String projectDir = System.getProperty("user.dir");
		
		
		
		File file = new File(projectDir + "\\src\\main\\notes\\file\\targetFile.txt");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.canRead());
		System.out.println(file.canWrite());

		
		ArrayList<String> fileLines = new ArrayList<String>();
		
		URL path = TimeElapseAppData.class.getResource("Localization.txt");
		File file1 = new File(path.getFile());
		
		Scanner localizationScanner = new Scanner(file1);
		while (localizationScanner.hasNextLine()) {
			fileLines.add(localizationScanner.nextLine());
		}
		localizationScanner.close();
		System.out.println(fileLines);
		
	}

}
