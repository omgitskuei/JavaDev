package main.projects.macroEditor;

import javax.swing.*;

import main.notes.gui.swing.i18nExample;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.prefs.Preferences;

public class MacroEditor {

	private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	private static final JFrame FRAME = new JFrame();
	private static final Preferences PREFS = Preferences.userNodeForPackage(main.projects.macroEditor.MacroEditor.class);
	private static ArrayList<String> fileContents = new ArrayList<String>();
	private static HashMap<String, String> contribURLs = new HashMap<String, String>();
	private static HashMap<String, HashMap<String, String>> i18nLabels = new HashMap<String, HashMap<String, String>>();
	private static HashMap<String, Integer> keyBindings = new HashMap<String, Integer>();
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
		FRAME.setAlwaysOnTop(true);
		
		// initcontribURLs
		contribURLs.put("Github", "https://github.com/omgitskuei");
		contribURLs.put("Gitlab", "https://gitlab.com/omgitskuei");
		
		// initPreferences
		PREFS.put("isDebug", "true");   // valid: true, false
		PREFS.put("lang", "english");   // valid: english, chinese
		
		// initI18nLabels
		HashMap<String, String> enLabels = new HashMap<String, String>();
		// menuBar labels
		enLabels.put("menuBarFile",        "File");
		enLabels.put("menuBarFileOpen",    "Open");
		enLabels.put("menuBarFileSaveAs",  "Save As");
		enLabels.put("menuBarHelp",        "Help");
		enLabels.put("menuBarHelpWelcome", "Welcome");
		enLabels.put("menuBarHelpAbout",   "About");
		enLabels.put("menuBarHelpContrib", "Contribute");
		enLabels.put("menuBarPrefs",       "Preferences");
		enLabels.put("menuBarPrefsDebug",  "Debug");
		enLabels.put("menuBarPrefsLang",   "Language");
		enLabels.put("menuBarPrefsLangEn", "English");
		enLabels.put("menuBarPrefsLangCn", "Chinese");
		i18nLabels.put("english", enLabels);
		HashMap<String, String> cnLabels = new HashMap<String, String>();
		cnLabels.put("menuBarFile",        "檔案");
		cnLabels.put("menuBarFileOpen",    "開啟");
		cnLabels.put("menuBarFileSaveAs",  "儲存");
		cnLabels.put("menuBarHelp",        "說明");
		cnLabels.put("menuBarHelpWelcome", "MacroEditor 說明");
		cnLabels.put("menuBarHelpAbout",   "關於 MacroEditor");
		cnLabels.put("menuBarHelpContrib", "贊助");
		cnLabels.put("menuBarPrefs",       "選項");
		cnLabels.put("menuBarPrefsDebug",  "除錯");
		cnLabels.put("menuBarPrefsLang",   "語言設定");
		cnLabels.put("menuBarPrefsLangEn", "英文");
		cnLabels.put("menuBarPrefsLangCn", "中文");
		i18nLabels.put("chinese", cnLabels);

		// initKeyBinding
		keyBindings.put("a", KeyEvent.VK_A);
		keyBindings.put("b", KeyEvent.VK_B);
		keyBindings.put("c", KeyEvent.VK_C);
		keyBindings.put("d", KeyEvent.VK_D);
		keyBindings.put("e", KeyEvent.VK_E);
		keyBindings.put("f", KeyEvent.VK_F);
		keyBindings.put("g", KeyEvent.VK_G);
		keyBindings.put("h", KeyEvent.VK_H);
		keyBindings.put("i", KeyEvent.VK_I);
		keyBindings.put("j", KeyEvent.VK_J);
		keyBindings.put("k", KeyEvent.VK_K);
		keyBindings.put("l", KeyEvent.VK_L);
		keyBindings.put("m", KeyEvent.VK_M);
		keyBindings.put("n", KeyEvent.VK_N);
		keyBindings.put("o", KeyEvent.VK_O);
		keyBindings.put("p", KeyEvent.VK_P);
		keyBindings.put("q", KeyEvent.VK_Q);
		keyBindings.put("r", KeyEvent.VK_R);
		keyBindings.put("s", KeyEvent.VK_S);
		keyBindings.put("t", KeyEvent.VK_T);
		keyBindings.put("u", KeyEvent.VK_U);
		keyBindings.put("v", KeyEvent.VK_V);
		keyBindings.put("w", KeyEvent.VK_W);
		keyBindings.put("x", KeyEvent.VK_X);
		keyBindings.put("y", KeyEvent.VK_Y);
		keyBindings.put("z", KeyEvent.VK_Z);
		
