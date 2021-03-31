package main.notes.gui.swing.gridBagLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GridBagLayoutTest {

	private static final SimpleDateFormat TIMESTAMPFORMAT = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	
	private static String getDebugTimestamp() {
		return TIMESTAMPFORMAT.format(new Date(System.currentTimeMillis()));
	}
	
	public static void main(String[] args) {
		// initJFrame
		JFrame frame = new JFrame();
		frame.setTitle("MacroWriter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setMinimumSize(new Dimension(400, 300));

		// preferences
		final Preferences prefs = Preferences
				.userNodeForPackage(main.notes.gui.swing.gridBagLayout.GridBagLayoutTest.class);
		prefs.put("isDebug", "false");

		// initMenuBar
		JMenuBar menuBar = new JMenuBar();
		// - - - - - - - - - - - - - - - Menu bar > File - - - - - - - - - - - - - - -
		JMenu menu1_file = new JMenu("File");
		// - - - - - - - - - - - - - - - Menu bar > File > Open - - - - - - - - - - - -
		JMenuItem menu1_file_open = new JMenuItem("Open");
		menu1_file_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String defaultValue = "false";
				String isDebug = prefs.get("isDebug", defaultValue);
				if (isDebug.equals("true")) {
					System.out.println("[" + getDebugTimestamp() + "] Clicked File -> " + actEvent.getActionCommand());
				}
			}
		});
		menu1_file.add(menu1_file_open);
		// - - - - - - - - - - - - - - - Menu bar > File > Save as - - - - - - - - - - -
		JMenuItem menu1_file_saveAs = new JMenuItem("Save as");
		menu1_file_saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String defaultValue = "false";
				String isDebug = prefs.get("isDebug", defaultValue);
				if (isDebug.equals("true")) {
					System.out.println("[" + getDebugTimestamp() + "] Clicked File -> " + actEvent.getActionCommand());
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
				String defaultValue = "false";
				String isDebug = prefs.get("isDebug", defaultValue);
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
				String defaultValue = "false";
				String isDebug = prefs.get("isDebug", defaultValue);
				if (isDebug.equals("true")) {
					System.out.println("[" + getDebugTimestamp() + "] Clicked Help -> " + actEvent.getActionCommand());
				}
			}
		});
		menu2_help.add(menu2_help_about);
		// - - - - - - - - - - - - - - - Menu bar > Help > Contribute - - - - - - - - -
		JMenuItem menu2_help_contribute = new JMenuItem("Contribute");
		menu2_help_contribute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				String defaultValue = "false";
				String isDebug = prefs.get("isDebug", defaultValue);
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
				String defaultValue = "false";
				String isDebug = prefs.get("isDebug", defaultValue);
				if (isDebug.equals("false")) {
					prefs.put("isDebug", "true");
				} else {
					prefs.put("isDebug", "false");
				}
				if (isDebug.equals("true")) {
					System.out.println("[" + getDebugTimestamp() + "] Clicked Preferences -> " + actEvent.getActionCommand());
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
		frame.getContentPane().add(BorderLayout.NORTH, menuBarPanel);

		
		
		
		
		
		
		
		
		
		
		
		// InitButtonsJPanel
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridBagLayout());
		JButton button;
		buttonsPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//natural height, maximum width
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
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		buttonsPanel.add(button, c);

		button = new JButton("5");
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 2;       //third row
		buttonsPanel.add(button, c);
		frame.getContentPane().add(BorderLayout.SOUTH, buttonsPanel);

		frame.getContentPane().add(BorderLayout.WEST, new JButton("WEST"));
		frame.getContentPane().add(BorderLayout.CENTER, new JButton("CENTER"));
		frame.getContentPane().add(BorderLayout.EAST, new JButton("EAST"));
		
		frame.setVisible(true);
	}

}
