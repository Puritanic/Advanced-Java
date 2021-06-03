package collections;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListDemo {
    public static void main(String[] args) {
        String[] things = {"thing1", "thing2", "apples", "thing4", "bacon"};
        List<String> strlist = new LinkedList<>(Arrays.asList(things));

        String[] moreThings  = {"ufos", "cheese", "goat", "eggs"};
        List<String> strlist2 = new LinkedList<>(Arrays.asList(moreThings));

        strlist.addAll(strlist2);
        strlist2 = null; // To free up memory as it's not used anymore
        
        printMe(strlist);
        removeStuff(strlist, 2,5);
        printMe(strlist);
        reverseMe(strlist);
    }

    private static void reverseMe(List<String> strlist) {
        ListIterator<String> itr = strlist.listIterator(strlist.size());

        while(itr.hasPrevious()){
            System.out.printf("%s ", itr.previous());
        }
    }

    private static void removeStuff(List<String> strlist, int from, int to) {
        // this s going to return a portion of the list, and we are clearing it here,
        // thus deleting those items from the lsit
        strlist.subList(from, to).clear();
    }

    private static void printMe(List<String> strlist) {
        for (String el : strlist) {
            System.out.printf("%s ", el);
        }
        System.out.println();
    }
}
