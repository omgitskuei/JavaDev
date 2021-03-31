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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.prefs.Preferences;

public class MacroEditor {

	private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	private static final JFrame FRAME = new JFrame();
	private static final Preferences PREFS = Preferences.userNodeForPackage(main.projects.macroEditor.MacroEditor.class);
	private static ArrayList<String> fileContents = new ArrayList<String>();
	private static HashMap<String, String> contribURLs = new HashMap<String, String>();
	private static HashMap<String, HashMap<String, String>> i18nLabels = new HashMap<String, HashMap<String, String>>();
	private static String version = "2021-03";
	
	private static String getDebugTimestamp() {
		return TIMESTAMP_FORMAT.format(new Date(System.currentTimeMillis()));
	}

	public static void main(String[] args) {
		
		// initJFrame
		FRAME.setTitle("MacroEditor");
		FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRAME.setSize(400, 400);
		FRAME.setMinimumSize(new Dimension(400, 300));
		
		// initcontribURLs
		contribURLs.put("Github", "https://github.com/omgitskuei");
		contribURLs.put("Gitlab", "https://gitlab.com/omgitskuei");
		
		// initPreferences
		PREFS.put("isDebug", "true");   // valid: true, false
		PREFS.put("lang", "english");   // valid: english, chinese
		
		// initI18nLabels
		HashMap<String, String> englishLabels = new HashMap<String, String>();
		// menuBar labels
		englishLabels.put("menuBarFile",        "File");
		englishLabels.put("menuBarFileOpen",    "Open");
		englishLabels.put("menuBarFileSaveAs",  "Save As");
		englishLabels.put("menuBarHelp",        "Help");
		englishLabels.put("menuBarHelpWelcome", "Welcome");
		englishLabels.put("menuBarHelpAbout",   "About");
		englishLabels.put("menuBarHelpContrib", "Contribute");
		englishLabels.put("menuBarPrefs",       "Preferences");
		englishLabels.put("menuBarPrefsDebug",  "Debug");
		englishLabels.put("menuBarPrefsLang",   "Language");
		englishLabels.put("menuBarPrefsLangEn", "English");
		englishLabels.put("menuBarPrefsLangCn", "Chinese");
		i18nLabels.put("english", englishLabels);
		HashMap<String, String> chineseLabels = new HashMap<String, String>();
		chineseLabels.put("menuBarFile",        "檔案");
		chineseLabels.put("menuBarFileOpen",    "打開");
		chineseLabels.put("menuBarFileSaveAs",  "儲存");
		chineseLabels.put("menuBarHelp",        "Help-CN");
		chineseLabels.put("menuBarHelpWelcome", "歡迎訊息");
		chineseLabels.put("menuBarHelpAbout",   "關於App");
		chineseLabels.put("menuBarHelpContrib", "Contribute-CN");
		chineseLabels.put("menuBarPrefs",       "Preferences-CN");
		chineseLabels.put("menuBarPrefsDebug",  "除錯");
		chineseLabels.put("menuBarPrefsLang",   "語言");
		chineseLabels.put("menuBarPrefsLangEn", "英文");
		chineseLabels.put("menuBarPrefsLangCn", "中文");
		i18nLabels.put("chinese", chineseLabels);

		// initMenuBar
		JMenuBar menuBar = new JMenuBar();
		// - - - - - - - - - - - - - - - Menu bar > File - - - - - - - - - - - - - - -
		JMenu menuBarFile = new JMenu(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarFile"));
		// - - - - - - - - - - - - - - - Menu bar > File > Open - - - - - - - - - - - -
		JMenuItem menuBarFileOpen = new JMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarFileOpen"));
		menuBarFileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("[" + getDebugTimestamp() + "] Clicked File -> " + actEvent.getActionCommand());
				JFileChooser fileChooser = new JFileChooser("src\\main\\projects\\macroEditor\\");
				int chosenFile = fileChooser.showOpenDialog(FRAME);
				if (chosenFile == JFileChooser.APPROVE_OPTION) { // JFileChooser.APPROVE_OPTION is 0
					File file = fileChooser.getSelectedFile();
					outputDebugMsg("[" + getDebugTimestamp() + "] User selected a file;");
					outputDebugMsg("File name:     " + file.getName());
					outputDebugMsg("Absolute path: " + file.getAbsolutePath());
					outputDebugMsg("Writeable:     " + file.canWrite());
					outputDebugMsg("Readable       " + file.canRead());
					outputDebugMsg("Size (bytes):  " + file.length());
					try {
						Scanner myReader = new Scanner(file);
						outputDebugMsg("<<File contents - BEGIN>>");
						int lnCount = 1;
						while (myReader.hasNextLine()) {
							String eachLine = myReader.nextLine();
							outputDebugMsg("(ln"+lnCount+") "+eachLine);
							fileContents.add(eachLine);
							lnCount++;
						}
						outputDebugMsg("<<File contents - END>>");
						myReader.close();
					} catch (FileNotFoundException e) {
						System.err.println("[" + getDebugTimestamp() + "] A FileNotFoundException error occurred.");
						e.printStackTrace();
					}
				} else {
					outputDebugMsg("[" + getDebugTimestamp() + "] User didn't select file");
				}
			}
		});
		menuBarFile.add(menuBarFileOpen);
		// - - - - - - - - - - - - - - - Menu bar > File > Save as - - - - - - - - - - -
		JMenuItem menuBarFileSaveAs = new JMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarFileSaveAs"));
		menuBarFileSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("[" + getDebugTimestamp() + "] Clicked File -> " + actEvent.getActionCommand());
				JFileChooser fileChooser = new JFileChooser("src\\main\\projects\\macroEditor\\");
				fileChooser.setDialogTitle("Save As");   
				int userSelection = fileChooser.showSaveDialog(FRAME);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    outputDebugMsg("[" + getDebugTimestamp() + "] Save as file name: " + fileToSave.getName());
				    outputDebugMsg("[" + getDebugTimestamp() + "] Save as file:      " + fileToSave.getAbsolutePath());
				    FileWriter myWriter;
					BufferedWriter writer;
					try {
						myWriter = new FileWriter(fileToSave.getAbsolutePath());
						writer = new BufferedWriter(myWriter);
						for(int index = 0; index<fileContents.size(); index++) {
							String eachLine = fileContents.get(index);
							outputDebugMsg("[" + getDebugTimestamp() + "] On (Ln"+(index+1)+"), wrote \"" + eachLine + "\"");
							eachLine = eachLine + "\r\n";
							writer.write(eachLine);
						}
						writer.close();
					} catch (IOException e) {
						System.err.println("[" + getDebugTimestamp() + "] An IOException error occurred.");
						e.printStackTrace();
					}
				}
			}
		});
		menuBarFile.add(menuBarFileSaveAs);
		menuBar.add(menuBarFile);
		// - - - - - - - - - - - - - - - Menu bar > Help - - - - - - - - - - - - - - -
		JMenu menuBarHelp = new JMenu(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarHelp"));
		// - - - - - - - - - - - - - - - Menu bar > Help > Welcome - - - - - - - - - - -
		JMenuItem menuBarHelpWelcome = new JMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarHelpWelcome"));
		menuBarHelpWelcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("[" + getDebugTimestamp() + "] Clicked Help -> " + actEvent.getActionCommand());
			}
		});
		menuBarHelp.add(menuBarHelpWelcome);
		// - - - - - - - - - - - - - - - Menu bar > Help > About - - - - - - - - - - - -
		JMenuItem menuBarHelpAbout = new JMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarHelpAbout"));
		menuBarHelpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("[" + getDebugTimestamp() + "] Clicked Help -> " + actEvent.getActionCommand());
				outputDebugMsg("[" + getDebugTimestamp() + "] version = " + version);
				JOptionPane.showMessageDialog(
					FRAME, 
					"MacroEditor (Version:" + version + ")\r\n"
					+ "\r\n"
					+ "MacroEditor was created by Kuei-Feng Tung in 2021 \r\n"
					+ "with the purpose of simplifying the creation of \r\n"
					+ "automation commands. With this app, users can \r\n"
					+ "automate using text files, without needing the \r\n"
					+ "know-how of coding with Java robot API. \r\n"
					+ "\r\n"
					+ "(C) Copyright MacroEditor 2021. All rights reserved.\r\n"
					+ "MacroEditor and the MacroEditor logo are trademarks \r\n"
					+ "of Kuei-Feng Tung, https://github.com/omgitskuei. \r\n"
					+ "The MacroEditor logo cannot be altered without \r\n"
					+ "permission. Oracle and Java are trademarks or \r\n"
					+ "registered trademarks of Oracle and/or its \r\n"
					+ "affiliates.",
					"About MacroEditor", 
					JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuBarHelp.add(menuBarHelpAbout);
		// - - - - - - - - - - - - - - - Menu bar > Help > Contribute - - - - - - - - -
		JMenuItem menuBarHelpContrib = new JMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarHelpContrib"));
		menuBarHelpContrib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("[" + getDebugTimestamp() + "] Clicked Help -> " + actEvent.getActionCommand());
				outputDebugMsg("[" + getDebugTimestamp() + "] contribURLs = " + contribURLs);
				String selectedOption = (String) JOptionPane.showInputDialog(
					FRAME,
					"Contribute by following me on:", 
					"Contribute",					// Dialog window title
					JOptionPane.PLAIN_MESSAGE, 		// Type of Dialog window
					null, 							// Icon
					contribURLs.keySet().toArray(), // Select options (Object[]), convert Map to Set<String> to String[]
					"Github"						// Default option
				);
				// If the user presses OK, and return a selected option, go to the corresponding URL
				if ((selectedOption != null) && (selectedOption.length() > 0)) {
					outputDebugMsg("[" + getDebugTimestamp() + "] User chose [" + selectedOption + "]");
					try {
						java.awt.Desktop.getDesktop().browse(java.net.URI.create(contribURLs.get(selectedOption)));
					} catch (IOException ioE) {
						System.err.println("[" + getDebugTimestamp() + "] A IOException error occurred.");
						ioE.printStackTrace();
					}
				} else {
					outputDebugMsg("[" + getDebugTimestamp() + "] User closed Contribute dialog window.");
				}
				// Close dialog window, Return to FRAME
				return;
			}
		});
		menuBarHelp.add(menuBarHelpContrib);
		menuBar.add(menuBarHelp);
		// - - - - - - - - - - - - - - - Menu bar > Preferences - - - - - - - - - - - -
		JMenu menuBarPrefs = new JMenu(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefs"));
		// - - - - - - - - - - - - - - - Menu bar > Preferences > Debug - - - - - - - -
		JCheckBoxMenuItem menuBarPrefsDebug;
		if(PREFS.get("isDebug", "false").equals("true")) {
			menuBarPrefsDebug = new JCheckBoxMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsDebug"), true); 
		} else {
			menuBarPrefsDebug = new JCheckBoxMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsDebug")); 
		}
		menuBarPrefsDebug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				if (PREFS.get("isDebug", "false").equals("false")) {
					PREFS.put("isDebug", "true");
				} else {
					PREFS.put("isDebug", "false");
				}
				outputDebugMsg("[" + getDebugTimestamp() + "] Clicked Preferences -> " + actEvent.getActionCommand());
			}
		});
		menuBarPrefs.add(menuBarPrefsDebug);
		// - - - - - - - - - - - - - - - Menu bar > Preferences > Language - - - - - - - -
		JMenu menuBarPrefsLang = new JMenu(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLang"));
		ButtonGroup langRadioGrp = new ButtonGroup();
		
		// - - - - - - - - - - - - - - - Menu bar > Preferences > Language > English - - - - - - - -
		JRadioButtonMenuItem menuBarPrefsLangEn;
		if(PREFS.get("lang", "english").equals("english")) {
			menuBarPrefsLangEn = new JRadioButtonMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangEn"), true); 
		} else {
			menuBarPrefsLangEn = new JRadioButtonMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangEn")); 
		}
		menuBarPrefsLangEn.setActionCommand(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangEn"));
		menuBarPrefsLangEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String command = langRadioGrp.getSelection().getActionCommand();
				outputDebugMsg("[" + getDebugTimestamp() + "] Clicked Preferences -> Language -> " + command);
			}
		});
		menuBarPrefsLang.add(menuBarPrefsLangEn);
		langRadioGrp.add(menuBarPrefsLangEn);
		// - - - - - - - - - - - - - - - Menu bar > Preferences > Language > Chinese - - - - - - - -
		JRadioButtonMenuItem menuBarPrefsLangCn;
		if(PREFS.get("lang", "english").equals("chinese")) {
			menuBarPrefsLangCn = new JRadioButtonMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangCn"), true); 
		} else {
			menuBarPrefsLangCn = new JRadioButtonMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangCn")); 
		}
		menuBarPrefsLangCn.setActionCommand(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangCn"));
		menuBarPrefsLangCn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String command = langRadioGrp.getSelection().getActionCommand();
				outputDebugMsg("[" + getDebugTimestamp() + "] Clicked Preferences -> Language -> " + command);
			}
		});
		menuBarPrefsLang.add(menuBarPrefsLangCn); 
		langRadioGrp.add(menuBarPrefsLangCn);
		menuBarPrefs.add(menuBarPrefsLang);
		menuBar.add(menuBarPrefs);
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

		SwingUtilities.invokeLater(() -> FRAME.setVisible(true));
	}

	// Helper methods
	
	private void updateLang(String newLang) {
		outputDebugMsg("[" + getDebugTimestamp() + "] Update labels to Language [" + newLang + "]");
		HashMap<String, String> newLabels = i18nLabels.get(newLang);
		
	}
	
	private static void outputDebugMsg(String message) {
		if(PREFS.get("isDebug", "false").equals("true")) {
			System.out.println(message);
		}
	}
}