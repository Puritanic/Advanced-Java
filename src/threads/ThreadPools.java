package threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ProcessorTwo implements Runnable {
    private String name = "";
    private CountDownLatch latch;

    public ProcessorTwo(){}

    public ProcessorTwo(CountDownLatch latch){
        this.latch = latch;
    }

    public ProcessorTwo(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Starting: " + name);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed: " + name);

        if (latch != null){
            latch.countDown();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class ThreadPools {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executor.submit(new ProcessorTwo("Task " + i));
        }
        // This will not shutdown immediately, it's going to wait for all threads to complete what they're doing
        // and then it will terminate. Also, thi is not blocking operation.
        executor.shutdown();

        System.out.println("All tasks submitted.");

        // If we want to execute soemthing after threads have finished work, we use awaitTermination
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed.");
    }
}
