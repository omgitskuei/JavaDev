package main.projects.macroEditor.v2;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.projects.macroEditor.v2.gui.MacroEditorGUI;

public class MacroEditor {

	/*
	 * Constants
	 */
	private static final String projectRoot = System.getProperty("user.dir");

	private static Preferences appConfig = null;
	private static MacroEditorGUI meGUI = null;
	private static MacroEditorUtil meUtil = null;

	/*
	 * Globals
	 */
	private HashMap<String, HashMap<String, String>> labels = new HashMap<String, HashMap<String, String>>();

	/**
	 * Constructor
	 */
	private MacroEditor() {
		appConfig = Preferences.userNodeForPackage(main.projects.macroEditor.v2.MacroEditor.class);
		meGUI = new MacroEditorGUI();
		meUtil = new MacroEditorUtil();
	}

	public static void main(String[] args) {
		MacroEditor me = new MacroEditor();
		int returnCode = 0;
		String macroEditorV2Path = projectRoot + "\\src\\main\\projects\\macroEditor\\v2";

		// Apply UIManager
		returnCode = meGUI.applyLookAndFeel();
		if (returnCode != 200) {
			System.exit(returnCode);
		}

		// Apply config
		returnCode = me.initAppConfigs(macroEditorV2Path + "\\config.txt");
		if (returnCode != 200) {
			System.exit(returnCode);
		}

		// Apply default English localization
		returnCode = me.initLocalization("english", macroEditorV2Path + "\\localization\\i18nEnglish.txt");
		if (returnCode != 200) {
			System.exit(returnCode);
		}
		// Apply default English localization
		returnCode = me.initLocalization("mandarinTr", macroEditorV2Path + "\\localization\\i18nMandarinTr.txt");
		if (returnCode != 200) {
			System.exit(returnCode);
		}
		
		// Create app frame
		JFrame frame = meGUI.initJFrame("MacroEditor", true, 400, 500);

		// Prepare file paths for MenuItem icons
		HashMap<String, String> menuIconPaths = new HashMap<String, String>();
		menuIconPaths.put("menuBar_File_Open", macroEditorV2Path + "\\resources\\fileOpen.png");
		menuIconPaths.put("menuBar_File_SaveAs", macroEditorV2Path + "\\resources\\fileSaveAs.png");
		menuIconPaths.put("menuBar_Help_About", macroEditorV2Path + "\\resources\\helpAbout.png");
		menuIconPaths.put("menuBar_Prefs_Debug", macroEditorV2Path + "\\resources\\prefsDebug.png");
		menuIconPaths.put("menuBar_Prefs_Lang", macroEditorV2Path + "\\resources\\prefsLang.png");
		menuIconPaths.put("menuBar_Prefs_Lang_En", macroEditorV2Path + "\\resources\\prefsLangEn.png");
		menuIconPaths.put("menuBar_Prefs_Lang_Cn", macroEditorV2Path + "\\resources\\prefsLangCn.png");
		
		// Create app menu
		JPanel menuBarPanel = meGUI.initJMenuPanel(
				me.labels.get(appConfig.get("lang", "english")), 
				menuIconPaths);
		
		frame.getContentPane().add(BorderLayout.NORTH, menuBarPanel);
		
		
		
		// test getting label text from i18n files
		String a = me.labels.get(appConfig.get("lang", "english")).get("menuBar_File");
		System.out.println(a); // prints "File"
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	private int initLocalization(String lang, String path) {
		try {
			labels.put(lang, meUtil.readFileAsMaps(path));
		} catch (FileNotFoundException e) {
			System.out.println("Failed to Read File: File URL was " + path);
			return 404;
		}
		return 200;
	}

	private int initAppConfigs(String configFilePath) {
		HashMap<String, String> configPairs = null;
		try {
			configPairs = meUtil.readFileAsMaps(configFilePath);
		} catch (FileNotFoundException e) {
			System.out.println("Failed to Read File: File URL was " + configFilePath);
			return 404;
		}
		appConfig.put("isDebug", configPairs.get("isDebug")); // valid: true, false
		appConfig.put("lang", configPairs.get("lang")); // valid: english, chinese
		return 200;
	}
}
