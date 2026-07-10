package deadLock;

public class DeadLock implements Runnable {
    private final Object lock1;
    private final Object lock2;
    private final String threadName;

    public DeadLock(Object lock1, Object lock2, String threadName) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(threadName + "захватил lock1.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            System.out.println(threadName + " ожидает второй lock2.");

            synchronized (lock2) {
                System.out.println(threadName + " захватил lock1");
            }
        }
    }
}
