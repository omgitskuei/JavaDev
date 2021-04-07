package main.projects.timeElapse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TimeElapse {

	private static final String APPNAME = "TimeElapse";
	private static final String VERSION = "2021-04";
	private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

	private static final Preferences PREFS = Preferences
			.userNodeForPackage(main.projects.macroEditor.MacroEditor.class);

	private static final String newline = System.getProperty("line.separator");

	private static final JFrame FRAME = new JFrame();

	private static HashMap<String, String> contribURLs = new HashMap<String, String>();
	private static HashMap<String, HashMap<String, String>> i18nLabels = new HashMap<String, HashMap<String, String>>();

	public static void main(String[] args) {

		TimeElapse timeElapse = new TimeElapse();

		// set LookAndFeel
		timeElapse.setLookAndFeel();

		// populate ContribURLs
		contribURLs.put("Github", "https://github.com/omgitskuei");
		contribURLs.put("Gitlab", "https://gitlab.com/omgitskuei");

		// initialize Preferences
		PREFS.put("isDebug", "true"); // valid: true, false
		PREFS.put("lang", "english"); // valid: english, chinese

		// initialize I18nLabels
		timeElapse.populateI18NLabels();

		// initialize JFrame
		FRAME.setTitle(APPNAME);
		FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRAME.setSize(400, 400);
		FRAME.setMinimumSize(new Dimension(400, 300));
		FRAME.setAlwaysOnTop(true);

		/*
		 * UI Layout
		 * FRAME
		 * --> JPanel (frameJPanel, BorderLayout)
		 * -- --> JPanel (menuBarJPanel, GridBagLayout)
		 * -- -- --> JMenuBar (menuBar)
		 * -- --> JPanel (appbodyJPanel, GridBagLayout)
		 * -- -- --> JPanel (startDateTimeJPanel, GridBagLayout)
		 * -- -- -- --> ComboBox (start
		 * -- -- -- --> ?
		 * -- -- -- --> ComboBox (AMPM
		 * -- -- --> ?
		 * -- -- --> ?
		 * -- -- --> ?
		 */
		
		// Add JPanel to FRAME
		JPanel frameJPanel = new JPanel();
		// frameJPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		frameJPanel.setLayout(new BorderLayout());
		FRAME.getContentPane().add(frameJPanel);

		// Initialize JMenuBar menuBar, GridBagConstraints menuBarGBC, JPanel menuBarJPanel
		JPanel menuBarJPanel = new JPanel();
		menuBarJPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		menuBarJPanel.setLayout(new GridBagLayout());
		frameJPanel.add(menuBarJPanel, BorderLayout.PAGE_START);
		
		GridBagConstraints menuBarGBC = new GridBagConstraints();
		menuBarGBC.fill = GridBagConstraints.HORIZONTAL;
		menuBarGBC.anchor = GridBagConstraints.NORTHWEST;
		menuBarGBC.weightx = 1;
		
		JMenuBar menuBar = createMenuBar();
		menuBarJPanel.add(menuBar, menuBarGBC);
		
		
		// Initialize 
		JPanel appbodyJPanel = new JPanel();
		appbodyJPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		appbodyJPanel.setLayout(new GridBagLayout());
		frameJPanel.add(appbodyJPanel, BorderLayout.CENTER);
		
		appbodyJPanel.add(, );
		
//		GridBagConstraints mGBC = new GridBagConstraints();
//		mGBC.fill = GridBagConstraints.HORIZONTAL;
//		mGBC.anchor = GridBagConstraints.NORTHWEST;
//		mGBC.weightx = 1;
//		mGBC.weighty = 1;
//		JMenuBar menuBar2 = createMenuBar();
//		mJPanel.add(menuBar2, mGBC);
		
		

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FRAME.setVisible(true);
			}
		});
	}

	/**
	 * Set look and feel of the App to current system's native esthetics If failed,
	 * use default cross-platform Java esthetics
	 * 
	 * @author omgitskuei
	 * @since Apr 7 2021
	 */
	private void setLookAndFeel() {
		try {
			// Set Look&Feel to current System's
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			outputDebugMsg("Successfully set UIManager look&feel to current system's look&feel ["
					+ UIManager.getSystemLookAndFeelClassName() + "].");
		} catch (UnsupportedLookAndFeelException e) {
			// Default to CrossPlatformLookAndFeel if System's fails
			System.err.println(
					"An UnsupportedLookAndFeelException error occurred while setting LookAndFeel to current sysL&F.");
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (UnsupportedLookAndFeelException e1) {
				System.err.println(
						"An UnsupportedLookAndFeelException error occurred while setting LookAndFeel to crossPlatformL&F.");
			} catch (ClassNotFoundException e1) {
				System.err.println(
						"An ClassNotFoundException error occurred while setting LookAndFeel to crossPlatformL&F.");
			} catch (InstantiationException e1) {
				System.err.println(
						"An InstantiationException error occurred while setting LookAndFeel to crossPlatformL&F.");
			} catch (IllegalAccessException e1) {
				System.err.println(
						"An IllegalAccessException error occurred while setting LookAndFeel to crossPlatformL&F.");
			}
		} catch (ClassNotFoundException e) {
			System.err.println("An ClassNotFoundException error occurred while setting LookAndFeel to current sysL&F.");
		} catch (InstantiationException e) {
			System.err.println("An InstantiationException error occurred while setting LookAndFeel to current sysL&F.");
		} catch (IllegalAccessException e) {
			System.err.println("An IllegalAccessException error occurred while setting LookAndFeel to current sysL&F.");
		}
	}

	/**
	 * Populate component text in various languages
	 * 
	 * @return void
	 * @author omgitskuei
	 * @since Apr 7, 2021
	 */
	private void populateI18NLabels() {
		HashMap<String, String> enLabels = new HashMap<String, String>();
		// menuBar labels
		enLabels.put("menuBarFile", "File");
		enLabels.put("menuBarFileOpen", "Open");
		enLabels.put("menuBarFileSaveAs", "Save As");
		enLabels.put("menuBarHelp", "Help");
		enLabels.put("menuBarHelpAbout", "About");
		enLabels.put("menuBarHelpContrib", "Contribute");
		enLabels.put("menuBarPrefs", "Preferences");
		enLabels.put("menuBarPrefsDebug", "Debug");
		enLabels.put("menuBarPrefsLang", "Language");
		enLabels.put("menuBarPrefsLangEn", "English");
		enLabels.put("menuBarPrefsLangCn", "Chinese");
		i18nLabels.put("english", enLabels);
		HashMap<String, String> cnLabels = new HashMap<String, String>();
		cnLabels.put("menuBarFile", "檔案");
		cnLabels.put("menuBarFileOpen", "開啟");
		cnLabels.put("menuBarFileSaveAs", "儲存");
		cnLabels.put("menuBarHelp", "說明");
		cnLabels.put("menuBarHelpAbout", "關於 " + APPNAME);
		cnLabels.put("menuBarHelpContrib", "贊助");
		cnLabels.put("menuBarPrefs", "選項");
		cnLabels.put("menuBarPrefsDebug", "除錯");
		cnLabels.put("menuBarPrefsLang", "語言設定");
		cnLabels.put("menuBarPrefsLangEn", "英文");
		cnLabels.put("menuBarPrefsLangCn", "中文");
		i18nLabels.put("chinese", cnLabels);
	}

	/**
	 * Create a menu bar and add menu items, sub-menu items, and their onClick
	 * functionality
	 * 
	 * @return JMenuBar
	 * @author omgitskuei
	 * @since Apr 7 2021
	 */
	private static JMenuBar createMenuBar() {
		// initMenuBar
		JMenuBar menuBar = new JMenuBar();
		// - - - - - - - - - - - - - - - Menu bar > Help - - - - - - - - - - - - - - -
		JMenu menuBarHelp = new JMenu(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarHelp"));
		menuBarHelp.setName("menuBarHelp");
		// - - - - - - - - - - - - - - - Menu bar > Help > About - - - - - - - - - - - -
		JMenuItem menuBarHelpAbout = new JMenuItem(
				i18nLabels.get(PREFS.get("lang", "english")).get("menuBarHelpAbout"));
		menuBarHelpAbout.setName("menuBarHelpAbout");
		menuBarHelpAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("Clicked Help -> " + actEvent.getActionCommand());
				outputDebugMsg("version = " + VERSION);
				JOptionPane.showMessageDialog(FRAME, APPNAME + " (Version:" + VERSION + ")" + newline + newline
						+ APPNAME + " was created by Kuei-Feng Tung in 2021" + newline
						+ "for calculating time differences. With this app," + newline
						+ "users can quickly find time elapsed" + newline + "between two given times within a day. "
						+ newline + "Calculating time elapsed across longer periods " + newline
						+ "can be chosen under the Preferences > Configuration" + newline + "menu." + newline
						+ "(C) Copyright " + APPNAME + " 2021. All rights reserved." + newline + APPNAME + " and the "
						+ APPNAME + " logo are trademarks" + newline
						+ "of Kuei-Feng Tung, https://github.com/omgitskuei." + newline + "The " + APPNAME
						+ " logo cannot be altered without" + newline + "permission. Oracle and Java are trademarks or"
						+ newline + "registered trademarks of Oracle and/or its" + newline + "affiliates.",
						"About TimeElapse", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuBarHelp.add(menuBarHelpAbout);
		// - - - - - - - - - - - - - - - Menu bar > Help > Contribute - - - - - - - - -
		JMenuItem menuBarHelpContrib = new JMenuItem(
				i18nLabels.get(PREFS.get("lang", "english")).get("menuBarHelpContrib"));
		menuBarHelpContrib.setName("menuBarHelpContrib");
		menuBarHelpContrib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("Clicked Help -> " + actEvent.getActionCommand());
				outputDebugMsg("contribURLs = " + contribURLs);
				String selectedOption = (String) JOptionPane.showInputDialog(FRAME, "Contribute by following me on:",
						"Contribute", // Dialog window title
						JOptionPane.PLAIN_MESSAGE, // Type of Dialog window
						null, // Icon
						contribURLs.keySet().toArray(), // Select options (Object[]), convert Map to Set<String> to
														// String[]
						"Github" // Default option
				);
				// If the user presses OK, and return a selected option, go to the corresponding
				// URL
				if ((selectedOption != null) && (selectedOption.length() > 0)) {
					outputDebugMsg("User chose [" + selectedOption + "]");
					try {
						java.awt.Desktop.getDesktop().browse(java.net.URI.create(contribURLs.get(selectedOption)));
					} catch (IOException ioE) {
						System.err.println("A IOException error occurred.");
						ioE.printStackTrace();
					}
				} else {
					outputDebugMsg("User closed Contribute dialog window.");
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
		if (PREFS.get("isDebug", "false").equals("true")) {
			menuBarPrefsDebug = new JCheckBoxMenuItem(
					i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsDebug"), true);
		} else {
			menuBarPrefsDebug = new JCheckBoxMenuItem(
					i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsDebug"));
		}
		menuBarPrefsDebug.setName("menuBarPrefsDebug");
		menuBarPrefsDebug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				if (PREFS.get("isDebug", "false").equals("false")) {
					PREFS.put("isDebug", "true");
				} else {
					PREFS.put("isDebug", "false");
				}
				outputDebugMsg("Clicked Preferences -> " + actEvent.getActionCommand());
			}
		});
		menuBarPrefs.add(menuBarPrefsDebug);
		// - - - - - - - - - - - - - - - Menu bar > Preferences > Language - - - - - - -
		JMenu menuBarPrefsLang = new JMenu(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLang"));
		menuBarPrefsLang.setName("menuBarPrefsLang");
		final ButtonGroup langRadioGrp = new ButtonGroup();

		// - - - - - - - - - - - - - - - Menu bar > Preferences > Language > English - -
		JRadioButtonMenuItem menuBarPrefsLangEn;
		if (PREFS.get("lang", "english").equals("english")) {
			menuBarPrefsLangEn = new JRadioButtonMenuItem(
					i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangEn"), true);
		} else {
			menuBarPrefsLangEn = new JRadioButtonMenuItem(
					i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangEn"));
		}
		menuBarPrefsLangEn.setActionCommand(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangEn"));
		menuBarPrefsLangEn.setName("menuBarPrefsLangEn");
		menuBarPrefsLangEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String command = langRadioGrp.getSelection().getActionCommand();
				outputDebugMsg("Clicked Preferences -> Language -> " + command);
				outputDebugMsg("Language chosen = [english]");
				HashMap<String, String> newLabels = i18nLabels.get("english");
				List<Component> allComponents = getChildren(Component.class, FRAME);
				for (Component eachComp : allComponents) {
					updateLabel(eachComp, newLabels);
				}
			}
		});
		menuBarPrefsLang.add(menuBarPrefsLangEn);
		langRadioGrp.add(menuBarPrefsLangEn);
		// - - - - - - - - - - - - - - - Menu bar > Preferences > Language > Chinese - -
		JRadioButtonMenuItem menuBarPrefsLangCn;
		if (PREFS.get("lang", "english").equals("chinese")) {
			menuBarPrefsLangCn = new JRadioButtonMenuItem(
					i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangCn"), true);
		} else {
			menuBarPrefsLangCn = new JRadioButtonMenuItem(
					i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangCn"));
		}
		menuBarPrefsLangCn.setActionCommand(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarPrefsLangCn"));
		menuBarPrefsLangCn.setName("menuBarPrefsLangCn");
		menuBarPrefsLangCn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String command = langRadioGrp.getSelection().getActionCommand();
				outputDebugMsg("Clicked Preferences -> Language -> " + command);
				outputDebugMsg("Language chosen = [chinese]");
				HashMap<String, String> newLabels = i18nLabels.get("chinese");
				List<Component> allComponents = getChildren(Component.class, FRAME);
				for (Component eachComp : allComponents) {
					updateLabel(eachComp, newLabels);
				}
			}
		});
		menuBarPrefsLang.add(menuBarPrefsLangCn);
		langRadioGrp.add(menuBarPrefsLangCn);
		menuBarPrefs.add(menuBarPrefsLang);

		menuBar.add(menuBarPrefs);
		return menuBar;
	}

	/**
	 * Change app's UI language by Updating all components' text
	 * 
	 * @param aComponent
	 * @param newLabels
	 * @author omgitskuei
	 * @since Apr 1 2021
	 */
	private static void updateLabel(Component aComponent, HashMap<String, String> newLabels) {
		String debugMsg = "";
		// Objects that inherit from AbstractButton have setText("") method
		// and JLabel has setText("") method
		if (AbstractButton.class.isAssignableFrom(aComponent.getClass()) || aComponent.getClass() == JLabel.class) {
			if (aComponent instanceof JMenu) {
				JMenu thisComp = (JMenu) aComponent;
				debugMsg = "Component [class=" + String.valueOf(thisComp.getClass()) + ", name=\"" + thisComp.getName()
						+ "\"" + ", text=\"" + thisComp.getText() + "\"] " + "changed text to \""
						+ newLabels.get(aComponent.getName()) + "\"";
				thisComp.setText(newLabels.get(aComponent.getName()));
			} else if (aComponent instanceof JButton) {
				JButton thisComp = (JButton) aComponent;
				debugMsg = "Component [class=" + String.valueOf(thisComp.getClass()) + ", name=\"" + thisComp.getName()
						+ "\"" + ", text=\"" + thisComp.getText() + "\"] " + "changed text to \""
						+ newLabels.get(aComponent.getName()) + "\"";
				thisComp.setText(newLabels.get(aComponent.getName()));
			} else if (aComponent instanceof JMenuItem) {
				JMenuItem thisComp = (JMenuItem) aComponent;
				debugMsg = "Component [class=" + String.valueOf(thisComp.getClass()) + ", name=\"" + thisComp.getName()
						+ "\"" + ", text=\"" + thisComp.getText() + "\"] " + "changed text to \""
						+ newLabels.get(aComponent.getName()) + "\"";
				thisComp.setText(newLabels.get(aComponent.getName()));
			} else if (aComponent instanceof JCheckBoxMenuItem) {
				JCheckBoxMenuItem thisComp = (JCheckBoxMenuItem) aComponent;
				debugMsg = "Component [class=" + String.valueOf(thisComp.getClass()) + ", name=\"" + thisComp.getName()
						+ "\"" + ", text=\"" + thisComp.getText() + "\"] " + "changed text to \""
						+ newLabels.get(aComponent.getName()) + "\"";
				thisComp.setText(newLabels.get(aComponent.getName()));
			} else if (aComponent instanceof JRadioButtonMenuItem) {
				JRadioButtonMenuItem thisComp = (JRadioButtonMenuItem) aComponent;
				debugMsg = "Component [class=" + String.valueOf(thisComp.getClass()) + ", name=\"" + thisComp.getName()
						+ "\"" + ", text=\"" + thisComp.getText() + "\"] " + "changed text to \""
						+ newLabels.get(aComponent.getName()) + "\"";
				thisComp.setText(newLabels.get(aComponent.getName()));
			} else if (aComponent instanceof JLabel) {
				JLabel thisComp = (JLabel) aComponent;
				debugMsg = "Component [class=" + String.valueOf(thisComp.getClass()) + ", name=\"" + thisComp.getName()
						+ "\"" + ", text=\"" + thisComp.getText() + "\"] " + "changed text to \""
						+ newLabels.get(aComponent.getName()) + "\"";
				thisComp.setText(newLabels.get(aComponent.getName()));
			} else {
				System.err.println("Uncaught component=" + aComponent);
			}
			outputDebugMsg(debugMsg);
		} else {
			// This component does not have setText("") method
			outputDebugMsg("Found Component [class=" + aComponent.getClass() + "] "
					+ ", component does not have setText(\"\") method, has nothing to update.");
		}
	}

	/**
	 * Prints out console messages to assist in debugging Checks preferences
	 * settings if debugging is enabled
	 * 
	 * @param message
	 * @author omgitskuei
	 * @since Mar 31 2021
	 */
	private static void outputDebugMsg(String message) {
		if (PREFS.get("isDebug", "false").equals("true")) {
			System.out.println("[" + TIMESTAMP_FORMAT.format(new Date(System.currentTimeMillis())) + "] " + message);
		}
	}

	/**
	 * A recursive method to get children components of a java swing component Some
	 * children components of this component may have children of their own So, it
	 * recursively calls this method itself on the child to get ITS children
	 * 
	 * Reminder List<T> is a List of Generics named 'T'; Generics added in Java 5 T
	 * is used for type, K for key, V for value
	 * 
	 * @param <T>
	 * @param clazz
	 * @param container
	 * @return List<T>
	 * @author omgitskuei
	 * @since Apr 1 2021
	 */
	private static <T extends Component> List<T> getChildren(Class<T> clazz, final Container container) {
		Component[] components;
		// JMenu has a different getComponents() method from other components
		if (container instanceof JMenu) {
			components = ((JMenu) container).getMenuComponents();
		} else {
			components = container.getComponents();
		}
		List<T> compList = new ArrayList<T>();
		for (Component comp : components) {
			// class1.isAssignableFrom(class2) - does class2 inherit (extends) from class1?
			if (clazz.isAssignableFrom(comp.getClass())) {
				compList.add(clazz.cast(comp));
			}
			// This child has children of their own
			if (comp instanceof Container) {
				// Recursion
				compList.addAll(getChildren(clazz, (Container) comp));
			}
		}
		return compList;
	}
}
