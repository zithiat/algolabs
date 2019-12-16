import java.util.*;

class RadixSort {
	public static void runRadixSort(int n, int range) {
		System.out.println("=== Radix Sort ===");
		/*
		 * if (range <= 0) range = Integer.MAX_VALUE; int[] arr = Utils.generateArray(n,
		 * range); long start = System.nanoTime();
		 */
		int[] arr = { 80, 1, 46, 53, 28, 55, 32, 6, 9 };
		System.out.println(Arrays.toString(arr));
		int[] sortedArr = sort(arr);
		System.out.println(Arrays.toString(sortedArr));
		// System.out.println("Time taken: " + (System.nanoTime() - start) + " ns for
		// sorting " + n + " numbers");
	}

	public static int[] sort(int[] input) {
		int max = Utils.findMax(input);
		int digitNumber = Utils.findDigitNumber(max);
		System.out.println("Max: " + max);
		System.out.println("Digit number: " + max);
		int digitPlace = (int) Math.pow(10, digitNumber);
		System.out.println("Digit place: " + digitPlace);
		for (int place = 1; place <= digitPlace; place *= 10) {
			// Using counting sort at each digit place
			input = countingSort(input, place, 10); // Using base-10
			System.out.println("place=" + place + "--array=" + Arrays.toString(input));
		}
		return input;
	}

	/*
	 * Counting sort uses three lists: - the input list, A[0,1,…,n], - the output
	 * list, B[0,1,…,n], - and a list that serves as temporary memory,
	 * C[0,1,…,k]. Note that A and B have n slots (a slot for each element), while
	 * C contains k slots (a slot for each key value). This function is based on
	 * https://brilliant.org/wiki/counting-sort/
	 */
	private static int[] countingSort(int[] input, int place, int base) {
		int[] output = new int[input.length];

		int[] counting = new int[base];

		// Calculate for the temporary C array with the corresponding digit
		for (int i = 0; i < input.length; i++) {
			counting[Utils.getDigitValue(input[i], place)] += 1; // counting for C array
		}
		System.out.println("counting=" + Arrays.toString(counting));

		// Now add up the C array to the Modifed C array
		for (int i = 1; i < counting.length; i++) {
			counting[i] += counting[i - 1];
		}
		System.out.println("modified counting=" + Arrays.toString(counting));

		// Last step, starting at the end of A, add elements to B by checking
		// the value of A[i], going to C[A[i]], writing the value of
		// the element at A[i] to B[C[A[i]]].
		// Finally, decrement the value of C[A[i]] by 1 since that slot in B is now
		// occupied.
		int digit = 0;
		System.out.println("last step");
		for (int i = input.length - 1; i >= 0; i--) {
			digit = Utils.getDigitValue(input[i], place);
			System.out.println(
					"digit=" + digit + "--counting[digit]-1=" + (counting[digit] - 1) + "--input[i]=" + input[i]);
			output[counting[digit] - 1] = input[i]; // Since the array index starts from 0, we need to minus 1
			counting[digit]--;
			System.out.println("updated counting=" + Arrays.toString(counting));
			System.out.println("updated output=" + Arrays.toString(output));
		}
		System.out.println("output=" + Arrays.toString(output));
		return output;
	}
}