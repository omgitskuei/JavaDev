package exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SearchEnglishDictionary {
	/**
	 * 
	 * @param filePath
	 * @return result
	 */
    public ArrayList<String> readWordbank(String filePath) {
        File file = new File(filePath); 
        
        ArrayList<String> result = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String word = sc.nextLine();
                result.add(word);
                System.out.println(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        return result;
    }
    
    
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
	
	

	public static void main(String[] args) {
		SearchEnglishDictionary a = new SearchEnglishDictionary();
		String filePath = System.getProperty("user.dir") + "\\englishWordbank.txt";

		// ArrayList<String> result = a.readWordbank(filePath);
		
		

	}

}
