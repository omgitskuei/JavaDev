package main.projects.macroEditor.v2.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MacroEditorGUI {
	
	private JMenu initJMenu(String name, String label) {
		JMenu jMenu = new JMenu(label);
		jMenu.setName(name);
		return jMenu;
	}
	
	private JMenuItem initJMenuItem(String name, String label, ActionListener action, ImageIcon icon) {
		JMenuItem jMenuItem = new JMenuItem(label);
		jMenuItem.setName(name);
		jMenuItem.addActionListener(action);
		jMenuItem.setIcon(icon);
		return jMenuItem;
	}
	
	private JRadioButtonMenuItem initRadioMenuItem(String name, String label, ActionListener action) {
		JRadioButtonMenuItem radio = new JRadioButtonMenuItem(name, true);
//		radio.setActionCommand(actionCommand);
		radio.addActionListener(action);
		radio.setName(name);
		return radio;
	}
	
	private ImageIcon initIcon(String path, int height, int width) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		return imageIcon;
	}
	
	private JMenuBar initJMenuBar(HashMap<String, String> labels, HashMap<String, String> iconPaths) {
		JMenuBar menu = new JMenuBar();
		JMenu file = initJMenu("menuBar_File", labels.get("menuBar_File"));
		file.add(initJMenuItem("menuBar_File_Open", labels.get("menuBar_File_Open"), null, initIcon(iconPaths.get("menuBar_File_Open"), 20, 20)));
		file.add(initJMenuItem("menuBar_File_SaveAs", labels.get("menuBar_File_SaveAs"), null, initIcon(iconPaths.get("menuBar_File_SaveAs"), 20, 20)));
		menu.add(file);
		
		JMenu help = initJMenu("menuBar_Help", labels.get("menuBar_Help"));
		help.add(initJMenuItem("menuBar_Help_About", labels.get("menuBar_Help_About"), null, initIcon(iconPaths.get("menuBar_Help_About"), 20, 20)));
		menu.add(help);
		
		JMenu prefs = initJMenu("menuBar_Prefs", labels.get("menuBar_Prefs"));
		prefs.add(initJMenuItem("menuBar_Prefs_Debug", labels.get("menuBar_Prefs_Debug"), null, initIcon(iconPaths.get("menuBar_Prefs_Debug"), 20, 20)));
		
		JMenu langsMenu = initJMenu("menuBar_Prefs_Lang", labels.get("menuBar_Prefs_Lang"));
		langsMenu.add(initRadioMenuItem("menuBar_Prefs_Lang_En", "English", null));
		langsMenu.add(initRadioMenuItem("menuBar_Prefs_Lang_Cn", "Chinese", null));
		
//		langsMenuItem.add(initJMenuItem("menuBar_Prefs_Lang_En", labels.get("menuBar_Prefs_Lang_En"), null, initIcon(iconPaths.get("menuBar_Prefs_Lang_En"), 20, 20)));
//		langsMenuItem.add(initJMenuItem("menuBar_Prefs_Lang_Cn", labels.get("menuBar_Prefs_Lang_Cn"), null, initIcon(iconPaths.get("menuBar_Prefs_Lang_Cn"), 20, 20)));
		prefs.add(langsMenu);
		menu.add(prefs);
		
		return menu;
	}
	
	public JPanel initJMenuPanel(HashMap<String, String> labels, HashMap<String, String> iconPaths) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		JMenuBar menu = initJMenuBar(labels, iconPaths);
		panel.add(menu, gbc);
		return panel;
	}
	
	public JFrame initJFrame(String title, Boolean alwaysOnTop, int minWidth, int minHeight) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(alwaysOnTop);
		frame.setSize(minWidth, minHeight);
		frame.setMinimumSize(new Dimension(minWidth, minHeight));
		return frame;
	}
	
	public int applyLookAndFeel() {
		int exitCode = 200;
		try {
			// Set Look&Feel to current System's
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// Default to CrossPlatformLookAndFeel if System's fails
			System.err.println("An UnsupportedLookAndFeelException error occurred while setting LookAndFeel to current sysL&F.");
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (UnsupportedLookAndFeelException e1) {
				exitCode = 400;
				System.err.println("An UnsupportedLookAndFeelException error occurred while setting LookAndFeel to crossPlatformL&F.");
			}  catch (ClassNotFoundException e1) {
				exitCode = 400;
				System.err.println("An ClassNotFoundException error occurred while setting LookAndFeel to crossPlatformL&F.");
			} catch (InstantiationException e1) {
				exitCode = 400;
				System.err.println("An InstantiationException error occurred while setting LookAndFeel to crossPlatformL&F.");
			} catch (IllegalAccessException e1) {
				exitCode = 400;
				System.err.println("An IllegalAccessException error occurred while setting LookAndFeel to crossPlatformL&F.");
			}
		} catch (ClassNotFoundException e) {
			exitCode = 404;
			System.err.println("An ClassNotFoundException error occurred while setting LookAndFeel to current sysL&F.");
		} catch (InstantiationException e) {
			exitCode = 404;
			System.err.println("An InstantiationException error occurred while setting LookAndFeel to current sysL&F.");
		} catch (IllegalAccessException e) {
			exitCode = 404;
			System.err.println("An IllegalAccessException error occurred while setting LookAndFeel to current sysL&F.");
		}
		return exitCode;
	}
}
