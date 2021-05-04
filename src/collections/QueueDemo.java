package collections;

import java.util.PriorityQueue;

public class QueueDemo {
    public static void main(String[] args) {
        PriorityQueue<String> queue = new PriorityQueue<>();

        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");

        System.out.printf("%s \n", queue);
        // Returns the element with the highest priority (First in this case)
        System.out.printf("%s \n", queue.peek());

        // Removes the element with the highest priority
        queue.poll();
        System.out.printf("%s \n", queue);
    }
}
