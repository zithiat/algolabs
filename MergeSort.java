import java.util.*;

public class MergeSort {
	public static void sort(int[] arr, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			System.out.println("Divide LEFT: l=" + l + "--m=" + m + "--r=" + r + "--array="
					+ Arrays.toString(subArray(arr, l, m)));
			sort(arr, l, m);
			System.out.println("Divide RIGHT: l=" + l + "--m+1=" + (m + 1) + "--r=" + r + "--array="
					+ Arrays.toString(subArray(arr, m + 1, r)));
			sort(arr, m + 1, r);

			System.out.println("MERGE: l=" + l + "--m=" + m + "--r=" + r + "--array=" + Arrays.toString(arr));
			merge(arr, l, m, r);
		}
	}

	private static void merge(int[] arr, int l, int m, int r) {
		// Size of merged array
		int n1 = m - l + 1;
		int n2 = r - m;

		int[] tmpL = new int[n1];
		int[] tmpR = new int[n2];

		// copy data to temp arrays
		for (int i = 0; i < n1; i++) {
			tmpL[i] = arr[l + i];
		}
		for (int j = 0; j < n2; j++) {
			tmpR[j] = arr[m + j + 1];
		}

		// merge 2 temp arrays
		int i = 0, j = 0;
		// first index of the merged array will be the left index given
		int k = l;
		// loop based on the size of 2 temp arrays
		while (i < n1 && j < n2) {
			// compare values from tmpL and tmpR sub-arrays
			// assign value then increase the k and i or j
			if (tmpL[i] < tmpR[j]) {
				arr[k++] = tmpL[i++];
			} else {
				arr[k++] = tmpR[j++];
			}
		}
		// copy remaining elements from two sub-arrays
		while (i < n1) {
			arr[k++] = tmpL[i++];
		}
		while (j < n2) {
			arr[k++] = tmpR[j++];
		}
	}

	private static int[] subArray(int[] arr, int l, int r) {
		int[] resArr = new int[r - l + 1];
		for (int i = 0; i < resArr.length; i++) {
			resArr[i] = arr[l + i];
		}
		return resArr;
	}
}