package dependenciesWorkspace;

import static org.junit.Assert.*;

import org.junit.Test;

import util.SecurityCodeGenerator;

public class JUnitTester {

	@Test
	public void testGenerateCode() {
		SecurityCodeGenerator instance;
		try {
			instance = new SecurityCodeGenerator(10, true, false, true, false);
			instance.setGeneratorParams(true, true, true, true);
			instance.generateCode();
			instance.returnCode();
			
			assertSame(9, instance.getLength());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
