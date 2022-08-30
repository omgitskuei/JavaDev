package main.notes.dataStructures.collections.map;

import java.util.HashMap;

public class ComparingTwoMaps {

	public static void main(String[] args) {
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("NN", "12,000");
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("NN", "12,000");
		
		System.out.println(map1 == map2);
		// FALSE! compares IDs
		
		System.out.println(map1.equals(map2));
		// true
	}

}
