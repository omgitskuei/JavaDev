package util;

import java.util.ArrayList;

public class Sort {
	// Local Fields

	// Constructors
	public Sort() {
		System.out.println("BEGIN: util.Sort");
	}

	// Methods

	// Selection Sort
	// Time Complexity: O(n2) as there are two nested loops.
	// Auxiliary Space: O(1)
	// The good thing about selection sort is it never makes more than O(n) swaps
	// and can be useful when memory write is a costly operation.
	public void selectionSort(int arr[], boolean ascendingOrder) {
		int n = arr.length;
		System.out.println("BEGIN: util.Sort.selectionSort");
		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i + 1; j < n; j++) {
				if (ascendingOrder==true) {
					if (arr[j] < arr[min_idx]) {
						min_idx = j;
					}
				} else {
					if (arr[j] > arr[min_idx]) {
						min_idx = j;
					}
				}
				
			}
			// Swap the found minimum element with the first
			// element
			int temp = arr[min_idx];
			arr[min_idx] = arr[i];
			arr[i] = temp;
		}
	}

	// Bubble Sort
	// Time Complexity: O(n) Best case occurs when array is already sorted. O(n*n) Average Case & Worst case occurs when array is reverse sorted.
	// Auxiliary Space: O(1)
	// Can be optimized to break if no swaps happen
	public void bubbleSort(ArrayList<Integer> sortThis, boolean ascendingOrder) {
		// Local Variables
		int temp = 0;
		boolean swapped = false;
		System.out.println("BEGIN: util.Sort.bubbleSort");
		try {
			// Two forLoops to iterate down whole arrayList
			for (int index = 0; index < sortThis.size() - 1; index++) {
				System.out.println("Current ArrayList: "+sortThis);
				swapped = false;
				for (int index1 = 0; index1 < sortThis.size() - 1; index1++) {
					// Swap Variables if latter is Smaller than former
					if (ascendingOrder == true) {
						System.out.println("	Comparing if index[" + index1 + "]" + "{" + sortThis.get(index1) + "}"
								+ " > " + "index[" + (index1 + 1) + "]" + "{" + sortThis.get(index1 + 1) + "}");
						if (sortThis.get(index1) > sortThis.get(index1 + 1)) {
							System.out.println("		Swapping");
							// Ascending Order Swap
							temp = sortThis.get(index1);
							sortThis.set(index1, sortThis.get(index1 + 1));
							sortThis.set(index1 + 1, temp);
							swapped = true;
						}
					}
					// Swap Variables if latter is Bigger than former
					else {
						System.out.println("	Comparing if index[" + index1 + "]" + "{" + sortThis.get(index1) + "}"
								+ " < " + "index[" + (index1 + 1) + "]" + "{" + sortThis.get(index1 + 1) + "}");
						if (sortThis.get(index1) < sortThis.get(index1 + 1)) {
							System.out.println("		Swapping");
							// Descending Order Swap
							temp = sortThis.get(index1);
							sortThis.set(index1, sortThis.get(index1 + 1));
							sortThis.set(index1 + 1, temp);
							swapped = true;
						}
					}
				}
				if (swapped == false) {
					System.out.println("No Swaps Happened: Breaking!");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("FINISH: util.Sort.bubbleSort");
	}
}
