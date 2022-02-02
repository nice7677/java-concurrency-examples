package kr.springboot.synchronizers;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample {

    public static void main(String[] args) {

        var executor = Executors.newCachedThreadPool();
        var semaphore = new Semaphore(3);

        Runnable r = () -> {
            try {
                System.out.println("Trying to acquire - " + Thread.currentThread().getName());
                if (semaphore.tryAcquire(2, TimeUnit.SECONDS)) {
                    System.out.println("Acquired - " + Thread.currentThread().getName());
                    Thread.sleep(2000);
                    System.out.println("Done - " + Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        };
        for (int i = 0; i < 15; i++) {
            executor.execute(r);
        }

        executor.shutdown();

        /**
         * Trying to acquire - pool-1-thread-12
         * Trying to acquire - pool-1-thread-1
         * Trying to acquire - pool-1-thread-4
         * Acquired - pool-1-thread-12
         * Trying to acquire - pool-1-thread-2
         * Acquired - pool-1-thread-4
         * Trying to acquire - pool-1-thread-14
         * Trying to acquire - pool-1-thread-6
         * Trying to acquire - pool-1-thread-8
         * Trying to acquire - pool-1-thread-7
         * Acquired - pool-1-thread-1
         * Trying to acquire - pool-1-thread-11
         * Trying to acquire - pool-1-thread-13
         * Trying to acquire - pool-1-thread-10
         * Trying to acquire - pool-1-thread-9
         * Trying to acquire - pool-1-thread-5
         * Trying to acquire - pool-1-thread-3
         * Trying to acquire - pool-1-thread-15
         * Done - pool-1-thread-4
         * Acquired - pool-1-thread-8
         * Done - pool-1-thread-12
         * Acquired - pool-1-thread-13
         * Acquired - pool-1-thread-11
         * Done - pool-1-thread-1
         * Acquired - pool-1-thread-5
         * Acquired - pool-1-thread-9
         * Acquired - pool-1-thread-10
         * Acquired - pool-1-thread-3
         * Done - pool-1-thread-8
         * Done - pool-1-thread-13
         * Done - pool-1-thread-5
         * Done - pool-1-thread-9
         * Done - pool-1-thread-10
         * Done - pool-1-thread-3
         * Done - pool-1-thread-11
         */

    }

}
