import java.util.*;

class Main {
  public static void main(String[] args) {
    // Call Lab2 exercises
    Lab2Exercises();
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
    list.add("A");
    list.add("B");
    list.add("C");
    System.out.println(Lab2.powerset(list));
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
}