package references.sort;

import java.util.ArrayList;

public class SelectionSort {
	// Local Fields

	// Constructors
	public SelectionSort() {
		System.out.println("BEGIN: util.SelectionSort()");
	}

	public static void main(String args[]) {
		SelectionSort instance = new SelectionSort();

		ArrayList<Integer> sortThis = instance.makeData();

		instance.selectionSort(sortThis, true);
	}
	
	private ArrayList<Integer> makeData() {
		ArrayList<Integer> sortThis = new ArrayList<Integer>();
		sortThis.add(3);
		sortThis.add(2);
		sortThis.add(6);
		sortThis.add(2);
		sortThis.add(1);
		sortThis.add(1);
		sortThis.add(1);
		sortThis.add(1);
		sortThis.add(1);
		return sortThis;
	}
	
	// Selection Sort_WIP
	// Time Complexity: O(n2) as there are two nested loops.
	// Auxiliary Space: O(1)
	// The good thing about selection sort is it never makes more than O(n) swaps
	// and can be useful when memory write is a costly operation.
	public void selectionSort(ArrayList<Integer> arr, boolean ascendingOrder) {
		int n = arr.size();
		int countActions = 0;
		System.out.println("BEGIN: util.SelectionSort.selectionSort()");
		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			countActions++;
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i + 1; j < n; j++) {
				if (ascendingOrder==true) {
					if (arr.get(j) < arr.get(min_idx)) {
						min_idx = j;
					}
				} else {
					if (arr.get(j) > arr.get(min_idx)) {
						min_idx = j;
					}
				}
			}
			// Swap the found minimum element with the first
			// element
			int temp = arr.get(min_idx);
			arr.set(min_idx, arr.get(i));
			arr.set(i, temp);
		}
		System.out.println(arr);
		System.out.println(countActions);
		System.out.println("FINISH: util.SelectionSort.selectionSort()");
	}
}
