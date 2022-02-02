package kr.springboot.tutorial.second;

import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkStealingExecutorsExample {

    public static void main(String[] args) throws ExecutionException {
        System.out.println("=== WorkStealingThreadPool ===");
        var workStealingPool = Executors.newWorkStealingPool();

        workStealingPool.execute(() -> System.out.println("Prints normally"));

        Callable<UUID> generatesUUID = UUID::randomUUID;
        var severalUUIDsTasks = new LinkedList<Callable<UUID>>();
        for (int i = 0; i < 20; i++) {
            severalUUIDsTasks.add(generatesUUID);
        }

        try {
            var futureUUIDs = workStealingPool.invokeAll(severalUUIDsTasks);
            for (var future : futureUUIDs) {
                if (future.isDone()) {
                    var uuid = future.get();
                    System.out.println("New UUID :" + uuid);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        try {
            workStealingPool.awaitTermination(6, TimeUnit.SECONDS);
            workStealingPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * === WorkStealingThreadPool ===
         * Prints normally
         * New UUID :601dbbc3-2c56-4812-811e-3b90a43e8b5a
         * New UUID :172b6611-1f3d-4188-ade9-ec399603d159
         * New UUID :544d9c11-5a40-43c1-9bc3-bdde59ab00e7
         * New UUID :3a5bb8eb-a74b-4aa2-9420-c0036025674c
         * New UUID :947b1eb1-5ae2-4fb9-a0a0-59581ced1f07
         * New UUID :290061fa-2eac-47e4-9154-526759840a86
         * New UUID :78524029-46df-4468-9fff-9bd7f1083098
         * New UUID :38429fc4-c625-47b7-83d3-cc9af246023f
         * New UUID :4bddd461-f2e2-490a-9624-f54404afbee3
         * New UUID :26a9c500-3e2a-4d88-b213-68367f1bd405
         * New UUID :7c9afd6f-eb27-413a-a207-0a9512587c86
         * New UUID :515978a8-26ba-4823-9d31-4b17884c3321
         * New UUID :5a3b382e-5b91-4bbc-9faa-0b7aa62055c9
         * New UUID :ad5b867c-c795-4788-8e95-243dd655a660
         * New UUID :f414a84f-5e15-47a9-8b3a-7456c0527690
         * New UUID :94996d52-c2de-4a83-81ea-b03f86e21f17
         * New UUID :cae410f0-8bfd-4587-9807-87f02a51c8fc
         * New UUID :deba71fa-efb0-4e83-b055-16c5c1bc81bd
         * New UUID :38fb834e-9288-4cb2-8bb8-85f43cd82f87
         * New UUID :64e57331-031a-454f-89ae-3955cf5b8703
         *
         * Process finished with exit code 0
         */
    }

}
