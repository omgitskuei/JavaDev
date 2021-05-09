package main.notes.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamExamples {

	/**
	 * https://stackify.com/streams-guide-java-8/
	 * 
	 * https://www.baeldung.com/java-8-streams-introduction#operations
	 * 
	 */

	public static void main(String[] args) {
		/**
		 * Modify
		 */
		// GOAL: Format these phone numbers
		runTest1();
		// GOAL: Capitalize these names
		runTest2();
		// GOAL: Convert these country codes to upper case
		runTest3();

		/**
		 * Filter/Find many
		 */
		// GOAL: Return country codes while ignoring country names
		runTest4();

		/**
		 * IsExists/Find one
		 */
		// GOAL: Return empty OR first matching result
		runTest5();

		/**
		 * Return data from stream (as is) as Array (not Collection)
		 */
		// GOAL: Format phone numbers (0963798118 to 0963-798-118)
		runTest6();

		/**
		 * flatMap()
		 * 
		 * reduces number of layers of objects wrapping data (eg List<List<String>> -> List<String>)
		 */
		// GOAL: Remove List layer
		runTest7();

		/**
		 * peek()
		 * 
		 * sometimes we need to perform multiple operations on each element of the stream before 
		 * any terminal operation is applied
		 * peek() can be useful for this - it performs the specified operation on each element 
		 * of the stream and returns a new stream which can be used further.
		 */
		// GOAL: 
		runTest8();
	}

	private static void runTest1() {
		List<String> arrList = new ArrayList<String>();
		arrList.add("0963798118"); // Expected: "0963-798-118"
		arrList.add("0227139503"); // Expected: "02-2713-9503"

		Stream<String> stream = arrList.stream();
		arrList = stream.map(eachStr -> {
			if (eachStr.substring(0, 2).equals("02")) {
				return eachStr.substring(0, 2) + "-" + eachStr.substring(2, 6) + "-" + eachStr.substring(6);
			} else {
				return eachStr.substring(0, 4) + "-" + eachStr.substring(4, 7) + "-" + eachStr.substring(7);
			}
		}).collect(Collectors.toList());

		System.out.println(arrList);
	}

	private static void runTest2() {
		List<String> arrList = new ArrayList<String>();
		arrList.add("joe"); // Expected: "Joe"
		arrList.add("bob"); // Expected: "Bob"

		arrList = arrList.stream().map(name -> {
			return name.substring(0, 1).toUpperCase() + name.substring(1);
		}).collect(Collectors.toList());
		System.out.println(arrList);
	}

	private static void runTest3() {
		List<String> arrList = new ArrayList<String>();
		arrList.add("uk"); // Expected: "UK"
		arrList.add("tw"); // Expected: "TW"

		arrList = arrList.stream().map(String::toUpperCase).collect(Collectors.toList());
		System.out.println(arrList);
	}

	private static void runTest4() {
		List<String> arrList = new ArrayList<String>();
		arrList.add("united states"); // Expected: not returned
		arrList.add("us"); // Expected: not filtered out
		arrList.add("singapore");
		arrList.add("si");

		arrList = arrList.stream().filter(s -> s.length() == 2).collect(Collectors.toList());
		System.out.println(arrList);
	}

	private static void runTest5() {
		Integer[] empIds = { 1, 2, 3, 4, null, 0, null, 5, null, 6, 7 };

		// No chaining
		// 1) convert Integer[] array into Stream
		Stream<Integer> stream = Stream.of(empIds);
		System.out.println(stream.collect(Collectors.toList()));
		// 2) filter out nulls
		stream = Stream.of(empIds).filter(e -> e != null);
		System.out.println(stream.collect(Collectors.toList()));
		// 3) filter out Integers under 5
		stream = Stream.of(empIds).filter(e -> e != null).filter(e -> e > 5);
		System.out.println(stream.collect(Collectors.toList()));
		// 4) find first Integer that's over 6 (from the remaining integers [6, 7])
		Optional<Integer> b = Stream.of(empIds).filter(e -> e != null).filter(e -> e > 5).findFirst(); // returns
																										// optional
		System.out.println(b);
		// 5) if findFirst DOES NOT find a valid one, return null
		Integer c = Stream.of(empIds).filter(e -> e != null).filter(e -> e > 5).findFirst() // returns optional
				.orElse(null); // changes to return Integer
		System.out.println(c);

		// Chained version
		Integer a = Stream.of(empIds).filter(e -> e != null).filter(e -> e > 5).findFirst().orElse(null);

		System.out.println(a);
	}

	private static void runTest6() {
		List<String> arrList = new ArrayList<String>();
		arrList.add("0963798118"); // Expected: "0963-798-118"
		arrList.add("0227139503"); // Expected: "02-2713-9503"

		String[] nums = arrList.stream().toArray(String[]::new);
		System.out.println(nums);
		for (String string : nums) {
			System.out.println(string);
		}
	}

	private static void runTest7() {
		List<List<String>> list1 = Arrays.asList(
				Arrays.asList("Jeff", "Bezos"), 
				Arrays.asList("Bill", "Gates"),
				Arrays.asList("Mark", "Zuckerberg")
			);
		System.out.println(list1);			// List<List<String>>  [[Jeff, Bezos], [Bill, Gates], [Mark, Zuckerberg]]
		
		// convert the Stream<List<String>> to a simpler Stream<String> using the flatMap() API
		List<String> list2 = list1.stream()
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		System.out.println(list2);			// List<String>        [Jeff, Bezos, Bill, Gates, Mark, Zuckerberg]
		
		// Again, flatMap() example, this time without chaining
		Stream<List<String>> streamListStr = list1.stream();
		Stream<String> streamStr = streamListStr.flatMap(Collection::stream);
		List<String> listStr = streamStr.collect(Collectors.toList());
		System.out.println(listStr);		// List<String>        [Jeff, Bezos, Bill, Gates, Mark, Zuckerberg]
	}

	private static void runTest8() {
		ArrayList<String> nums = new ArrayList<String>();
		nums.add("omg");
		nums.add("wow");
		nums.add("doge");
		
//		List<String> bools = nums.stream()
//			.peek(aStr -> aStr.contains("e"))
//			.peek(System.out::println)
//			.collect(Collectors.toList());
	}
}
