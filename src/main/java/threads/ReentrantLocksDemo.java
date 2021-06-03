package threads;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class RunnerTwo {
    private int count = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    RunnerTwo() {}

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting...");
        condition.await();
        System.out.println("Woken up!");

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();

        System.out.println("Press the return key.");
        new Scanner(System.in).nextLine();
        System.out.println("Got the return key.");

        condition.signal();

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}

// Reentrant locks are 'kinda' an alternative to using synchronized keyword
public class ReentrantLocksDemo {
    public static void main(String[] args) {
        final RunnerTwo runner = new RunnerTwo();

        Thread t1 = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                runner.secondThread();
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

        runner.finished();
    }
}
