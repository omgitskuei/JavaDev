package main.notes.automation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadTxtAndRobot {

	private static Robot r;
	

	public static void main(String[] args) {
		// Configurate program
		String fileLocation = "src\\main\\notes\\automation\\macroTxt.txt";
		boolean isDebug = true;
		ArrayList<String> fileContentsStrings = new ArrayList<String>();
		ArrayList<Integer> fileContentsKeyEvents = new ArrayList<Integer>();
		// Get file of macros
		File targetFile = new File(fileLocation);
		if (isDebug) {
			System.out.println("File name:     " + targetFile.getName());
			System.out.println("Absolute path: " + targetFile.getAbsolutePath());
			System.out.println("Writeable:     " + targetFile.canWrite());
			System.out.println("Readable       " + targetFile.canRead());
			System.out.println("Size (bytes):  " + targetFile.length());
		}
		// Check if file doesn"t exist
		if (!(targetFile.canWrite() && targetFile.canRead())) {
			// File doesn"t exist - Make a new file
			System.err.println("Making a new file");
			// System.exit(1);
		}
		// Read file
		try {
			Scanner myReader = new Scanner(targetFile);
			if (isDebug) {
				System.out.println("File contents - BEGIN");
			}
			int lnCount = 1;
			while (myReader.hasNextLine()) {
				String eachLine = myReader.nextLine();
				if (isDebug) {
					System.out.println("[ln" + lnCount + "] " + eachLine);
				}
				fileContentsStrings.add(eachLine);
				lnCount++;
			}
			if (isDebug) {
				System.out.println("File contents - END");
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("A FileNotFoundException error occurred.");
			e.printStackTrace();
			System.exit(1);
		}
		// Convert String to KeyEvents
		for (String eachLine : fileContentsStrings) {
			switch (eachLine) {
			case "a":
				fileContentsKeyEvents.add(KeyEvent.VK_A);
				break;
			case "b":
				fileContentsKeyEvents.add(KeyEvent.VK_B);
				break;
			case "c":
				fileContentsKeyEvents.add(KeyEvent.VK_C);
				break;
			case "d":
				fileContentsKeyEvents.add(KeyEvent.VK_D);
				break;
			case "e":
				fileContentsKeyEvents.add(KeyEvent.VK_E);
				break;
			case "f":
				fileContentsKeyEvents.add(KeyEvent.VK_F);
				break;
			case "g":
				fileContentsKeyEvents.add(KeyEvent.VK_G);
				break;
			case "h":
				fileContentsKeyEvents.add(KeyEvent.VK_H);
				break;
			case "i":
				fileContentsKeyEvents.add(KeyEvent.VK_I);
				break;
			case "j":
				fileContentsKeyEvents.add(KeyEvent.VK_J);
				break;
			case "k":
				fileContentsKeyEvents.add(KeyEvent.VK_K);
				break;
			case "l":
				fileContentsKeyEvents.add(KeyEvent.VK_L);
				break;
			case "m":
				fileContentsKeyEvents.add(KeyEvent.VK_M);
				break;
			case "n":
				fileContentsKeyEvents.add(KeyEvent.VK_N);
				break;
			case "o":
				fileContentsKeyEvents.add(KeyEvent.VK_O);
				break;
			case "p":
				fileContentsKeyEvents.add(KeyEvent.VK_P);
				break;
			case "q":
				fileContentsKeyEvents.add(KeyEvent.VK_Q);
				break;
			case "r":
				fileContentsKeyEvents.add(KeyEvent.VK_R);
				break;
			case "s":
				fileContentsKeyEvents.add(KeyEvent.VK_S);
				break;
			case "t":
				fileContentsKeyEvents.add(KeyEvent.VK_T);
				break;
			case "u":
				fileContentsKeyEvents.add(KeyEvent.VK_U);
				break;
			case "v":
				fileContentsKeyEvents.add(KeyEvent.VK_V);
				break;
			case "w":
				fileContentsKeyEvents.add(KeyEvent.VK_W);
				break;
			case "x":
				fileContentsKeyEvents.add(KeyEvent.VK_X);
				break;
			case "y":
				fileContentsKeyEvents.add(KeyEvent.VK_Y);
				break;
			case "z":
				fileContentsKeyEvents.add(KeyEvent.VK_Z);
				break;
//		        case "A": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_A); break;
//		        case "B": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_B); break;
//		        case "C": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_C); break;
//		        case "D": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_D); break;
//		        case "E": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_E); break;
//		        case "F": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_F); break;
//		        case "G": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_G); break;
//		        case "H": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_H); break;
//		        case "I": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_I); break;
//		        case "J": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_J); break;
//		        case "K": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_K); break;
//		        case "L": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_L); break;
//		        case "M": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_M); break;
//		        case "N": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_N); break;
//		        case "O": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_O); break;
//		        case "P": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_P); break;
//		        case "Q": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_Q); break;
//		        case "R": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_R); break;
//		        case "S": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_S); break;
//		        case "T": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_T); break;
//		        case "U": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_U); break;
//		        case "V": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_V); break;
//		        case "W": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_W); break;
//		        case "X": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_X); break;
//		        case "Y": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_Y); break;
//		        case "Z": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_Z); break;
			case "`":
				fileContentsKeyEvents.add(KeyEvent.VK_BACK_QUOTE);
				break;
			case "0":
				fileContentsKeyEvents.add(KeyEvent.VK_0);
				break;
			case "1":
				fileContentsKeyEvents.add(KeyEvent.VK_1);
				break;
			case "2":
				fileContentsKeyEvents.add(KeyEvent.VK_2);
				break;
			case "3":
				fileContentsKeyEvents.add(KeyEvent.VK_3);
				break;
			case "4":
				fileContentsKeyEvents.add(KeyEvent.VK_4);
				break;
			case "5":
				fileContentsKeyEvents.add(KeyEvent.VK_5);
				break;
			case "6":
				fileContentsKeyEvents.add(KeyEvent.VK_6);
				break;
			case "7":
				fileContentsKeyEvents.add(KeyEvent.VK_7);
				break;
			case "8":
				fileContentsKeyEvents.add(KeyEvent.VK_8);
				break;
			case "9":
				fileContentsKeyEvents.add(KeyEvent.VK_9);
				break;
			case "-":
				fileContentsKeyEvents.add(KeyEvent.VK_MINUS);
				break;
			case "=":
				fileContentsKeyEvents.add(KeyEvent.VK_EQUALS);
				break;
//		        case "~": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE); break;
			case "!":
				fileContentsKeyEvents.add(KeyEvent.VK_EXCLAMATION_MARK);
				break;
			case "@":
				fileContentsKeyEvents.add(KeyEvent.VK_AT);
				break;
			case "#":
				fileContentsKeyEvents.add(KeyEvent.VK_NUMBER_SIGN);
				break;
			case "$":
				fileContentsKeyEvents.add(KeyEvent.VK_DOLLAR);
				break;
//		        case "%": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_5); break;
			case "^":
				fileContentsKeyEvents.add(KeyEvent.VK_CIRCUMFLEX);
				break;
			case "&":
				fileContentsKeyEvents.add(KeyEvent.VK_AMPERSAND);
				break;
			case "*":
				fileContentsKeyEvents.add(KeyEvent.VK_ASTERISK);
				break;
			case "(":
				fileContentsKeyEvents.add(KeyEvent.VK_LEFT_PARENTHESIS);
				break;
			case ")":
				fileContentsKeyEvents.add(KeyEvent.VK_RIGHT_PARENTHESIS);
				break;
			case "_":
				fileContentsKeyEvents.add(KeyEvent.VK_UNDERSCORE);
				break;
			case "+":
				fileContentsKeyEvents.add(KeyEvent.VK_PLUS);
				break;
			case "\t":
				fileContentsKeyEvents.add(KeyEvent.VK_TAB);
				break;
			case "\n":
				fileContentsKeyEvents.add(KeyEvent.VK_ENTER);
				break;
			case "[":
				fileContentsKeyEvents.add(KeyEvent.VK_OPEN_BRACKET);
				break;
			case "]":
				fileContentsKeyEvents.add(KeyEvent.VK_CLOSE_BRACKET);
				break;
			case "\\":
				fileContentsKeyEvents.add(KeyEvent.VK_BACK_SLASH);
				break;
//		        case "{": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET); break;
//		        case "}": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET); break;
//		        case "|": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH); break;
			case ";":
				fileContentsKeyEvents.add(KeyEvent.VK_SEMICOLON);
				break;
			case ":":
				fileContentsKeyEvents.add(KeyEvent.VK_COLON);
				break;
			case "\'":
				fileContentsKeyEvents.add(KeyEvent.VK_QUOTE);
				break;
			case "\"":
				fileContentsKeyEvents.add(KeyEvent.VK_QUOTEDBL);
				break;
			case ",":
				fileContentsKeyEvents.add(KeyEvent.VK_COMMA);
				break;
//		        case "<": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA); break;
			case ".":
				fileContentsKeyEvents.add(KeyEvent.VK_PERIOD);
				break;
//		        case ">": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD); break;
			case "/":
				fileContentsKeyEvents.add(KeyEvent.VK_SLASH);
				break;
//		        case "?": fileContentsKeyEvents.add(KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH); break;
			case " ":
				fileContentsKeyEvents.add(KeyEvent.VK_SPACE);
				break;
			default:
				System.err.println("Cannot type character: [" + eachLine + "]");
			}
		}
		// Initiate Robot
		try {
			r = new Robot();
		} catch (AWTException e) {
			System.err.println("Failed to create new java.awt.Robot()");
			e.printStackTrace();
		}
		// Countdown before starting automation
		try {
			for(int index=5; index>0; index--) {
				System.err.println("Initiating automation in " + index);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.err.println("Program threw InterruptedException;");
			e.printStackTrace();
		}
		// Type each keypress
		for (int index = 0; index < fileContentsKeyEvents.size(); index++) {
			r.keyPress(fileContentsKeyEvents.get(index));
			r.delay(1000);
			r.keyRelease(fileContentsKeyEvents.get(index));
		}
		
		// Finish
		System.err.println("Finished.");
	}
}
