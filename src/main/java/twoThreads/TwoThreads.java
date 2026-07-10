package twoThreads;

public class TwoThreads implements Runnable {
    private static final Object lock = new Object();
    private static volatile boolean flag = true;

    private final int number;
    private final boolean firstTurn;

    public TwoThreads(int number, boolean firstTurn) {
        this.number = number;
        this.firstTurn = firstTurn;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                while (flag != firstTurn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println(number);
                flag = !firstTurn;
                lock.notifyAll();
            }
        }
    }
}
