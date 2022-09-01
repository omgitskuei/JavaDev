package main.notes.dataTypes.stringRelated;

public class ReversingStrings {
    public static void main(String[] args) {
    	String aString = "保單號碼[12345]險別[CY]";
		
		// Get MSG, which always appear at the end of the String after the last "]"
		StringBuilder sb = new StringBuilder(aString);
		String temp = sb.reverse().toString();
		
		System.out.println(temp);
    }
    
    private String reverse(String original) {
    	return new StringBuilder(original).reverse().toString();
    }
}
