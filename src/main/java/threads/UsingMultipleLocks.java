package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UsingMultipleLocks {
    private static final List<Integer> list1 = new ArrayList<>();
    private static final List<Integer> list2 = new ArrayList<>();
    private static final Random rand = new Random();

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    // Adding synchronized to the methods signature is not a real solution, as it's going to block threads
    // from calling them when those shouldn't be blocked.
//    public static void stageOne(){
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        list1.add(rand.nextInt());
//    }
//    public static synchronized void stageTwo(){
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        list2.add(rand.nextInt());
//    }
//    public static synchronized void process(){
//        for (int i = 0; i < 1000; i++) {
//            stageOne();
//            stageTwo();
//        }
//    }

    public static void stageOne() {
        synchronized (lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list1.add(rand.nextInt());
        }
    }

    public static void stageTwo() {
        synchronized (lock2){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(rand.nextInt());
        }
    }

    public static void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting...");

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(UsingMultipleLocks::process);
        Thread t2 = new Thread(UsingMultipleLocks::process);
        Thread t3 = new Thread(UsingMultipleLocks::process);

        t1.start();
        t2.start();
        t3.start();


        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        System.out.println("List 1: " + list1.size());
        System.out.println("List 2: " + list2.size());
    }
}
