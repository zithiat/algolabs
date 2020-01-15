import java.util.Arrays;

public class MyKnapsack {
	static int[][] knap;
	
	/**
	 * Computes the maximum value of the knapsack
	 * 
	 * Inputs: two arrays - a[], c[] and an integer b 
	 * Output: the maximum value achieved
	 * 
	 */
	public static int knapsack01(int[] a, int[] c, int b) {
		// instantiate knap the dynamic programming matrix
		knap = new int[a.length + 1][b];
		// initial row - solutions to the subproblems with no items selected
		for (int i = 0; i < b; i++)
			knap[0][i] = 0;
		// process one item at a time for each weight
		for (int k = 1; k <= a.length; k++)
			for (int y = 1; y <= b; y++)
				if (y < a[k - 1])
					knap[k][y - 1] = knap[k - 1][y - 1];
				else if (y > a[k - 1])
					knap[k][y - 1] = Math.max(knap[k - 1][y - 1], knap[k - 1][y - 1 - a[k - 1]] + c[k - 1]);
				else
					knap[k][y - 1] = Math.max(knap[k - 1][y - 1], c[k - 1]);
		return knap[a.length][b - 1];
	}

	/**
	 * Determine which items are selected to maximum carrying value of the knapsack
	 * finds an array which contains 1 in the item's slot if the item is selected
	 * and 0 otherwise
	 * 
	 * Inputs: the dynamic programming matrix, the length of the second sequence,
	 * 		   the first sequence
	 * Output: an array containing 1 in the item's slot if the item is selected 
	 * 		   and 0 otherwise
	 * 
	 */
	public static int[] findKnap(int[][] knap, int b, int[] a) { // 
		int k = a.length;
		int[] soln = new int[k];
		for (int j = 0; j < k; j++)
			soln[j] = 0;
		while (k > 0) {
			int val = knap[k][b - 1];
			while (knap[k - 1][b - 1] == val && k - 1 > 0) {
				k--;
			}
			soln[k - 1] = 1;
			b -= a[k - 1];
			k--;
		}
		return soln;
	}
	
	public static void printMatrix(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int n : m[i]) {
				System.out.print(n + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[] weights = {1, 3, 2, 5, 4};
		int[] values = {4, 2, 3, 1, 1};
		int limit = 7;
		System.out.println(knapsack01(weights, values, limit));
		
		printMatrix(knap);
		
		// knap matrix is generated from the knapsack01() method
		int[] soln = findKnap(knap, limit, weights);
		System.out.println(Arrays.toString(soln));
	}
}
