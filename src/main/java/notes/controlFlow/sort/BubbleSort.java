package main.java.notes.controlFlow.sort;

import java.util.ArrayList;

public class BubbleSort {
	// Local Fields

	// Constructors
	public BubbleSort() {
		System.out.println("BEGIN: util.BubbleSort()");
	}

	public static void main(String args[]) {
		BubbleSort instance = new BubbleSort();
		
		ArrayList<Integer> sortThis = instance.makeData();

		instance.bubbleSort(sortThis, true);
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
	
	// Bubble Sort
	// Time Complexity: O(n) Best case occurs when array is already sorted. O(n*n) Average Case & Worst case occurs when array is reverse sorted.
	// Auxiliary Space: O(1)
	// Can be optimized to break if no swaps happen
	public void bubbleSort(ArrayList<Integer> sortThis, boolean ascendingOrder) {
		// Local Variables
		int countActions = 0;
		int temp = 0;
		boolean swapped = false;
		System.out.println("BEGIN: util.BubbleSort.bubbleSort(ArrayList<Integer>, boolean)");
		try {
			// Two forLoops to iterate down whole arrayList
			for (int index = 0; index < sortThis.size() - 1; index++) {
				countActions++;
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
		System.out.println(countActions);
		System.out.println("FINISH: util.BubbleSort.bubbleSort(ArrayList<Integer>, boolean)");
	}
}
