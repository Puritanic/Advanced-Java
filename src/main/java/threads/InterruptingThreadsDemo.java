package threads;

import java.util.Random;
import java.util.concurrent.*;

public class InterruptingThreadsDemo {
    public static void main(String[] args) {
        System.out.println("Starting...");

//        Thread th = new Thread(() -> {
//            Random rand = new Random();
//            for (int i = 0; i < 1E8; i++) {
//                if(Thread.currentThread().isInterrupted()){
//                    System.out.println("Thread interrupted!!!");
//                    break;
//                }
//                Math.sin(rand.nextDouble());
//            }
//        });
//        th.start();
//
//        Thread.sleep(500);
//
//        // interrupt is not actually stopping a thread, it just triggers a flag in Thread (isInterrupted())
//        th.interrupt();
//        th.join();

        ExecutorService executor = Executors.newCachedThreadPool();

        Future<?> fu =  executor.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Random rand = new Random();
                for (int i = 0; i < 1E8; i++) {
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Thread interrupted!!!");
                        break;
                    }
                    Math.sin(rand.nextDouble());
                }
                return null;
            }
        });

        executor.shutdown();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Same as interrupt()
        fu.cancel(true);
        // We can also use:
//        executor.shutdownNow();

        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished.");
    }
}
