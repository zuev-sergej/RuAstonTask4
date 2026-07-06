package LiveLock;

import java.util.concurrent.locks.ReentrantLock;

public class LiveLock implements Runnable {
    private final ReentrantLock lock1;
    private final ReentrantLock lock2;
    private final String threadName;

    public LiveLock(ReentrantLock lock1, ReentrantLock lock2, String threadName) {
        this.lock1 = lock1;
        this.lock2 = lock2;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while (true) {
            if (lock1.tryLock()) {
                try {
                    System.out.println(threadName + " захватил lock1.");

                    Thread.sleep(10);

                    if (lock2.tryLock()) {
                        try {
                            System.out.println(threadName + " выполнил работу.");
                            return;
                        } finally {
                            lock2.unlock();
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock1.unlock();
                }
            }
            System.out.println(threadName + " уступает и пробует снова");
        }

    }

}
