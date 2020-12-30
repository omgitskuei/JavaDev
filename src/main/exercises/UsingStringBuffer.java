package main.exercises;

/**
 * StringBuffer vs StringBuilder
 * 
 * StringBuffer is synchronized i.e. thread safe. It means two threads can't call the methods of StringBuffer simultaneously.
 * BUT, StringBuffer is less efficient (slower) than StringBuilder.
 */

/**
 * String represents fixed-length, immutable character sequences while
 * StringBuffer represents growable and writable character sequences.
 * 
 * StringBuffer may have characters and substrings inserted in the middle or
 * appended to the end. It will automatically grow to make room for such
 * additions and often has more characters preallocated than are actually
 * needed, to allow room for growth.
 * 
 * String buffers are safe for use by multiple threads. The methods can be
 * synchronized wherever necessary so that all the operations on any particular
 * instance behave as if they occur in some serial order. Whenever an operation
 * occurs involving a source sequence (such as appending or inserting from a
 * source sequence) this class synchronizes only on the string buffer performing
 * the operation, not on the source.
 * 
 * StringBuilder: J2SE 5 adds a new string class to Java?™s already powerful
 * string handling capabilities. It is identical to StringBuffer except for one
 * important difference: it is not synchronized, which means that it is not
 * thread-safe. The advantage of StringBuilder is faster performance. However,
 * in cases in which you are using multithreading, you must use StringBuffer
 * rather than StringBuilder.
 * 
 * @author
 *
 */
public class UsingStringBuffer {
	/**
	 * Constructors
	 */

	// StringBuffer(): It reserves room for 16 characters without reallocation.
	StringBuffer s = new StringBuffer();

	// StringBuffer(int size)It accepts an integer argument that explicitly sets the
	// size of the buffer.
	StringBuffer a = new StringBuffer(20);

	// StringBuffer(String str): It accepts a String argument that sets the initial
	// contents of the StringBuffer object and reserves room for 16 more characters
	// without reallocation.
	StringBuffer d = new StringBuffer("GeeksforGeeks");

