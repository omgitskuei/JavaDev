package main.projects.portConnector.oopVersion;

import java.util.ArrayList;

public class Config {
	// Attributes
	private String name = "";
	private ArrayList<String> options = new ArrayList<String>();
	private String selectedOption = "";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	
	@Override
	public String toString() {
		return "Config[name=" + name + ","
			+ "options=" + options + ","
			+ "selectedOption=" + selectedOption + "]";
	}
}
