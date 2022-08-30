package main.notes.dataStructures.collections.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DeclaringAFinalMapFieldWithValues {

	private static final Map<String, String> RCPTKindMap;
	static {
		Map<String, String> aMap = new HashMap<String, String>();
		aMap.put("1", "Intensive Care");
		aMap.put("2", "Medication");
		aMap.put("3", "Doctor Consultation");
		aMap.put("4", "Ambulance");
		RCPTKindMap = Collections.unmodifiableMap(aMap);
	}

	public static void main(String[] args) {
		System.out.println(RCPTKindMap);
		// Output: {1=Intensive Care, 2=Medication, 3=Doctor Consultation, 4=Ambulance}
	}

}
