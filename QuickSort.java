public class QuickSort {
	/**
		* Quick sort the given array in ascending order 
		* 
		* @param array
		*/
	public static void sort(int[] array) {
			sort(array, 0, array.length - 1);
	}
    
	/**
		* Quick sort the given array starting from index 
		* {@code l} to {@code r} 
		* 
		* Uses the first element in the array as the pivot 
		* 
		* @param array
		* @param l
		* @param r
		*/
	private static void sort(int[] array, int l, int r) {
		if (l < r) {
			// select pivot element (left-most)  
			int pivot = array[l];
			// partition and shuffle around pivot 
			int i = l;
			int j = r;
			while (i < j) {
				//System.out.println("i < j: " + i + " < " + j + "--r=" + r + "--l=" + l + "--pivot=" + pivot + "--array[i]=" + array[i] + "--array[j]=" + array[j]);
				//Utils.printArray(array);
				// move right to avoid pivot element 
				i += 1;
				// scan right: find elements greater than pivot 
				while (i <= r && array[i] < pivot) {
					//System.out.println("scan right: i <= r && array[i] < pivot--i=" + i + "--r=" + r + "--j=" + j + "--l=" + l + "--pivot=" + pivot + "--array[i]=" + array[i] + "--array[j]=" + array[j]);
					//Utils.printArray(array);
					i++;
				}
				// scan left: find elements smaller than pivot
				while (j >= l && array[j] > pivot) {
					//System.out.println("scan left: j >= l && array[j] > pivot--i=" + i + "--r=" + r + "--j=" + j + "--l=" + l + "--pivot=" + pivot + "--array[i]=" + array[i] + "--array[j]=" + array[j]);
					//Utils.printArray(array);
					j--;
				}
				if (i <= r && i < j) {
					//System.out.println("i <= r && i < j--i=" + i + "--r=" + r + "--j=" + j + "--l=" + l + "--pivot=" + pivot + "--array[i]=" + array[i] + "--array[j]=" + array[j]);
					//Utils.printArray(array);
					// swap around pivot 
					swap(array, i, j);
					//System.out.println("While INNER - Swapping ---i=" + i + "--j=" + j);
					//Utils.printArray(array);
				}
			}
			//System.out.println("While OUTTER - Swapping ---left=" + l + "--j=" + j);
			// put pivot in correct place
			swap(array, l, j);
			
			// sort partitions 
			int tmpR = j - 1;
			//System.out.println("Sort RIGHT partition l=" + l + "--r(j - 1)=" + tmpR);
			sort(array, l, tmpR);
			
			int tmpL = j + 1;
			//System.out.println("Sort LEFT partition l(j + 1)=" + tmpL + "--r=" + r);
			sort(array, tmpL, r);
		}
	}
    
	/**
		* Swap elements at indexes {@code i} and {@code j}
		* in the give array 
		* 
		* @param array
		* @param i
		* @param j
		*/
	private static void swap(int[] array, int i, int j) {
		if (i >= 0 && j >= 0 && i < array.length && j < array.length) {
			int tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
		}
	}
}