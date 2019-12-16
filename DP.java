public class DP {
	/**
	 * https://brilliant.org/practice/dynamic-programming-introduction/?p=12 Amanda
	 * can climb 1, 2, 3, or 4 stairs at a time. How many different ways can she
	 * climb up exactly 12 stairs? For example, one way would be: 4 -> 2 -> 3 -> 2
	 * -> 1
	 */
	public static long countingStairs(int n) {
		if (n == 0)
			return 0;
		else if (n <= 4 & n > 0)
			return (long) Math.pow(2, n - 1);
		else
			return countingStairs(n - 4) + countingStairs(n - 3) + countingStairs(n - 2) + countingStairs(n - 1);
	}

	public static void runCountingStairs(int n) {
		long ways = countingStairs(n);
		System.out.printf("Number of ways to count %d-th stairs is %d\n", n, ways);
	}

	public static void runTileProblem(int n, int m) {
		System.out.printf("Number of ways to place 1x1 tile on %dx%d area: %d\n", n, m, (long) Math.pow(n, m));
	}

	public static void runDPTileProblem(int n) {
		System.out.printf("Number of ways to place 2x1 tile on 2x%d area: %d\n", n, dpTileProblem(n));
	}

	/**
	 * Let's try to generalize this for an 2×n floor. Again, we have two ways we
	 * can start from the left side: - Two horizontal - One vertical If we start
	 * with one vertical tile we have a{n-1} ways to do this since we have a
	 * (N−1)×2 area of floor left over. If we start with two horizontal tiles we
	 * have a{n-2} ways to do this since we have a (N−2)×2 area of floor left
	 * over. So, we can see that the general equation for a_n given all the previous
	 * values for a_m (m<n) is given by: a_n = a{n-1} + a{n-2} So, how many ways can
	 * we tile a 2×10 floor?
	 */
	public static int dpTileProblem(int n) {
		if (n == 1)
			return 1;
		else if (n == 2)
			return 2;
		else {
			return dpTileProblem(n - 1) + dpTileProblem(n - 2);
		}
	}
}