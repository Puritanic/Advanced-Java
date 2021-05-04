package collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetsDemo {
    public static void main(String[] args) {
        String[] stuff = {"water", "goop", "apples", "crowbar", "bacon", "goop"};
        List<String> list = Arrays.asList(stuff);
        System.out.println(list);

        Set<String> set = new HashSet<>(list);
        // Sets are removing duplicate values
        System.out.println(set);
    }
}
