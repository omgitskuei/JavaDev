package main.notes.dataTypes.stringRelated;

public class appendString {
    public static void main(String[] args) {
        String a = "Col1,";
        a = appendRating001toX(a, 120);
        System.out.println(a);
    }
    
    private static String appendRating001toX(String beginningStr, int lastNum) {
        String iAsStr = "";
        for (int i = 1; i <= lastNum; i++) {
            if(String.valueOf(i).length() == 1) {			//0-9
                iAsStr = "00"+String.valueOf(i);				// pad ZEROs
            } else if (String.valueOf(i).length() == 2) {	//10-99
                iAsStr = "0"+String.valueOf(i);					// pad ZEROs
            } else {										//100-...
                iAsStr = String.valueOf(i);						// pad ZEROs
            }
            beginningStr = beginningStr.concat(",?•¸?¼_" + iAsStr);
        }
        return beginningStr;
    }
}
