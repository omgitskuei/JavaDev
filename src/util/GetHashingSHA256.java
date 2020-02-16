package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GetHashingSHA256 {
	// Local fields
	String originalString = "null";
	String resultHash = "null";
	
	// Constructors
	public GetHashingSHA256() {
		System.out.println("BEGIN: util.GetHashingSHA256()");
		;
	}
	public GetHashingSHA256(String aString) {
		System.out.println("BEGIN: util.GetHashingSHA256(String)");
		this.originalString = aString;
		String resultHashString = hashString(this.originalString);
		System.out.println("Result HashString: "+resultHashString);
		this.resultHash = resultHashString;
	}
	
	// Methods
	public String hashString(String originalString) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedHash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
			System.out.println("  Converting plaintext(String) to hash(byte[]): "+encodedHash);
			String hexString = bytesToHex(encodedHash);
			System.out.println("  Converting hash(byte[]) to hash(hexString): "+hexString);
			return hexString;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("ERROR: util.GetHashingSHA256().hashString(String) threw EXCEPTION");
			e.printStackTrace();
			return "null";
		}
	}
	// Private method for hashString(String)
	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	// Executable
	public static void main(String[] args) {
		GetRuntimeInput util0 = new GetRuntimeInput();
		String input = util0.getInputOnce("Input String:");
		// Two ways of using this util;
		// 1) Goood for calculating hash result right away
		GetHashingSHA256 util1 = new GetHashingSHA256(input);
		System.out.println("What are its local fields? "+util1.originalString+", "+util1.resultHash);
		String result1 = util1.resultHash;
		// 2) Explicitly call method to get a return
		GetHashingSHA256 util2 = new GetHashingSHA256();
		System.out.println("What are its local fields? "+util2.originalString+", "+util2.resultHash);
		String result2 = util2.hashString(input);
	}

	
}
