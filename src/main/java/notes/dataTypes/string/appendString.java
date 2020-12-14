package main.java.notes.dataTypes.string;

public class appendString {
    public static void main(String[] args) {
        String a = "Col1,";
        a = appendRating001toX(a, 120);
        System.out.println(a);
    }
    
    private static String appendRating001toX(
            String beginningStr, int lastNum) {
        String iAsStr = "";
        for (int i = 1; i <= lastNum; i++) {
            if(String.valueOf(i).length() == 1) {
                iAsStr = "00"+String.valueOf(i);
            } else if (String.valueOf(i).length() == 2) {
                iAsStr = "0"+String.valueOf(i);
            } else {
                iAsStr = String.valueOf(i);
            }
            beginningStr = beginningStr.concat(",數值_" + iAsStr);
        }
        return beginningStr;
    }
}
