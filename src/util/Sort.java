package util;

import java.util.ArrayList;

public class Sort {
	// Local Fields

	// Constructors
	public Sort() {
		System.out.println("BEGIN: util.Sort");
	}

	// Methods
	public void bubbleSort(ArrayList<Integer> sortThis, boolean ascendingOrder) {
		// Local Variables
		int temp = 0;
		System.out.println("BEGIN: util.Sort.bubbleSort");
		// sorting in Ascending Order (Smaller to Bigger)
		if ( ascendingOrder==true) {
			try {
				// Two forLoops to iterate down whole arrayList
				for (int index=0; index<sortThis.size()-1; index++) {
					for (int index1=0; index1<sortThis.size()-1; index1++) {
						// Swap Variables if latter number was Smaller than former
						if (sortThis.get(index1)>sortThis.get(index1+1)) {
							//Ascending Order Swap
							temp = sortThis.get(index1);
							sortThis.set(index1, sortThis.get(index1+1));
							sortThis.set(index1+1, temp);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		// sorting in Descending Order
		} else {
			try {
				for (int index=0; index<sortThis.size()-1; index++) {
					//
					for (int index1=0; index1<sortThis.size()-1; index1++) {
						// Swap Variables if latter is Bigger than former
						if (sortThis.get(index1)<sortThis.get(index1+1)) {
							//Descending Order Swap
							temp = sortThis.get(index1);
							sortThis.set(index1, sortThis.get(index1+1));
							sortThis.set(index1+1, temp);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("FINISH: util.Sort.bubbleSort");
	}
}
