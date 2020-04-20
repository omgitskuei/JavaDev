package util;

public class SecurityCodeLengthTypeError extends Exception {
	/**
	 * auto-generated serial number
	 */
	private static final long serialVersionUID = 1182964780764208050L;
	public String getMessage() {
		return "GetCode Exception 01: Input type for Code length was invalid; must be Integer/int or String.";
	}
}