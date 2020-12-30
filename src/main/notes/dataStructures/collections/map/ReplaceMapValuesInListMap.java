package main.notes.dataStructures.collections.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplaceMapValuesInListMap {

    public static void main(String[] args) {
        /**
         * Replace null, trailing spaces, and "null" with empty string for each value in map
         */
        List<Map<String, String>> listOfMaps = new ArrayList<>();
        Map<String, String> newMap = new HashMap<>();       // Add a map of bad data
        newMap.put("SEQ_NO", "1");
        newMap.put("COMPANY", "         CupMaker");
        newMap.put("PROD_NAME", "Super Cup X2000    ");     // trailing spaces
        newMap.put("RATING", null);                         // null
        newMap.put("DESCRIPTION", "");                      // empty
        newMap.put("WEIGTH", "31kg");
        listOfMaps.add(newMap);
        newMap = new HashMap<>();
        newMap.put("SEQ_NO", "2");
        newMap.put("COMPANY", "  CupMaker     ");
        newMap.put("PROD_NAME", "Crappy Cup X1000");
        newMap.put("RATING", null);
        newMap.put("DESCRIPTION", "      ");                // spaces
        newMap.put("WEIGTH", "null");                       // "null"
        listOfMaps.add(newMap);
        
        for (int i = 0; i < listOfMaps.size(); i++) {
            Map<String, String> thisMap = listOfMaps.get(i);
            for (Map.Entry<String, String> eachPair : thisMap.entrySet()) {
                String thisKey = "";
                String thisValue = "";
                thisKey = eachPair.getKey();
                thisValue = String.valueOf(eachPair.getValue());
                if(thisValue == null || thisValue.equals("null") || thisValue.trim().equals("")) {
                    thisValue = "";
                } else {
                    thisValue = thisValue.trim();
                }
                thisMap.put(thisKey, thisValue);
            }               
            listOfMaps.set(i, thisMap);
            System.out.println("results.get("+i+") = {" 
                + listOfMaps.get(i).get("SEQ_NO")
                +", "+listOfMaps.get(i).get("COMPANY")
                +", "+listOfMaps.get(i).get("PROD_NAME")
                +", "+listOfMaps.get(i).get("RATING")
                +", "+listOfMaps.get(i).get("DESCRIPTION")
                +", "+listOfMaps.get(i).get("WEIGTH")
            +"}");
        }
        
    }

}
