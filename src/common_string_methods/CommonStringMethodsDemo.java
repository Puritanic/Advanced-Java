package common_string_methods;

public class CommonStringMethodsDemo {
    public static void main(String[] args) {
        String[] words = {"punk", "dunk", "chink", "furry", "baconator", "funk"};

        // Starts with
        for (String w : words) {
            if(w.startsWith("fu")){
                System.out.println(w + " starts with 'fu'.");
            }
            if (w.endsWith("nk")){
                System.out.println(w + " ends with 'nk'.");
            }
        }

        String str = "darkotasevskidarkotasevskidarkotasevski";

        System.out.println(str.indexOf("k"));
        // Search for the firs 'k' match, but start from the fifth index
        System.out.println(str.indexOf("k", 5));

        String a = "Bacon";
        String b = "Cheese";

        System.out.println(a.concat(b));
        System.out.println(a.replace("B", "T"));
        System.out.println(b.toUpperCase());
    }
}
