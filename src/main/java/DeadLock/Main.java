package DeadLock;

public class Main {
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new DeadLock(LOCK1, LOCK2, "Thread-1"));
        Thread thread2 = new Thread(new DeadLock(LOCK2, LOCK1, "Thread-2"));

        thread1.start();
        thread2.start();


        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
