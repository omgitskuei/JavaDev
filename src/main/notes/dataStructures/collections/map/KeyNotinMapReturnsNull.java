package main.notes.dataStructures.collections.map;

import java.util.HashMap;
import java.util.Map;

public class KeyNotinMapReturnsNull {

    /**
     * 
     * When key is not in the map, returned value is null
     * @param args
     */
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("age", "27");
        map.put("weight", "75kg");
        System.out.println(map.containsKey("age"));             // true
        
        System.out.println(map.containsKey("name"));            // false
        String missingKey = map.get("name");
        System.out.println(missingKey);                         // null
    }

}
