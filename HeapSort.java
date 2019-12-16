/**
 * Sorting in ascending order: - Create a max Heap from the given input array. -
 * Extract the root (it will be the maximum value in array) and replace it with
 * the last element in the array. - Heapify the root of the heap. - Repeat the
 * steps b and c till entire array is sorted. Sorting in descending order -
 * Create a min Heap from the given input array. - Extract the root (it will be
 * the minimum value in array) and replace it with the last element in the
 * array. - Heapify the root of the heap. - Repeat the steps b and c till entire
 * array is sorted. Time Complexity: O(nLogn)
 */
public class HeapSort {
	public static int counterLeft = 0;
	public static int counterRight = 0;
	public static int counterNotRoot = 0;

	public static void heapSort(int[] arrA) {
		int size = arrA.length;

		// Build heap
		for (int i = size / 2 - 1; i >= 0; i--)
			heapify(arrA, size, i);

		// One by one extract (Max) an element from heap and
		// replace it with the last element in the array
		for (int i = size - 1; i >= 0; i--) {
			// arrA[0] is a root of the heap and is the max element in heap
			int x = arrA[0];
			arrA[0] = arrA[i];
			arrA[i] = x;

			// call max heapify on the reduced heap
			heapify(arrA, i, 0);
		}
	}

	// To heapify a subtree rooted with node i which is an index in arr[]
	public static void heapify(int[] arrA, int heapSize, int i) {
		int largest = i; // Initialize largest as root
		int leftChildIdx = 2 * i + 1; // left = 2*i + 1
		int rightChildIdx = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (leftChildIdx < heapSize && arrA[leftChildIdx] > arrA[largest]) {
			largest = leftChildIdx;
			counterLeft++;
		}

		// If right child is larger than largest so far
		if (rightChildIdx < heapSize && arrA[rightChildIdx] > arrA[largest]) {
			largest = rightChildIdx;
			counterRight++;
		}

		// If largest is not root
		if (largest != i) {
			int swap = arrA[i];
			arrA[i] = arrA[largest];
			arrA[largest] = swap;
			counterNotRoot++;

			// Recursive call to heapify the sub-tree
			heapify(arrA, heapSize, largest);
		}
	}
}