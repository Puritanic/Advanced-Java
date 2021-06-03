package recursion;

public class RecursionDemo {
    public static void main(String[] args) {
        System.out.println("Factorial of 5 is: " + factorial(5));
    }

    public static long factorial(long n){
        if(n <= 1){
            return 1;
        }

        return n * factorial(n-1);
    }
}
