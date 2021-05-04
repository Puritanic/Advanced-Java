package threads;

import java.util.Scanner;

class ProcessorThree {

    ProcessorThree(){}

    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Producer thread running...");
            // wait() can be only called within synchronized code blocks.
            wait();
            System.out.println("Producer resumed...");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this){
            System.out.println("Consumer: Waiting for return key...");
            scanner.nextLine();
            System.out.println("Consumer: Return key pressed.");
            // Notify can only be called within synchronized code blocks
            //  It's going to notify a thread locked in on a same object, so that that thread can wake up
            notify();
            // We can also use notifyAll, if there are multiple threads that are locked on 'this' object.
            // notify does not hand over control of the lock unlike wait. Because of this notify() should be
            // the last operation in synchronized code block.
            // For example if we put this line of code after notify:
//            Thread.sleep(5000);
            // then the lock will be handed over to other thread after 5 seconds, meaning that produce is going to wait 5 seconds before printing 'Resumed'.
        }
    }
}

public class WaitNotifyDemo {
    public static void main(String[] args) {
        final ProcessorThree proc = new ProcessorThree();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    proc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    proc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
