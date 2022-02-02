package kr.springboot.concurrent.collections;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] args) {

        // interrupt와 함께
        System.out.println("=== BlockingQueue ===");

        var uuidQueue = new LinkedBlockingQueue<UUID>(10);

        System.out.println("Queue will execute for 10s");

        Runnable runConsumer = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    var uuid = uuidQueue.take();
                    System.out.println("Consumed: " + uuid + " by " + Thread.currentThread().getName());

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Consumer Finished");
                }
            }
        };
        var consumer1 = new Thread(runConsumer);
        consumer1.start();
        var consumer2 = new Thread(runConsumer);
        consumer2.start();

        Runnable runProducer = () -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Random r = new Random();
                    Thread.sleep(r.nextInt(1000));
                    UUID randomUUID = UUID.randomUUID();
                    System.out.println("Produced: " + randomUUID + " by " + Thread.currentThread().getName());
                    uuidQueue.put(randomUUID);
                }
            } catch (InterruptedException e) {
                System.err.println("Producer Finished");
            }
        };

        var producer1 = new Thread(runProducer);
        producer1.start();
        var producer2 = new Thread(runProducer);
        producer2.start();
        var producer3 = new Thread(runProducer);
        producer3.start();

        try {

            Thread.sleep(10000);
            producer1.interrupt();
            producer2.interrupt();
            producer3.interrupt();
            consumer1.interrupt();
            consumer2.interrupt();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
