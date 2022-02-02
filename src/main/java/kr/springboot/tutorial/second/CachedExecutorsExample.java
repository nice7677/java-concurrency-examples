package kr.springboot.tutorial.second;

import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CachedExecutorsExample {

    public static void main(String[] args) {

        System.out.println("=== CachedThreadPool ===");
        var cachedThreadPool = Executors.newCachedThreadPool();
        var uuids = new LinkedList<Future<UUID>>();
        for (int i = 0; i < 10; i++) {
            var submittedUUID = cachedThreadPool.submit(() -> {
                var randomUUID = UUID.randomUUID();
                System.out.println("UUID " + randomUUID + " from " + Thread.currentThread().getName());
                return randomUUID;
            });
            uuids.add(submittedUUID);
        }
        cachedThreadPool.execute(() -> uuids.forEach((f) -> {
            try {
                System.out.println("Result " + f.get() + " from thread " + Thread.currentThread().getName());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }));
        cachedThreadPool.shutdown();
        try {
            cachedThreadPool.awaitTermination(4, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * === CachedThreadPool ===
         * UUID 9443c44d-4e2c-46f8-8787-c5d26f90211e from pool-1-thread-3
         * UUID 4d1686e7-e400-4bfc-9193-10f1ce575325 from pool-1-thread-10
         * UUID 1d57f6fc-4c37-4674-a405-dba9af975f8d from pool-1-thread-9
         * UUID c8404b1c-7f89-4457-aa36-26a0a8d31c03 from pool-1-thread-4
         * UUID f1ad3df5-75e7-44fc-a7ef-02c4b4056631 from pool-1-thread-5
         * UUID e4993bad-dcc7-41fd-a6ef-62e5f1cf70ec from pool-1-thread-6
         * UUID 1cd60ee7-eb91-4139-b514-d1084d43bb88 from pool-1-thread-7
         * UUID 738ce41e-0f89-42b2-adf9-13c9a385dc8b from pool-1-thread-2
         * UUID 363cc147-bd18-4248-aed9-69331aa16c7a from pool-1-thread-8
         * UUID 02fbde28-bb50-4a00-adf4-38c3d97878bd from pool-1-thread-1
         * Result 02fbde28-bb50-4a00-adf4-38c3d97878bd from thread pool-1-thread-11
         * Result 738ce41e-0f89-42b2-adf9-13c9a385dc8b from thread pool-1-thread-11
         * Result 9443c44d-4e2c-46f8-8787-c5d26f90211e from thread pool-1-thread-11
         * Result c8404b1c-7f89-4457-aa36-26a0a8d31c03 from thread pool-1-thread-11
         * Result f1ad3df5-75e7-44fc-a7ef-02c4b4056631 from thread pool-1-thread-11
         * Result e4993bad-dcc7-41fd-a6ef-62e5f1cf70ec from thread pool-1-thread-11
         * Result 1cd60ee7-eb91-4139-b514-d1084d43bb88 from thread pool-1-thread-11
         * Result 363cc147-bd18-4248-aed9-69331aa16c7a from thread pool-1-thread-11
         * Result 1d57f6fc-4c37-4674-a405-dba9af975f8d from thread pool-1-thread-11
         * Result 4d1686e7-e400-4bfc-9193-10f1ce575325 from thread pool-1-thread-11
         */

    }

}
