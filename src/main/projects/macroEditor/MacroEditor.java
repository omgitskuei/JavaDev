package main.projects.macroEditor;

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

public class MacroEditor {

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
		prefs.put("isDebug", "false");

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
				JFileChooser fileChooser = new JFileChooser("src\\main\\projects\\macroEditor\\");
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
						System.out.println("[" + getDebugTimestamp() + "] A FileNotFoundException error occurred.");
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
				JFileChooser fileChooser = new JFileChooser("src\\main\\projects\\macroEditor\\");
				fileChooser.setDialogTitle("Save As");   
				int userSelection = fileChooser.showSaveDialog(FRAME);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    if (isDebug.equals("true")) {
				    	System.out.println("[" + getDebugTimestamp() + "] Save as file name: " + fileToSave.getName());
				    	System.out.println("[" + getDebugTimestamp() + "] Save as file:      " + fileToSave.getAbsolutePath());
				    }
				    FileWriter myWriter;
					BufferedWriter writer;
					try {
						myWriter = new FileWriter(fileToSave.getAbsolutePath());
						writer = new BufferedWriter(myWriter);
						for(int index = 0; index<fileContents.size(); index++) {
							String eachLine = fileContents.get(index);
							if (isDebug.equals("true")) {
								System.out.println("[" + getDebugTimestamp() + "] On (Ln"+index+1+"), wrote \"" + eachLine + "\"");
							}
							eachLine = eachLine + "\r\n";
							writer.write(eachLine);
						}
						writer.close();
					} catch (IOException e) {
						System.out.println("[" + getDebugTimestamp() + "] An IOException error occurred.");
						e.printStackTrace();
					}
				}
			}
		});
		menu1_file.add(menu1_file_saveAs);
		menuBar.add(menu1_file);
		// - - - - - - - - - - - - - - - Menu bar > Help - - - - - - - - - - - - - - -
		JMenu menu2_help = new JMenu("Help");
		// - - - - - - - - - - - - - - - Menu bar > Help > Welcome - - - - - - - - - - -
		JMenuItem menu2_help_welcome = new JMenuItem("Welcome");
		menu2_help_welcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String isDebug = prefs.get("isDebug", "false");
				if (isDebug.equals("true")) {
					System.out.println("[" + getDebugTimestamp() + "] Clicked Help -> " + actEvent.getActionCommand());
				}
			}
		});
		menu2_help.add(menu2_help_welcome);
		// - - - - - - - - - - - - - - - Menu bar > Help > About - - - - - - - - - - - -
		JMenuItem menu2_help_about = new JMenuItem("About");
		menu2_help_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String isDebug = prefs.get("isDebug", "false");
				if (isDebug.equals("true")) {
					System.out.println("[" + getDebugTimestamp() + "] Clicked Help -> " + actEvent.getActionCommand());
				}
				PopupFactory pf = PopupFactory.getSharedInstance();
				JFrame f = new JFrame();
				JPanel p = new JPanel();
				JTextField t = new JTextField(10);
                p.setSize(t.getWidth(), t.getHeight());
                p.setPreferredSize(new Dimension(t.getWidth(), t.getHeight()));
                Point l = t.getLocationOnScreen();
                Popup popup = pf.getPopup(f, p, l.x, l.y + t.getHeight());
                popup.show();
			}
		});
		menu2_help.add(menu2_help_about);
		// - - - - - - - - - - - - - - - Menu bar > Help > Contribute - - - - - - - - -
		JMenuItem menu2_help_contribute = new JMenuItem("Contribute");
		menu2_help_contribute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String isDebug = prefs.get("isDebug", "false");
				if (isDebug.equals("true")) {
					System.out.println("[" + getDebugTimestamp() + "] Clicked Help -> " + actEvent.getActionCommand());
				}
			}
		});
		menu2_help.add(menu2_help_contribute);
		menuBar.add(menu2_help);
		// - - - - - - - - - - - - - - - Menu bar > Preferences - - - - - - - - - - - -
		JMenu menu3_pref = new JMenu("Preferences");
		// - - - - - - - - - - - - - - - Menu bar > Preferences > Debug - - - - - - - -
		JCheckBoxMenuItem menu3_pref_debug = new JCheckBoxMenuItem("Debug");
		menu3_pref_debug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String isDebug = prefs.get("isDebug", "false");
				if (isDebug.equals("false")) {
					prefs.put("isDebug", "true");
				} else {
					prefs.put("isDebug", "false");
				}
				if (isDebug.equals("true")) {
					System.out.println(
							"[" + getDebugTimestamp() + "] Clicked Preferences -> " + actEvent.getActionCommand());
				}
			}
		});
		menu3_pref.add(menu3_pref_debug);
		menuBar.add(menu3_pref);
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

		// InitButtonsJPanel
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		JButton button;
		buttonsPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// natural height, maximum width
		c.fill = GridBagConstraints.HORIZONTAL;
		button = new JButton("Button 1");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		buttonsPanel.add(button, c);

		button = new JButton("Button 2");
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		buttonsPanel.add(button, c);

		button = new JButton("Button 3");
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		buttonsPanel.add(button, c);

		button = new JButton("Long-Named Button 4");
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40; // make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		buttonsPanel.add(button, c);

		button = new JButton("5");
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; // bottom of space
		c.insets = new Insets(10, 0, 0, 0); // top padding
		c.gridx = 1; // aligned with button 2
		c.gridwidth = 2; // 2 columns wide
		c.gridy = 2; // third row
		buttonsPanel.add(button, c);
		FRAME.getContentPane().add(BorderLayout.SOUTH, buttonsPanel);

		FRAME.getContentPane().add(BorderLayout.WEST, new JButton("WEST"));
		FRAME.getContentPane().add(BorderLayout.CENTER, new JButton("CENTER"));
		FRAME.getContentPane().add(BorderLayout.EAST, new JButton("EAST"));

		FRAME.setVisible(true);
	}

}