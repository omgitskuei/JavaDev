package main.notes.gui.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.prefs.Preferences;

public class JMenuSaveFile {

	private static final SimpleDateFormat TIMESTAMPFORMAT = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	private static final JFrame FRAME = new JFrame();
	private static ArrayList<String> fileContents = new ArrayList<String>();
	
	private static String getDebugTimestamp() {
		return TIMESTAMPFORMAT.format(new Date(System.currentTimeMillis()));
	}

	
	public static void main(String[] args) {
		// initJFrame
		
		FRAME.setTitle("MacroEditor");
		FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRAME.setSize(400, 400);
		FRAME.setMinimumSize(new Dimension(400, 300));

		// preferences
		final Preferences prefs = Preferences
				.userNodeForPackage(main.projects.macroEditor.MacroEditor.class);
		prefs.put("isDebug", "true");

		// initMenuBar
		JMenuBar menuBar = new JMenuBar();
		// - - - - - - - - - - - - - - - Menu bar > File - - - - - - - - - - - - - - -
		JMenu menu1_file = new JMenu("File");
		// - - - - - - - - - - - - - - - Menu bar > File > Open - - - - - - - - - - - -
		JMenuItem menu1_file_open = new JMenuItem("Open");
		menu1_file_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String isDebug = prefs.get("isDebug", "false");
				if (isDebug.equals("true")) {
					System.out.println("[" + getDebugTimestamp() + "] Clicked File -> " + actEvent.getActionCommand());
				}
				JFileChooser fileChooser = new JFileChooser();
				int chosenFile = fileChooser.showOpenDialog(FRAME);
				if (chosenFile == JFileChooser.APPROVE_OPTION) { // JFileChooser.APPROVE_OPTION is 0
					File file = fileChooser.getSelectedFile();
					if (isDebug.equals("true")) {
						System.out.println("[" + getDebugTimestamp() + "] User selected a file;");
						System.out.println("File name:     " + file.getName());
						System.out.println("Absolute path: " + file.getAbsolutePath());
						System.out.println("Writeable:     " + file.canWrite());
						System.out.println("Readable       " + file.canRead());
						System.out.println("Size (bytes):  " + file.length());
					}
					try {
						Scanner myReader = new Scanner(file);
						if (isDebug.equals("true")) {
							System.out.println("<<File contents - BEGIN>>");
						}
						int lnCount = 1;
						while (myReader.hasNextLine()) {
							String eachLine = myReader.nextLine();
							if (isDebug.equals("true")) {
								System.out.println("(ln"+lnCount+") "+eachLine);
							}
							fileContents.add(eachLine);
							lnCount++;
						}
						if (isDebug.equals("true")) {
							System.out.println("<<File contents - END>>");
						}
						myReader.close();
					} catch (FileNotFoundException e) {
						System.out.println("A FileNotFoundException error occurred.");
						e.printStackTrace();
					}
				} else {
					if (isDebug.equals("true")) {
						System.out.println("[" + getDebugTimestamp() + "] User didn't select file");
					}
				}
			}
		});
		menu1_file.add(menu1_file_open);
		// - - - - - - - - - - - - - - - Menu bar > File > Save as - - - - - - - - - - -
		JMenuItem menu1_file_saveAs = new JMenuItem("Save as");
		menu1_file_saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String isDebug = prefs.get("isDebug", "false");
				if (isDebug.equals("true")) {
					System.out.println("[" + getDebugTimestamp() + "] Clicked File -> " + actEvent.getActionCommand());
				}
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Save As");   
				 
				int userSelection = fileChooser.showSaveDialog(FRAME);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    System.out.println("Save as file name: " + fileToSave.getName());
				    System.out.println("Save as file:      " + fileToSave.getAbsolutePath());
				    
				    FileWriter myWriter;
					BufferedWriter writer;
					try {
						myWriter = new FileWriter(fileToSave.getAbsolutePath());
						writer = new BufferedWriter(myWriter);
						for(int index = 0; index<fileContents.size(); index++) {
							String eachLine = fileContents.get(index);
							if (isDebug.equals("true")) {
								System.out.println("[" + getDebugTimestamp() + "] On (Ln"+index+"), wrote \"" + eachLine + "\"");
							}
							eachLine = eachLine + "\r\n";
							writer.write(eachLine);
						}
						writer.close();
					} catch (IOException e) {
						System.out.println("An IOException error occurred.");
						e.printStackTrace();
					}
				}
			}
		});
		menu1_file.add(menu1_file_saveAs);
		menuBar.add(menu1_file);
		// InitMenuBarJPanel
		JPanel menuBarPanel = new JPanel();
		menuBarPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.weighty = 0;
		menuBarPanel.add(menuBar, gbc);
		FRAME.getContentPane().add(BorderLayout.NORTH, menuBarPanel);

		FRAME.setVisible(true);
	}

}