package main.notes.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class JMenuOpenFile {

	private static final SimpleDateFormat TIMESTAMPFORMAT = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	private static JFrame frame = new JFrame();

	private static String getDebugTimestamp() {
		return TIMESTAMPFORMAT.format(new Date(System.currentTimeMillis()));
	}

	public static void main(String[] args) {
		// initJFrame
		frame.setTitle("MacroWriter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setMinimumSize(new Dimension(400, 300));

		// preferences
		final Preferences prefs = Preferences
				.userNodeForPackage(main.notes.gui.swing.gridBagLayout.GridBagLayoutTest.class);
		prefs.put("isDebug", "true");

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
				
				JFileChooser fileChooser = new JFileChooser();
				int chosenFile = fileChooser.showOpenDialog(frame); // if the User chose a file, return 0
				if (chosenFile == JFileChooser.APPROVE_OPTION) { // JFileChooser.APPROVE_OPTION is 0
					File file = fileChooser.getSelectedFile();

					if (isDebug.equals("true")) {
						System.out.println("File name:     " + file.getName());
						System.out.println("Absolute path: " + file.getAbsolutePath());
						System.out.println("Writeable:     " + file.canWrite());
						System.out.println("Readable       " + file.canRead());
						System.out.println("Size (bytes):  " + file.length());
					}
					
					try {
						Scanner myReader = new Scanner(file);
						
						if (isDebug.equals("true")) {
							System.out.println("<<File contents - BEGIN>>");
						}
						
						int lnCount = 1;
						while (myReader.hasNextLine()) {
							String eachLine = myReader.nextLine();
							
							if (isDebug.equals("true")) {
								System.out.println("(ln"+lnCount+") "+eachLine);
							}
							
							lnCount++;
						}
						
						if (isDebug.equals("true")) {
							System.out.println("<<File contents - END>>");
						}
						
						myReader.close();
					} catch (FileNotFoundException e) {
						System.out.println("A FileNotFoundException error occurred.");
						e.printStackTrace();
					}
				}
			}
		});
		menu1_file.add(menu1_file_open);
		menuBar.add(menu1_file);
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

		frame.setVisible(true);
	}

}
