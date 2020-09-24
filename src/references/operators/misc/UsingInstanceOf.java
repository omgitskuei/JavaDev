package references.operators.misc;

public class UsingInstanceOf {
	private Object variable;
	
	@SuppressWarnings("rawtypes")
    private Class classType;
	
	// Constructors
	public UsingInstanceOf () {
		System.out.println("BEGIN: References.Operators.Misc.UsingInstanceOf()");
	}
	
	public void setVariable(Object variable) {
		// Set local field to passed variable
		this.variable = variable;
		System.out.print("	UsingInstanceOf.setVariable("+variable+")	;	this.variable = "+variable);
		
		// Set local field to passed variable's class type
		this.classType = this.variable.getClass();
		System.out.println("	|	this.classType = "+classType);
	}
	
	public static void main(String[] args) throws Throwable {
		UsingInstanceOf instance = new UsingInstanceOf();
		instance.setVariable("300");		// string
		instance.setVariable(300);			// int
		instance.setVariable(300.0);		// double
		instance.setVariable(300F);			// float
		instance.setVariable(300L);			// long
	}

}
