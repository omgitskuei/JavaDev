package util;

public class Palindromes {

	public boolean isPalindrome(String text) {
		// Compare the first and last characters and repeat inward until center
		text = text.replaceAll("\\s+", "").toLowerCase();
		String frontPointer = "";
		String backPointer = "";
		for (int index = 0; index < text.length() / 2; index++) {
			frontPointer = text.substring(index, index + 1);
			backPointer = text.substring(text.length() - index - 1, text.length() - index);
			System.out.println(frontPointer + " compared to " + backPointer);
			if (!frontPointer.equals(backPointer)) {
				return false;
			}
		}
		return true;
	}

	public boolean isPalindrome1(String text) {
		// Flip the text backwards and then compare
		text = text.replaceAll("\\s+", "").toLowerCase();
		String backwards = "";
		for (int index = 0; index < text.length(); index++) {
			backwards = backwards + text.substring(text.length()-index-1, text.length()-index);
		}
		System.out.println(text + " compared to " + backwards);
		if (text.equals(backwards)) {
			return true;
		}
		return false;
	}

	public boolean isPalindromeUsingStringBuilder(String text) {
		// use java.lang.StringBuilder.reverse()
		text = text.replaceAll("\\s+", "").toLowerCase();
		StringBuilder plain = new StringBuilder(text);
		StringBuilder reverse = plain.reverse();
		return (reverse.toString()).equals(text);
	}

	public boolean isPalindromeUsingStringBuffer(String text) {
		text = text.replaceAll("\\s+", "").toLowerCase();
		StringBuffer plain = new StringBuffer(text);
		StringBuffer reverse = plain.reverse();
		return (reverse.toString()).equals(text);
	}

	public static void main(String[] args) {
		Palindromes p = new Palindromes();
		System.out.println(p.isPalindrome("Racecar"));
		System.out.println(p.isPalindrome1("Racecar"));
		System.out.println(p.isPalindromeUsingStringBuffer("Racecar"));
		System.out.println(p.isPalindromeUsingStringBuilder("Racecar"));
	}

}
