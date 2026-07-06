package LiveLock;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Thread thread1 = new Thread(new LiveLock(lock1, lock2, "Thread-1"));
        Thread thread2 = new Thread(new LiveLock(lock2, lock1, "Thread-2"));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
