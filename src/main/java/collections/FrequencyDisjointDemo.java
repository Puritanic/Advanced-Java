package collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FrequencyDisjointDemo {
    public static void main(String[] args) {
        String[] stuff = {"water", "goop", "apples", "crowbar", "bacon"};
        List<String> list = Arrays.asList(stuff);
        String[] moreStuff = {"mouse", "slippers", "lasers", "lamp", "hats", "crowbar"};
        List<String> list2 = Arrays.asList(moreStuff);

        // Returns the number of times element apperas in the provided list
        System.out.println(Collections.frequency(list, "goop"));

        // Returns true if there are no items in common between two lists
        boolean areRelated = Collections.disjoint(list, list2);

        System.out.println(areRelated);
    }
}
