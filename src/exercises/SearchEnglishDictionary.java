package exercises;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchEnglishDictionary {
	
	public ArrayList<String> queryWholeWord(String wholeWord, HashMap<String, String> wordBank) {
		ArrayList<String> result = new ArrayList<String>();
		
		if(wordBank.containsKey(wholeWord)) {
			result.add(wordBank.get(wholeWord));
		}
		return result;
	}
	
	public ArrayList<String> queryFuzzy(String a, HashMap<String, String> wordBank, Integer precision) {
		
		ArrayList<String> result = new ArrayList<String>();
		
		
		return result;
	}
	
	public ArrayList<String> readWordbank(String a) {
		
		ArrayList<String> result = new ArrayList<String>();
		return result;
	}

	public static void main(String[] args) {
		SearchEnglishDictionary a = new SearchEnglishDictionary();
		
		String input = "";
		
	}

}
