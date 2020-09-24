package exercises;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ConvertDirtyStringToMoney {

    private String convertMoney(String numbers) {
        // check null & empty
        if (numbers == null || numbers.equals("")) {
            System.out.println("Only spaces entered, or null");
            return "";
        } else {
            // strip spaces
            numbers = numbers.trim();
            System.out.println("After trim: [" + numbers + "]");
            
            // strips away all non-digit AND non-dot chars
            numbers = numbers.replaceAll("[^0-9.]", "");
            System.out.println("After replaceAll: [" + numbers + "]");
            
            String result = "";
            BigDecimal obj = new BigDecimal(numbers);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
            decimalFormat.setMinimumFractionDigits(2);
            result = decimalFormat.format(obj);
            return result;
        }
    }

    public static void main(String[] args) {
        ConvertDirtyStringToMoney thisClass = new ConvertDirtyStringToMoney();
        String dirtyString = "  USD 1679.319344 ";
        System.out.println(thisClass.convertMoney(dirtyString));

    }

}
