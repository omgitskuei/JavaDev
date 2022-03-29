package main.projects.macroEditor.v2.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
	
	private JMenuBar initJMenuBar(HashMap<String, String> labels) {
		JMenuBar menu = new JMenuBar();
		JMenu file = initJMenu("menuBar_File", labels.get("menuBar_File"));
		file.add(initJMenuItem("menuBar_File_Open", labels.get("menuBar_File_Open"), null, new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\projects\\macroEditor\\v2\\resources\\fileOpen.png")));
		//file.add(initJMenuItem("menuBar_File_SaveAs", labels.get("menuBar_File_SaveAs"), null));
		menu.add(file);
		menu.add(initJMenu("menuBar_Help", labels.get("menuBar_Help")));
		menu.add(initJMenu("menuBar_Prefs", labels.get("menuBar_Prefs")));
		return menu;
	}
	
	public JPanel initJMenuPanel(HashMap<String, String> labels) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		JMenuBar menu = initJMenuBar(labels);
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
