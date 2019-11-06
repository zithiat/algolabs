import java.util.*;

class Fibonacci {
  public void testFibonacci() {
    int n = 40;
    System.out.println("\nFind Fibonnacci number at position: " + n);

    Recursion r = new Recursion();
    r.usingRecursion(n);

    DynamicProgramming dp = new DynamicProgramming();
    dp.usingDynamicProgramming(n);

    StoringValue sv = new StoringValue();
    sv.usingStore(n);

    OptimizedMatrix om = new OptimizedMatrix();
    om.usingOptimizedMatrix(n);

    DivideConquer dc = new DivideConquer();
    dc.usingDivideConquer(n);

    usingPhi(n);
  }

  // Time Complexity: T(n) = T(n-1) + T(n-2) which is exponential.
  // Extra Space: O(n) if we consider the function call stack size, otherwise O(1).
  class Recursion { 
    int fib(int n) { 
      if (n <= 1) 
        return n; 
      return fib(n-1) + fib(n-2); 
    } 
       
    public void usingRecursion(int n) { 
      long start = System.nanoTime();
      int fibNum = fib(n);
      long spendTime = System.nanoTime() - start;
      System.out.println("usingRecursion:\n\t\tvalue: " + fibNum + "\telaspsed: " + spendTime  + " ns");
    } 
  } 

  // By storing the Fibonacci numbers calculated
  class DynamicProgramming { 
    int fib(int n) { 
      // Declare an array to store Fibonacci numbers.
      int f[] = new int[n + 2]; // 1 extra to handle case, n = 0 
      int i; 
        
      // 0th and 1st number of the series are 0 and 1
      f[0] = 0; 
      f[1] = 1; 
        
      for (i = 2; i <= n; i++) { 
        // Add the previous 2 numbers in the series and store it
          f[i] = f[i - 1] + f[i - 2]; 
      }        
      return f[n]; 
    } 
      
    public void usingDynamicProgramming(int n) { 
      long start = System.nanoTime();
      int fibNum = fib(n);
      long spendTime = System.nanoTime() - start;
      System.out.println("usingDynamicProgramming:\n\t\tvalue: " + fibNum + "\telaspsed: " + spendTime  + " ns");
    } 
  } 

  // Time Complexity:O(n)
  // Extra Space: O(1)
  // Same from Dynamic Programming method, 
  // but storing the previous two numbers only 
  // because we only need to find the next number in series
  class StoringValue { 
    int fib(int n) { 
      int a = 0, b = 1, c; 
      if (n == 0) 
        return a; 
      for (int i = 2; i <= n; i++) { 
        c = a + b; 
        a = b; 
        b = c; 
      } 
      return b; 
    } 
  
    public void usingStore(int n) {
      long start = System.nanoTime();
      int fibNum = fib(n);
      long spendTime = System.nanoTime() - start;
      System.out.println("usingStore:\n\t\tvalue: " + fibNum + "\telaspsed: " + spendTime  + " ns");
    }
  }

  // https://www.quora.com/How-does-the-matrix-multiplication-for-getting-the-nth-Fibonacci-number-work
  class OptimizedMatrix { 
    /* function that returns nth Fibonacci number */
    int fib(int n) { 
      int F[][] = new int[][]{{1,1}, {1,0}}; 
      if (n == 0) 
        return 0; 
      power(F, n - 1);
      return F[0][0]; 
    } 
      
    void multiply(int F[][], int M[][]) { 
      int x =  F[0][0] * M[0][0] + F[0][1] * M[1][0]; 
      int y =  F[0][0] * M[0][1] + F[0][1] * M[1][1]; 
      int z =  F[1][0] * M[0][0] + F[1][1] * M[1][0]; 
      int w =  F[1][0] * M[0][1] + F[1][1] * M[1][1];         
      F[0][0] = x; 
      F[0][1] = y; 
      F[1][0] = z; 
      F[1][1] = w; 
    } 
    
    void power(int F[][], int n) { 
      if( n == 0 || n == 1) 
        return; 
      int M[][] = new int[][]{{1,1},{1,0}};         
      power(F, n/2); 
      multiply(F, F);        
      if (n%2 != 0) 
        multiply(F, M); 
    } 

    public void usingOptimizedMatrix (int n) { 
      long start = System.nanoTime();
      int fibNum = fib(n);
      long spendTime = System.nanoTime() - start;
      System.out.println("usingOptimizedMatrix:\n\t\tvalue: " + fibNum + "\telaspsed: " + spendTime  + " ns");
    } 
  }

  // Using same approach of matrix, http://mathforum.org/library/drmath/view/52711.html
  // If n is even then k = n/2:
  //    F(n) = [2*F(k-1) + F(k)]*F(k)
  // If n is odd then k = (n + 1)/2
  //    F(n) = F(k)*F(k) + F(k-1)*F(k-1)
  class DivideConquer {      
    int MAX = 1000; 
    int f[]; 
      
    // Returns n'th fibonacci number using table f[] 
    int fib(int n) { 
      // Base cases 
      if (n == 0) 
        return 0; 
            
      if (n == 1 || n == 2) 
        return (f[n] = 1); 
      
      // If fib(n) is already computed 
      if (f[n] != 0) 
        return f[n]; 
      
      int k = (n & 1) == 1? (n + 1) / 2 : n / 2; 
      
      // Applyting above formula: value n&1 is 1 if n is odd, else 0. 
      f[n] = (n & 1) == 1 ? (fib(k) * fib(k) + fib(k - 1) * fib(k - 1)) 
                          : (2 * fib(k - 1) + fib(k)) * fib(k);

      return f[n]; 
    } 
      
    public void usingDivideConquer(int n) { 
      long start = System.nanoTime();
      f = new int[MAX];
      int fibNum = fib(n);
      long spendTime = System.nanoTime() - start;
      System.out.println("usingDivideConquer:\n\t\tvalue: " + fibNum + "\telaspsed: " + spendTime  + " ns");
    } 
  }

  // http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/fibFormula.html
  public void usingPhi(int n) {
    long start = System.nanoTime();
    double phi = (1 + Math.sqrt(5)) / 2;
    int fibNum = (int) Math.round(Math.pow(phi, n) / Math.sqrt(5));
    long spendTime = System.nanoTime() - start;
    System.out.println("usingPhi:\n\t\tvalue: " + fibNum + "\telaspsed: " + spendTime  + " ns");
  }
}