package exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderingOldAndNewRecords {

    public static void main(String[] args) {
        OrderingOldAndNewRecords instance = new OrderingOldAndNewRecords();
        List<Map<String, String>> allRecords = new ArrayList<Map<String, String>>();
        Map<String, String> aRecord = new HashMap<String, String>();
        // Test data
        aRecord = instance.generateRecord(true, "robot", "Motzart Sonatas 1");
        allRecords.add(aRecord);
        aRecord = instance.generateRecord(false, "10", "Bach's Beats 10");    // old
        allRecords.add(aRecord);
        aRecord = instance.generateRecord(false, "3", "Bach's Beats 3");    // old
        allRecords.add(aRecord);
        aRecord = instance.generateRecord(true, "cat", "Motzart Sonatas 2");
        allRecords.add(aRecord);
        aRecord = instance.generateRecord(false, "4", "Bach's Beats 4");    // old
        allRecords.add(aRecord);
        aRecord = instance.generateRecord(true, "0101010", "Motzart Sonatas 3");
        allRecords.add(aRecord);
        aRecord = instance.generateRecord(false, "1", "Bach's Beats 1");    // old
        allRecords.add(aRecord);
        aRecord = instance.generateRecord(false, "11", "Bach's Beats 11");    // old
        allRecords.add(aRecord);
        aRecord = instance.generateRecord(true, "0", "Motzart Sonatas 5");
        allRecords.add(aRecord);
        System.out.println("allRecords=" + allRecords);

        List<Map<String, String>> oldRecords = new ArrayList<Map<String, String>>();
        List<Map<String, String>> newRecords = new ArrayList<Map<String, String>>();
        int largestSeqNo = 0;

        // Split by old and new records
        for (Map<String, String> eachRecord : allRecords) {
            if (eachRecord.get("SEQ_NO").equals("")) {
                newRecords.add(eachRecord);
            } else {
                oldRecords.add(eachRecord);
                if (Integer.valueOf(eachRecord.get("SEQ_NO")) > largestSeqNo) {
                    largestSeqNo = Integer.valueOf(eachRecord.get("SEQ_NO"));
                }
            }
        }
        System.out.println("oldRecords=" + oldRecords);
        System.out.println("newRecords=" + newRecords);
        // Update all SEQ_NO to increments of largest SEQ_NO
        System.out.println("largestSeqNo of oldRecords=" + largestSeqNo);
        for (Map<String, String> eachRecord : newRecords) {
            largestSeqNo = largestSeqNo + 1;
            eachRecord.replace("SEQ_NO", String.valueOf(largestSeqNo));
        }
        // Optional: Order old records by SEQ_NO
        Map<String, String> temp1;
        Map<String, String> temp2;
        boolean notDone = true;
        while (notDone) {
            notDone = false;
            for (int i = 0; i < oldRecords.size() - 1; i++) {
                temp1 = oldRecords.get(i);
                temp2 = oldRecords.get(i + 1);
                if (Integer.valueOf(temp1.get("SEQ_NO")) > Integer.valueOf(temp2.get("SEQ_NO"))) {
                    notDone = true;
                    oldRecords.set(i + 1, temp1);
                    oldRecords.set(i, temp2);
                }
            }
        }
        // Combine Old and New records as allRecords
        allRecords.clear();
        allRecords.addAll(oldRecords);
        allRecords.addAll(newRecords);
        System.out.println("allRecords=" + allRecords);

    }

    private Map<String, String> generateRecord(boolean isNew, String seqNo, String name) {
        Map<String, String> aRecord = new HashMap<String, String>();
        if (isNew) {
            aRecord.put("SEQ_NO", "");
            aRecord.put("NAME", name);
        } else {
            aRecord.put("SEQ_NO", String.valueOf(seqNo));
            aRecord.put("NAME", name);
        }
        return aRecord;
    }

}
