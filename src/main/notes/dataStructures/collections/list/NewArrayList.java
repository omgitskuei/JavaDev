package main.notes.dataStructures.collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewArrayList {

	public static void main(String[] args) {
		List<String> list = Arrays.asList(new String[] { "test1", "test2", "test3" });
		System.out.println(list);
		System.out.println(list.contains("test1"));
	}

}
