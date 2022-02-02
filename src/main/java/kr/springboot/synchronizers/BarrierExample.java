package kr.springboot.synchronizers;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

public class BarrierExample {

    public static void main(String[] args) {

        Runnable barrierAction = () -> System.out.println("Well done, guys!");

        var executor = Executors.newCachedThreadPool();
        var barrier = new CyclicBarrier(5, barrierAction);

        Runnable task = () -> {
            try {
                // simulating a task that can take at most 1sec to run
                System.out.println("Doing task for " + Thread.currentThread().getName());
                Thread.sleep(new Random().nextInt(10) * 100);
                System.out.println("Done for " + Thread.currentThread().getName());
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 10; i++) {
            executor.execute(task);
        }
        executor.shutdown();

    }


}
