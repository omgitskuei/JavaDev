package main.notes.gui.swing;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.prefs.Preferences;

public class i18nExampleMenuBar {

	private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	private static final JFrame FRAME = new JFrame();
	private static final Preferences PREFS = Preferences.userNodeForPackage(main.projects.macroEditor.MacroEditor.class);
	private static HashMap<String, String> contribURLs = new HashMap<String, String>();
	private static HashMap<String, HashMap<String, String>> i18nLabels = new HashMap<String, HashMap<String, String>>();
	
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
		cnLabels.put("menuBarFile",        "File_CN");
		cnLabels.put("menuBarFileOpen",    "Open_CN");
		cnLabels.put("menuBarFileSaveAs",  "Save As_CN");
		cnLabels.put("menuBarHelp",        "Help_CN");
		cnLabels.put("menuBarHelpWelcome", "Welcome_CN");
		cnLabels.put("menuBarHelpAbout",   "About_CN");
		cnLabels.put("menuBarHelpContrib", "Contribute_CN");
		cnLabels.put("menuBarPrefs",       "Preferences_CN");
		cnLabels.put("menuBarPrefsDebug",  "Debug_CN");
		cnLabels.put("menuBarPrefsLang",   "Lang_CN");
		cnLabels.put("menuBarPrefsLangEn", "English_CN");
		cnLabels.put("menuBarPrefsLangCn", "Chinese_CN");
		i18nLabels.put("chinese", cnLabels);

		// initMenuBar
		JMenuBar menuBar = new JMenuBar();
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
				// outputDebugMsg("[" + getDebugTimestamp() + "] english labels = [" + newLabels + "]");
				List<Component> allComponents = getChildren(Component.class, FRAME);
				// outputDebugMsg("[" + getDebugTimestamp() + "] All components labels = [" + allComponents + "]");
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
				// outputDebugMsg("[" + getDebugTimestamp() + "] chinese labels = [" + newLabels + "]");
				List<Component> allComponents = getChildren(Component.class, FRAME);
				// outputDebugMsg("[" + getDebugTimestamp() + "] All components labels = [" + allComponents + "]");
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

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FRAME.setVisible(true);
			}
		});
	}

	// Helper methods
	private static void outputDebugMsg(String message) {
		if(PREFS.get("isDebug", "false").equals("true")) {
			System.out.println(message);
		}
	}
	
	
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