package threads;

import java.util.Random;

public class Runner implements Runnable {
    String name;
    int time;
    Random rand = new Random();

    public Runner(){}

    public Runner(String name){
        this.name = name;
        this.time = rand.nextInt(999); // in milliseconds
    }

    /**
     * This method gets called whenever we start the thread
     */
    @Override
    public void run() {
        try {
            System.out.printf("%s is sleeping for %d\n", name, time);
            // we don't need the reference to the exact Thread we are putting to sleep
            // run() automatically knows what thread are we talking about. It's like 'this' keyword
            Thread.sleep(time);
            System.out.printf("%s is done\n", name);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
