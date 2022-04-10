package main.projects.macroEditor.v2;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Robot;

import main.projects.macroEditor.v2.gui.MacroEditorGUI;

public class MacroEditor {

	/*
	 * Constants
	 */
	private static final String newline = System.lineSeparator();
	private static final String version = "v2";
	private static final String fileSeparator = FileSystems.getDefault().getSeparator();
	private static final String projectRoot = System.getProperty("user.dir");
	private static final String macroEditorV2Path = projectRoot + fileSeparator + "src" + fileSeparator + "main"
			+ fileSeparator + "projects" + fileSeparator + "macroEditor" + fileSeparator + "v2";

	/*
	 * MacroEditor utility or helper classes
	 */
	private static Preferences appConfig = null;
	private static MacroEditorGUI meGUI = null;
	private static MacroEditorUtil meUtil = null;

	/*
	 * Global variables
	 */
	private HashMap<String, HashMap<String, String>> labels = new HashMap<String, HashMap<String, String>>();
	private JTextArea routine = new JTextArea();
	private JTextField console = new JTextField();
	private Robot bot = null;

	/**
	 * Constructor
	 */
	private MacroEditor() {
		appConfig = Preferences.userNodeForPackage(main.projects.macroEditor.v2.MacroEditor.class);
		meGUI = new MacroEditorGUI();
		meUtil = new MacroEditorUtil();
		try {
			bot = new Robot();
		} catch (AWTException e) {
			System.exit(500);
		}
	}

