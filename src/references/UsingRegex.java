package references;

public class UsingRegex {

	public static void main(String[] args) {
		UsingRegex instance = new UsingRegex();
		
		
		String date = "2020/05/03    ";
		date = date.replaceAll("\\D", "");		// strips away all non-digit chars
		System.out.println("["+date+"]");
		
		String date2 = "dasd20qf20ef0304f";
		date2=instance.convertDBDate(date2);
		System.out.println(date2);
	}

	private String convertDBDate(String date) {
		date = date.replaceAll("\\D", "");
		if(date.length()<8) {
			return date;
		} 
		String result = "";
		result = date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8);
		return result;
	}
	
}

