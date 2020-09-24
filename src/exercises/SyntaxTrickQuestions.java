package exercises;

public class SyntaxTrickQuestions {

	public static void main(String[] args) {
		/**
		 * If statements DON'T need a defined open-close scope with brackets.
		 * This lack is bad practice but it works.
		 * The statement's scope, in this case, becomes the following statement
		 * up to the next ;.
		 */
		int x=2;
		int y=1;
		// You'd think this doesn't compile because the If-statements lack "{"	
		if(x==1&&y==2)
			System.out.println("x=1,");
			// But actually it does. Ln[8-9] becomes one statement ended at ";"
			// if(x==1&&y==2) System.out.println("x=1,"); // false
			System.out.println("y=2,");
			// Ln[10] System.out.println("y=2,"); doesn't have a if statement in front so it prints ALWAYS
		if((x+y)==3)
			System.out.println("x+y=3"); // true, prints
		System.out.println("-------------------------------");
		
		/**
		 * 
		 */
		int i = 100;
		String s = "10";
		s+=i;
		System.out.println(s);	// 10100
		System.out.println(s.getClass());	// string
		System.out.println("-------------------------------");
		
		/**
		 * 
		 */
		int b = 1, c = 1;
		boolean d = ++b > c++;	// b = 2, c = 1
		System.out.println(c);	// c = 2
		System.out.println(d);
		System.out.println("-------------------------------");
		
		/**
		 * 
		 */
		int e = 1, f = 1;
		boolean g = !(e>f)^(e<f);
		System.out.println(g);	// true
		System.out.println("-------------------------------");
		
		/**
		 * 
		 */
		//char xy = 'ys';	// doesnt compile
		//char a = '\';		// doesnt compile
		String s1 = "\"";
		System.out.println(s1);	// prints "
		//int i1 = 1+0.1;	// doesnt compile - type convert needs casting
		System.out.println("-------------------------------");
		
		/**
		 * 
		 */
		double _tax = 0.006;
		System.out.println(_tax);
		String $money = "1000";
		System.out.println($money);
		System.out.println("-------------------------------");
		
		/**
		 * 
		 */
		int t = 1;
		long t1 = 1;
		float t2 = 1;
		double t3 = 1.0;
		double sum = t+t1+t2+t3;	// when these types are added, it uses the biggest type
		System.out.println(sum);
		System.out.println("-------------------------------");
	}

}
