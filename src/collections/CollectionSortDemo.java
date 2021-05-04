package collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionSortDemo {
    public static void main(String[] args) {
        String[] stuff = {"water", "goop", "apples", "crowbar", "bacon"};

        List<String> list = Arrays.asList(stuff);

        Collections.sort(list);
        System.out.println(list);

        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);
    }
}
