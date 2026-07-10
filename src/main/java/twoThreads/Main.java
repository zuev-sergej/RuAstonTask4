package twoThreads;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new TwoThreads(1, true));
        Thread thread2 = new Thread(new TwoThreads(2, false));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
