package main.projects.timeElapse.version2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import main.projects.timeElapse.version2.FileFormatException;

public class TimeElapseV2 {

	// UI Layout:
	// FRAME
	// --> JPanel (mainframeJPanel)
	// -- --> JPanel (menuBarJPanel)
	// -- -- --> JMenuBar (menuBar, menuBarGBC)
	// -- -- -- --> JMenu (menuBarHelp)
	// -- -- -- -- --> JMenuItem (menuBarHelpAbout)
	// -- -- -- -- --> JMenuItem (menuBarHelpContrib)
	// -- -- -- -- -->
	// -- --> JPanel (bodyJPanel)
	// -- -- --> JPanel (startPanel)
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
	// -- -- --> JPanel (endPanel)
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
	// -- -- --> JPanel (resultsPanel)
	// ------------ row 1
	// -- -- -- --> JScrollPane (resultScrollPane)
	// -- -- -- -- --> JTextArea (resultTextArea)
	// -- -- --> JPanel (watermarkPanel)
	// ------------ row 1
	// -- -- -- --> JLabel (watermarkLabel)
	
	// Java Swing Components
	private JFrame mainFrame = new JFrame();
	private JMenuBar menuBar;
	private HashMap<String, JPanel> allJPanels = new HashMap<String, JPanel>();
	private HashMap<String, JLabel> allJLabels = new HashMap<String, JLabel>();
	private HashMap<String, JComboBox<String>> allJComboBoxes = new HashMap<String, JComboBox<String>>();
	private HashMap<String, JButton> allJButtons = new HashMap<String, JButton>();
	private JScrollPane resultScrollPane = new JScrollPane();
	private JTextArea resultTextArea = new JTextArea();
	
	// App Data
	private static Preferences settings = Preferences.userNodeForPackage(main.projects.timeElapse.version2.TimeElapseV2.class);
	private HashMap<String, HashMap<String, String>> i18nLabels = new HashMap<String, HashMap<String, String>>();
	
	private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	private static final String newline = System.getProperty("line.separator");
	
	private static TimeElapseV2 instance;

	private TimeElapseV2() {}

	// Singleton Constructor
	public static synchronized TimeElapseV2 getInstance() {
		if (instance == null) {
			instance = new TimeElapseV2();
		}
		return instance;
	}

