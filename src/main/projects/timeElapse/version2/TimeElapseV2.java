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
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

public class TimeElapseV2 {

	// UI Layout:
	// FRAME
	// --> JPanel (mainframeJPanel, BorderLayout)
	// -- --> JPanel (menuBarJPanel, GridBagLayout)
	// -- -- --> JMenuBar (menuBar)
	// -- -- -- --> JMenu (menuBarHelp)
	// -- -- -- -- --> JMenuItem (menuBarHelpAbout)
	// -- -- -- -- --> JMenuItem (menuBarHelpContrib)
	// -- -- -- -- -->
	// -- --> JPanel (bodyJPanel, BorderLayout)
	// -- -- --> JPanel (startPanel, GridBagLayout)
	// ------------ row 1
	// -- -- -- --> JLabel (startLabel)
	// ------------ row 2
	// -- -- -- --> ComboBox (startYear)
	// -- -- -- --> ComboBox (startMonth)
	// -- -- -- --> ComboBox (startDay)
	// ------------ row 3
	// -- -- -- --> ComboBox (startHour)
	// -- -- -- --> ComboBox (startMins)
	// -- -- -- --> ComboBox (startAMPM)
	// -- -- --> JPanel (endPanel, GridBagLayout)
	// ------------ row 1
	// -- -- -- --> JLabel (endLabel)
	// ------------ row 2
	// -- -- -- --> ComboBox (endYear)
	// -- -- -- --> ComboBox (endMonth)
	// -- -- -- --> ComboBox (endDay)
	// ------------ row 3
	// -- -- -- --> ComboBox (endHour)
	// -- -- -- --> ComboBox (endMins)
	// -- -- -- --> ComboBox (endAMPM)
	// -- -- --> JPanel (resultsPanel, GridBagLayout)
	// ------------ row 1
	// -- -- -- --> JScrollPane (resultScrollPane)
	// -- -- -- -- --> JTextArea (resultTextArea)
	// -- -- --> JPanel (watermarkPanel, GridBagLayout)
	// ------------ row 1
	// -- -- -- --> JLabel (watermarkLabel)
	
	private JFrame mainFrame = new JFrame();
	private JMenuBar menuBar;
	private HashMap<String, JPanel> allJPanels = new HashMap<String, JPanel>();
	private HashMap<String, JLabel> allJLabels = new HashMap<String, JLabel>();
	private HashMap<String, JComboBox<String>> allJComboBoxes = new HashMap<String, JComboBox<String>>();
	private JScrollPane resultScrollPane = new JScrollPane();
	private JTextArea resultTextArea = new JTextArea();

	private static TimeElapseV2 instance;

	private TimeElapseV2() {
	}

	public static synchronized TimeElapseV2 getInstance() {
		if (instance == null) {
			instance = new TimeElapseV2();
		}
		return instance;
	}

