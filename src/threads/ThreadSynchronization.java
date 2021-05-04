package threads;

import java.util.Scanner;

class Processor extends Thread {
    // volatile is required here for Java to work on all versions and all systems
    // basically we need it here because this Thread is not expecting other threads to modify its internal state
    // when a variable is declared volatile, the value of the variable is read and written directly from the memory.
    // Each thread caches the values of variables from the memory. Thus the thread does not have to always refer the memory,
    // and it can simply read the values from the cache. This gives rise to the problem of visibility.
    // This cache may not be consistent with what other variables see.
    // With volatile, we skip the cache and read/write directly in the memory. Thus, changes are visible to all threads.
    private volatile boolean running = true;

    @Override
    public void run(){
        while(running){
            System.out.println("Heelo");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}

public class ThreadSynchronization {
    public static void main(String[] args) {
        Processor proc1 = new Processor();
        proc1.start();

        System.out.println("Press enter to stop the thread: ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        proc1.setRunning(false);
    }
}
