package threads;

class ThreadRunnerTwo implements Runnable {
    @Override
    public void run() {
        // Here we put a code that we want to run in it's own separate thread
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadsTwo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadRunnerTwo());
        Thread t2 = new Thread(new ThreadRunnerTwo());

        t1.start();
        t2.start();
    }
}
