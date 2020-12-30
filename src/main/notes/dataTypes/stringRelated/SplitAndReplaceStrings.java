package main.notes.dataTypes.stringRelated;

public class SplitAndReplaceStrings {

	public static void main(String[] args) {
		// String a = "(1,15), (2,14), (2,16), (3,13), (3,17), (4,12), (4,18), (5,11), (5,19), (6,10), (6,20), (7,9), (7,21), (8,8), (8,22), (9,7), (9,23), (10,6), (10,24), (11,5), (11,25), (12,4), (12,26), (13,3), (13,27), (14,2), (14,28), (15,1), (15,2), (15,3), (15,4), (15,5), (15,6), (15,7), (15,8), (15,9), (15,10), (15,11), (15,12), (15,13), (15,14), (15,15), (15,16), (15,17), (15,18), (15,19), (15,20), (15,21), (15,22), (15,23), (15,24), (15,25), (15,26), (15,27), (15,28), (15,29)";
		
		
		String case1 = "(21,333)";
		
		String[] arrStr = case1.split(",");
		System.out.println(arrStr.length);				// 2
		
		for (int i = 0; i < arrStr.length; i++) {
			String str = arrStr[i];
			System.out.println("Before:"+str);
			str = str.replaceAll("[()]", "");
			System.out.println("After:"+str);
		}
	}

}
