package generics;

public class GenericMethods {
    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5};
        Character[] carr = {'b', 'o', 'i', 'c', 'y'};

        printMe(arr);
        printMe(carr);

        System.out.println(max(23, 42, 11));
        System.out.println(max("A", "B", "C"));
     }

    private static <T> void printMe(T[] arr) {
        for (T c: arr) {
            System.out.println("C: " + c);
        }
    }

    public static <T extends Comparable<T>> T max(T a, T b, T c){
        T max = a;

        if (b.compareTo(max) > 0){
            max = b;
        }
        if (c.compareTo(max) > 0){
            max = c;
        }
        return max;
    }
}
