package util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadWriteTxt {
	// Local fields
	private String filePath;
	private boolean appendToFile = true; // TRUE for append, FALSE for overwriting the Entire file
	FileWriter bytesWriter;
	PrintWriter textWriter;
	FileReader bytesReader;
	BufferedReader textReader;

	// Constructors
	// -- Empty
	public ReadWriteTxt() {
		System.out.println("BEGIN: util.ReadWriteTxt()");
	}

	// -- Specify file path upon initialization
	public ReadWriteTxt(String filePath) {		// Constructor injection
		System.out.println("BEGIN: util.ReadWriteTxt(String)");
		this.filePath = filePath;				// Save the reference to the passed-in filepath inside this client
	}

	// -- Pass true to add more lines to file
	public ReadWriteTxt(String filePath, boolean appendToFile) {
		System.out.println("BEGIN: util.ReadWriteTxt(String, boolean)");
		this.filePath = filePath;
		this.appendToFile = appendToFile; // true for append, false for override
	}

	
	
	
	// -- Executable
	public static void main(String args[]) {

		ReadWriteTxt data = new ReadWriteTxt("D:\\log.txt"); // Appends new contents to end of file contents
		//data.filePath = "D:\\log.txt";
		//data.appendToFile = false;
		
		
		try {
			data.writeFile("This is wOAH");
			data.writeFile("This is amazing");
			ArrayList<String> s = data.readFile(data.filePath);
			System.out.println(s);
			
		} catch (IOException e) {
			System.out.println("ERROR: IOException thrown.");
		}
	}

	
	
	
	// Methods
	// -- writeFile appends
	public void writeFile(String newText) throws IOException {
		System.out.println("BEGIN: Writing File ["+filePath+"]");
		// Double-check if field variables passed
		// System.out.println("Append: " + appendToFile);
		
		// New FileWriter - Bytes, New PrintWriter - Text
		openWriters();
		// Format, and print text
		textWriter.printf("%s" + "%n", newText); // "%n" means a newline, "%s" means a string of any length
		System.out.println("Wrote: "+newText);
		// Close writers
		closeWriters();
		System.out.println("FINISH: Writing File");
	}

	public ArrayList<String> readFile(String filePath) throws IOException {
		System.out.println("BEGIN: Reading File ["+ filePath+"]");
		// Object declaration
		openReaders();
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
		// Because it's a whileLoop, the end of fileContent will be a NULL
		// ... So, the last index value needs to be removed
		fileContent.remove(fileContent.size()-1);
		
		// Close readers
		closeReaders();
		System.out.println("FINISH: Reading File");
		// Return result
		return fileContent;
	}

	public boolean openWriters() {
		try {
			bytesWriter = new FileWriter(filePath, appendToFile);
			textWriter = new PrintWriter(bytesWriter);
			System.out.println("Opening Writers SUCCEEDED");
			return true;
		} catch (Exception e) {
			System.out.println("Opening Writers FAILED");
			return false;
		}
	}
	
	public boolean openReaders() {
		try {
			bytesReader = new FileReader(filePath);
			textReader = new BufferedReader(bytesReader);
			System.out.println("Opening Readers SUCCEEDED");
			return true;
		} catch (Exception e) {
			System.out.println("Opening Writers FAILED");
			return false;
		}
	}
	
	public boolean closeWriters() {
		try {
			textWriter.close();
			bytesWriter.close();
			System.out.println("Closing Writers SUCCEEDED");
			return true;
		} catch (Exception e) {
			System.out.println("Closing Writers FAILED");
			return false;
		}
	}
	
	public boolean closeReaders() {
		try {
			textReader.close();
			bytesReader.close();
			System.out.println("Closing Readers SUCCEEDED");
			return true;
		} catch (Exception e) {
			System.out.println("Closing Readers FAILED");
			return false;
		}
	}
}
