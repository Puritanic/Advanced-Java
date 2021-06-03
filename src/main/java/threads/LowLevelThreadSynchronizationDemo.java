package threads;

import java.util.LinkedList;
import java.util.Random;

class ProcessorFour {
    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();

    ProcessorFour(){}

    public void produce() throws InterruptedException {
        int value = 0;
        while(true){
            synchronized (lock){
                while(list.size() == LIMIT){
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random rand = new Random();

        while(true){
            synchronized (lock){
                while(list.size() == 0){
                    lock.wait();
                }
                System.out.print("Consume: list size is: " + list.size());
                int value = list.removeFirst();
                System.out.println("; Consume: first value was: " + value);
                lock.notify();
            }

            Thread.sleep(rand.nextInt(1000));
        }
    }
}

public class LowLevelThreadSynchronizationDemo {
    public static void main(String[] args) {
        final ProcessorFour proc = new ProcessorFour();

        Thread t1 = new Thread(() -> {
            try {
                proc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                proc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
