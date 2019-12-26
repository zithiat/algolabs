import java.util.Arrays;

public class MyMinHeap {
	private int[] data;
	private int heapSize;
	public MyMinHeap(int size) {
		data = new int[size];
		heapSize = 0;
	}
	
	public Integer getMinimum() {
		if (isEmpty()) 
			return null;
		else
			return data[0];
	}
	
	public boolean isEmpty() {
		return heapSize == 0;
	}
	
	private int getLeftChildIndex(int nodeIndex) {
		return 2 * nodeIndex + 1;
	}
	
	private int getRightChildIndex(int nodeIndex) {
		return 2 * nodeIndex + 2;
	}
	
	private int getParentIndex(int nodeIndex) {
		return (nodeIndex - 1) / 2;
	}
	
	public void insertItem(int value) {
		System.out.println("Insert " + value);
		if (heapSize == data.length) {
			System.out.println("Heap is full");
		} else {
			heapSize++;
			data[heapSize - 1] = value;
			siftUp(heapSize - 1);
		}
	}
	
	private void siftUp(int nodeIndex) {
		int parentIndex, tmp;
		if (nodeIndex != 0) {
			parentIndex = getParentIndex(nodeIndex);
			String aa = (data[parentIndex] > data[nodeIndex]) ? "swap and siftUp" : "no swap"; 
			System.out.println("parent=" + data[parentIndex] + ", current=" + data[nodeIndex] + "=>" + aa);
			if (data[parentIndex] > data[nodeIndex]) {
				System.out.println("SiftUp between parent=" + data[parentIndex] + ", current=" + data[nodeIndex]);
				tmp = data[parentIndex];
				data[parentIndex] = data[nodeIndex];
				data[nodeIndex] = tmp;
				siftUp(parentIndex);
			}
		}
	}
	
	public int removeMin() {
		System.out.println("Remove minimum value " + data[0]);
		int min = Integer.MIN_VALUE;
		if (isEmpty()) {
			System.out.println("Heap is empty");
		} else {
			min = data[0];
			data[0] = data[heapSize - 1];
			heapSize--;
			if (heapSize > 0) {
				siftDown(0);
			}
		}
		return min;
	}
	
	private void siftDown(int nodeIndex) {
		int leftChildIndex, rightChildIndex, minIndex, tmp;
		leftChildIndex = getLeftChildIndex(nodeIndex);
		rightChildIndex = getRightChildIndex(nodeIndex);
		if (rightChildIndex >= heapSize) {
			if (leftChildIndex >= heapSize) 
				return;
			else
				minIndex = leftChildIndex;
		} else {
			if (data[leftChildIndex] <= data[rightChildIndex]) {
				minIndex = leftChildIndex;
			} else {
				minIndex = rightChildIndex;
			}
		}
		if (data[nodeIndex] > data[minIndex]) {
			System.out.println("SiftDown for current=" + data[nodeIndex] + ", minimum=" + data[minIndex]);
			tmp = data[nodeIndex];
			data[nodeIndex] = data[minIndex];
			data[minIndex] = tmp;
			siftDown(minIndex);
		}
	}
	
	public String toString() {
		return Arrays.toString(data);
	}
	
	public int[] heapSortArrayBased() {
		int len = heapSize;
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = removeMin();
		}
		return arr;
	}
	
	public void restoreDown(int arr[], int i, int size) {
		System.out.println("Call Restore Down");
		int left = 2 * i;
		int right = 2 * i + 1;
		int num = arr[i];
		System.out.println("left=" + arr[left] + ", right=" + arr[right] + ", current arr[i]=" + arr[i]);
		while (right < size) {
			System.out.println("Right index < heapSize");
			if (num > arr[left] && num > arr[right]) {
				System.out.println("Current > left child && current > right child");
				arr[i] = num;
				return;
			} else if (arr[left] > arr[right]) {
				System.out.println("Left child > right child -> swap current with left");
				arr[i] = arr[left];
				i = left;
			} else {
				System.out.println("Left child < right child -> swap current with right");
				arr[i] = arr[right];
				i = right;
			}
			left = 2 * i;
			right = 2 * i + 1;
		}
		if (left == size && arr[left] > num) {
			System.out.println("Left child index = size && left child > current node -> swap with left");
			arr[i] = arr[left];
			i = left;
		}
		arr[i] = num;
		System.out.println(Arrays.toString(arr));
	}

	public void buildBottomUpHeap(int arr[], int size) {
		System.out.println("buildBottomUpHeap: " + Arrays.toString(arr));
		int i;
		for (i = size / 2 - 1; i >= 0; i--) {
			restoreDown(arr, i, size);
		}
		for (int j = arr.length - 1; j >=0; j--) {
			data[arr.length - j - 1] = arr[j];
		}
		System.out.println("Heap (data): " + Arrays.toString(data));
	}

	public static void main(String[] args) {
		int arr[] = {1, 2, 4, 4, 5, 6, 9, 11, 12, 12, 17};
		MyMinHeap my = new MyMinHeap(arr.length);
		System.out.println("Create heap from array with the same size: " + Arrays.toString(arr));
		for (int i : arr) {
			my.insertItem(i);
		}
		System.out.println("Heap in array: " + my.toString());
		
		my.insertItem(7);
				
		System.out.println("Remove root node=" + my.removeMin());
		System.out.println("Updated heap in array: " + my.toString());
		
		my.insertItem(14);
		System.out.println("Updated heap in array: " + my.toString());
		
		System.out.println("Remove root node=" + my.removeMin());
		System.out.println("Updated heap in array: " + my.toString());
		
		int[] arr1 = {1, 4, 3, 9, 12, 2, 4};
		MyMinHeap my1 = new MyMinHeap(arr1.length);
		for (int i : arr1) 
			my1.insertItem(i);
		System.out.println("Heap in array: " + my1.toString());
		System.out.println("HeapSort in Array-based: " + Arrays.toString(my1.heapSortArrayBased()));
		
		int[] arr2 = {11, 5, 2, 3, 17, 24, 1};
		MyMinHeap my2 = new MyMinHeap(arr2.length);
		my2.buildBottomUpHeap(arr2, arr2.length);
	}
}
