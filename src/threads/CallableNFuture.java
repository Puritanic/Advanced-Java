package threads;

import java.util.Random;
import java.util.concurrent.*;

public class CallableNFuture {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random rand = new Random();
                int duration = rand.nextInt(4000);

                if (duration > 3000){
                    throw new Exception("Sleeping for too long.");
                }

                System.out.println("Starting...");

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Finished.");

                return duration;
            }
        });

        executor.shutdown();

        try {
            // get is blocking operation. If we call it before the thread is done,
            // it's going to wait for it to be done and then will get the results
            int result = future.get();
            System.out.println("Result is: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
