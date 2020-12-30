package main.exercises;

import java.util.ArrayList;
import java.util.HashMap;

public class TestUtil {

	public static void main(String[] args) {
		try {
			
			String w = "123.22";
			int s = (int) Double.parseDouble(w);
			
//			ArrayList<String> cartSliced = new ArrayList<String>();
//			cartSliced.add("ProductID");	//0
//			cartSliced.add("26");
//			cartSliced.add("ProductName");
//			cartSliced.add("?��");			//3
//			cartSliced.add("ProductCount");
//			cartSliced.add("3");			//5
//			cartSliced.add("ProductPrice");
//			cartSliced.add("20");			//7
//			
//			cartSliced.add("ProductID");	//8
//			cartSliced.add("30");
//			cartSliced.add("ProductName");
//			cartSliced.add("山藥");			//11
//			cartSliced.add("ProductCount");
//			cartSliced.add("1");			//13
//			cartSliced.add("ProductPrice");
//			cartSliced.add("100");			//15
//			
//			cartSliced.add("ProductID");	//16
//			cartSliced.add("27");
//			cartSliced.add("ProductName");
//			cartSliced.add("洋蔥");					//19, name
//			cartSliced.add("ProductCount");
//			cartSliced.add("3");					//21, count
//			cartSliced.add("ProductPrice");
//			cartSliced.add("18");					//23, unit price
//			
//			ArrayList<Object> lineItems = new ArrayList<Object>();
//			
//			int iteration = cartSliced.size()/8;
//			
//			int count = 0;
//			for (int i = 0; i < iteration; ++i) {
//				HashMap<String,Object> lineItem = makeOrderItem(cartSliced.get(3+8*count), "", Integer.parseInt(cartSliced.get(7+8*count)), "TWD", Integer.parseInt(cartSliced.get(5+8*count)));
//				
//				System.out.println(cartSliced.get(3+8*count));
//				System.out.println(cartSliced.get(7+8*count));
//				System.out.println(cartSliced.get(5+8*count));
//				lineItems.add(lineItem);
//				count++;
//			}
//			
//			System.out.println(lineItems);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HashMap<String,Object> makeOrderItem (String name, String description, int amount, String currency, int quantity) {
		HashMap<String, Object> orderItem = new HashMap<String, Object>();
		orderItem.put("name", name);
		orderItem.put("description", description);
		orderItem.put("amount", amount);
		orderItem.put("currency", currency);
		orderItem.put("quantity", quantity);
		return orderItem;
	}
	
	public static boolean validatePwd(String pwd) {
		System.out.println("Begin: validatePwd(String)");
		try {
			// A valid password must have >=8 characters
			if (pwd.length() >= 8) {
				// Partition pwd into substrings of each character
				ArrayList<String> pwdEachChar = new ArrayList<String>();
				for (int index = 0; index < pwd.length(); index++) {
					pwdEachChar.add(pwd.substring(index, index + 1));
				}
				// A valid password must NOT contain spaces
				if (!(pwdEachChar.contains(" "))) {
					// A valid password must contain >=1 special characters
					// A valid password must contain >=1 Capitalized letters
					// A valid password must contain >=1 Lower-case letters
					CheckSubstring util = new CheckSubstring();
					if (util.countCapLetters(pwd) >= 1 && util.countLowLetters(pwd) >= 1
							&& util.countSpecialCharacters(pwd) >= 1) {
						// A valid password must contain >=1 numbers
						if (util.countNums(pwd) >= 1) {
							System.out.println("Valid Input: Pwd");
							return true;
						} else {
							System.out.println("Invalid Input: Pwd must contain at least one number");
						}
					} else {
						System.out.println(
								"Invalid Input: Pwd must contain at least one Special character, Capital letter, and Lower-case letter");
					}
				} else {
					System.out.println("Invalid Input: Pwd must not contain spaces");
				}
			} else {
				System.out.println("Invalid Input: Pwd must be longer than 8 characters");
			}
			System.out.println("Finish: validatePwd(String)");
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
