package main.java.notes.dataTypes.string;

public class UsingStringFormat {

	
	
	
	public static void main(String[] args) {
		String a = "22w";
		String b = "fd3";
		String c = "sdasd333";
		String d = "wowDoge";
		String s = String.format("%s (%s - %s - %s)"
				,a
				,b
				,c
				,d);
		System.out.println(s);
	}

}
