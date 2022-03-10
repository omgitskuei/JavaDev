package main.projects.clipboardMod.v1;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardModUtil {

	/**
	 * Calls String.replaceAll(findChar, newChar) on the contents of system Clipboard
	 * @param findChar	What character are you modifying?
	 * @param newChar	New character to replace old one with. Can be "" for 'removal' effect.
	 * @throws UnsupportedFlavorException
	 * @throws IOException
	 */
	public void replaceChar(String findChar, String newChar) throws UnsupportedFlavorException, IOException {
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		String input = c.getData(java.awt.datatransfer.DataFlavor.stringFlavor).toString();
		StringSelection selection = new StringSelection(input.replaceAll("-", ""));
		// replace clipboard with modified string
		c.setContents(selection, selection);
	}
}
