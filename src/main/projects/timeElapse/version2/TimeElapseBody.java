package main.projects.timeElapse.version2;

public class TimeElapseBody {

	// UI Layout:
	// FRAME
	// --> JPanel (frameJPanel, BorderLayout)
	// -- --> JPanel (menuBarJPanel, GridBagLayout)
	// -- -- --> JMenuBar (menuBar, menuBarGBC)
	// -- -- -- --> JMenu (menuBarHelp)
	// -- -- -- -- --> JMenuItem (menuBarHelpAbout)
	// -- -- -- -- --> JMenuItem (menuBarHelpContrib)
	// -- -- -- -- -->
	// -- --> JPanel (bodyJPanel, BorderLayout)
	// -- -- --> JPanel (startPanel, GridBagLayout)
	// ------------ row 1
	// -- -- -- --> JLabel (startLabel, startLabelGBC)
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
}
