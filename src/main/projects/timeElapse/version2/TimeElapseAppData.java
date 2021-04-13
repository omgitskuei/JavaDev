package main.projects.timeElapse.version2;

import java.awt.GridBagConstraints;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TimeElapseAppData {
	
	private String appName = "TimeElapse";
	private String version = "2021-05";
	private HashMap<String, HashMap<String, String>> i18nLabels = new HashMap<String, HashMap<String, String>>();
	private HashMap<String, GridBagConstraints> allGBCs = new HashMap<String, GridBagConstraints>();
	
	// Constructor
	TimeElapseAppData() throws FileNotFoundException, FileFormatException {
		/*
		 *  i18nLabels
		 */
		ArrayList<String> fileLines = new ArrayList<String>();
		
		URL path = TimeElapseAppData.class.getResource("../resources/i18n.txt");
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
		
		/*
		 * allGBCs
		 */
		GridBagConstraints gbc;
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		allGBCs.put("menuBarGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		allGBCs.put("startLabelGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 90;
		gbc.weightx = 1;
		gbc.weighty = 1;
		allGBCs.put("startTextFieldGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		allGBCs.put("startAMPMComboGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		allGBCs.put("endLabelGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.ipadx = 90;
		gbc.weightx = 1;
		gbc.weighty = 1;
		allGBCs.put("endTextFieldGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		allGBCs.put("endAMPMComboGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		allGBCs.put("calcElapseBtnGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		allGBCs.put("clearBtnGBC", gbc);
		
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		allGBCs.put("resultTextArea", gbc);
		
		/*
		 * 
		 */
	}
	
	public String getVersion() {
		return version;
	}
	
	public String getAppName() {
		return appName;
	}

	public HashMap<String, HashMap<String, String>> getI18nLabels() {
		return i18nLabels;
	}

	public HashMap<String, GridBagConstraints> getAllGBCs() {
		return allGBCs;
	}
}
