package main.notes.exceptions;

public class PrintingExceptions {

	public static void main(String[] args) {
		String a = "nan";
		int b;
		
		try {
			b = Integer.parseInt(a);
			System.out.println(b);
		} catch (NumberFormatException e) {
			System.out.print("e.getLocalizedMessage() =			");
			System.out.println(e.getLocalizedMessage());
			
			System.out.print("e.getMessage() =					");
			System.out.println(e.getMessage());
			
			System.out.print("e.getCause() =						");
			System.out.println(e.getCause());
			// The 'cause' is the throwable that caused this throwable to get thrown.
			
			System.out.print("e.getClass() =						");
			System.out.println(e.getClass());
			System.out.print("e.getClass().getCanonicalName() =	");
			System.out.println(e.getClass().getCanonicalName());
			System.out.print("e.getClass().getName() =			");
			System.out.println(e.getClass().getName());
			System.out.print("e.getClass().getTypeName() =		");
			System.out.println(e.getClass().getTypeName());
			System.out.print("e.getClass().getPackageName() =		");
			System.out.println(e.getClass().getPackageName());
			System.out.print("e.getClass().getSimpleName() =		");
			System.out.println(e.getClass().getSimpleName());
			System.out.print("e.getClass().toString() =			");
			System.out.println(e.getClass().toString());
			
			System.out.print("e.getStackTrace() =					");
			System.out.println(e.getStackTrace());
			
			System.out.print("e.getSuppressed() =					");
			System.out.println(e.getSuppressed());
			
			// e.getLocalizedMessage() =			For input string: "nan"
			// e.getMessage() =						For input string: "nan"
			// e.getCause() =						null
			// e.getClass() =						class java.lang.NumberFormatException
			// e.getClass().getCanonicalName() =	java.lang.NumberFormatException
			// e.getClass().getName() =				java.lang.NumberFormatException
			// e.getClass().getTypeName() =			java.lang.NumberFormatException
			// e.getClass().getPackageName() =		java.lang
			// e.getClass().getSimpleName() =		NumberFormatException
			// e.getClass().toString() =			class java.lang.NumberFormatException
			// e.getStackTrace() =					[Ljava.lang.StackTraceElement;@3830f1c0
			// e.getSuppressed() =					[Ljava.lang.Throwable;@39ed3c8d
		}
	}

}
