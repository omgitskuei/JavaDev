package main.notes.gui.swing;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Keylogger {
	
	private static JFileChooser fileChooser = new JFileChooser();
	
	private static JFrame frame = new JFrame("Keylogger");
	private static JMenuBar menuBar = new JMenuBar();
	private static JTextArea textarea = new JTextArea();
	private static JPanel panel = new JPanel();
	
	public static void main(String[] args) {
		// Configure Frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);

		// - - - - - - - - - - - - - - - Menu bar > File - - - - - - - - - - - - - - -
		JMenu menu1_file = new JMenu("File");
		// - - - - - - - - - - - - - - - Menu bar > File > Open - - - - - - - - - - - - - - -
		JMenuItem menu1_file_open = new JMenuItem("Open");
		menu1_file_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				System.out.println("Clicked File -> Open");
				// System.out.println(actEvent.getActionCommand()); // "Open"
				
				int result = fileChooser.showOpenDialog(frame);
				System.out.println(result); // ??????????????????????????????????????????????
                if (result==JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    // System.out.println(file);
                    try {
//                    	final JEditorPane document = new JEditorPane();
//                      document.setPage(file.toURI().toURL());
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
				// TO-DO
			}
		});
		menu1_file.add(menu1_file_open);
		// - - - - - - - - - - - - - - - Menu bar > File > Save as - - - - - - - - - - - - - - -
		JMenuItem menu1_file_saveAs = new JMenuItem("Save as");
		menu1_file_saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked File -> Save as");
				String value = textarea.getText();
				// System.out.println(value);
				// TO-DO
			}
		});
		menu1_file.add(menu1_file_saveAs);
		menuBar.add(menu1_file);
		// - - - - - - - - - - - - - - - Menu bar > Help - - - - - - - - - - - - - - -
		JMenu menu2_help = new JMenu("Help");
		// - - - - - - - - - - - - - - - Menu bar > Help > Welcome - - - - - - - - - - - - - - -
		JMenuItem menu2_help_welcome = new JMenuItem("Welcome");
		menu2_help_welcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Help -> Welcome");
				// TO-DO
			}
		});
		menu2_help.add(menu2_help_welcome);
		// - - - - - - - - - - - - - - - Menu bar > Help > About - - - - - - - - - - - - - - -
		JMenuItem menu2_help_about = new JMenuItem("About");
		menu2_help_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Help -> About");
				// TO-DO;
			}
		});
		menu2_help.add(menu2_help_about);
		// - - - - - - - - - - - - - - - Menu bar > Help > Contribute - - - - - - - - - - - - - - -
		JMenuItem menu2_help_contribute = new JMenuItem("Contribute");
		menu2_help_contribute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Help -> Contribute");
				// TO-DO
			}
		});
		menu2_help.add(menu2_help_contribute);
		menuBar.add(menu2_help);

		// - - - - - - - - - - - - - - - Text Area - - - - - - - - - - - - - - -
		textarea.setEditable(false);

		// - - - - - - - - - - - - - - - Panel - - - - - - - - - - - - - - -
		JLabel label = new JLabel("Enter Text");
		panel.add(label);
		// - - - - - - - - - - - - - - - Panel - TextField - - - - - - - - - - - - - - -
		final JTextField inputText = new JTextField(10); // accepts upto 10 characters
		panel.add(inputText);
		// - - - - - - - - - - - - - - - Panel - Button("Add") - - - - - - - - - - - - - - -
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Add button");
				String value = inputText.getText();
				value = checkKeyValue(value);
				textarea.append(value);
				inputText.setText("");
				inputText.requestFocus();
			}
		});
		panel.add(addBtn);
		// - - - - - - - - - - - - - - - Panel - Button("Clear") - - - - - - - - - - - - - - -
		JButton resetBtn = new JButton("Clear");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Reset button");
				inputText.setText("");
				inputText.requestFocus();
			}
		});
		panel.add(resetBtn);

		frame.getContentPane().add(BorderLayout.NORTH, menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, textarea);
		frame.getContentPane().add(BorderLayout.SOUTH, panel);
		frame.setVisible(true);
	}

	private static String checkKeyValue(String inputValue) {
		String rtnString = "";
		switch (inputValue) {
		case " ":
			rtnString = "<space>";
			break;
		case "^":
			rtnString = "<shift>";
			break;
		case "":
			rtnString = "<enter>";
			break;
		default:
			rtnString = inputValue;
		}
		return rtnString + "\r\n";
	}
}
