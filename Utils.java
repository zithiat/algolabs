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

  public static int findMax(int[] arr) {
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > max) {
        max = arr[i];
      }
    }
    return max;
  }

  public static int findDigitNumber(int n) {
    int count = 0;
    while (n > 0) {
      n = n / 10; // After each division, the max number is reduced by 10th
      count++;
    }
    return count;
  }

  public static int getDigitValue(int n, int place) {
    return (n/place) % 10;
  }
}