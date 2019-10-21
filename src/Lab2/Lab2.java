import java.util.*;

class Lab2 {
  // Question 3
  public static void mergeTwoSorted(int[] arr1, int[] arr2) {
    int[] arr3 = new int[arr1.length + arr2.length];
    int i = 0; 
    int j = 0;
    int k = 0;

    while (i < arr1.length && j < arr2.length) {
      if (arr1[i] < arr2[j]) {
        // first: the value will be from k and i, 
        // then k and i will be increased by 1 
        // right after the execution of the line.
        arr3[k++] = arr1[i++]; 
      } else {
        arr3[k++] = arr2[j++];
      }
    }

    while (i < arr1.length) {
      arr3[k++] = arr1[i++];
    }

    while (j < arr2.length) {
      arr3[k++] = arr2[j++];
    }
    System.out.println("Arr 1: ");
    printArray(arr1);
    System.out.println("Arr 2: ");
    printArray(arr2);
    System.out.println("Merged array: ");
    printArray(arr3);
  }

  // Question 8
  // Using ArrayList, so we need to check 
  // before insert the generated number in to the list
  // n: the number of generated array
  // k: the max range of generated numbers
  public static void generateRandomWithArrayList(int n, int k) {
    Random ran = new Random();
    ArrayList<Integer> arrList = new ArrayList<Integer>();
    while (arrList.size() < n) {
      int a = ran.nextInt(k + 1);
      if (!arrList.contains(a)) {
        arrList.add(a);
      }
    }
    System.out.println(arrList);
  }

  // Using HashSet because HashSet does not allow duplicate item.
  public static void generateRandomWithHashSet(int n, int k) {
    Random ran = new Random();
    Set<Integer> gen = new HashSet<Integer>();
    while (gen.size() < n) {
      gen.add(ran.nextInt(k + 1));
    }
    System.out.println(gen);
  }

  public static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  // Question 6
  // PowerSet: Time Complexity: O( 2^n )
  // This iterative is the implementation of the given algo in Lab 2.
  // Each subset is in the order that the element appears in the input list. 
  // With this implementation the input is kept separately.
  public static <T> List<List<T>> powerset(Collection<T> list) {
    List<List<T>> ps = new ArrayList<List<T>>();
    ps.add(new ArrayList<T>()); // add empty set

    // for every item in the original list
    for (T item : list) {
      // A temporary holder for the generated powerset
      List<List<T>> tempPs = new ArrayList<List<T>>();
      for (List<T> subset : ps) {
        // copy all items from the current powerset's subsets
        tempPs.add(subset);

        // add the subsets appended with the current item
        List<T> newSubset = new ArrayList<T>(subset);
        newSubset.add(item);
        tempPs.add(newSubset);
      }
      ps = tempPs;
    }
    return ps;
  }
  
  // Additional approaches for the powerset. 
  // - Using Binary
  // - Using Recursive
  // Use the fact that numbers represented in binary can be
  // used to generate all the subsets of an array.
  // Java's max array size is Integer.MAX_VAL, which is 2^31 - 1.  
  // So, the max size we can handle is 30 elements, 
  // since the powerset will be 2^30 elements long.
  public static void powerSetUsingBinary(int[] set) {
    final int N = set.length;
    final int MAX_VAL = 1 << N;

    for (int subset = 0; subset < MAX_VAL; subset++) {
      System.out.print("[ ");
      for (int i = 0; i < N; i++) {
        int mask = 1 << i;
        if ((subset & mask) == mask) {
          System.out.print(set[i] + " ");
        }
      }
      System.out.print("]");
    }
    System.out.println();
  }

  // Recursively generate the powerset (set of all subsets) of an array by maintaining
  // a boolean array used to indicate which element have been selected
  // As each recursion call will represent subset here, 
  // we will add resultList(see recursion code below) to 
  // the list of subsets in each call. Iterate over elements of a set.
  // In each iteration, add elements to the list
  // explore(recursion) and make start = i + 1 
  // to go through remaining elements of the array.
  // Remove element from the list. 
  // This approach is similar to tree traversal.
  public static void powerSetRecursive(int idx, int[] set, boolean[] flags) {
    if (idx == set.length) {
      // Print found subset!
      System.out.print("[ ");
      for (int i = 0; i < set.length; i++) {
        if (flags[i]) {
          System.out.print(set[i] + " ");
        }          
      }        
      System.out.print("]");
    } else {
      // Include this element
      flags[idx] = true;
      powerSetRecursive(idx + 1, set, flags);

      // Not include this element
      flags[idx] = false;
      powerSetRecursive(idx + 1, set, flags);
    }
  }

  // Question 13
  // Assume that the given array is sorted in ascending order from left to right
  // If not sorted, use sortBinaryArray
  // rIdx = Right Index
  // lIdx = Left Index
  public static int countOnes(int[] arr, int lIdx, int rIdx) {
    // If last element of the array is 0, no 1 in the array
    if (arr[rIdx] == 0) 
      return 0;
    // If first element of the array is 1, return the length of the checking array
    // which is (rightIndex - leftIndex + 1)
    if (arr[lIdx] == 1) 
      return rIdx - lIdx + 1;

    // Apply divide and conquer approach and recur
    int middle = (lIdx + rIdx) / 2;
    return countOnes(arr, lIdx, middle) + countOnes(arr, middle + 1, rIdx);
  }

  // Simple sort method
  public static int[] sortBinaryArray(int[] arr) {
    // printArray(arr); // Before sorting debug

    int count = 0;
    // Count how many 0
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == 0) {
        count++;
      }
    }

    // Fill in all 0 occurrences first
    for (int i = 0; i < count; i++) {
      arr[i] = 0;
    }

    // Then fill in remaining 1 occurrences
    for (int i = count; i < arr.length; i++) {
      arr[i] = 1;
    }
    
    // printArray(arr); // After sorting debug
    return arr;
  }
}