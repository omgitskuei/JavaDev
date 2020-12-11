package references.dataStructures.collections.map;

import java.util.HashMap;
import java.util.Map;

public class ForEachAndPrintAllEntrySets {

    public static void main(String[] args) {
        Map<String, String> thisMap = new HashMap<>();
        thisMap.put("SEQ_NO", "1");
        thisMap.put("COMPANY", "CupMaker");
        thisMap.put("PROD_NAME", "Super Cup X200");
        
        for (Map.Entry<String, String> eachPair : thisMap.entrySet()) {
            String thisKey = eachPair.getKey();
            String thisValue = eachPair.getValue();
            System.out.println("{key: "+thisKey+", value: "+thisValue+"}");
        }
    }

}
