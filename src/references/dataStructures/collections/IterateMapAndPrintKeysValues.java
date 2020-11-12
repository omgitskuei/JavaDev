package references.dataStructures.collections;

import java.util.HashMap;
import java.util.Map;

public class IterateMapAndPrintKeysValues {

    public static void main(String[] args) {
        Map<String, String> e = new HashMap<String, String>();
        e.put("IS_CHECKED", "Y");
        e.put("UPL_NO", "3");
        e.put("PRICE", "300.00");
        for (Map.Entry<String, String> entry : e.entrySet()) {
            System.out.println("Key = " + entry.getKey() + " : Value = " + entry.getValue());
        }
    }

}
