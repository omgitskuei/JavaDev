package references.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ListMapToCSV {

    public void convertDTARA052to055DataToCSV(String formType,
            List<Map> rtnList, String fileName) throws IOException {
        try {
            File file = new File("");
            FileWriter bw = new FileWriter("new.csv");
            // Write column headers (Chinese) into CSV file's first row
            switch (formType) {
            case "1": // headers for DTARA052
                System.out.print("信用等級_列,信用等級_欄,數值");
                break;
            case "2": // headers for DTARA053
                // rtn "信用等級,數值_001,數值_002, ... ,數值_120"
                System.out.print(appendRating001toX("信用等級", 120));
                break;
            case "3": // headers for DTARA054
                System.out.print(appendRating001toX("信用等級", 120));
                break;
            case "4": // headers for DTARA055
                System.out.print(appendRating001toX("LCD", 120));
                break;
            default:
                break;
            }
            StringBuilder sb = new StringBuilder();
            // Write each row's cell data
            int columnCount = 0;
            for (Map<String, Object> eachRow : rtnList) {
                sb.append("\n");
                for (Map.Entry<String, Object> eachPair : eachRow.entrySet()) {
                    String columnName = eachPair.getKey();
                    String cellValue = String.valueOf(eachPair.getValue());
                    if (columnCount != 0) {
                        sb.append(",");
                    }
                    // If value contains \, add "" to capture escape characters
                    if (cellValue.indexOf("\"") >= 0) {
                        cellValue = cellValue.replace("\"", "\"\"");
                    }
                    // 檢查內容是否含有,，若有則將該 value 用 "" 包起來
                    if (cellValue.indexOf(',') >= 0) {
                        sb.append('"').append(cellValue).append('"');
                    } else {
                        sb.append(cellValue);
                    }
                    columnCount++;
                }
                System.out.println(sb.toString());
                bw.flush();
                sb.setLength(0);
                columnCount = 0;
            }
            // Add file to resp
            if (bw != null) {
                bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 // - - - - - - - - - - - - - Helper Methods - - - - - - - - - - - - -
    private String appendRating001toX(String beginningStr, int lastNum) {
        String iStr = "";
        for (int i = 1; i <= lastNum; i++) {
            if (String.valueOf(i).length() == 1) {
                iStr = "00" + String.valueOf(i);
            } else if (String.valueOf(i).length() == 2) {
                iStr = "0" + String.valueOf(i);
            } else {
                iStr = String.valueOf(i);
            }
            beginningStr = beginningStr.concat(",數值_" + iStr);
        }
        return beginningStr;
    }
    
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) throws IOException {
        ListMapToCSV sCsv = new ListMapToCSV();
        
        List<Map> rtnList = new ArrayList<Map>();
        Map aMap = new HashMap<String, String>();
        aMap.put("信用等級", "AAA");
        aMap.put("數值_001", "100.42");
        aMap.put("數值_002", "12.11");
        aMap.put("數值_003", "44.1");
        aMap.put("數值_004", "20.4");
        aMap.put("數值_005", "2");
        aMap.put("數值_006", "53");
        aMap.put("數值_007", "3");
        aMap.put("數值_008", "5555");
        rtnList.add(aMap);
        sCsv.convertDTARA052to055DataToCSV("2", rtnList, "sdf.csv");

        // NOTE wrong order
    }

}
