package threads;

class ThreadRunner extends Thread {
    @Override
    public void run(){
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

public class ThreadsOne {
    public static void main(String[] args) {
        ThreadRunner runner1 = new ThreadRunner();
        runner1.start();

        ThreadRunner runner2 = new ThreadRunner();
        runner2.start();
    }
}
