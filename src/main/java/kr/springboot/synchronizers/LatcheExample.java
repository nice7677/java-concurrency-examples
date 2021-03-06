package kr.springboot.synchronizers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LatcheExample {

    public static void main(String[] args) {
        var executor = Executors.newCachedThreadPool();
        var latch = new CountDownLatch(3);
        Runnable r = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Service in " + Thread.currentThread().getName() + " initialized.");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        executor.execute(r);
        executor.execute(r);
        executor.execute(r);
        try {
            latch.await(2, TimeUnit.SECONDS);
            System.out.println("All services up and running!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

        /**
         * Service in pool-1-thread-1 initialized.
         * Service in pool-1-thread-3 initialized.
         * Service in pool-1-thread-2 initialized.
         * All services up and running!
         */
    }

}
