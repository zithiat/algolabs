class Lab4 {
	/*
	 * The original algorithm works by repeatedly swapping the adjacent elements if
	 * they are in wrong order. This algo always runs O(n^2) time even if the array
	 * is sorted.
	 */
	public static void bubbleSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				// ">": ascending order
				// "<": descending order
				if (arr[j] > arr[j + 1]) {
					// swap arr[j] and arr[j+1]
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
		// Utils.printArray(arr);
	}

	/*
	 * We optimize the original algo by stopping the algo if inner loop didn't have
	 * any swap. By using the flag, if the array is already sorted, the inner loop
	 * occurs only once for each incremental i, that means the outter loop will run
	 * O(n) time, and inner loop runs once. Ref:
	 * https://en.wikipedia.org/wiki/Bubble_sort
	 */
	public static void bubbleSortWithFlag(int[] arr) {
		int n = arr.length;
		boolean swapFlag = true;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				// ">": ascending order
				// "<": descending order
				if (arr[j] > arr[j + 1]) {
					// swap arr[j] and arr[j+1]
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
					swapFlag = true;
				}
			}
			if (swapFlag == false) {
				break;
			}
		}
		// Utils.printArray(arr);
	}

	/*
	 * Other way of sorting by moving the largest element to the last position,
	 * which is the correct position for that element in the final sorted array. The
	 * effective size of the array is reduced by one, and the process repeated until
	 * the effective size becomes one. Each bubble step moves the largest element in
	 * the effective array to the highest index of the effective array.
	 */
	public static void bubbleSortUsingLastPos(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			int newn = n - i;
			// Debug purpose
			// System.out.println("Effecitve array of size: " + newn);
			sortLargestLastPostion(arr, newn);
		}
		// Utils.printArray(arr);
	}

	/*
	 * The size of the effective array is the original size reduced by one after
	 * each step. Thus, if the initial size of the array to be sorted is lim, the
	 * size of each successive effective array is lim, lim -1, lim - 2, etc. We have
	 * included a debug statement in bubbleSortUsingLastPos() to trace the bubble
	 * process after each bubble step. This function, sortLargestLastPostion(),
	 * compares adjacent elements of an array of the specified size in sequence and
	 * swaps them if necessary.
	 */
	private static void sortLargestLastPostion(int[] arr, int size) {
		for (int i = 0; i < size - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				// swap arr[i] and arr[i + 1]
				int tmp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = tmp;
			}
		}
	}

	public static long exp(long m, int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return m;
		if (n % 2 == 0)
			return exp(m * m, n / 2);
		else
			return exp(m * m, (n - 1) / 2) * m;
	}

	public static long expWithStore(long m, int n) {
		long store;
		if (n == 0)
			return 1;
		store = exp(m, n / 2);
		if (n % 2 == 0)
			return store * store;
		else
			return store * store * m;
	}

	public static void sort3ElementsArray(int[] arr) {
		int counter0 = 0;
		int counter1 = 0;
		int counter2 = 0;
		// Loop for counters
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				counter0++;
			} else if (arr[i] == 1) {
				counter1++;
			} else {
				counter2++;
			}
		}
		// Loops to arrange elements in order from left to right
		// Starting with loop for 0
		for (int i = 0; i < counter0; i++) {
			arr[i] = 0;
		}
		// Then loop for 1
		for (int i = counter0; i < counter0 + counter1; i++) {
			arr[i] = 1;
		}
		// And loop for 2
		for (int i = counter0 + counter1; i < counter0 + counter1 + counter2; i++) {
			arr[i] = 2;
		}
		// Utils.printArray(arr);
	}
}