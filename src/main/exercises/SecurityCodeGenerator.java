package main.exercises;

import java.util.ArrayList;

public class SecurityCodeGenerator {
	// Local variables
	private int length;
	private boolean includeNums = false;
	private boolean includeLowLetters = false;
	private boolean includeUpperLetters = false;
	private boolean includeSpecialCharacters = false;
	private ArrayList<String> genBank = new ArrayList<String>();
	private String code = "";

	// Constructors
	public SecurityCodeGenerator(Object length, boolean includeNums, boolean includeLowLetters, boolean includeUpperLetters,
			boolean includeSpecialCharacters) throws Exception {
		System.out.println("BEGIN: util.GetCode(Object, boolean, boolean, boolean)");
		// Check data type for length
		if (length instanceof java.lang.String) {
			setLength(Integer.parseInt((String) length));
		} else if (length instanceof java.lang.Integer) {
			setLength((int) length);
		} else {
			throw new SecurityCodeLengthTypeError();
		}
		setGeneratorParams(includeNums, includeLowLetters, includeUpperLetters, includeSpecialCharacters);
	}
	
	

	// Executable
	public static void main(String[] args) {
//		GetRuntimeInput getInput = new GetRuntimeInput("Enter code length");
//		String length = getInput.returnInput();
//		getInput.closeReader();
		
		SecurityCodeGenerator instance;
		try {
			instance = new SecurityCodeGenerator(14, true, true, true, true); //(length, nums, low, up, spec)
			instance.appendCode();			// "" + [new code]
			instance.generateCode();		// [new code]
			instance.appendCode();			// [prev code] + [new code]
			instance.generateCode();		// [new code]
			System.out.println("result: " + instance.returnCode());
			
			instance.setGeneratorParams(false, false, true, false);			// upper cases only
			instance.setLength(20);
			instance.getLength();
			instance.generateCode();		// [new code]
			
			instance.setGeneratorParams(true, false, false, false);			// nums only
			instance.appendCode();			// [prev code] + [new code]
			
			instance.setGeneratorParams(false, false, false, true);			// spec chars only
			instance.appendCode();			// [prev code] + [prev code] + [new code]
			System.out.println("result: " + instance.returnCode());
			
			instance = new SecurityCodeGenerator(13.2, false, false, true, false);  // throws error
			instance = new SecurityCodeGenerator("asd", false, false, true, false);  // throws error
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		// Note: the math.Random() formula;
		// int randomNum = (int) ((Math.random() * (max - min)) + min);
		// ... where range = max-min

		
	}

	// Methods

	public void appendCode() {
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
		System.out.println("Generated code: " + this.code);
	}
	
	public void generateCode() {
		code="";
		appendCode();
	}

	public String returnCode() {
		return code;
	}

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

	
	// Getter / Setter Methods
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setGeneratorParams(boolean includeNums, boolean includeLowLetters, boolean includeUpperLetters, boolean includeSpecialCharacters) {
		this.includeNums = includeNums;
		this.includeLowLetters = includeLowLetters;
		this.includeUpperLetters = includeUpperLetters;
		this.includeSpecialCharacters = includeSpecialCharacters;
		genBank.clear();
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
		System.out.println("Generator Bank: " + genBank);
	}
}
