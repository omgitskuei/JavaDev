package references.dataStructures.collections.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IterateListAndRemoveMaps {

    public static void main(String[] args) {
        List<Map<String, String>> payDatas = new ArrayList<Map<String, String>>();
        Map<String, String> e = new HashMap<String, String>();
        e.put("IS_CHECKED", "Y");
        e.put("UPL_NO", "3");
        payDatas.add(e);
        e = new HashMap<String, String>();
        e.put("IS_CHECKED", "N");
        e.put("UPL_NO", "4");
        payDatas.add(e);
        e = new HashMap<String, String>();
        e.put("IS_CHECKED", "Y");
        e.put("UPL_NO", "2");
        payDatas.add(e);
        e = new HashMap<String, String>();
        e.put("IS_CHECKED", "Y");
        e.put("UPL_NO", "1");
        payDatas.add(e);
        
        System.out.println(payDatas);
        
        // 取得被勾選的賠案資料
        for (int i = 0; i < payDatas.size(); i++) {
            System.out.println(i);
            Map<String, String> payData = payDatas.get(i);
            // Remove all Maps with IS_CHECKED:N
            if ("N".equals(payData.get("IS_CHECKED"))) {
                System.out.println(i);
                payDatas.remove(i--);
                System.out.println(i);
            }
            System.out.println("---");
        }
        
        System.out.println(payDatas);
    }

}
