package threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchesDemo {
    static CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executor.submit(new ProcessorTwo(latch));
        }
        // This waits until CountdownLatch counts down to zero
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed.");
    }
}
