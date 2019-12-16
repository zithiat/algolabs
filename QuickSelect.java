public class QuickSelect {
	public static int findMedian(int[] arr) {
		int n = arr.length;
		return quickSelect(arr, n / 2, 0, n - 1);
	}

	private static int quickSelect(int[] arr, int pos, int left, int right) {
		if (left == right && left == pos) {
			System.out.println("left == right && left == pos: left=" + left + "--right=" + right + "--pos=" + pos
					+ "--arr[left]=" + arr[left]);
			return arr[left];
		}
		System.out.println("Partitioning with left=" + left + "--right=" + right + "--pos=" + pos);
		int posRes = partition(arr, left, right, pos);
		if (posRes == pos) {
			System.out.println("posRes == pos: posRes=" + posRes + "--pos=" + pos + "--arr[posRes]=" + arr[posRes]);
			return arr[posRes];
		} else if (posRes < pos) {
			System.out.println("posRes < pos: posRes=" + posRes + "--pos=" + pos + "--arr[posRes]=" + arr[posRes]);
			return quickSelect(arr, pos, posRes + 1, right);
		} else {
			System.out.println("posRes > pos: posRes=" + posRes + "--pos=" + pos + "--arr[posRes]=" + arr[posRes]);
			return quickSelect(arr, pos, left, posRes - 1);
		}
	}

	private static int partition(int[] arr, int left, int right, int pos) {
		int pivot = arr[left];
		int position = left;
		System.out.println("Partition: pivot=" + pivot + "--position=" + position + "--left=" + left + "--right="
				+ right + "--pos=" + pos);
		for (int i = left + 1; i <= right; i++) {
			System.out.println(
					"Partition-loop: pivot=" + pivot + "--i=" + i + "--arr[i]=" + arr[i] + "--position=" + position);
			if (arr[i] <= pivot) {
				System.out.println("arr[i] <= pivot: pivot=" + pivot + "--i=" + i + "--arr[i]=" + arr[i] + "--position="
						+ position);
				position++;
				System.out.println("Swap IN loop: i=" + i + "--arr[i]=" + arr[i] + "--postion=" + position
						+ "--arr[position]=" + arr[position]);
				swap(arr, i, position);
				System.out.println("AFTER Swap IN loop:");
				Utils.printArray(arr);
			}
		}
		System.out.println("Swap after loop: left=" + left + "--arr[left]=" + arr[left] + "--postion=" + position
				+ "--arr[position]=" + arr[position]);
		swap(arr, left, position);
		System.out.println("AFTER Swap after loop:");
		Utils.printArray(arr);
		return position;
	}

	private static void swap(int[] arr, int first, int second) {
		System.out.println("first=" + first + "--arr[first]=" + arr[first] + "--second=" + second + "--arr[second]="
				+ arr[second]);
		int tmp = arr[first];
		arr[first] = arr[second];
		arr[second] = tmp;
	}
}