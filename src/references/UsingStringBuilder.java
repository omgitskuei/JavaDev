package references;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UsingStringBuilder {

	public static void main(String[] args) {
		UsingStringBuilder instance = new UsingStringBuilder();
		ArrayList<String> swumidList = new ArrayList<String>();
		swumidList.add("HK80700902017071210300002");
		swumidList.add("MO80700602018091210300001");
		swumidList.add("MO80700602018111270100002");
		swumidList.add("MO80700602020010100000001");
		String oldAmlStus = "S";
		String newAmlStus = "P";
		
		StringBuilder sqlQueryStr = new StringBuilder();
		sqlQueryStr.append(
				"SELECT * FROM SGMOMSG "
				+ "WHERE ");
		sqlQueryStr.append("SGMOMSG.SW_UMID in (");
		Map<String, Object> userParams = new HashMap<String, Object>();
		// Generates the sqlQueryStr's list of IDs, and pass it swumidList 
		for (int index=0; index<swumidList.size(); index++) {
			userParams.put("SWUMID"+index, swumidList.get(index));
			sqlQueryStr.append(":SWUMID"+index);
			if (index!=swumidList.size()-1) {
				sqlQueryStr.append(", ");
			} else {
				sqlQueryStr.append(")");
			}
		}
		
		// Generate the "UPDATE SGMOMSG SET SGMOMSG.LAST_UPDATE_TIME = CURRENT_TIMESTAMP, SGMOMSG.AML_STUS = ':newAMLStus' 
		// WHERE SGMOMSG.SW_UMID in (" part of the sqlQueryStr
//		StringBuilder sqlQueryStr = new StringBuilder();
//		sqlQueryStr.append(
//				"UPDATE SGMOMSG "
//				+ "SET SGMOMSG.LAST_UPDATE_TIME = CURRENT_TIMESTAMP, SGMOMSG.AML_STUS = :newAMLStus "
//				+ "WHERE ");
//		oldAmlStus = oldAmlStus!=null ? oldAmlStus.trim() : "";
//		oldAmlStus = oldAmlStus.length()==1 ? oldAmlStus : "";
//		oldAmlStus = oldAmlStus.matches("[a-zA-Z]+") ? oldAmlStus : "";
//		if (!(oldAmlStus.equals(""))) {
//			sqlQueryStr.append("SGMOMSG.AML_STUS = :oldAMLStus AND ");
//		}
//		sqlQueryStr.append("SGMOMSG.SW_UMID in (");
//		Map<String, Object> userParams = new HashMap<String, Object>();
//		userParams.put("newAMLStus", newAmlStus);
//		// Generates the sqlQueryStr's list of IDs, and pass it swumidList 
//		for (int index=0; index<swumidList.size(); index++) {
//			userParams.put("SWUMID"+index, swumidList.get(index));
//			sqlQueryStr.append(":SWUMID"+index);
//			if (index!=swumidList.size()-1) {
//				sqlQueryStr.append(", ");
//			} else {
//				sqlQueryStr.append(")");
//			}
//		}
//		Query q = entityManager.createNativeQuery(sqlQueryStr.toString(), Sgmomsg.class);
//		for (Map.Entry<String, Object> entry : userParams.entrySet()) {
//			q.setParameter(entry.getKey(), entry.getValue());
//		}
		
		System.out.println(sqlQueryStr);
	}

}
