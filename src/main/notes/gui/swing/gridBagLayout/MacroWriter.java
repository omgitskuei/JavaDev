package main.notes.gui.swing.gridBagLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.prefs.Preferences;

public class MacroWriter {

	private static JFileChooser fileChooser = new JFileChooser();
	private Preferences prefs = Preferences.userNodeForPackage(main.notes.gui.swing.gridBagLayout.MacroWriter.class);
	private JFrame frame;
	private JMenuBar menuBar = new JMenuBar();
	private static JTextArea textarea = new JTextArea();
	private ArrayList<ArrayList<JButton>> rowsBtns;

	private JFrame initJFrame() {
		JFrame frame = new JFrame();
		frame.setTitle("MacroWriter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		return frame;
	}

	private JMenuBar initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		// - - - - - - - - - - - - - - - Menu bar > File - - - - - - - - - - - - - - -
		JMenu menu1_file = new JMenu("File");
		// - - - - - - - - - - - - - - - Menu bar > File > Open - - - - - - - - - - - -
		JMenuItem menu1_file_open = new JMenuItem("Open");
		menu1_file_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				System.out.println("Clicked File -> " + actEvent.getActionCommand());

				int result = fileChooser.showOpenDialog(frame);
				System.out.println(result);
				if (result == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					// System.out.println(file);
					try {
//		                    	final JEditorPane document = new JEditorPane();
//		                      document.setPage(file.toURI().toURL());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// TO-DO
			}
		});
		menu1_file.add(menu1_file_open);
		// - - - - - - - - - - - - - - - Menu bar > File > Save as - - - - - - - - - - -
		JMenuItem menu1_file_saveAs = new JMenuItem("Save as");
		menu1_file_saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				System.out.println("Clicked File -> " + actEvent.getActionCommand());
				String value = textarea.getText();
				// System.out.println(value);
				// TO-DO
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
				System.out.println("Clicked Help -> " + actEvent.getActionCommand());
				// TO-DO
			}
		});
		menu2_help.add(menu2_help_welcome);
		// - - - - - - - - - - - - - - - Menu bar > Help > About - - - - - - - - - - - -
		JMenuItem menu2_help_about = new JMenuItem("About");
		menu2_help_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				System.out.println("Clicked Help -> " + actEvent.getActionCommand());
				// TO-DO;
			}
		});
		menu2_help.add(menu2_help_about);
		// - - - - - - - - - - - - - - - Menu bar > Help > Contribute - - - - - - - - -
		JMenuItem menu2_help_contribute = new JMenuItem("Contribute");
		menu2_help_contribute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				System.out.println("Clicked Help -> " + actEvent.getActionCommand());
				// TO-DO
			}
		});
		menu2_help.add(menu2_help_contribute);
		menuBar.add(menu2_help);

		// - - - - - - - - - - - - - - - Menu bar > Preferences - - - - - - - - - - - -
		JMenu menu3_pref = new JMenu("Preferences");
		// - - - - - - - - - - - - - - - Menu bar > Preferences > Debug - - - - - - - -
		JMenuItem menu3_pref_debug = new JMenuItem("Debug");
		menu2_help_welcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				System.out.println("Clicked Preferences -> " + actEvent.getActionCommand());
				// TO-DO
			}
		});
		menu3_pref.add(menu3_pref_debug);
		menuBar.add(menu3_pref);
		return menuBar;
	}

	private JButton createJButton(String btnLabel, ActionListener onClick) {
		JButton newBtn = new JButton(btnLabel);
		newBtn.addActionListener(onClick);
		return newBtn;
	}

	private ArrayList<ArrayList<JButton>> createButtons() {
		ArrayList<ArrayList<JButton>> rowsBtns = new ArrayList<ArrayList<JButton>>();
		ActionListener onClick;
		// Row 1
		ArrayList<JButton> row1Btns = new ArrayList<JButton>();
		// Row 1 -> Shift KeyDown
		onClick = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String defaultValue = "true";
				String isDebug = prefs.get("isDebug", defaultValue);
				if (isDebug.equals("true")) {
					System.out.println("Clicked Shift KeyDown button");
				}
			}
		};
		JButton shiftDBtn = createJButton("Shift KeyDown", onClick);
		row1Btns.add(shiftDBtn);
		// Row 1 -> Shift KeyUp
		onClick = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String defaultValue = "true";
				String isDebug = prefs.get("isDebug", defaultValue);
				if (isDebug.equals("true")) {
					System.out.println("Clicked Shift KeyUp button");
				}
			}
		};
		JButton shiftUBtn = createJButton("Shift KeyUp", onClick);
		row1Btns.add(shiftUBtn);

		rowsBtns.add(row1Btns);

		// Row 2
		ArrayList<JButton> row2Btns = new ArrayList<JButton>();
		// Row 2 -> Alt KeyDown
		onClick = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String defaultValue = "true";
				String isDebug = prefs.get("isDebug", defaultValue);
				if (isDebug.equals("true")) {
					System.out.println("Clicked Alt KeyDown button");
				}
			}
		};
		JButton altDBtn = createJButton("Alt KeyDown", onClick);
		row2Btns.add(altDBtn);
		// Row 2 -> Alt KeyUp
		onClick = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String defaultValue = "true";
				String isDebug = prefs.get("isDebug", defaultValue);
				if (isDebug.equals("true")) {
					System.out.println("Clicked Alt KeyUp button");
				}
			}
		};
		JButton altUBtn = createJButton("Alt KeyUp", onClick);
		row2Btns.add(altUBtn);

		rowsBtns.add(row2Btns);
		return rowsBtns;
	}

	private void setPreferences(HashMap<String, String> newPreferences) {
		// Set isDebug
		String isDebugValue = newPreferences.get("isDebug");
		prefs.put("isDebug", isDebugValue);
		// Set ...

	}

	private void configGridBagLayout(JFrame frame, ArrayList<ArrayList<JButton>> rowsBtns) {
		Container pane = frame.getContentPane();
		pane.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		// Add TextAreas

		// Add TextFields

		// Add Buttons
		int rowNum = 0;
		for (ArrayList<JButton> eachRow : rowsBtns) {
			int colNum = 0;
			for (JButton eachBtn : eachRow) {
				gbc.fill = GridBagConstraints.HORIZONTAL;
				// System.out.println("rowNum:"+rowNum+","+"colNum:"+colNum);
				gbc.gridx = colNum;
				gbc.gridy = rowNum;
				frame.add(eachBtn, gbc);
				colNum++;
			}
			rowNum++;
		}
	}

	private void showApp(JFrame frame) {
		// frame.pack(); // Set size to wrap around components
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		MacroWriter mWriter = new MacroWriter();
		mWriter.frame = mWriter.initJFrame();
		mWriter.menuBar = mWriter.initMenuBar();
		mWriter.frame.getContentPane().add(mWriter.menuBar);

		mWriter.rowsBtns = mWriter.createButtons();

		mWriter.configGridBagLayout(mWriter.frame, mWriter.rowsBtns);

		mWriter.showApp(mWriter.frame);
	}

}