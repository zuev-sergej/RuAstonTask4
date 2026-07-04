import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();


    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (lock1.tryLock()) {
                        try {
                            System.out.println("Thread-1 захватил lock1");

                            Thread.sleep(10);

                            if (lock2.tryLock()) {
                                try {
                                    System.out.println("Thread-1 выполнил работу");
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
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (lock2.tryLock()) {
                        try {
                            System.out.println("Thread-2 захватил lock2");

                            Thread.sleep(10);

                            if (lock1.tryLock()) {
                                try {
                                    System.out.println("Thread-2 выполнил работу");
                                    return;
                                } finally {
                                    lock1.unlock();
                                }
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            lock2.unlock();
                        }
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