	public static void main(String[] args) {
		MacroEditor me = new MacroEditor();
		int returnCode = 0;
		String path = "";

		// Apply UIManager
		returnCode = meGUI.applyLookAndFeel(); // returns 200, 400, 404
		if (returnCode != 200) {
			System.exit(returnCode);
		}

		// Apply config
		try {
			me.initAppConfigs(macroEditorV2Path + fileSeparator + "config.txt");
		} catch (FileNotFoundException e1) {
			meUtil.logDebug(appConfig,
					"Failed to Read File: File URL was " + macroEditorV2Path + fileSeparator + "config.txt");
			System.exit(404);
		}

		// Add English localization
		try {
			path = macroEditorV2Path + fileSeparator + "localization" + fileSeparator + "i18nEnglish.txt";
			me.labels.put("english", meUtil.readFileAsMaps(path));
		} catch (FileNotFoundException e) {
			meUtil.logDebug(appConfig, "Failed to Read File: File URL was " + path);
			System.exit(404);
		}

		// Add Mandarin-Traditional localization
		try {
			path = macroEditorV2Path + fileSeparator + "localization" + fileSeparator + "i18nMandarinTr.txt";
			me.labels.put("mandarinTr", meUtil.readFileAsMaps(path));
		} catch (FileNotFoundException e) {
			meUtil.logDebug(appConfig, "Failed to Read File: File URL was " + path);
			System.exit(404);
		}

		// Prepare file paths for MenuItem icons
		HashMap<String, String> menuIconPaths = new HashMap<String, String>();
		path = macroEditorV2Path + fileSeparator + "resources" + fileSeparator + "file.png";
		menuIconPaths.put("menuBar_File", path);
		path = macroEditorV2Path + fileSeparator + "resources" + fileSeparator + "fileOpen.png";
		menuIconPaths.put("menuBar_File_Open", path);
		path = macroEditorV2Path + fileSeparator + "resources" + fileSeparator + "fileSaveAs.png";
		menuIconPaths.put("menuBar_File_SaveAs", path);
		path = macroEditorV2Path + fileSeparator + "resources" + fileSeparator + "help.png";
		menuIconPaths.put("menuBar_Help", path);
		path = macroEditorV2Path + fileSeparator + "resources" + fileSeparator + "helpAbout.png";
		menuIconPaths.put("menuBar_Help_About", path);
		path = macroEditorV2Path + fileSeparator + "resources" + fileSeparator + "prefs.png";
		menuIconPaths.put("menuBar_Prefs", path);
		path = macroEditorV2Path + fileSeparator + "resources" + fileSeparator + "prefsDebug.png";
		menuIconPaths.put("menuBar_Prefs_Debug", path);
		path = macroEditorV2Path + fileSeparator + "resources" + fileSeparator + "prefsLang.png";
		menuIconPaths.put("menuBar_Prefs_Lang", path);
		path = macroEditorV2Path + fileSeparator + "resources" + fileSeparator + "prefsLangEn.png";
		menuIconPaths.put("menuBar_Prefs_Lang_En", path);
		path = macroEditorV2Path + fileSeparator + "resources" + fileSeparator + "prefsLangCn.png";
		menuIconPaths.put("menuBar_Prefs_Lang_Cn", path);

		Dimension appSize = new Dimension();
		int width = 700;
		int height = 300;
		appSize.setSize(width, height);

		// Create app frame
		JFrame frame = new JFrame("Macro Editor " + version);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.setPreferredSize(appSize);
		GridLayout gl = new GridLayout(1, 2); // one row, two cols
		frame.setLayout(gl);

		JPanel leftBody = new JPanel();
		leftBody.setLayout(new GridBagLayout());
		leftBody.setBackground(Color.lightGray);
		frame.getContentPane().add(leftBody, new GridLayout(1, 1));

		JPanel rightBody = new JPanel();
		rightBody.setLayout(new GridBagLayout());
		rightBody.setBackground(Color.lightGray);
		frame.getContentPane().add(rightBody, new GridLayout(1, 2));

		me.routine.setLineWrap(true);
		me.routine.setWrapStyleWord(true);
		GridBagConstraints gbcRoutine = meGUI.createGridBagConstraint(0, 0, 1, 1, true, true);
		gbcRoutine.weighty = 5; // component takes 90% of leftBody's total height
		gbcRoutine.insets = new Insets(5, 10, 0, 0);
		ScrollPane scrollerRoutine = new ScrollPane();
		scrollerRoutine.add(me.routine);
		leftBody.add(scrollerRoutine, gbcRoutine);

		me.console.setEditable(false);
		me.console.setText("Initialize app successful");
		GridBagConstraints gbcConsole = meGUI.createGridBagConstraint(0, 1, 1, 1, true, false);
		gbcConsole.weighty = 1;
		gbcConsole.insets = new Insets(0, 10, 5, 0);
		ScrollPane scrollerConsole = new ScrollPane();
		scrollerConsole.add(me.console);
		leftBody.add(scrollerConsole, gbcConsole);

		JButton btnMouseMove = new JButton("New mouse move");
		GridBagConstraints gbcBtnMouseMove = meGUI.createGridBagConstraint(0, 0, 1, 1, true, true);
		gbcBtnMouseMove.insets = new Insets(5, 0, 0, 0);
		rightBody.add(btnMouseMove, gbcBtnMouseMove);

		JButton btnMouseClick = new JButton("New mouse click");
		GridBagConstraints gbcBtnMouseClick = meGUI.createGridBagConstraint(1, 0, 1, 1, true, true);
		gbcBtnMouseClick.insets = new Insets(5, 0, 0, 10);
		rightBody.add(btnMouseClick, gbcBtnMouseClick);

		JButton btnWait = new JButton("New wait");
		GridBagConstraints gbcBtnWait = meGUI.createGridBagConstraint(0, 2, 2, 1, true, true);
		gbcBtnWait.insets = new Insets(0, 0, 0, 10);
		rightBody.add(btnWait, gbcBtnWait);

		JButton btnKeyPress = new JButton("New key press");
		GridBagConstraints gbcBtnKeyPress = meGUI.createGridBagConstraint(0, 3, 1, 1, true, true);
		rightBody.add(btnKeyPress, gbcBtnKeyPress);

		// Group of Check boxes
		// ...

		JButton btnClearAll = new JButton("Clear all");
		btnClearAll.setBackground(Color.red);
		GridBagConstraints gbcBtnClearAll = meGUI.createGridBagConstraint(0, 4, 1, 1, true, true);
		rightBody.add(btnClearAll, gbcBtnClearAll);

		JButton btnClearLast = new JButton("Clear last");
		GridBagConstraints gbcBtnClearLast = meGUI.createGridBagConstraint(1, 4, 1, 1, true, true);
		gbcBtnClearLast.insets = new Insets(0, 0, 0, 10);
		rightBody.add(btnClearLast, gbcBtnClearLast);

		JButton btnRun = new JButton("Run");
		GridBagConstraints gbcBtnRun = meGUI.createGridBagConstraint(0, 5, 2, 1, true, true);
		gbcBtnRun.insets = new Insets(0, 0, 5, 10);
		btnRun.setBackground(Color.BLACK);
		rightBody.add(btnRun, gbcBtnRun);
		
		// TODO add repeat/loop button
		

		// test getting label text from i18n files
//		String b = me.labels.get(appConfig.get("lang", "english")).get("menuBar_File");
//		meUtil.logDebug(appConfig, b); // prints "File"

		// Define all actionListeners
		HashMap<String, ActionListener> allActions = me.defineActions(frame, me.routine, me.console);

		// Create app menu
		frame.setJMenuBar(
				meGUI.initJMenuBar(me.labels.get(appConfig.get("lang", "english")), menuIconPaths, allActions));

		btnMouseMove.addActionListener(allActions.get("btnMouseMove"));
		btnMouseClick.addActionListener(allActions.get("btnMouseClick"));
		btnWait.addActionListener(allActions.get("btnWait"));
		btnKeyPress.addActionListener(allActions.get("btnKeyPress"));
		// Group checkboxes
		btnClearAll.addActionListener(allActions.get("btnClearAll"));
		btnClearLast.addActionListener(allActions.get("btnClearLast"));
		btnRun.addActionListener(allActions.get("btnRun"));
		frame.pack();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	private void initAppConfigs(String configFilePath) throws FileNotFoundException {
		HashMap<String, String> configPairs = null;
		configPairs = meUtil.readFileAsMaps(configFilePath); // can throw exception
		appConfig.put("isDebug", configPairs.get("isDebug")); // valid: true, false
		appConfig.put("lang", configPairs.get("lang")); // valid: english, chinese
	}

	private void updateGUILanguage(String newLanguage, JFrame frame) {
		HashMap<String, String> newLabels = labels.get(newLanguage);
		meUtil.logDebug(appConfig, "newLabels = " + newLabels);

		System.out.println(frame.getJMenuBar());
		System.out.println(frame.getRootPane());
		System.out.println(frame.getRootPane().getComponents());
		
		// TODO implement this components tree search
		
//		for (MenuElement c : frame.getRootPane()) {
//			String thisCompName = c.toString();
//			System.out.println(thisCompName);
		// Objects that inherit from AbstractButton have setText("") method
		// and JLabel has setText("") method
//			if (AbstractButton.class.isAssignableFrom(c.getClass()) || c.getClass() == JLabel.class) {
//				if (c instanceof JMenu) {
//					JMenu thisComp = (JMenu) c;
////					debugMsg = "Component [class=" + String.valueOf(thisComp.getClass()) + ", name=\"" + thisComp.getName()
////							+ "\"" + ", text=\"" + thisComp.getText() + "\"] " + "changed text to \""
////							+ newLabels.get(eachComp.getName()) + "\"";
//					thisComp.setText(newLabels.get(c.getName()));
//				} else if (c instanceof JButton) {
//					JButton thisComp = (JButton) c;
////					debugMsg = "Component [class=" + String.valueOf(thisComp.getClass()) + ", name=\"" + thisComp.getName()
////							+ "\"" + ", text=\"" + thisComp.getText() + "\"] " + "changed text to \""
////							+ newLabels.get(eachComp.getName()) + "\"";
//					thisComp.setText(newLabels.get(c.getName()));
//				} else if (c instanceof JMenuItem) {
//					JMenuItem thisComp = (JMenuItem) c;
////					debugMsg = "Component [class=" + String.valueOf(thisComp.getClass()) + ", name=\"" + thisComp.getName()
////							+ "\"" + ", text=\"" + thisComp.getText() + "\"] " + "changed text to \""
////							+ newLabels.get(eachComp.getName()) + "\"";
//					thisComp.setText(newLabels.get(c.getName()));
//				} else if (c instanceof JCheckBoxMenuItem) {
//					JCheckBoxMenuItem thisComp = (JCheckBoxMenuItem) c;
////					debugMsg = "Component [class=" + String.valueOf(thisComp.getClass()) + ", name=\"" + thisComp.getName()
////							+ "\"" + ", text=\"" + thisComp.getText() + "\"] " + "changed text to \""
////							+ newLabels.get(eachComp.getName()) + "\"";
//					thisComp.setText(newLabels.get(c.getName()));
//				} else if (c instanceof JRadioButtonMenuItem) {
//					JRadioButtonMenuItem thisComp = (JRadioButtonMenuItem) c;
////					debugMsg = "Component [class=" + String.valueOf(thisComp.getClass()) + ", name=\"" + thisComp.getName()
////							+ "\"" + ", text=\"" + thisComp.getText() + "\"] " + "changed text to \""
////							+ newLabels.get(eachComp.getName()) + "\"";
//					thisComp.setText(newLabels.get(c.getName()));
//				} else if (c instanceof JLabel) {
//					JLabel thisComp = (JLabel) c;
////					debugMsg = "Component [class=" + String.valueOf(thisComp.getClass()) + ", name=\"" + thisComp.getName()
////							+ "\"" + ", text=\"" + thisComp.getText() + "\"] " + "changed text to \""
////							+ newLabels.get(eachComp.getName()) + "\"";
//					thisComp.setText(newLabels.get(c.getName()));
//				} else {
//					System.err.println("Uncaught component=" + c);
//				}
////				outputDebugMsg(debugMsg);
//			} else {
//				// This component does not have setText("") method
//				System.err.println("Found Component [class=" + c.getClass() + "] "
//						+ ", component does not have setText(\"\") method, has nothing to update.");
//			}
//		}
	}

	private HashMap<String, ActionListener> defineActions(JFrame frame, JTextArea routine, JTextField console) {
		HashMap<String, ActionListener> actions = new HashMap<String, ActionListener>();
		actions.put("menuBar_File_Open", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked File > Open");
				JFileChooser fileChooser = new JFileChooser(macroEditorV2Path + fileSeparator + "routines");
				int chosenFile = fileChooser.showOpenDialog(frame);
				if (chosenFile == JFileChooser.APPROVE_OPTION) { // JFileChooser.APPROVE_OPTION is 0
					File file = fileChooser.getSelectedFile();

					meUtil.logDebug(appConfig,
							"User selected a file;" + newline + "                         File name:     "
									+ file.getName() + newline + "                         Absolute path: "
									+ file.getAbsolutePath() + newline + "                         Writeable:     "
									+ file.canWrite() + newline + "                         Readable       "
									+ file.canRead() + newline + "                         Size (bytes):  "
									+ file.length());
					Scanner myReader = null;
					try {
						myReader = new Scanner(file);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					meUtil.logDebug(appConfig, "<<File contents - BEGIN>>");
					int lnCount = 1;
					StringBuilder sb = new StringBuilder();
					while (myReader.hasNextLine()) {
						String eachLine = myReader.nextLine();
						meUtil.logDebug(appConfig, "[ln" + lnCount + "] " + eachLine);
						lnCount++;
						sb.append(eachLine + newline);
					}
					meUtil.logDebug(appConfig, "<<File contents - END>>");
					myReader.close();
					routine.setText(sb.toString());
				} else {
					meUtil.logDebug(appConfig, "User cancelled the Open");
				}
			}
		});
		actions.put("menuBar_File_SaveAs", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked File > Save As");
				JFileChooser fileChooser = new JFileChooser(macroEditorV2Path + fileSeparator + "routines");
				fileChooser.setDialogTitle("Save As");
				int userSelection = fileChooser.showSaveDialog(frame);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					meUtil.logDebug(appConfig, "Save as file name: " + fileToSave.getName());
					meUtil.logDebug(appConfig, "Save file at path: " + fileToSave.getAbsolutePath());
					FileWriter fWriter = null;
					BufferedWriter bWriter = null;
					ArrayList<String> fileContents = stringToArrayList(routine.getText());
					try {
						fWriter = new FileWriter(fileToSave.getAbsolutePath());
						bWriter = new BufferedWriter(fWriter);
						meUtil.logDebug(appConfig, "<<File contents - BEGIN>>");
						for (int index = 0; index < fileContents.size(); index++) {
							String eachLine = fileContents.get(index);
							meUtil.logDebug(appConfig, "[Ln" + (index + 1) + "], wrote \"" + eachLine + "\"");
							eachLine = eachLine + newline;
							bWriter.write(eachLine);
						}
						meUtil.logDebug(appConfig, "<<File contents - END>>");
						bWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					meUtil.logDebug(appConfig, "User cancelled the Save As");
				}
			}
		});
		actions.put("menuBar_Help_About", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked Help > About");
				JOptionPane.showMessageDialog(frame,
						"MacroEditor (Version:" + version + ")" + newline + newline
								+ "MacroEditor was created by Kuei-Feng Tung in 2022" + newline
								+ "with the purpose of simplifying the creation of" + newline
								+ "automation commands. With this app, users can" + newline
								+ "automate using text files, without needing the" + newline
								+ "know-how of coding with Java robot API." + newline + newline
								+ "(C) Copyright MacroEditor 2022. All rights reserved." + newline
								+ "MacroEditor and the MacroEditor logo are trademarks" + newline
								+ "of Kuei-Feng Tung, https://github.com/omgitskuei." + newline
								+ "The MacroEditor logo cannot be altered without" + newline
								+ "permission. Oracle and Java are trademarks or" + newline
								+ "registered trademarks of Oracle and/or its" + newline + "affiliates.",
						"About MacroEditor", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		actions.put("menuBar_Prefs_Debug", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked Preferences > Debug");
				if (appConfig.get("isDebug", "false").equals("false")) {
					System.out.println(
							"[" + new Timestamp(System.currentTimeMillis()) + "]" + "Set config.isDebug to \"true\"");
					appConfig.put("isDebug", "true");
				} else {
					meUtil.logDebug(appConfig, "Set config.isDebug to \"false\"");
					appConfig.put("isDebug", "false");
				}
			}
		});
		actions.put("menuBar_Prefs_Lang_En", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked Preferences > English");
				updateGUILanguage(appConfig.get("lang", "english"), frame);
			}
		});
		actions.put("menuBar_Prefs_Lang_Cn", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked Preferences > Mandarin (traditional)");
				updateGUILanguage(appConfig.get("lang", "mandarinTr"), frame);
			}
		});
		actions.put("btnMouseMove", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked Mouse Move");
				console.setText("Move mouse to destination, hold for 3 seconds.");
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				// TODO implement 3 seconds Timer
				Point mouseCoord = MouseInfo.getPointerInfo().getLocation();
				String xCoord = String.valueOf(mouseCoord.x);
				String yCoord = String.valueOf(mouseCoord.y);
				routine.setText(routine.getText() + ((routine.getText().length() > 0) ? newline : "") + "mm" + xCoord + "." + yCoord);
			}
		});
		actions.put("btnMouseClick", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked Mouse Click");
				routine.setText(routine.getText() + ((routine.getText().length() > 0) ? newline : "") + "mc");
			}
		});
		actions.put("btnWait", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked Wait");
				// TODO implement this, add UI to specify duration
				String durationSeconds = "1";
				routine.setText(routine.getText() + ((routine.getText().length() > 0) ? newline : "") + "w" + durationSeconds);
			}
		});
		actions.put("btnKeyPress", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked Key Press");
				// TODO implement this
			}
		});
		// Group checkboxes
		// TODO implement this

		actions.put("btnClearAll", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked Clear All");
				routine.setText("");
				console.setText("Cleared routine.");
			}
		});
		actions.put("btnClearLast", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked Clear Last");
				ArrayList<String> routineList = stringToArrayList(routine.getText());
				String lastRoutine = routineList.get(routineList.size() - 1);
				if (lastRoutine != null && lastRoutine.length() > 0) {
					console.setText("Removed [" + lastRoutine + "].");
					routineList.remove(routineList.size() - 1);
					routine.setText(arraylistToString(routineList));
				} else {
					console.setText("Routine is empty.");
				}
			}
		});
		actions.put("btnRun", new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				meUtil.logDebug(appConfig, "User clicked Run");
				console.setText("Routine started.");
//				try {
//					Thread.sleep(1000);
//					console.setText(console.getText() + ", 2");
//					Thread.sleep(1000);
//					console.setText(console.getText() + ", 1.");
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				Don't use Thread because GUI including JTextField.setText("...")
//				won't update until AFTER all Thread.sleep(####) is complete
				// TODO use Timer()
				
				//bot.mouseMove(x, y);
			}
		});
		// TODO add repeat/loop button action
		return actions;
	}

	private ArrayList<String> stringToArrayList(String s) {
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(Arrays.asList(s.split(newline)));
		return list;
	}

	private String arraylistToString(ArrayList<String> s) {
		return String.join(newline, s);
	}
}
