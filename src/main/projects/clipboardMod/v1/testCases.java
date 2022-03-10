package main.projects.clipboardMod.v1;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class testCases {

	public static void main(String[] args) {
		ClipboardModUtil clipbMod = new ClipboardModUtil();
		
		/*
		 * 1)
		 * Copy this:
		 * 978-1-332-2931
		 * 
		 * 2)
		 * Run this java app
		 * 
		 * 3)
		 * Paste (Cltr+V), should print out 97813322931
		 */
		try {
			clipbMod.replaceChar("-", "");
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
