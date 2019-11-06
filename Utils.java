import java.util.*;

class Utils {
  public static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static int[] generateArray(int n, int range) {
    Random rnd = new Random();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = rnd.nextInt(range);
    }
    return arr;
  }
}