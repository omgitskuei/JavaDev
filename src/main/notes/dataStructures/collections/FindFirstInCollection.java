package main.notes.dataStructures.collections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class FindFirstInCollection {
    
    /**
     * Find anything in Collection using Stream(), without looping
     * This seems to be NOT meaningfully faster than using ForEach/For through the Collection
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String args[]) throws InterruptedException {
        List<BigDecimal> bigDecimalsArrayList2 = new ArrayList<>();
        bigDecimalsArrayList2.add(new BigDecimal(1));
        bigDecimalsArrayList2.add(new BigDecimal(2));
        bigDecimalsArrayList2.add(new BigDecimal(3));
        bigDecimalsArrayList2.add(new BigDecimal(3));
        System.out.println("List:" + bigDecimalsArrayList2);
        System.out.println("Stream.findAny() = "+bigDecimalsArrayList2.stream().findAny());
        System.out.println();
        
        // - - - - - - - - - - - List - - - - - - - - - - - 
        List<BigDecimal> bigDecimalsArrayList = new ArrayList<>();
        bigDecimalsArrayList.add(new BigDecimal(1));
        bigDecimalsArrayList.add(new BigDecimal(2));
        bigDecimalsArrayList.add(new BigDecimal(3));
        bigDecimalsArrayList.add(new BigDecimal(3));
        System.out.println("List:" + bigDecimalsArrayList);
        BigDecimal aBigDecimal = bigDecimalsArrayList.stream()
                .filter(bDecimal -> "3".equals(String.valueOf(bDecimal)))
                .findFirst()
                .orElse(new BigDecimal(0));
        System.out.println(aBigDecimal);
        System.out.println();
        
        
        // - - - - - - - - - - - Set - - - - - - - - - - - 
        Set<String> stringSet = new HashSet<String>();
        stringSet.add("1");
        stringSet.add("2");
        stringSet.add("3");
        stringSet.add("3");
        System.out.println("Set:" + stringSet);
        String aString = stringSet.stream()
                .filter(eachString -> "3".equals(eachString))
                .findFirst()
                .orElse("notFound");
        System.out.println(aString);
        System.out.println();
        
        
        // - - - - - - - - - - - Map - - - - - - - - - - - 
        // NOTE:
        // Map can't use .stream() directly but can be converted to
        // 1) Entry (key-value pairs)
        // 2) Set (for keys)
        // 3) Collection (for values)
        HashMap<Integer, String> errMsgMap = new HashMap<Integer, String>();
        errMsgMap.put(404, "Resource not found");
        errMsgMap.put(500, "Internal server error");
        errMsgMap.put(400, "Bad request error");
        System.out.println("Map:"+errMsgMap);
        // Option 1) Obtain a set of key-value pairs:
        Set<Entry<Integer, String>> entries = errMsgMap.entrySet();
        Entry<Integer, String> errMsgPairs = entries.stream()
                .filter(eachPair -> 400 == (eachPair.getKey()))
                .findFirst()
                .orElse(null);
        System.out.println("Option 1:" + errMsgPairs);
        // Option 2) Obtain a set of (only) the keys (, no values)
        Set<Integer> errMsgKeys = errMsgMap.keySet();
        Integer key = errMsgKeys.stream()
                . filter(eachInt -> 400 == eachInt)
                .findFirst()
                .orElse(0);
        System.out.println("Option 2:" + key);
        // Option 3) Or we could work directly with the set of values
        Collection<String> errMsgText = errMsgMap.values();
        String errMsg = errMsgText.stream()
                .filter(eachErrMsgText -> "Bad request error".equals(eachErrMsgText))
                .findFirst()
                .orElse("ErrMsg not found");
        System.out.println("Option 3:" + errMsg);
        System.out.println();
        
        
        // - - - - - - - - - - - Queue - - - - - - - - - - - 
        Queue<String> queue = new ArrayBlockingQueue<String>(4);
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("3");
        System.out.println("Queue:" + queue);
        aString = queue.stream()
                .filter(eachString -> "3".equals(eachString))
                .findFirst()
                .orElse("notFound");
        System.out.println(aString);
        System.out.println();
        
        
        // - - - - - - - - - - - Stack - - - - - - - - - - - 
        Stack<String> stack = new Stack<String>();
        stack.add("pancakes");
        stack.add("pan");
        stack.add("can");
        System.out.println("Stack:"+stack);
        String item = stack.stream()
                .filter(eachStr -> "pan".equals(eachStr))
                .findFirst()
                .orElse("notFound");
        System.out.println(item);
        System.out.println();
        
        
        // - - - - - - - - - - - Time trial - - - - - - - - - - - 
        // Data population
        List<Integer> timedTrialList = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            timedTrialList.add(Integer.valueOf((int) (Math.random()*100)));
        }
        System.out.println(timedTrialList);
        // Target
        Integer target = timedTrialList.get(999);
        Long time = 0L;
        Long time2 = 0L;
        
        // Timed trial with For Loop
        time = System.currentTimeMillis();
        System.out.println("Begin:      " + time);
        Integer anInt1 = 0;
        for(int i = 0; i < timedTrialList.size(); i++) {
            if(timedTrialList.get(i) == target) {
                anInt1 = target;
                break;
            }
        }
        System.out.println("Result:     " + anInt1);
        time2 = System.currentTimeMillis();
        System.out.println("End:        " + time2);
        System.out.println(" > Difference: " + String.valueOf(time-time2));
        Thread.sleep(1);
        
        // Timed trial with ForEach Loop
        time = System.currentTimeMillis();
        System.out.println("Begin:      " + time);
        Integer anInt2 = 0;
        for (Integer integer : timedTrialList) {
            if(integer == target) {
                anInt2 = integer;
                break;
            }
        }
        System.out.println("Result:     " + anInt2);
        time2 = System.currentTimeMillis();
        System.out.println("End:        " + time2);
        System.out.println(" > Difference: " + String.valueOf(time-time2));
        Thread.sleep(1);
        
        // Timed trial with Stream and FindAny
        time = System.currentTimeMillis();
        System.out.println("Begin:      " + time);
        Integer anInt3 = timedTrialList.stream()
                .filter(bDecimal -> target == bDecimal)
                .findAny()
                .orElse(0);
        System.out.println("Result:     " + anInt3);
        time2 = System.currentTimeMillis();
        System.out.println("End:        " + time2);
        System.out.println(" > Difference: " + String.valueOf(time-time2));
        Thread.sleep(1);
        
        // Timed trial with Stream and FindFirst
        time = System.currentTimeMillis();
        System.out.println("Begin:      " + time);
        Integer anInt4 = timedTrialList.stream()
                .filter(bDecimal -> target == bDecimal)
                .findFirst()
                .orElse(0);
        System.out.println("Result:     " + anInt4);
        time2 = System.currentTimeMillis();
        System.out.println("End:        " + time2);
        System.out.println(" > Difference: " + String.valueOf(time-time2));
        Thread.sleep(1);
    }
}
