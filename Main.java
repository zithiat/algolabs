import java.util.*;

class Main {
  public static void main(String[] args) {
    // Call Lab2 exercises
    //Lab2Exercises();
    
    // Find n-th number from Fibonacci series with various algorithms
    //Fibonacci f = new Fibonacci();
    //f.testFibonacci();

    //Lab4Exercise();

    //reverseString();
    
    //RadixSort.runRadixSort(1000, 10000);

		//int[] arr1 = {1, 6, 2, 4, 3, 5};
		//Utils.printArray(arr1);
		//QuickSort.sort(arr1);
		//Utils.printArray(arr1);

		//int[] arr1 = {1, 12, 8, 7, -2, -3, 6};
		//Utils.printArray(arr1);
		//System.out.print("Median=" + QuickSelect.findMedian(arr1));

		//int[] arr2 = {-10, 1, 4, 12, 8, 7, -2, -3, 6};
		//Utils.printArray(arr2);
		//System.out.print("Median=" + QuickSelect.findMedian(arr2) + "\n");
		//Utils.printArray(arr2);

		//int[] arr3 = {5, 1, 4, 3, 6, 2, 7, 1, 3};
		//Utils.printArray(arr3);
		//System.out.print("Median=" + QuickSelect.findMedian(arr3) + "\n");
		//Utils.printArray(arr3);

		//int arr[] = {9, 10, 5, 3, 1, 2, 6};
		//int arr[] = {10, 5, 10, 1};// 5 comparisons
		//System.out.println(Arrays.toString(arr));
		//HeapSort.heapSort(arr);
		//System.out.println(Arrays.toString(arr));
		//System.out.println("CounterLeft=" + HeapSort.counterLeft);
		//System.out.println("CounterRight=" + HeapSort.counterRight);
		//System.out.println("counterNotRoot=" + HeapSort.counterNotRoot);

		//int[] arr = {1, 2, 17, -4, -6, 8};
		//System.out.println("Array: " + Arrays.toString(arr));
		//SortIntoPairs.runSortIntoPairs(arr); // it should be {-6, 17, -4, 8, 1, 2}

		//int[] arr1 = {1, 2, 17, -4, -6, 8, 5};
		//System.out.println("Array: " + Arrays.toString(arr1));
		//SortIntoPairs.runSortIntoPairs(arr1); // it should be {-6, 17, -4, 8, 1, 5, 2}

		//int[] arr = {12, 11, 13, 5, 6, 7};
		//System.out.println("Array: " + Arrays.toString(arr));
		//MergeSort.sort(arr, 0, arr.length - 1);
		//System.out.println("Array: " + Arrays.toString(arr));

		//int[] arr = {64, 25, 12, 22, 11};
		//System.out.println("Array: " + Arrays.toString(arr));
		//SelectionSort.sort(arr);
		//System.out.println("Array: " + Arrays.toString(arr));

		//int[] arr = {12, 11, 13, 5, 6};
		//System.out.println("Array: " + Arrays.toString(arr));
		//InsertionSort.sort(arr);
		//System.out.println("Array: " + Arrays.toString(arr));

		RadixSort.runRadixSort(1, 1);
  }

