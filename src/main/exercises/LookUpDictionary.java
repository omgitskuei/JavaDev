package main.exercises;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LookUpDictionary {
	// Local fields
	TreeMap<String, Integer> rankedMostSimilar = new TreeMap<String, Integer>();
	
	// Constructors
	public LookUpDictionary() {
		System.out.println("BEGIN: util.LookUpDictionary()");
	}
	
	// Executable
	public static void main(String[] args) throws Exception {
		LookUpDictionary data = new LookUpDictionary();
		ArrayList<String> dictionary = data.readFile(System.getProperty("user.dir") + "\\englishWordbank.txt");
		
		GetRuntimeInput util1 = new GetRuntimeInput("Input String:");
		String input = util1.returnInput();
		util1.closeReader();
		
		Map<String, String> result = data.define(input, dictionary);
		
		System.out.println(result);
	}
	
	// Methods
	public Map<String, String> define(String word, ArrayList<String> dictionary) {
		Map<String, String> queryResults = new HashMap<String, String>();
		if (dictionary.contains(word)) {
			String vocab = word;
			String definition = word;
			queryResults.put(vocab, definition);
		}
		
		return queryResults;
		
	}
	
	public ArrayList<String> readFile(String filePath) throws IOException {
		System.out.println("  BEGIN: Reading File [" + filePath + "]");
		// Object declaration
		FileReader bytesReader = new FileReader(filePath);
		BufferedReader textReader = new BufferedReader(bytesReader);

		String aLine = "";
		// Data structure declaration
		ArrayList<String> fileContent = new ArrayList<String>();

		// Until there are no more lines, ...
		while (aLine != null) {
			// ... Read a line from the file, ...
			aLine = textReader.readLine();
			// ... and add that line to the ArrayList fileContent.
			fileContent.add(aLine);
		}
		// Because it'UsingSuper a whileLoop, the end of fileContent will be a NULL
		// ... So, the last index value needs to be removed
		fileContent.remove(fileContent.size() - 1);
		System.out.println("      Completed reading File, Total Lines: "+fileContent.size());
		// Close readers
		
		textReader.close();
		bytesReader.close();

		System.out.println("  FINISH: Reading File");
		// Return result
		return fileContent;
	}
}
