package collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ReverseCopyDemo {
    public static void main(String[] args) {
        Character[] charr = {'p', 'c', 'a', 'b','g', 'm', 'n', 'c'};
        List<Character> list = Arrays.asList(charr);

        output(list);

        // reverse and print out the lsit
        Collections.reverse(list);
        output(list);

        // create a new array and a new list
        Character[] charr2 = new Character[charr.length];
        List<Character> listCopy = Arrays.asList(charr2);

        // Copy the contents of list to listCopy
        Collections.copy(listCopy, list);
        output(listCopy);

        // fill collection
        Collections.fill(list, 'X');
        output(list);
    }

    private static void output(List<Character> list) {
        for (Character chr: list) {
            System.out.printf("%s ", chr);
        }
        System.out.println();
    }

}
