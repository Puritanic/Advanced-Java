package collections;

import java.util.Arrays;
import java.util.LinkedList;

public class ConvertListToArr {
    public static void main(String[] args) {
        String[] stuff = {"water", "goop", "apples", "crowbar", "bacon"};

        LinkedList<String> list = new LinkedList<>(Arrays.asList(stuff));

        list.add("pumpkin");
        list.addFirst("first");
        list.addLast("last");

        // Convert back to array
        stuff = list.toArray(new String[list.size()]);
        System.out.println(Arrays.toString(stuff));
    }
}
