package collections;

import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        String[] things = {"eggs", "lasers", "hats", "pies"};

        List<String> strList = new ArrayList<>();
        strList.addAll(Arrays.asList(things));

        String[] moreThings = {"mouse", "slippers", "lasers", "lamp", "hats"};
        List<String> strList2 = new ArrayList<>(Arrays.asList(moreThings));

        for (int i = 0; i < strList.size(); i++){
            System.out.printf("%s ", strList.get(i));
        }

        System.out.println("\n");

        editList(strList, strList2);

        for (int i = 0; i < strList.size(); i++){
            System.out.printf("%s ", strList.get(i));
        }
        System.out.println();
        Collections.addAll(strList, moreThings);
        System.out.println(strList);
    }

    public static void editList(Collection<String> listA, Collection<String> listB){
        Iterator<String> itr = listA.iterator();

        while (itr.hasNext()){
            if(listB.contains(itr.next())){
                itr.remove();
            }
        }
        // Or use shorthand:
        // listA.removeIf(listB::contains);
    }
}
