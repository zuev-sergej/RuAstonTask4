public class DeadLock {
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK1) {
                    System.out.println("Tread-1 захватил LOCK1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    System.out.println("Tread-1 ожидает LOCK2");

                    synchronized (LOCK2) {
                        System.out.println("Tread-1 захватил LOCK2");
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (LOCK2) {
                    System.out.println("Tread-2 захватил LOCK2");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    System.out.println("Tread-2 ожидает LOCK1");

                    synchronized (LOCK1) {
                        System.out.println("Tread-2 захватил LOCK1");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
