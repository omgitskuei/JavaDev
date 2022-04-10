package main.notes.system;

import java.io.File;
import java.nio.file.FileSystems;

public class GetSystemStrings {

	public static void main(String[] args) {
		
		// How to get the string for local system file separator
		// Windows do Desktop\MysteryFolder\TotallySafe\OhGodMyEyes, which makes the separator "\\" in Java
		System.out.println(File.separator);
		System.out.println(File.separatorChar);
		System.out.println(FileSystems.getDefault().getSeparator());
		
		// Doing Enter or Linebreak in local system
		System.out.println("test1" + System.lineSeparator() + "test2");
	}

}
