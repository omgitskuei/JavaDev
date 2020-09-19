package exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvaluateArithmetic {
	
	public String eval(String input) {
		// Check for case where first number is a negative
		if(input.substring(0,1).equals("-")) {
			input = "0" + input;
		}
		
		// Split input nums and symbols into separate arraylists
		// nums
		String inputDelimitedBySymbols[] = input.split("[+-/*/]");
		List<String> inputNums = new ArrayList<String>();
		inputNums.addAll(Arrays.asList(inputDelimitedBySymbols));
		// symbols
		List<String> inputSymbols = new ArrayList<String>();
		String inputDelimitedByNums[] = input.split("[0-9]");
		inputSymbols.addAll(Arrays.asList(inputDelimitedByNums));
		for (int i=0; i<inputSymbols.size(); i++) {
			if(inputSymbols.get(i).equals("")) {
				inputSymbols.remove(new String(""));
				i--;
			}
		}
		System.out.println("After Split:			" + inputNums + " and " + inputSymbols);
		
		// Simplify multiplications and divisions from left to right
		Integer result = 0, x = 0, y = 0;
		String currSymbol = "";
		if(inputSymbols.contains("*") || inputSymbols.contains("/")) {
			for (int i = 0; i < inputSymbols.size(); i++) {
				currSymbol = inputSymbols.get(i);
				x = Integer.valueOf(inputNums.get(i));
				y = Integer.valueOf(inputNums.get(i+1));
				if (currSymbol.equals("*")) {
					result = x*y;
					inputNums.set(i, result.toString());
					inputNums.remove(i+1);
					inputSymbols.remove(i);
					i--;
				} else if (currSymbol.equals("/")) {
					result = x/y;
					inputNums.set(i, result.toString());
					inputNums.remove(i+1);
					inputSymbols.remove(i);
					i--;
				}
			}
		}
		System.out.println("After Multiply/Divide: 	" + inputNums + " and " + inputSymbols);
		
		// Simplify additions and subtractions from left to right
		if(inputSymbols.contains("+") || inputSymbols.contains("-")) {
			for (int i = 0; i < inputSymbols.size(); i++) {
				currSymbol = inputSymbols.get(i);
				x = Integer.valueOf(inputNums.get(i));
				y = Integer.valueOf(inputNums.get(i+1));
				if (currSymbol.equals("+")) {
					result = x+y;
					inputNums.set(i, result.toString());
					inputNums.remove(i+1);
					inputSymbols.remove(i);
					i--;
				} else if (currSymbol.equals("-")) {
					result = x-y;
					inputNums.set(i, result.toString());
					inputNums.remove(i+1);
					inputSymbols.remove(i);
					i--;
				}
			}
		}
		System.out.println("After Add/Subtract:		" + inputNums + " and " + inputSymbols);
		
		// Re-construct result
		String answer = input + "=" + inputNums.get(0);
		return answer;
	}
	
	public static void main(String[] args) {
		EvaluateArithmetic evalClass = new EvaluateArithmetic();
		System.out.println(evalClass.eval("0-100*5+4/2-1"));
		System.out.println(evalClass.eval("1*2*3"));
		System.out.println(evalClass.eval("300-50*10"));
		System.out.println(evalClass.eval("10000*1*1-0"));
		System.out.println(evalClass.eval("-10*2"));
		System.out.println(evalClass.eval("-5-5-5-5-5"));
	}

}
