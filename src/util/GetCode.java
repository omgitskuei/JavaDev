package util;

import java.util.ArrayList;

public class GetCode {
	// Local variables
	private int length;
	private boolean includeNums = false;
	private boolean includeLowLetters = false;
	private boolean includeUpperLetters = false;
	private boolean includeSpecialCharacters = false;
	ArrayList<String> genBank = new ArrayList<String>();
	String code = "";

	// Constructors
	public GetCode() {
		System.out.println("BEGIN: util.GetCode()");
	}

	public GetCode(int length, boolean includeNums, boolean includeLowLetters, boolean includeUpperLetters,
			boolean includeSpecialCharacters) {
		System.out.println("BEGIN: util.GetCode(int, boolean, boolean, boolean)");
		this.length = length;
		this.includeNums = includeNums;
		this.includeLowLetters = includeLowLetters;
		this.includeUpperLetters = includeUpperLetters;
		this.includeSpecialCharacters = includeSpecialCharacters;
	}

	public GetCode(String length, boolean includeNums, boolean includeLowLetters, boolean includeUpperLetters,
			boolean includeSpecialCharacters) {
		System.out.println("BEGIN: util.GetCode(int, boolean, boolean, boolean)");
		int lengthInt = Integer.parseInt(length);
		this.length = lengthInt;
		this.includeNums = includeNums;
		this.includeLowLetters = includeLowLetters;
		this.includeUpperLetters = includeUpperLetters;
		this.includeSpecialCharacters = includeSpecialCharacters;
	}

	// Executable
	public static void main(String[] args) {
//			GetRuntimeInput console = new GetRuntimeInput();
//			String input = console.getConsoleInputBR("Input length:");
//			int length = Integer.parseInt(input);

		GetCode gen = new GetCode(50, true, true, true, true);

		// Note: the math.Random() formula;
		// int randomNum = (int) ((Math.random() * (max - min)) + min);
		// ... where range = max-min

		gen.generateCode();
		String result = gen.returnCode();

		System.out.println("result: " + result);
	}

	// Methods
	private void appendNums() {
		genBank.add("0");
		genBank.add("1");
		genBank.add("2");
		genBank.add("3");
		genBank.add("4");
		genBank.add("5");
		genBank.add("6");
		genBank.add("7");
		genBank.add("8");
		genBank.add("9");
	}

	private void appendLowerLetters() {
		genBank.add("a");
		genBank.add("b");
		genBank.add("c");
		genBank.add("d");
		genBank.add("e");
		genBank.add("f");
		genBank.add("g");
		genBank.add("h");
		genBank.add("i");
		genBank.add("j");
		genBank.add("k");
		genBank.add("l");
		genBank.add("m");
		genBank.add("n");
		genBank.add("o");
		genBank.add("p");
		genBank.add("q");
		genBank.add("r");
		genBank.add("s");
		genBank.add("t");
		genBank.add("u");
		genBank.add("v");
		genBank.add("x");
		genBank.add("y");
		genBank.add("z");
	}

	private void appendUpperLetters() {
		genBank.add("A");
		genBank.add("B");
		genBank.add("C");
		genBank.add("D");
		genBank.add("E");
		genBank.add("F");
		genBank.add("G");
		genBank.add("H");
		genBank.add("I");
		genBank.add("J");
		genBank.add("K");
		genBank.add("L");
		genBank.add("M");
		genBank.add("N");
		genBank.add("O");
		genBank.add("P");
		genBank.add("Q");
		genBank.add("R");
		genBank.add("S");
		genBank.add("T");
		genBank.add("U");
		genBank.add("V");
		genBank.add("X");
		genBank.add("Y");
		genBank.add("Z");
	}

	private void appendSpecChars() {
		genBank.add("!");
		genBank.add("@");
		genBank.add("#");
		genBank.add("$");
		genBank.add("%");
		genBank.add("^");
		genBank.add("&");
		genBank.add("*");
		genBank.add("(");
		genBank.add(")");
		genBank.add("?");
	}

	public void generateCode() {
		if (this.includeNums == true) {
			appendNums();
		}
		if (this.includeLowLetters == true) {
			appendLowerLetters();
		}
		if (this.includeUpperLetters == true) {
			appendUpperLetters();
		}
		if (this.includeSpecialCharacters == true) {
			appendSpecChars();
		}
		// Show bank
		System.out.println("Code Generator Bank: " + genBank);

		String newest = "";
		for (int index = 0; index < this.length; index++) {
			int random = (int) ((Math.random() * ((genBank.size() - 1) - 0)) + 0);
			newest = genBank.get(random);
			// Show new appended random character
			// System.out.println(newest);

			this.code = this.code + newest;
			// Show code so far
			// System.out.println(this.code);
		}
		System.out.println("");
		System.out.println("Generated code: " + this.code);
	}

	public String returnCode() {
		return code;
	}

	// Getter / Setter Methods
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isIncludeNums() {
		return includeNums;
	}

	public void setIncludeNums(boolean includeNums) {
		this.includeNums = includeNums;
	}

	public boolean isIncludeLowLetters() {
		return includeLowLetters;
	}

	public void setIncludeLowLetters(boolean includeLetters) {
		this.includeLowLetters = includeLetters;
	}

	public boolean isIncludeUpperLetters() {
		return includeUpperLetters;
	}

	public void setIncludeUpperLetters(boolean includeUpperLetters) {
		this.includeUpperLetters = includeUpperLetters;
	}

	public boolean isIncludeSpecialCharacters() {
		return includeSpecialCharacters;
	}

	public void setIncludeSpecialCharacters(boolean includeSpecialCharacters) {
		this.includeSpecialCharacters = includeSpecialCharacters;
	}
}
