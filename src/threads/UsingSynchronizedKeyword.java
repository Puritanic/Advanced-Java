package threads;

public class UsingSynchronizedKeyword {
    private int count = 0;
    private volatile int count1 = 0;
    private int count2 = 0;

    public static void main(String[] args) {
        UsingSynchronizedKeyword app = new UsingSynchronizedKeyword();
        app.doWorkBad();
        try {
            app.doWorkGood();
            app.doWorkBetter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void increment(){
        count2++;
    }

    public void doWorkBad(){
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        });
        t1.start();
        t2.start();

        System.out.println("doWorkBad - Count is: " + count);
    }
    public void doWorkGood() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count1++;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                count1++;
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("doWorkGood - Count is: " + count1);
    }

    public void doWorkBetter() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("doWorkBetter - Count is: " + count2);
    }
}
