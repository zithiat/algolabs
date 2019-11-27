import java.util.Arrays;

public class SortIntoPairs {

	private static int maxIdx = -1;
	private static int minIdx = -1;

	public static void runSortIntoPairs(int[] arr) {
		int n = arr.length;
		int[] resArr = new int[n];
		int pairCount = n/2;
		int cnt = 0;
		for (int i = 0; i < pairCount; i++) {
			// find the MINIMUM of the given array and fill into the first position of the pair
			resArr[i + cnt] = findMin(arr);
			// reduce the given array by removing the found MINIMUM element
			arr = reduceArray(arr, minIdx);
			
			// find the MAXIMUM of the given array and fill into the second position of the pair
			resArr[i + cnt + 1] = findMax(arr);
			// reduce the given array by removing the found MAXIMUM element
			arr = reduceArray(arr, maxIdx);

			// Jump to the next pair
			cnt = cnt + 1;
		}
		if (arr.length > 0) {
			resArr[resArr.length - 1] = arr[arr.length - 1];
		}
		System.out.println("Sorted array into pairs: " + Arrays.toString(resArr));
	}

	private static int findMax(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
				maxIdx = i;
			}
		}
		return max;
	}

	private static int findMin(int[] arr) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
				minIdx = i;
			}
		}
		return min;
	}

	private static int[] reduceArray(int[] arr, int idx) {
		int[] retArr = new int[arr.length - 1];
		int s = 0;
		for (int i = 0; i < arr.length; i++) {
			if (i != idx) {
				retArr[s++] = arr[i];
			}
		}
		return retArr;
	}
}
