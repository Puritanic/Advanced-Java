package threads;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private int balance = 10000;

    public void deposit(int amount){
        balance += amount;
    }

    public void withdraw(int amount){
        balance -= amount;
    }

    public int getBalance(){
        return balance;
    }

    public static void transfer(Account acc1, Account acc2, int amount){
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}

class RunnerThree {
    private final Account acc1 = new Account();
    private final Account acc2 = new Account();
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    RunnerThree() {}

    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while(true){
            // Acquire locks
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;
            try {
                // Try to get the locks
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            } finally {
                // If we have both locks then just return from the method
                // as both locks are acquired as intended
                if (gotFirstLock && gotSecondLock){
                    return;
                }
                // If we haven't got both locks, then we need to check each one and unlock it
                // if it locked to give other threads a chance to get it, and to avoid deadlock
                 if(gotFirstLock){
                    firstLock.unlock();
                }
                if(gotSecondLock){
                    secondLock.unlock();
                }
            }

            // Locks not acquired - sleep for bit and try again
            Thread.sleep(1);
        }
    }

    public void firstThread() throws InterruptedException {
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);
            try{
            Account.transfer(acc1, acc2, rand.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);
            try{
                Account.transfer(acc2, acc1, rand.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() {
        System.out.println("Account 1 balance: " + acc1.getBalance() + "; Account 2 balance: " + acc2.getBalance());
        System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
    }
}

// Reentrant locks are 'kinda' an alternative to using synchronized keyword
public class DeadlockDemo {
    public static void main(String[] args) {
        final RunnerThree runner = new RunnerThree();

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
