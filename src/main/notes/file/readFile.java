package main.notes.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class readFile {

	public static void main(String[] args) throws FileNotFoundException {
		
		String projectDir = System.getProperty("user.dir");
		File file = new File(projectDir + "\\src\\main\\notes\\file\\targetFile.txt");
		
		System.out.println(file.getAbsolutePath());
		System.out.println(file.canRead());
		System.out.println(file.canWrite());

		ArrayList<String> fileLines = new ArrayList<String>();
		
		//URL path = TimeElapseAppData.class.getResource("Localization.txt");
		//File file1 = new File(path.getFile());
		
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
		
		System.out.println(fileLines);
		
	}

}
