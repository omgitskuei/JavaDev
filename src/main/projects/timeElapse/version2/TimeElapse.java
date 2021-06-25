package main.projects.timeElapse.version2;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TimeElapse {

	private static final String APPNAME = "TimeElapse";
	private static final String VERSION = "2021-04";
	private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

	private static final Preferences PREFS = Preferences
			.userNodeForPackage(main.projects.timeElapse.version2.TimeElapse.class);

	private static final String newline = System.getProperty("line.separator");

	private static final JFrame FRAME = new JFrame();

	private static HashMap<String, String> contribURLs = new HashMap<String, String>();
	private static HashMap<String, HashMap<String, String>> i18nLabels = new HashMap<String, HashMap<String, String>>();
	private static HashMap<String, GridBagConstraints> componentGBCs = new HashMap<String, GridBagConstraints>();

	private static String results = "";
	
	/*
	 * UI Layout:
	 * FRAME
	 * --> JPanel (frameJPanel, BorderLayout)
	 * -- --> JPanel (menuBarJPanel, GridBagLayout)
	 * -- -- --> JMenuBar (menuBar)
	 * -- -- -- --> JMenu "Help"
	 * -- -- -- -- --> JMenuItem "About"
	 * -- -- -- -- --> JMenuItem "Contribute"
	 * -- -- -- --> JMenu "Preferences"
	 * -- -- -- -- --> JCheckBoxMenuItem "Debug"
	 * -- -- -- -- --> JMenu "Language"
	 * -- -- -- -- -- --> JRadioButtonMenuItem "English"
	 * -- -- -- -- -- --> JRadioButtonMenuItem "Chinese"
	 * -- --> JPanel (appbodyJPanel, GridBagLayout)
	 * -- -- // Row 1
	 * -- -- --> JLabel (startLabel)
	 * -- -- --> JTextField (startTextField)
	 * -- -- --> ComboBox (startAMPMCombo)
	 * -- -- // Row 2
	 * -- -- --> JLabel (endLabel)
	 * -- -- --> JTextFiel (endTextField)
	 * -- -- --> ComboBox (endAMPMCombo)\
	 * -- -- // Row 3
	 * -- -- --> JButton (calcElapseBtn)
	 * -- -- --> JButton (clearBtn)
	 * -- -- // Row 4
	 * -- -- --> JScrollPane (scrollPane)
	 * -- -- -- --> JTextArea (resultTextArea)
	 */
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
		FRAME.setAlwaysOnTop(true);
		FRAME.setResizable(false);
		
		// initialize GridBagConstraints of various UI components (eg. JLabel, JTextField, JButton, etc)
		timeElapse.populateGBCs();
		
		// Add JPanel to FRAME
		JPanel frameJPanel = new JPanel();
		frameJPanel.setLayout(new BorderLayout());
		FRAME.getContentPane().add(frameJPanel);

		// Initialize JMenuBar menuBar, GridBagConstraints menuBarGBC, JPanel menuBarJPanel
		JPanel menuBarJPanel = new JPanel();
		menuBarJPanel.setLayout(new GridBagLayout());
		frameJPanel.add(menuBarJPanel, BorderLayout.PAGE_START);
		

		JMenuBar menuBar = createMenuBar();
		menuBarJPanel.add(menuBar, componentGBCs.get("menuBarGBC"));
		
		
		// Initialize app body jpanel
		JPanel appbodyJPanel = new JPanel();
		appbodyJPanel.setLayout(new GridBagLayout());
		frameJPanel.add(appbodyJPanel, BorderLayout.CENTER);
		// - - - - - - - - Row 1 - - - - - - - -
		// startLabel
		JLabel startLabel = new JLabel(i18nLabels.get(PREFS.get("lang", "english")).get("startLabel"));
		startLabel.setName("startLabel");
		startLabel.setHorizontalTextPosition(JLabel.CENTER);
		startLabel.setVerticalTextPosition(JLabel.CENTER);
		appbodyJPanel.add(startLabel, componentGBCs.get("startLabelGBC"));
		// startTextField
		final JTextField startTextField = new JTextField();
		startTextField.setEnabled(true);
		appbodyJPanel.add(startTextField, componentGBCs.get("startTextFieldGBC"));
		// startAMPMCombo
		String[] AMPMOptions = {"AM", "PM"};
		final JComboBox<String> startAMPMCombo = new JComboBox<String>(AMPMOptions);
		startAMPMCombo.setSelectedIndex(0);
		appbodyJPanel.add(startAMPMCombo, componentGBCs.get("startAMPMComboGBC"));
		// - - - - - - - - Row 2 - - - - - - - -
		// endLabel
		JLabel endLabel = new JLabel(i18nLabels.get(PREFS.get("lang", "english")).get("endLabel"));
		endLabel.setName("endLabel");
		endLabel.setHorizontalTextPosition(JLabel.CENTER);
		endLabel.setVerticalTextPosition(JLabel.CENTER);
		appbodyJPanel.add(endLabel, componentGBCs.get("endLabelGBC"));
		// endTextField
		final JTextField endTextField = new JTextField();
		endTextField.setEnabled(false);
		appbodyJPanel.add(endTextField, componentGBCs.get("endTextFieldGBC"));
		// endAMPMCombo
		final JComboBox<String> endAMPMCombo = new JComboBox<String>(AMPMOptions);
		endAMPMCombo.setSelectedIndex(0);
		endAMPMCombo.setEnabled(false);
		appbodyJPanel.add(endAMPMCombo, componentGBCs.get("endAMPMComboGBC"));
		// - - - - - - - - Row 3 - - - - - - - -
		// calcElapseBtn
		final JButton calcElapseBtn = new JButton(i18nLabels.get(PREFS.get("lang", "english")).get("calcElapseBtn"));
		calcElapseBtn.setEnabled(false);
		calcElapseBtn.setName("calcElapseBtn");
		appbodyJPanel.add(calcElapseBtn, componentGBCs.get("calcElapseBtnGBC"));
		// clearBtn
		final JButton clearBtn = new JButton(i18nLabels.get(PREFS.get("lang", "english")).get("clearBtn"));
		clearBtn.setName("clearBtn");
		appbodyJPanel.add(clearBtn, componentGBCs.get("clearBtnGBC"));
		// - - - - - - - - Row 4 - - - - - - - -
		final JTextArea resultTextArea = new JTextArea();
		resultTextArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(resultTextArea);
		scrollPane.setPreferredSize(new Dimension(300, 100));
		appbodyJPanel.add(scrollPane, componentGBCs.get("resultTextArea"));
		
		startTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("Entered into startTextField -> \'" + actEvent.getActionCommand() + "\'");
				String debugMsg = "";
				if(startTextField.getText().trim().length()>0) {
					if(!endTextField.isEnabled()) {
						debugMsg = debugMsg + ", Enable endTextField & endAMPMCombo";
						endTextField.setEnabled(true);
						endAMPMCombo.setEnabled(true);
					}
					debugMsg = debugMsg + ", Focus endTextField";
					endTextField.grabFocus();
				} else {
					if(endTextField.isEnabled()) {
						debugMsg = debugMsg + ", Disable endTextField & endAMPMCombo";
						endTextField.setEnabled(false);
						endAMPMCombo.setEnabled(false);
					}
				}
				outputDebugMsg("startTextField.getText() = \'"+startTextField.getText()+"\'" + debugMsg);
			}
		});
		startAMPMCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("Changed startAMPMCombo -> \'" + actEvent.getActionCommand() + "\'" + ", " + startAMPMCombo.getItemAt(startAMPMCombo.getSelectedIndex()));
				String debugMsg = "";
				if(startTextField.getText().length()>0) {
					if(endTextField.isEnabled()==false) {
						debugMsg = debugMsg + ", Enable endTextField";
						endTextField.setEnabled(true);
						endAMPMCombo.setEnabled(true);
					}
					debugMsg = debugMsg + ", Focus endTextField";
					endTextField.grabFocus();
				} else {
					if(endTextField.isEnabled()==true) {
						debugMsg = debugMsg + ", Disable endTextField";
						endTextField.setEnabled(false);
						endAMPMCombo.setEnabled(false);
					}
				}
				outputDebugMsg("Start time textField.getText() = \'"+startTextField.getText()+"\'" + debugMsg);
			}
		});
		
		endTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("Entered into endTextField -> \'" + actEvent.getActionCommand() + "\'");
				String debugMsg = "";
				if(endTextField.getText().trim().length()>0 && startTextField.getText().trim().length()>0) {
					if(calcElapseBtn.isEnabled()==false) {
						debugMsg = debugMsg + ", Enable calcElapseBtn";
						calcElapseBtn.setEnabled(true);
					}
					debugMsg = debugMsg + ", Focus calcElapseBtn";
					calcElapseBtn.grabFocus();
				} else {
					if(calcElapseBtn.isEnabled()) {
						debugMsg = debugMsg + ", Disable calcElapseBtn";
						calcElapseBtn.setEnabled(false);
					}
				}
				outputDebugMsg("endTextField.getText() = \'"+endTextField.getText()+"\'" + debugMsg);
			}
		});
		endAMPMCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("Changed endAMPMCombo -> \'" + actEvent.getActionCommand() + "\'" + ", " + endAMPMCombo.getItemAt(endAMPMCombo.getSelectedIndex()));
				String debugMsg = "";
				if(endTextField.getText().length()>0) {
					if(calcElapseBtn.isEnabled()==false) {
						debugMsg = debugMsg + ", Enable calcElapseBtn";
						calcElapseBtn.setEnabled(true);
					}
					debugMsg = debugMsg + ", Focus calcElapseBtn";
					calcElapseBtn.grabFocus();
				} else {
					if(calcElapseBtn.isEnabled()==true) {
						debugMsg = debugMsg + ", Disable calcElapseBtn";
						calcElapseBtn.setEnabled(false);
					}
				}
				outputDebugMsg("startTextField.getText() = \'"+startTextField.getText()+"\'" + ", endTextField.getText() = \'"+endTextField.getText()+"\'" + debugMsg);
			}
		});
		
		calcElapseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("Clicked calcElapseBtn -> \'" + actEvent.getActionCommand() + "\'");
				// get input
				String startTime = startTextField.getText();
				String startAMPM = startAMPMCombo.getItemAt(startAMPMCombo.getSelectedIndex());
				String endTime = endTextField.getText();
				String endAMPM = endAMPMCombo.getItemAt(endAMPMCombo.getSelectedIndex());
				// validate input
				boolean validStart = validateInput(startTime, startAMPM);
				boolean validEnd = validateInput(endTime, endAMPM);
				if(!validStart) {
					results = results + "Start time is Invalid" + "\r\n";
					resultTextArea.setText(results);
					clearBtn.grabFocus();
				} 
				if(!validEnd) {
					results = results + "End time is Invalid" + "\r\n";
					resultTextArea.setText(results);
					clearBtn.grabFocus();
				}				
				if(validStart  && validEnd) {
					String diff = calcDifference(startTime, startAMPM, endTime, endAMPM);
					results = results + diff + "\r\n";
					resultTextArea.setText(results);
					clearBtn.grabFocus();
				}
			}
		});
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actEvent) {
				outputDebugMsg("Clicked clearBtn -> \'" + actEvent.getActionCommand() + "\'");
				startTextField.setText("");
				endTextField.setText("");
				endTextField.setEnabled(false);
				endAMPMCombo.setEnabled(false);
				calcElapseBtn.setEnabled(false);
				startTextField.grabFocus();
			}
		});
		
		FRAME.pack();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FRAME.setVisible(true);
			}
		});
	}

	/**
	 * Given inputTime and ampm, is the input valid?
	 * Valid is true when these conditions are all met;
	 * Input has to be the correct String.length
	 * Input must contain ":" semi-colon
	 * Input hours cannot be > 24
	 * Input mins cannot be > 59
	 * Input must fit "yyyy-MM-dd hh:mm a" format
	 * Input must be parse-able
	 * 
	 * @param String inputTime
	 * @param String ampm
	 * @return boolean valid
	 * @author omgitskuei
	 * @since May 5, 2021
	 */
	private static Boolean validateInput(String inputTime, String ampm) {
		outputDebugMsg("validateInput(time=\'"+inputTime+"\', ampm=\'"+ampm+"\')");
		if(!inputTime.contains(":")) {
			outputDebugMsg("Input missing \':\'");
			return false;
		}
		String hrs;
		String mins;
		try {
			hrs = inputTime.substring(0, 2);
			mins = inputTime.substring(3);
		} catch (StringIndexOutOfBoundsException e) {
			outputDebugMsg("Input has invalid length");
			return false;
		}
		// Catch all H:MM (eg. 1:34, convert to 01:34)
		if(hrs.contains(":")) {
			outputDebugMsg("[hrs] passed needs zero padding; " + hrs + " -> " + "0" + hrs.substring(0, 1));
			hrs = "0" + hrs.substring(0, 1);
			mins = inputTime.substring(2);
		}
		if(Integer.valueOf(hrs)>24) {
			outputDebugMsg("Input has invalid hours");
			return false;
		}
		if(Integer.valueOf(mins)>59) {
			outputDebugMsg("Input has invalid mins");
			return false;
		}
		
		Calendar cal = Calendar.getInstance();
		long millis=System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.ENGLISH);
		String time = String.valueOf(date) + " " +hrs + ":" +mins + " " + ampm;
		try {
			outputDebugMsg("Try parsing \'" + time + "\'");
			cal.setTime(
				sdf.parse(
					String.valueOf(date) + " " +
					hrs + ":" + mins + " " + ampm
				)
			);
		} catch (ParseException e) {
			outputDebugMsg("Parsing " + time + " FAILED");
			return false;
		}
		outputDebugMsg("Parsing " + time + " successful");
		return true;
	}
	
	/**
	 * Retrieve user input and clean data for common dirty data like h:mm instead of hh:mm (eg. 3:45, valid but incorrect format).
	 * Convert strings into Calendar, get long millis, and subtract end and start.
	 * Use modulus operator (%) for the remainders of the difference, to convert millis into Hours and Minutes
	 * 
	 * @param startTime
	 * @param startAMPM
	 * @param endTime
	 * @param endAMPM
	 * @return result
	 * @author omgitskuei
	 * @since Apri 8, 2021
	 */
	private static String calcDifference(String startTime, String startAMPM, String endTime, String endAMPM) {
		outputDebugMsg("calcDifference(startTime=\'"+startTime+"\', startAMPM=\'"+startAMPM+"\', endTime=\'"+endTime+"\', endAMPM=\'"+endAMPM+"\')");
		
		// Get current date
		long millis=System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		outputDebugMsg("Assuming same day [" + String.valueOf(date) + "] for both startTime, endTime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.ENGLISH);
		
		// Convert startTime to Calendar
		Calendar start = Calendar.getInstance();
		String startHour;
		String startMins;
		startHour = startTime.substring(0, 2);
		startMins = startTime.substring(3);
		
		// Catch all H:MM (eg. 1:34, convert to 01:34)
		if(startHour.contains(":")) {
			outputDebugMsg("startHour passed needs zero padding; " + startHour + " -> " + "0" + startHour.substring(0, 1));
			startHour = "0" + startHour.substring(0, 1);
			startMins = startTime.substring(2);
		}
		
			try {
				start.setTime(
					sdf.parse(String.valueOf(date) + " " +startHour +":"+startMins + " " + startAMPM)
				);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
		
		// Convert endTime to Calendar
		Calendar end = Calendar.getInstance();
		
		String endHour;
		String endMins;
		try {
			endHour = endTime.substring(0, 2);
			endMins = endTime.substring(3);
		} catch (StringIndexOutOfBoundsException e) {
			return "Invalid [End time]";
		}
		
		// Catch all H:MM (eg. 1:34, convert to 01:34)
		if(endHour.contains(":")) {
			outputDebugMsg("endHour passed needs zero padding; " + endHour + " -> " + "0" + endHour.substring(0, 1));
			endHour = "0" + endHour.substring(0, 1);
			endMins = endTime.substring(2);
		}
		try {
			outputDebugMsg("Try parsing \'" + String.valueOf(date) + " " +endHour +":"+endMins + " " + endAMPM + "\' for Calendar end");
			end.setTime(
					sdf.parse(String.valueOf(date) + " " +endHour +":"+endMins + " " + endAMPM)
			);
		} catch (ParseException e) {
			outputDebugMsg("PARSE ENDTIME FAILED");
			return "Failed to parse [End time]";
		}
		
		long hrs = TimeUnit.MILLISECONDS.toHours(end.getTimeInMillis() - start.getTimeInMillis()) % 24;
		long min = TimeUnit.MILLISECONDS.toMinutes(end.getTimeInMillis() - start.getTimeInMillis()) % 60;
		
		// validate calculated difference
		// 1) difference cannot be a negative number (this means start time happened AFTER end time)
		if(hrs<0 || min<0) {
			return "[Start time] is after [End time]";
		} else {
			return hrs+" Hours, "+min+" Minutes";
		}
	}
	
	
	/**
	 * Set look and feel of the App to current system's native esthetics. If failed,
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
			outputDebugMsg(
					"An UnsupportedLookAndFeelException error occurred while setting LookAndFeel to current sysL&F.");
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				outputDebugMsg("Failed to apply CrossPlatformLookAndFeel");
				System.exit(1);
			}
		} catch (ClassNotFoundException e) {
			outputDebugMsg("An ClassNotFoundException error occurred while setting LookAndFeel to currSysLaF.");
		} catch (InstantiationException e) {
			outputDebugMsg("An InstantiationException error occurred while setting LookAndFeel to currSysLaF.");
		} catch (IllegalAccessException e) {
			outputDebugMsg("An IllegalAccessException error occurred while setting LookAndFeel to currSysLaF.");
		}
	}

	
	/**
	 * Create GridBagConstraints for various Java swing UI components.
	 * The app's UI layout is noted below.
	 * 
	 * @author omgitskuei
	 * @since Apr 8 2021
	 */
	private void populateGBCs() {
		GridBagConstraints gbc;
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		componentGBCs.put("menuBarGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		componentGBCs.put("startLabelGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 90;
		gbc.weightx = 1;
		gbc.weighty = 1;
		componentGBCs.put("startTextFieldGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		componentGBCs.put("startAMPMComboGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		componentGBCs.put("endLabelGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.ipadx = 90;
		gbc.weightx = 1;
		gbc.weighty = 1;
		componentGBCs.put("endTextFieldGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		componentGBCs.put("endAMPMComboGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		componentGBCs.put("calcElapseBtnGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		componentGBCs.put("clearBtnGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		componentGBCs.put("resultTextArea", gbc);
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
		enLabels.put("startLabel", "Start time:");
		enLabels.put("endLabel", "End time:");
		enLabels.put("calcElapseBtn", "Calculate Time elapsed");
		enLabels.put("clearBtn", "Clear");
		i18nLabels.put("english", enLabels);
		HashMap<String, String> cnLabels = new HashMap<String, String>();
		cnLabels.put("menuBarFile", "檔案");
		cnLabels.put("menuBarFileOpen", "開啟");
		cnLabels.put("menuBarFileSaveAs", "儲存");
		cnLabels.put("menuBarHelp", "説明");
		cnLabels.put("menuBarHelpAbout", "有關於 " + APPNAME);
		cnLabels.put("menuBarHelpContrib", "贊助");
		cnLabels.put("menuBarPrefs", "設定");
		cnLabels.put("menuBarPrefsDebug", "除錯模式");
		cnLabels.put("menuBarPrefsLang", "語言");
		cnLabels.put("menuBarPrefsLangEn", "英文");
		cnLabels.put("menuBarPrefsLangCn", "中文");
		cnLabels.put("startLabel", "開始時間");
		cnLabels.put("endLabel", "結束時間");
		cnLabels.put("calcElapseBtn", "計算");
		cnLabels.put("clearBtn", "清楚");
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
						outputDebugMsg("A IOException error occurred.");
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
				outputDebugMsg("Uncaught component=" + aComponent);
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
	 * recursively calls this method itself on the child to get ITS children.
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