	public static void main(String[] args) {
		// Some of the most used methods are length() and capacity():
		// The length of a StringBuffer can be found by the length( ) method
		// The total allocated capacity can be found by the capacity( ) method.
		StringBuffer s = new StringBuffer("GeeksforGeeks");
		int p = s.length();
		int q = s.capacity();
		System.out.println("Length of string GeeksforGeeks=" + p);
		System.out.println("Capacity of string GeeksforGeeks=" + q);

		// append( ): It is used to add text at the end of the existence text. Here are
		// a few of its forms
		// StringBuffer append(String str)
		// StringBuffer append(int num)
		StringBuffer e = new StringBuffer("Geeksfor");
		e.append("Geeks");
		System.out.println(e); // returns GeeksforGeeks
		e.append(1);
		System.out.println(e); // returns GeeksforGeeks1

		// insert( ): It is used to insert text at the specified index position. These
		// are a few of its forms
		// StringBuffer insert(int index, String str)
		// StringBuffer insert(int index, char ch)
		// Here, index specifies the index at which point the string will be inserted
		// into the invoking StringBuffer object.
		StringBuffer t = new StringBuffer("GeeksGeeks");
		t.insert(5, "for");
		System.out.println(t); // returns GeeksforGeeks
		t.insert(0, 5);
		System.out.println(t); // returns 5GeeksforGeeks
		t.insert(3, true);
		System.out.println(t); // returns 5GetrueeksforGeeks
		t.insert(5, 41.35d);
		System.out.println(t); // returns 5Getr41.35ueeksforGeeks
		t.insert(8, 41.35f);
		System.out.println(t); // returns 5Getr41.41.3535ueeksforGeeks
		// You can even insert arrays
		char arrayOfChars[] = { 'p', 'a', 'w', 'a', 'n' };
		t.insert(2, arrayOfChars);
		System.out.println(t); // returns 5Gpawanetr41.41.3535ueeksforGeeks

		// reverse( ): It can reverse the characters within a StringBuffer object using
		// reverse( ).This method returns the reversed object on which it was called.
		t = new StringBuffer("A b c D");
		t.reverse();
		System.out.println(t); // returns D c b A

		// delete( ) and deleteCharAt( ): It can delete characters within a StringBuffer
		// by using the methods delete( ) and deleteCharAt( ).The delete( ) method
		// deletes a sequence of characters from the invoking object. Here, start Index
		// specifies the index of the first character to remove, and end Index specifies
		// an index one past the last character to remove. Thus, the substring deleted
		// runs from start Index to endIndex??1. The resulting StringBuffer object is
		// returned. The deleteCharAt( ) method deletes the character at the index
		// specified by loc. It returns the resulting StringBuffer object.These methods
		// are shown here:
		// StringBuffer delete(int startIndex, int endIndex)
		// StringBuffer deleteCharAt(int loc)
		StringBuffer y = new StringBuffer("GeeksforGeeks");
		y.delete(0, 5);
		System.out.println(y); // returns forGeeks
		y.deleteCharAt(7);
		System.out.println(y); // returns forGeek

		// replace( ): It can replace one set of characters with another set inside a
		// StringBuffer object by calling replace( ). The substring being replaced is
		// specified by the indexes start Index and endIndex. Thus, the substring at
		// start Index through endIndex??1 is replaced. The replacement string is passed
		// in str.The resulting StringBuffer object is returned.Its signature is shown
		// here:
		// StringBuffer replace(int startIndex, int endIndex, String str)
		StringBuffer u = new StringBuffer("GeeksforGeeks");
		u.replace(5, 8, "are");
		System.out.println(u); // returns GeeksareGeeks

		// ensureCapacity( ): It is used to increase the capacity of a StringBuffer
		// object. The new capacity will be set to either the value we specify or twice
		// the current capacity plus two (i.e. capacity+2), whichever is larger. Here,
		// capacity specifies the size of the buffer.
		// void ensureCapacity(int capacity)

		// StringBuffer appendCodePoint(int codePoint): This method appends the string
		// representation of the codePoint argument to this sequence.
		// Syntax c:public StringBuffer appendCodePoint(int codePoint)

		// char charAt(int index): This method returns the char value in this sequence
		// at the specified index.
		// Syntax:
		// public char charAt(int index)

		/// //IntStream chars(): This method returns a stream of int zero-extending the
		/// char values from this sequence.
		// Syntax:
		// public IntStream chars()

		// int codePointAt(int index): This method returns the character (Unicode code
		// point) at the specified index.
		// Syntax:
		// public int codePointAt(int index)

		// int codePointBefore(int index): This method returns the character (Unicode
		// code point) before the specified index.
		// Syntax:
		// public int codePointBefore(int index)
		
		// int codePointCount(int beginIndex, int endIndex): This method returns the
		// number of Unicode code points in the specified text range of this sequence.
		// Syntax:
		// public int codePointCount(int beginIndex, int endIndex)
		
		// IntStream codePoints(): This method returns a stream of code point values
		// from this sequence.
		// Syntax:
		// public IntStream codePoints()
		
		// void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin): In this
		// method, the characters are copied from this sequence into the destination
		// character array dst.
		// Syntax:
		// public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
		
		// int indexOf(String str): This method returns the index within this string of
		// the first occurrence of the specified substring.
		// Syntax:
		// public int indexOf(String str)
		// public int indexOf(String str, int fromIndex)

		// int lastIndexOf(String str): This method returns the index within this string
		// of the last occurrence of the specified substring.
		// Syntax:
		// public int lastIndexOf(String str)
		// public int lastIndexOf(String str, int fromIndex)
		
		// int offsetByCodePoints(int index, int codePointOffset): This method returns
		// the index within this sequence that is offset from the given index by
		// codePointOffset code points.
		// Syntax:
		// public int offsetByCodePoints(int index, int codePointOffset)
		
		// void setCharAt(int index, char ch): In this method, the character at the
		// specified index is set to ch.
		// Syntax:
		// public void setCharAt(int index, char ch)
		
		// void setLength(int newLength): This method sets the length of the character
		// sequence.
		// Syntax:
		// public void setLength(int newLength)
		
		// CharSequence subSequence(int start, int end): This method returns a new
		// character sequence that is a subsequence of this sequence.
		// Syntax:
		// public CharSequence subSequence(int start, int end)
		
		// String substring(int start): This method returns a new String that contains a
		// subsequence of characters currently contained in this character sequence.
		// Syntax:
		// public String substring(int start)
		// public String substring(int start, int end)
		
		// String toString(): This method returns a string representing the data in this
		// sequence.
		// Syntax:
		// public String toString()
		
		// void trimToSize(): This method attempts to reduce storage used for the
		// character sequence.
		// Syntax:
		// public void trimToSize()
	}

}