		// initMenuBar
		JMenuBar menuBar = new JMenuBar();
		// - - - - - - - - - - - - - - - Menu bar > File - - - - - - - - - - - - - - -
		JMenu menuBarFile = new JMenu(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarFile"));
		menuBarFile.setName("menuBarFile");
		// - - - - - - - - - - - - - - - Menu bar > File > Open - - - - - - - - - - - -
		JMenuItem menuBarFileOpen = new JMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarFileOpen"));
		menuBarFileOpen.setName("menuBarFileOpen");
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
		menuBarFileSaveAs.setName("menuBarFileSaveAs");
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
		menuBarHelp.setName("menuBarHelp");
		// - - - - - - - - - - - - - - - Menu bar > Help > Welcome - - - - - - - - - - -
		JMenuItem menuBarHelpWelcome = new JMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarHelpWelcome"));
		menuBarHelpWelcome.setName("menuBarHelpWelcome");
		menuBarHelpWelcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("[" + getDebugTimestamp() + "] Clicked Help -> " + actEvent.getActionCommand());
			}
		});
		menuBarHelp.add(menuBarHelpWelcome);
		// - - - - - - - - - - - - - - - Menu bar > Help > About - - - - - - - - - - - -
		JMenuItem menuBarHelpAbout = new JMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarHelpAbout"));
		menuBarHelpAbout.setName("menuBarHelpAbout");
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
		menuBarHelpContrib.setName("menuBarHelpContrib");
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
		menuBarPrefs.setName("menuBarPrefs");
		// - - - - - - - - - - - - - - - Menu bar > Preferences > Debug - - - - - - - -
		JCheckBoxMenuItem menuBarPrefsDebug;
		if(PREFS.get("isDebug", "false").equals("true")) {
			menuBarPrefsDebug = new JCheckBoxMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsDebug"), true); 
		} else {
			menuBarPrefsDebug = new JCheckBoxMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsDebug")); 
		}
		menuBarPrefsDebug.setName("menuBarPrefsDebug");
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
		menuBarPrefsLang.setName("menuBarPrefsLang");
		final ButtonGroup langRadioGrp = new ButtonGroup();
		
		// - - - - - - - - - - - - - - - Menu bar > Preferences > Language > English - - - - - - - -
		JRadioButtonMenuItem menuBarPrefsLangEn;
		if(PREFS.get("lang", "english").equals("english")) {
			menuBarPrefsLangEn = new JRadioButtonMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangEn"), true); 
		} else {
			menuBarPrefsLangEn = new JRadioButtonMenuItem(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangEn")); 
		}
		menuBarPrefsLangEn.setActionCommand(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangEn"));
		menuBarPrefsLangEn.setName("menuBarPrefsLangEn");
		menuBarPrefsLangEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String command = langRadioGrp.getSelection().getActionCommand();
				outputDebugMsg("[" + getDebugTimestamp() + "] Clicked Preferences -> Language -> " + command);
				outputDebugMsg("[" + getDebugTimestamp() + "] Language chosen = [english]");
				HashMap<String, String> newLabels = i18nLabels.get("english");
				List<Component> allComponents = getChildren(Component.class, FRAME);
				for(Component eachComp: allComponents) {
					updateLabel(eachComp, newLabels);
				}
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
		menuBarPrefsLangCn.setName("menuBarPrefsLangCn");
		menuBarPrefsLangCn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String command = langRadioGrp.getSelection().getActionCommand();
				outputDebugMsg("[" + getDebugTimestamp() + "] Clicked Preferences -> Language -> " + command);
				outputDebugMsg("[" + getDebugTimestamp() + "] Language chosen = [chinese]");
				HashMap<String, String> newLabels = i18nLabels.get("chinese");
				List<Component> allComponents = getChildren(Component.class, FRAME);
				for(Component eachComp: allComponents) {
					updateLabel(eachComp, newLabels);
				}
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

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FRAME.setVisible(true);
			}
		});
	}

	// Helper methods
	private static void updateLabel(Component eachComp, HashMap<String, String> newLabels) {
		String name = "", currText = "", newText = "", className = "";
		// Objects that inherit from AbstractButton have setText("") method
		if(AbstractButton.class.isAssignableFrom(eachComp.getClass())) {
			if(eachComp.getClass() == JMenu.class) {
				JMenu thisComp = (JMenu) eachComp;
				outputDebugMsg("[" + getDebugTimestamp() + "] "
						+ "Component [class=" + String.valueOf(thisComp.getClass()) + ", "
						+ "name=" + thisComp.getName() + ", " 
						+ "text=" + thisComp.getText() + "] "
						+ "changed text to [" + newLabels.get(eachComp.getName()) + "]");
				thisComp.setText(newLabels.get(eachComp.getName()));
			} else if (eachComp.getClass() == JButton.class) {
				JButton thisComp = (JButton) eachComp;
				name = thisComp.getName();
				currText = thisComp.getText();
				thisComp.setText(newLabels.get(eachComp.getName()));
				newText = thisComp.getText();
				className = String.valueOf(thisComp.getClass());
			} else if (eachComp.getClass() == JMenuItem.class) {
				JMenuItem thisComp = (JMenuItem) eachComp;
				name = thisComp.getName();
				currText = thisComp.getText();
				thisComp.setText(newLabels.get(eachComp.getName()));
				newText = thisComp.getText();
				className = String.valueOf(thisComp.getClass());
			} else if (eachComp.getClass() == JCheckBoxMenuItem.class) {
				JCheckBoxMenuItem thisComp = (JCheckBoxMenuItem) eachComp;
				name = thisComp.getName();
				currText = thisComp.getText();
				thisComp.setText(newLabels.get(eachComp.getName()));
				newText = thisComp.getText();
				className = String.valueOf(thisComp.getClass());
			} else if (eachComp.getClass() == JRadioButtonMenuItem.class) {
				JRadioButtonMenuItem thisComp = (JRadioButtonMenuItem) eachComp;
				name = thisComp.getName();
				currText = thisComp.getText();
				thisComp.setText(newLabels.get(eachComp.getName()));
				newText = thisComp.getText();
				className = String.valueOf(thisComp.getClass());
			}
		} 
		// JLabel does NOT inherit from AbstractButton but itself comes with setText("") method
		else if (eachComp.getClass() == JLabel.class) {
			JLabel thisComp =(JLabel) eachComp;
			name = thisComp.getName();
			currText = thisComp.getText();
			thisComp.setText(newLabels.get(eachComp.getName()));
			newText = thisComp.getText();
			className = String.valueOf(thisComp.getClass());
		}
		if(name!=null && name.length() > 0) {
			outputDebugMsg("[" + getDebugTimestamp() + "] "
					+ "Component [class=" + className + ", name=" + name + ", text=" + currText + "] "
					+ "changed text to [" + newText + "]");
		}
	}
	
	private static void outputDebugMsg(String message) {
		if(PREFS.get("isDebug", "false").equals("true")) {
			System.out.println(message);
		}
	}
	
	private static <T extends Component> List<T> getChildren(Class<T> clazz, final Container container) {
        Component[] components;
        if (container instanceof JMenu)
            components = ((JMenu) container).getMenuComponents();
        else
            components = container.getComponents();
        List<T> compList = new ArrayList<T>();
        for (Component comp : components) {
            if (clazz.isAssignableFrom(comp.getClass())) {
                compList.add(clazz.cast(comp));
            }
            if (comp instanceof Container)
                compList.addAll(getChildren(clazz, (Container) comp));
        }
        return compList;
    }
}