	public static void main(String args[]) {
		TimeElapseV2 app = TimeElapseV2.getInstance();

		app.createAllComponents();
		app.configUI(
			app.mainFrame, 
			app.allJPanels, 
			app.allJLabels, 
			app.allJComboBoxes, 
			app.resultScrollPane, 
			app.resultTextArea);
		
		
		app.mainFrame.setTitle("");
		app.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.mainFrame.setAlwaysOnTop(true);
		
		app.mainFrame.pack();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				app.mainFrame.setVisible(true);
			}
		});
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
		// ...
		return menuBar;
	}

	private void configUI(
			JFrame mainFrame,
			HashMap<String, JPanel> allJPanels, 
			HashMap<String, JLabel> allJLabels,
			HashMap<String, JComboBox<String>> allJComboBoxes, 
			JScrollPane resultScrollPane,
			JTextArea resultTextArea) {
		
		HashMap<String, GridBagConstraints> allGBCs = new HashMap<String, GridBagConstraints>();
		
		GridBagConstraints gbc;
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		allGBCs.put("endLabel", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("endYear", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("endMonth", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("endDay", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("endHour", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("endMins", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("endAMPM", gbc);
		
		
		gbc = new GridBagConstraints();
		allGBCs.put("startLabel", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("startYear", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("startMonth", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("startDay", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("startHour", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("startMins", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("startAMPM", gbc);
		
		
		gbc = new GridBagConstraints();
		allGBCs.put("startPanel", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("endPanel", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("resultsPanel", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("watermarkPanel", gbc);
		
		gbc = new GridBagConstraints();
		allGBCs.put("menuBarJPanel", gbc);
		
		
		allJPanels.get("watermarkPanel").add(allJLabels.get("watermarkLabel"));
		
		resultScrollPane.add(resultTextArea);
		allJPanels.get("resultsPanel").add(resultScrollPane);
		
		allJPanels.get("endPanel").add(allJLabels.get("endLabel"), allGBCs.get("endLabel"));
		allJPanels.get("endPanel").add(allJComboBoxes.get("endYear"), allGBCs.get("endYear"));
		allJPanels.get("endPanel").add(allJComboBoxes.get("endMonth"), allGBCs.get("endMonth"));
		allJPanels.get("endPanel").add(allJComboBoxes.get("endDay"), allGBCs.get("endDay"));
		allJPanels.get("endPanel").add(allJComboBoxes.get("endHour"), allGBCs.get("endHour"));
		allJPanels.get("endPanel").add(allJComboBoxes.get("endMins"), allGBCs.get("endMins"));
		allJPanels.get("endPanel").add(allJComboBoxes.get("endAMPM"), allGBCs.get("endAMPM"));
		
		allJPanels.get("startPanel").add(allJLabels.get("startLabel"), allGBCs.get("startLabel"));
		allJPanels.get("startPanel").add(allJComboBoxes.get("startYear"), allGBCs.get("startYear"));
		allJPanels.get("startPanel").add(allJComboBoxes.get("startMonth"), allGBCs.get("startMonth"));
		allJPanels.get("startPanel").add(allJComboBoxes.get("startDay"), allGBCs.get("startDay"));
		allJPanels.get("startPanel").add(allJComboBoxes.get("startHour"), allGBCs.get("startHour"));
		allJPanels.get("startPanel").add(allJComboBoxes.get("startMins"), allGBCs.get("startMins"));
		allJPanels.get("startPanel").add(allJComboBoxes.get("startAMPM"), allGBCs.get("startAMPM"));
		
		allJPanels.get("bodyJPanel").add(allJPanels.get("startPanel"), allGBCs.get("startPanel"));
		allJPanels.get("bodyJPanel").add(allJPanels.get("endPanel"), allGBCs.get("endPanel"));
		allJPanels.get("bodyJPanel").add(allJPanels.get("resultsPanel"), allGBCs.get("resultsPanel"));
		allJPanels.get("bodyJPanel").add(allJPanels.get("watermarkPanel"), allGBCs.get("watermarkPanel"));
		
		allJPanels.get("mainframeJPanel").add(allJPanels.get("menuBarJPanel"), allGBCs.get("menuBarJPanel"));
		allJPanels.get("mainframeJPanel").add(allJPanels.get("bodyJPanel"));
		
		mainFrame.getContentPane().add(allJPanels.get("mainframeJPanel"));
	}

	private void createAllComponents() {
		JPanel panel;
		panel = new JPanel();
		panel.setName("mainframeJPanel");
		panel.setLayout(new GridBagLayout());
		allJPanels.put("mainframeJPanel", panel);

		panel = new JPanel();
		panel.setName("menuBarJPanel");
		panel.setLayout(new GridBagLayout());
		allJPanels.put("menuBarJPanel", panel);

		panel = new JPanel();
		panel.setName("bodyJPanel");
		panel.setLayout(new GridBagLayout());
		allJPanels.put("bodyJPanel", panel);

		panel = new JPanel();
		panel.setName("startPanel");
		panel.setLayout(new GridBagLayout());
		allJPanels.put("startPanel", panel);

		panel = new JPanel();
		panel.setName("endPanel");
		panel.setLayout(new GridBagLayout());
		allJPanels.put("endPanel", panel);

		panel = new JPanel();
		panel.setName("resultsPanel");
		panel.setLayout(new GridBagLayout());
		allJPanels.put("resultsPanel", panel);

		panel = new JPanel();
		panel.setName("watermarkPanel");
		panel.setLayout(new GridBagLayout());
		allJPanels.put("watermarkPanel", panel);

		JLabel label;
		label = new JLabel();
		label.setName("startLabel");
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		allJLabels.put("startLabel", label);

		label = new JLabel();
		label.setName("endLabel");
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		allJLabels.put("endLabel", label);

		label = new JLabel();
		label.setName("watermarkLabel");
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		allJLabels.put("watermarkLabel", label);

		
		JComboBox<String> dropdown;
		List<String> yearsList = IntStream.rangeClosed(1971, 2500).mapToObj(Integer::toString).collect(Collectors.toList());
		String[] yearsOptions = new String[yearsList.size()];
		yearsOptions = yearsList.toArray(yearsOptions);
		dropdown = new JComboBox<String>(yearsOptions);
		dropdown.setSelectedIndex(0);
		dropdown.setName("startYear");
		allJComboBoxes.put("startYear", dropdown);

		dropdown = new JComboBox<String>(yearsOptions);
		dropdown.setSelectedIndex(0);
		dropdown.setName("endYear");
		allJComboBoxes.put("endYear", dropdown);
		
		List<String> monthsList = IntStream.rangeClosed(1, 12).mapToObj(Integer::toString).collect(Collectors.toList());
		for (int i = 0; i < monthsList.size(); i++) {
			if (monthsList.get(i).length() == 1) {
				monthsList.set(i, "0" + monthsList.get(i));
			}
		}
		System.err.println(monthsList);
		String[] monthsOptions = new String[monthsList.size()];
		monthsOptions = monthsList.toArray(monthsOptions);
		dropdown = new JComboBox<String>(monthsOptions);
		dropdown.setSelectedIndex(0);
		dropdown.setName("startMonth");
		allJComboBoxes.put("startMonth", dropdown);

		dropdown = new JComboBox<String>(monthsOptions);
		dropdown.setSelectedIndex(0);
		dropdown.setName("endMonth");
		allJComboBoxes.put("endMonth", dropdown);
		
		List<String> daysList = IntStream.rangeClosed(1, 31).mapToObj(Integer::toString).collect(Collectors.toList());
		for (int i = 0; i < daysList.size(); i++) {
			if (daysList.get(i).length() == 1) {
				daysList.set(i, "0" + daysList.get(i));
			}
		}
		String[] daysOptions = new String[daysList.size()];
		daysOptions = daysList.toArray(daysOptions);
		dropdown = new JComboBox<String>(daysOptions);
		dropdown.setSelectedIndex(0);
		dropdown.setName("startDay");
		allJComboBoxes.put("startDay", dropdown);

		dropdown = new JComboBox<String>(daysOptions);
		dropdown.setSelectedIndex(0);
		dropdown.setName("endDay");
		allJComboBoxes.put("endDay", dropdown);
		
		List<String> hoursList = IntStream.rangeClosed(0, 23).mapToObj(Integer::toString).collect(Collectors.toList());
		for (int i = 0; i < hoursList.size(); i++) {
			if (hoursList.get(i).length() == 1) {
				hoursList.set(i, "0" + hoursList.get(i));
			}
		}
		String[] hoursOptions = new String[hoursList.size()];
		hoursOptions = hoursList.toArray(hoursOptions);
		dropdown = new JComboBox<String>(hoursOptions);
		dropdown.setSelectedIndex(0);
		dropdown.setName("startHour");
		allJComboBoxes.put("startHour", dropdown);
		
		dropdown = new JComboBox<String>(hoursOptions);
		dropdown.setSelectedIndex(0);
		dropdown.setName("endHour");
		allJComboBoxes.put("endHour", dropdown);
		
		List<String> minsList = IntStream.rangeClosed(0, 59).mapToObj(Integer::toString).collect(Collectors.toList());
		for (int i = 0; i < minsList.size(); i++) {
			if (minsList.get(i).length() == 1) {
				minsList.set(i, "0" + minsList.get(i));
			}
		}
		String[] minsOptions = new String[minsList.size()];
		minsOptions = minsList.toArray(minsOptions);
		dropdown = new JComboBox<String>(minsOptions);
		dropdown.setSelectedIndex(0);
		dropdown.setName("startMins");
		allJComboBoxes.put("startMins", dropdown);

		dropdown = new JComboBox<String>(minsOptions);
		dropdown.setSelectedIndex(0);
		dropdown.setName("endMins");
		allJComboBoxes.put("endMins", dropdown);
		
		String[] ampmOptions = { "AM", "PM" };
		dropdown = new JComboBox<String>(ampmOptions);
		dropdown.setSelectedIndex(0);
		dropdown.setName("startAMPM");
		allJComboBoxes.put("startAMPM", dropdown);

		dropdown = new JComboBox<String>(ampmOptions);
		dropdown.setSelectedIndex(0);
		dropdown.setName("endAMPM");
		allJComboBoxes.put("endAMPM", dropdown);
		
		// FRAME
		// -- -- --> JMenuBar (menuBar)

		resultTextArea = new JTextArea();
		resultTextArea.setEditable(false);
		
		resultScrollPane = new JScrollPane(resultTextArea);
		resultScrollPane.setPreferredSize(new Dimension(300, 100));
		
		menuBar = createMenuBar();
	}
}
