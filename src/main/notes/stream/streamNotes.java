package main.notes.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamNotes {
	/**
	 * One major new feature in Java 8 is the stream functionality –
	 * java.util.stream – which contains classes for processing sequences of
	 * elements. The central API class is the Stream<T>.
	 * 
	 * Table of Contents
	 * 
	 * - Stream Creation
	 * 
	 * - Multi-threading
	 * 
	 * - Stream operations
	 * 
	 * - - Iterating
	 * 
	 * - - Filtering
	 * 
	 * https://www.baeldung.com/java-8-streams-introduction, 2021/04/23
	 */

	public static void main(String args[]) {

		/**
		 * Stream Creation
		 * 
		 * Streams can be created from collection or array via stream() and of().
		 */

		Stream<String> stream;
		String[] array;

		// Array -> Stream
		array = new String[] { "a", "b", "c" };

		stream = Arrays.stream(array);

		stream = Stream.of(array);

		stream = Stream.of("a", "b", "c");

		// Collection -> Stream
		// Java 8 adds method stream() to Collection interface and allows
		// creating a Stream<T> using any collection as an element source:
		ArrayList<String> arrlist = new ArrayList<String>();
		arrlist.add("a");
		arrlist.add("b");
		arrlist.add("c");
		arrlist.add("c");

		stream = arrlist.stream();

		/**
		 * Multi-Threading, a.k.a. "Parallel mode"
		 * 
		 * Basically, parallel mode is faster if you have the hardware
		 * 
		 * 
		 * Stream API also simplifies multithreading by providing the parallelStream()
		 * method that runs operations over stream's elements in parallel mode.
		 * 
		 * 
		 * By Default...
		 * 
		 * Keep in mind to create parallel stream is to call the parallelStream() method
		 * on the collection else by default the sequential stream gets returned by
		 * stream() method.
		 * 
		 * https://www.baeldung.com/java-8-streams-introduction#1-stream-creation
		 * 
		 * 
		 * Implications
		 * 
		 * 1) Overhead
		 * 
		 * A parallel stream has a MUCH higher overhead compared to a sequential one. It
		 * has to coordinate many threads and assign work, and then aggregate results
		 * from many threads.
		 * 
		 * Parallel overhead = Splitting + Dispatch + Management + Result combination.
		 * 
		 * Main reason to use parallel instead of sequential is possibly increasing
		 * speed. Sometimes, overhead takes too much work/time compared to just doing
		 * the work right away sequentially.
		 * 
		 * 2) Locality
		 * 
		 * Array-based sources parallelize the best as the iterator's next element is
		 * cached, meaning there's less time wasted on CPU waiting for data. If your
		 * stream's source is not easily parallelized, time saved might not be very
		 * significant.
		 * 
		 * When to use Parallel Streams?
		 * 
		 * - I have a massive amount of items to process
		 * 
		 * - Processing each item takes a long time
		 * 
		 * - I have a performance problem in the first place with Sequential Streams.
		 * 
		 * - I won't be running the parallel stream in another multi-thread environment.
		 * Or I don't already have run other multi-thread processes happening. For
		 * example: in a web container, if I already have many requests to process in
		 * parallel, adding an additional layer of parallelism inside each request could
		 * have more negative than positive effects
		 * 
		 * https://stackoverflow.com/questions/20375176/should-i-always-use-a-parallel-stream-when-possible
		 * 
		 */

		// This arraylist is turned to a stream, and then iterated through sequentially
		// Eg.
		// Core 1: element1 -> element2 -> element3 -> element4
		arrlist.stream().forEach(element -> System.out.println(element));

		// This arraylist is turned to a stream, and then parallel mode iterated through
		// Eg. parallel mode in a 3x core machine
		// Core 1: element1 -> element2
		// Core 2: element3
		// Core 3: element4
		arrlist.parallelStream().forEach(element -> System.out.println(element));

		/**
		 * Stream Operations
		 * 
		 * Operations that can be performed on a stream are divided into 1) intermediate
		 * operations (return Stream<T>) and 2) terminal operations (return a result of
		 * definite type). Intermediate operations allow chaining.
		 * 
		 * Note: operations on streams don't change the source.
		 * 
		 * https://www.baeldung.com/java-8-streams-introduction#operations
		 */

		// the distinct() method represents an intermediate operation, which creates a
		// new stream of unique elements of the previous stream.

		// the count() method is a terminal operation, which returns stream's size.
		long count = arrlist.stream().distinct().count();
		System.out.println(count);

		/**
		 * Stream Operations - Iterating
		 * 
		 * When appropriate, stream API can substitute for, for-each, and while loops.
		 */

		// If the iteration is unimportant, like the following ...
		boolean isExist = false;

		// This for loop can be changed to ...
		for (String string : arrlist) {
			if (string.contains("a")) {
				isExist = true;
			}
		}
		// ... one line of Java 8 code
		isExist = arrlist.stream().anyMatch(element -> element.contains("a"));
		System.out.println(isExist);

		/**
		 * Stream Operations - Filtering
		 * 
		 * filter() method allows us to pick a stream of elements that satisfy a
		 * predicate.
		 */

		ArrayList<String> wordList = new ArrayList<>();
		wordList.add("OneAndOnly");
		wordList.add("Derek");
		wordList.add("Change");
		wordList.add("factory");
		wordList.add("Italy");
		wordList.add("Italy");
		wordList.add("Thursday");
		wordList.add("");
		// following code creates a Stream<String> of the List<String>, finds all
		// elements of this stream which contain char “d”, and creates a new stream
		// containing only the filtered elements
		Stream<String> streamOfWordsWithD = wordList.stream().filter(element -> element.contains("d"));
		streamOfWordsWithD.forEach(each -> System.out.println(each));

		/**
		 * Stream Operations - Mapping
		 * 
		 * map() produces a new stream after applying a function to each element of the
		 * original stream. The new stream could be of different type.
		 * 
		 * NOTE: forEach is a terminal operation, which means that, after the operation
		 * is performed, the stream pipeline is considered consumed, and can no longer
		 * be used.
		 * 
		 * https://stackify.com/streams-guide-java-8/
		 */
		// converts Stream<String> to the Stream<Integer> by applying a specific lambda
		// expression to every element of the initial Stream.
		List<String> arrlistOfNumStr = new ArrayList<>();
		arrlistOfNumStr.add("1");
		arrlistOfNumStr.add("10");
		arrlistOfNumStr.add("100");
		Stream<Integer> streamNums = arrlistOfNumStr.stream().map(uri -> Integer.valueOf(uri));
		streamNums.forEach(path -> System.out.println(path));
		
		/**
		 * Stream Operations - Collecting / Getting results
		 * 
		 * https://mkyong.com/java8/java-8-streams-map-examples/
		 */
		
		List<String> letters = Arrays.asList("a", "b", "c", "d");
		
		// Before Java8
		List<String> lettersUpper = new ArrayList<>();
		for (String s : letters) {
			lettersUpper.add(s.toUpperCase());
		}

		System.out.println(letters); // [a, b, c, d]
		System.out.println(lettersUpper); // [A, B, C, D]

		// Java 8
		List<String> lettersUpperJ8 = letters.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());
		System.out.println(lettersUpperJ8); // [A, B, C, D]
		
		// Streams apply to any data type.
		List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> numsDoubledJ8 = nums.stream()
				.map(eachNum -> eachNum * 2)
				.collect(Collectors.toList());
		System.out.println(numsDoubledJ8); // [2, 4, 6, 8, 10]

		// Streams can do more complex functions to each element
		lettersUpperJ8 = letters.stream()
				.map(eachLetter -> {
					System.out.println("this is eachLetter = " + eachLetter);
					if ("c".equals(eachLetter)) {
						return "omg";
					} else {
						return eachLetter.toUpperCase() + "123";
					}
				})
				.collect(Collectors.toList());
		System.out.println(lettersUpperJ8);
	}
}