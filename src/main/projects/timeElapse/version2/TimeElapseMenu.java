package main.projects.timeElapse.version2;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

public class TimeElapseMenu {
	
	private JMenuBar menuBar = null;
	
	TimeElapseMenu() {
		menuBar = new JMenuBar();
		// - - - - - - - - - - - - - - - Menu bar > Help - - - - - - - - - - - - - - -
		JMenu menuBarHelp = new JMenu(i18nLabels.get(PREFS.get("lang", "english")).get("menuBarHelp"));
		menuBarHelp.setName("menuBarHelp");
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
						+ newline
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
}

