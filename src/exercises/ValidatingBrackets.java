package exercises;

import java.util.ArrayList;

public class ValidatingBrackets {

	public boolean validateBrackets(String bracketsString) {
		// Remove all non-bracket characters
		bracketsString = bracketsString.replaceAll("[a-zA-Z0-9]", "");
		System.out.println("validate this: " + bracketsString);

		// Test if first and last are opens and closes
		if (!((bracketsString.startsWith("(") || bracketsString.startsWith("{") || bracketsString.startsWith("["))
				&& (bracketsString.endsWith(")") || bracketsString.endsWith("}") || bracketsString.endsWith("]")))) {
			System.out.println("false - first and last aren't valid");
			return false;
		}

		// Test if the string (w only brackets chars) is even
		if (bracketsString.length() % 2 != 0) {
			System.out.println("false - not even");
			return false;
		}

		// Index all open and close brackets of each type ( soft, { curly, [ hard
		ArrayList<Integer> softOpenIndex = new ArrayList<Integer>();
		ArrayList<Integer> softCloseIndex = new ArrayList<Integer>();
		ArrayList<Integer> curlyOpenIndex = new ArrayList<Integer>();
		ArrayList<Integer> curlyCloseIndex = new ArrayList<Integer>();
		ArrayList<Integer> hardOpenIndex = new ArrayList<Integer>();
		ArrayList<Integer> hardCloseIndex = new ArrayList<Integer>();
		ArrayList<Integer> layer = new ArrayList<Integer>();
		int layerCount = 0;
		String currChar = "";
		for (int strIndex = 0; strIndex < bracketsString.length(); strIndex++) {
			currChar = bracketsString.substring(strIndex, strIndex + 1);
			if (currChar.equals("(")) {
				softOpenIndex.add(strIndex);
				layerCount++;
				layer.add(layerCount);
			} else if (currChar.equals(")")) {
				softCloseIndex.add(strIndex);

				layer.add(layerCount);
				layerCount--;
			} else if (currChar.equals("{")) {
				curlyOpenIndex.add(strIndex);
				layerCount++;
				layer.add(layerCount);
			} else if (currChar.equals("}")) {
				curlyCloseIndex.add(strIndex);

				layer.add(layerCount);
				layerCount--;
			} else if (currChar.equals("[")) {
				hardOpenIndex.add(strIndex);
				layerCount++;
				layer.add(layerCount);
			} else if (currChar.equals("]")) {
				hardCloseIndex.add(strIndex);

				layer.add(layerCount);
				layerCount--;
			} else {
				System.out.println("false - invalid char");
				return false;
			}
		}

		System.out.println("softOpen = " + softOpenIndex);
		System.out.println("softClose = " + softCloseIndex);
		System.out.println("curlyOpen = " + curlyOpenIndex);
		System.out.println("curlyClose = " + curlyCloseIndex);
		System.out.println("hardOpen = " + hardOpenIndex);
		System.out.println("hardClose = " + hardCloseIndex);
		System.out.println("layer = " + layer);

		// Check if there's an equal number of opens and closes
		if ((softOpenIndex.size() != softCloseIndex.size()) 
				|| (curlyOpenIndex.size() != curlyCloseIndex.size())
				|| (hardOpenIndex.size() != hardCloseIndex.size())) {
			System.out.println("false - at least one type of brackets are not in pairs");
			return false;
		}

		// Check whether bracket pairs of increasingly inner layers ware closed properly
		Integer prevLayer = 0;
		Integer currLayer = 0;
		for (int strIndex = 0; strIndex < bracketsString.length(); strIndex++) {
			if (strIndex == 0) {
				prevLayer = layer.get(strIndex);
			} else {
				prevLayer = layer.get(strIndex - 1);
				currLayer = layer.get(strIndex);
				if (prevLayer == currLayer) { // corresponds to an Open-Close or a Close-Open pair
					// first is an Open
					if (softOpenIndex.contains(strIndex - 1)) {
						// second is NOT a Close OF THE SAME TYPE
						if (!softCloseIndex.contains(strIndex)) {
							System.out.println("false - plateau layers, Open-Close, but close is wrong type");
							return false;
						} else {
							
						}
					}else if (curlyOpenIndex.contains(strIndex - 1)) {
						// second is NOT a Close OF THE SAME TYPE
						if (!curlyCloseIndex.contains(strIndex)) {
							System.out.println("false - plateau layers, Open-Close, but close is wrong type");
							return false;
						}
					}else if (hardOpenIndex.contains(strIndex - 1)) {
						// second is NOT a Close OF THE SAME TYPE
						if (!hardCloseIndex.contains(strIndex)) {
							System.out.println("false - plateau layers, Open-Close, but close is wrong type");
							return false;
						} 
					}
					// first is a Close, second must be Open (any type) otherwise False
					else {
						if (!(softCloseIndex.contains(strIndex - 1)
								|| curlyCloseIndex.contains(strIndex - 1)
								|| hardCloseIndex.contains(strIndex - 1))
								|| !(softOpenIndex.contains(strIndex)
										|| curlyOpenIndex.contains(strIndex)
										|| hardOpenIndex.contains(strIndex))) {
							System.out.println("false - platue layers");
							return false;
						}
					}
				}
			}
		}
		System.out.println("valid");
		return true;
	}

	/**
	 * A bracket pair object "()", "{}", or "[]" 'Opens' refer to "(", "{", or "{"
	 * 'Closes' refer to ")", "}", or "]" Opens and Closes come in 3 types soft
	 * "()", curly "{}", hard "[]" Opens and Closes have indexes of where they are
	 * in the test string.
	 */
	class bracketPair {
		private String type = "";
		private int openIndex = 0;
		private int closeIndex = 0;
		private int layer = 0;

		// Constructor
		bracketPair(String type, int openIndex, int layer) {
			this.setType(type);
			this.setOpenIndex(openIndex);
			this.setLayer(layer);
		}

		// Getters and Setters
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getOpenIndex() {
			return openIndex;
		}

		public void setOpenIndex(int openIndex) {
			this.openIndex = openIndex;
		}

		public int getCloseIndex() {
			return closeIndex;
		}

		public void setCloseIndex(int closeIndex) {
			this.closeIndex = closeIndex;
		}

		public int getLayer() {
			return layer;
		}

		public void setLayer(int layer) {
			this.layer = layer;
		}
	}

	public static void main(String[] args) {
		ValidatingBrackets test = new ValidatingBrackets();
		// Test cases:
		// 0) False, first and last are not opens or closes
		String testCase0 = "(((((((";
		// 1) False, bracket not closed
		String testCase1 = "{[()]";

		// 2) False, mismatched bracket type
		String testCase2 = "{(]}";
		// 3) False, bracket closed out of order
		String testCase3 = "{()([)]}";
		test.validateBrackets(testCase0);
		System.out.println();
		test.validateBrackets(testCase1);
		System.out.println();
		test.validateBrackets(testCase2);
		System.out.println();
		test.validateBrackets(testCase3);
		System.out.println();
		
		test.validateBrackets("{()([])}");
	}

}
