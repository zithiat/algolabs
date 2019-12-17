
public class CheckingPrime {
	
	/**
	 * Instead of checking until n, we check until square of n
	 * Also, all primes are of the form 6k +/- 1 (not including 2, 3).
	 * https://en.wikipedia.org/wiki/Primality_test
	 * @return
	 */
	static boolean isPrimeSimple(int n) {
		if (n <= 1) return false;
		if (n <= 3) return true; // n = 2 or 3
		if (n % 2 == 0 || n % 3 == 0) return false;
		for (int i = 5; i * i <= n; i = i + 6)
			if (n % i == 0 || n % (i + 2) == 0)
				return false;
		return true;
	}
	
	/**
	 * Fermat's Little Theorem If n is a prime, then for every a, 1 < a < n - 1,
	 * a^(n-1) = 1 (mod n) OR a^(n-1) % n = 1
	 * 
	 * https://en.wikipedia.org/wiki/Proofs_of_Fermat's_little_theorem 
	 * If n is prime, then always returns true.
	 * If n is composite than returns false with high probability. 
	 * Higher value of k increases probability of correct result.
	 */
	static boolean isPrimeFermat(int n) {
		int k = 3;
		if (n <= 1 || n == 4)
			return false;
		if (n <= 3)
			return true;

		// Try k times
		while (k > 0) {
			// Pick a random number in [2..n-2]
			// Above corner cases make sure that n > 4
			int a = 2 + (int) (Math.random() % (n - 4));
			
			// Fermat's little theorem
			if (powerFermat(a, n - 1, n) != 1)
				return false;
			k--;
		}
		return true;
	}
	
	 
    /**
     * Iterative Function to calculate (a^n)%p in O(logy)
     */
    static int powerFermat(int a,int n, int p) { 
        int res = 1; 
          
        // if a >= p 
        a = a % p;  
      
        while (n > 0) { 
            // If n is odd, multiply a with result 
            if ((n & 1) == 1)
                res = (res * a) % p;

            // n must be even now 
            n = n >> 1; // n = n/2 
            a = (a * a) % p; 
        }
        return res;
    }
    
    /**
     * It returns false if n is composite and returns true if n is probably prime.
     * k is an input parameter that determines accuracy level.
     * Higher value of k indicates more accuracy.
     * https://en.wikipedia.org/wiki/Miller–Rabin_primality_test
     */
    static boolean isPrimeMillerRabin(int n, int k) {
        if (n <= 1 || n == 4) 
            return false; 
        if (n <= 3) 
            return true; 
      
        // Find r such that n = 2^d * r + 1  
        // for some r >= 1 
        int d = n - 1; 
          
        while (d % 2 == 0) 
            d /= 2; 
      
        // Iterate given number of 'k' times 
        for (int i = 0; i < k; i++) 
            if (!miillerTest(d, n)) 
                return false; 
      
        return true; 
    } 
    
    /**
     * This function is called for all k trials.
     * It returns false if n is composite and returns false if n is probably prime. 
     * d is an odd number such that d*2^r = n-1 for some r >= 1.
     */
    static boolean miillerTest(int d, int n) {
        // Pick a random number in [2..n-2] 
        // Corner cases make sure that n > 4
        int a = 2 + (int)(Math.random() % (n - 4)); 
      
        // Compute a^d % n
        int x = powerMillerRabin(a, d, n); 
      
        if (x == 1 || x == n - 1) 
            return true; 
      
        // Keep squaring x while one of the following doesn't happen 
        // - d does not reach n-1 
        // - (x^2) % n is not 1 
        // - (x^2) % n is not n-1 
        while (d != n - 1) { 
            x = (x * x) % n; 
            d *= 2; 
          
            if (x == 1) 
                return false; 
            if (x == n - 1) 
                return true; 
        } 
      
        // Return composite 
        return false; 
    }
    
    /**
     * Function to do modular exponentiation. 
     * It returns (x^y) % p.
     */
    static int powerMillerRabin(int x, int y, int p) {
        int res = 1;
          
        //Update x if it is more than or equal to p 
        x = x % p;

        while (y > 0) { 
            // If y is odd, multiply x with result 
            if ((y & 1) == 1) 
                res = (res * x) % p; 
          
            // y must be even now 
            y = y >> 1; // y = y/2 
            x = (x * x) % p; 
        } 
        return res; 
    }

	public static void main(String[] args) {
		int n1 = 97;
		int n2 = 63;
		int n3 = 101;
		long start, end;
		
		System.out.println("Simple Prime checking solution");
		start = System.nanoTime();
		System.out.println("isPrimeSimple: " + n1 + " is prime? => " + isPrimeSimple(n1) + " \telapsed=" + (System.nanoTime() - start));
		start = System.nanoTime();
		System.out.println("isPrimeSimple: " + n2 + " is prime? => " + isPrimeSimple(n2) + " \telapsed=" + (System.nanoTime() - start));
		start = System.nanoTime();
		System.out.println("isPrimeSimple: " + n3 + " is prime? => " + isPrimeSimple(n3) + " \telapsed=" + (System.nanoTime() - start));
		System.out.println();
		
		System.out.println("Prime checking solution with Fermat Little's Theorem");
		start = System.nanoTime();
		System.out.println("isPrimeFermat: " + n1 + " is prime? => " + isPrimeFermat(n1) + " \telapsed=" + (System.nanoTime() - start));
		start = System.nanoTime();
		System.out.println("isPrimeFermat: " + n2 + " is prime? => " + isPrimeFermat(n2) + " \telapsed=" + (System.nanoTime() - start));
		start = System.nanoTime();
		System.out.println("isPrimeFermat: " + n3 + " is prime? => " + isPrimeFermat(n3) + " \telapsed=" + (System.nanoTime() - start));
		System.out.println();
		
		int k = 4;
		System.out.println("Prime checking solution with Miller-Rabin");
		start = System.nanoTime();
		System.out.println("isPrimeMillerRabin: " + n1 + " is prime? => " + isPrimeMillerRabin(n1, k) + " \telapsed=" + (System.nanoTime() - start));
		start = System.nanoTime();
		System.out.println("isPrimeMillerRabin: " + n2 + " is prime? => " + isPrimeMillerRabin(n2, k) + " \telapsed=" + (System.nanoTime() - start));
		start = System.nanoTime();
		System.out.println("isPrimeMillerRabin: " + n3 + " is prime? => " + isPrimeMillerRabin(n3, k) + " \telapsed=" + (System.nanoTime() - start));
	}
}
