package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// Singleton class
class Connection {
    private static final Connection instance = new Connection();
    private int connections = 0;
    // allow only 10 connections at a time
    private final Semaphore semaphore = new Semaphore(10, true);

    private Connection(){}

    public static Connection getInstance() {
        return instance;
    }

    public void connect(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    private void doConnect(){
        synchronized (this){
            connections++;
            System.out.println("Current connections: " + connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this){
            connections--;
            System.out.println("Current connections: " + connections);
        }
    }
}

public class SemaphoresDemo {
    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(1);

        System.out.println("Available permits: " + sem.availablePermits());

        sem.release(); // Increases a number of semaphore permits

        System.out.println("Available permits: " + sem.availablePermits());

        sem.acquire(); // Decreases a number of permits
        // acquire is actually going to wait if no permits are available

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            executor.submit(() -> {
                Connection.getInstance().connect();
            });
        }

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.HOURS);
    }
}