  // For Lab 2 coding questions
  public static void Lab2Exercises() {
    System.out.println("=== Lab 2 - Question 3 ===");
    int[] arr1 = { 1, 4, 5, 8, 17 };
    int[] arr2 = { 2, 4, 8, 11, 13, 21, 23, 25 };
    Lab2.mergeTwoSorted(arr1, arr2);
    System.out.println();

    System.out.println("=== Lab 2 - Question 6 ===");
    List<String> list = new ArrayList<String>();
    long start = System.currentTimeMillis();
    for(int i = 0; i < 3; i++) {
      list.add(i + "");
    }
    System.out.println(Lab2.powerset(list));
    System.out.println("Time taken: " + (System.currentTimeMillis() - start) + " ms");

    // int[] set = {1, 2, 3, 4};
    // System.out.println("PowerSet using binary from set {1, 2, 3, 4}");
    // Lab2.powerSetUsingBinary(set);
    // System.out.println("PowerSet using recursive from set {1, 2, 3, 4}");
    // Lab2.powerSetRecursive(0, set, new boolean[set.length]);
    System.out.println();

    System.out.println("=== Lab 2 - Question 8 ===");
    System.out.println("Lab2.generateRandomWithArrayList(5, 50)");
    Lab2.generateRandomWithArrayList(5, 50);
    System.out.println("Lab2.generateRandomWithHashSet(5, 50)");
    Lab2.generateRandomWithHashSet(5, 50);
    System.out.println();

    System.out.println("=== Lab 2 - Question 11 ===");
    Lab2.fibonacci(10);
    System.out.println("\n");

    System.out.println("=== Lab 2 - Question 13 ===");
    int[] arrOnes1 = { 1, 1, 1, 0, 0, 0, 0, 0, 0 }; // We will need to call sortBinaryArray()
    int[] arrOnes2 = { 0, 0, 0, 0, 0, 0, 0, 1, 1 };
    int[] arrOnes3 = { 1, 1, 1, 1, 1 };
    int[] arrOnes4 = { 0, 0, 0, 0, 0, 0, 0 };
    int[] arrOnes5 = { 1, 0, 1, 0, 1, 1, 0 };
    System.out.print("arrOnes1 {1, 1, 1, 0, 0, 0, 0, 0, 0}: ");
    System.out.println(Lab2.countOnes(Lab2.sortBinaryArray(arrOnes1), 0, arrOnes1.length - 1));
    System.out.print("arrOnes2 {0, 0, 0, 0, 0, 0, 0, 1, 1}: ");
    System.out.println(Lab2.countOnes(arrOnes2, 0, arrOnes2.length - 1));
    System.out.print("arrOnes3 {1, 1, 1, 1, 1}: ");
    System.out.println(Lab2.countOnes(arrOnes3, 0, arrOnes3.length - 1));
    System.out.print("arrOnes4 {0, 0, 0, 0, 0, 0, 0}: ");
    System.out.println(Lab2.countOnes(arrOnes4, 0, arrOnes4.length - 1));
    System.out.print("arrOnes5 {1, 0, 1, 0, 1, 1, 0}: ");
    System.out.println(Lab2.countOnes(Lab2.sortBinaryArray(arrOnes5), 0, arrOnes5.length - 1));
  }

  public static void Lab4Exercise() {
    System.out.println();

    int arrLen = 1000;
    int range = 1001;
    long start = 0;
    int[] arr = Utils.generateArray(arrLen, range);
    int[] arr1 = Utils.generateArray(arrLen, range);
    int[] arr2 = Utils.generateArray(arrLen, range);
    int[] arr3 = Utils.generateArray(arrLen, 3);

    System.out.println("=== Lab 4 - BubbleSort WITHOUT flag ===");
    //Utils.printArray(arr);
    start = System.nanoTime();
    Lab4.bubbleSort(arr);
    long noFlagTime = System.nanoTime() - start;
    System.out.println("Time taken: " + noFlagTime + " ns\n");

    System.out.println("=== Lab 4 - BubbleSort WITH flag ===");
    //Utils.printArray(arr1);
    start = System.nanoTime();
    Lab4.bubbleSortWithFlag(arr1);
    long flagTime = System.nanoTime() - start;
    System.out.println("Time taken: " + flagTime + " ns\n");

    System.out.println("=== Lab 4 - BubbleSort using LARGEST IN LAST POSITION ===");
    //Utils.printArray(arr2);
    start = System.nanoTime();
    Lab4.bubbleSortUsingLastPos(arr2);
    long lastPosTime = System.nanoTime() - start;
    System.out.println("Time taken: " + lastPosTime + " ns\n");

    System.out.println("=== Lab 4 - pow(m^n) without store ===");
    start = System.nanoTime();
    long m = 2;
    int n = 50;
    long powmn = Lab4.exp(m, n);
    long powMNTime = System.nanoTime() - start;
    System.out.println("Pow of " + m + "^" + n + " is " + powmn);
    System.out.println("Time taken: " + powMNTime + " ms\n");

    System.out.println("=== Lab 4 - pow(m^n) with store ===");
    start = System.nanoTime();
    long powmnStore = Lab4.expWithStore(m, n);
    long powMNStoreTime = System.nanoTime() - start;
    System.out.println("Pow of " + m + "^" + n + " is " + powmnStore);
    System.out.println("Time taken: " + powMNStoreTime + " ms\n");

    System.out.println("=== Lab 4 - sort {0, 1, 2} arrays ===");
    start = System.nanoTime();
    Lab4.sort3ElementsArray(arr3);
    long lastSort3ElemsTime = System.nanoTime() - start;
    System.out.println("Time taken: " + lastSort3ElemsTime + " ns\n");
  }

  public static void reverseString() {
    String s = "I am a robot";
    System.out.println("Before reverse: " + s);
    String reversed = new StringBuilder(s).reverse().toString();
    System.out.println("After reverse: " + reversed);
  }
}