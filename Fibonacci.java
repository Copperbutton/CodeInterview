package interviewquestions.facebook;

public class Fibonacci {
    public int findFinbonacci(int n) {
        if (n <= 1)
            return 1;
        int prev = 1, curr = 1;
        for (int i = 2; i <= n; i++) {
            int fib = prev + curr;
            prev = curr;
            curr = fib;
        }
        return curr;
    }

}