	public static void main(String args[]) {
		TimeElapseV2 app = TimeElapseV2.getInstance();
		
		app.setDefaultSettings(settings);
		
		try {
			app.getAllUILangsFromResources(app.i18nLabels);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (FileFormatException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		HashMap<String, String> newLabelSet = app.getNewUILabelTexts(app.i18nLabels, settings);
		
		app.createAllComponents(
			newLabelSet, 
			app.menuBar, 
			app.allJPanels, 
			app.allJLabels, 
			app.allJComboBoxes,
			app.allJButtons,
			app.resultScrollPane, 
			app.resultTextArea);
		
		app.mainFrame.setJMenuBar(app.menuBar);
		
		app.configUI(
			app.mainFrame, 
			app.allJPanels, 
			app.allJLabels, 
			app.allJComboBoxes, 
			app.allJButtons,
			app.resultScrollPane, 
			app.resultTextArea);
		
		app.addActionListeners(
			app.allJComboBoxes,
			app.allJButtons);
		
		app.mainFrame.setTitle(
			settings.get("appName", null) + " " + settings.get("version", null));
		
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
	
	
	
	
	
	
	
	
	private void addActionListeners(HashMap<String, JComboBox<String>> allJComboBoxes) {
		ActionListener a;
		
		a = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				outputDebugMsg(
					"ActionCommand = \'" + e.getActionCommand() + "\', "
					+ "Source = " + e.getSource());
			}
		};
		allJComboBoxes.get("startYear").addActionListener(a);
		allJComboBoxes.get("startMonth").addActionListener(a);
		allJComboBoxes.get("startDay").addActionListener(a);
		allJComboBoxes.get("startHour").addActionListener(a);
		allJComboBoxes.get("startMins").addActionListener(a);
		allJComboBoxes.get("startAMPM").addActionListener(a);
		
		allJComboBoxes.get("endYear").addActionListener(a);
		allJComboBoxes.get("endMonth").addActionListener(a);
		allJComboBoxes.get("endDay").addActionListener(a);
		allJComboBoxes.get("endHour").addActionListener(a);
		allJComboBoxes.get("endMins").addActionListener(a);
		allJComboBoxes.get("endAMPM").addActionListener(a);
	}

	private HashMap<String, String> getNewUILabelTexts(
			HashMap<String, HashMap<String, String>> i18nLabels, Preferences settings) {
		return i18nLabels.get(settings.get("lang", null));
	}

	private void getAllUILangsFromResources(HashMap<String, HashMap<String, String>> i18nLabels) throws FileNotFoundException, FileFormatException {
		ArrayList<String> fileLines = new ArrayList<String>();
		
		URL path = TimeElapseV2.class.getResource("../resources/i18n.txt");
		File file = new File(path.getFile());
		Scanner i18nScanner = new Scanner(file);
		while (i18nScanner.hasNextLine()) {
			fileLines.add(i18nScanner.nextLine());
		}
		i18nScanner.close();
		
		int numOfLangs = 0;
		if(fileLines.contains("languagesKey")) {
			int indexLangsKey = fileLines.indexOf("languagesKey");
			while (!fileLines.get((indexLangsKey+1)+numOfLangs).equals("")){
				++numOfLangs; // if [en, cn], then 2
			}
			if(numOfLangs==0) {
				throw new FileFormatException("i18n.txt is missing languages under languagesKey key");
			}
		} else {
			throw new FileFormatException("i18n.txt is missing languagesKey key");
		}
		for(int indexLang=0; indexLang<numOfLangs; indexLang++) {  // with 2, run twice
			HashMap<String, String> labelSet = new HashMap<String, String>();
			for(int indexLine = 0; indexLine<fileLines.size(); indexLine=indexLine+(2+numOfLangs)) {
				String key = fileLines.get(indexLine);
				String val = fileLines.get(indexLang+indexLine+1);
				
				labelSet.put(key, val);
			}
			i18nLabels.put(fileLines.get(indexLang+1), labelSet);
		}
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
		JMenuBar menuBar = new JMenuBar();
		
		// ...
		
		return menuBar;
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
		if (settings.get("isDebug", "false").equals("true")) {
			System.out.println("[" + TIMESTAMP_FORMAT.format(new Date(System.currentTimeMillis())) + "] " + message);
		}
	}
	
	private void setDefaultSettings(Preferences settings) {
		settings.put("isDebug", "true"); // valid: true, false
		settings.put("lang", "english"); // valid: english, chinese
		settings.put("appName", "TimeElapse");
		settings.put("version", "2021-05");
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
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		gbc.gridwidth = 3;	// make component span multiple cells
		allGBCs.put("endLabel", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		allGBCs.put("endYear", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		allGBCs.put("endYearLabel", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		allGBCs.put("endMonth", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		allGBCs.put("endDay", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		allGBCs.put("endHour", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		allGBCs.put("endMins", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		allGBCs.put("endAMPM", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		gbc.gridwidth = 3;	// make component span multiple cells
		allGBCs.put("startLabel", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		allGBCs.put("startYear", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		allGBCs.put("startMonth", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		allGBCs.put("startDay", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 2;			// row 3 of startPanel
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;		// equally divides the space left and right of the combobox, centering it
		gbc.ipadx = 20;			// makes combobox longer <--->
		gbc.ipady = 5;			// makes combobox slightly taller
		allGBCs.put("startHour", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		allGBCs.put("startMins", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 5;
		allGBCs.put("startAMPM", gbc);
		
		
		gbc = new GridBagConstraints();
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 25;
		gbc.ipadx = 5;
		allGBCs.put("startPanel", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 25;
		gbc.ipadx = 5;
		allGBCs.put("endPanel", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 25;
		gbc.ipadx = 5;
		allGBCs.put("resultsPanel", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.ipady = 25;
		gbc.ipadx = 5;
		allGBCs.put("watermarkPanel", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.ipady = 25;
		gbc.ipadx = 5;
		allGBCs.put("menuBarJPanel", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.ipadx = 25;
		gbc.ipady = 5;
		allGBCs.put("bodyJPanel", gbc);
		
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
		
//		allJPanels.get("mainframeJPanel").add(allJPanels.get("menuBarJPanel"), allGBCs.get("menuBarJPanel"));
		allJPanels.get("mainframeJPanel").add(allJPanels.get("bodyJPanel"), allGBCs.get("bodyJPanel"));
		
		mainFrame.getContentPane().add(allJPanels.get("mainframeJPanel"));
	}

	private void createAllComponents(HashMap<String, String> newLabelSet, JMenuBar menuBar, HashMap<String, JPanel> allJPanels, HashMap<String, JLabel> allJLabels, HashMap<String, JComboBox<String>> allJComboBoxes, JScrollPane resultScrollPane, JTextArea resultTextArea) {
		JPanel panel;
		panel = new JPanel();
		panel.setName("mainframeJPanel");
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.green));
		allJPanels.put("mainframeJPanel", panel);

		panel = new JPanel();
		panel.setName("menuBarJPanel");
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.blue));
		allJPanels.put("menuBarJPanel", panel);

		panel = new JPanel();
		panel.setName("bodyJPanel");
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.blue));
		allJPanels.put("bodyJPanel", panel);

		panel = new JPanel();
		panel.setName("startPanel");
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		allJPanels.put("startPanel", panel);

		panel = new JPanel();
		panel.setName("endPanel");
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		allJPanels.put("endPanel", panel);

		panel = new JPanel();
		panel.setName("resultsPanel");
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		allJPanels.put("resultsPanel", panel);

		panel = new JPanel();
		panel.setName("watermarkPanel");
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		allJPanels.put("watermarkPanel", panel);

		JLabel label;
		label = new JLabel();
		label.setName("startLabel");
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.red));
		label.setText(newLabelSet.get("startLabel"));
		allJLabels.put("startLabel", label);
		
		label = new JLabel();
		label.setName("endLabel");
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.red));
		label.setText(newLabelSet.get("endLabel"));
		allJLabels.put("endLabel", label);

		label = new JLabel();
		label.setName("watermarkLabel");
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.red));
		label.setText(newLabelSet.get("watermarkLabel"));
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
		
		List<String> hoursList = IntStream.rangeClosed(1, 12).mapToObj(Integer::toString).collect(Collectors.toList());
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
		
		resultTextArea = new JTextArea();
		resultTextArea.setEditable(false);
		
		resultScrollPane = new JScrollPane(resultTextArea);
		resultScrollPane.setPreferredSize(new Dimension(300, 100));
		
		menuBar = createMenuBar();
	}
}
