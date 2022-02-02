package kr.springboot.concurrent.collections;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapExample {

    public static void main(String[] args) {
        System.out.println("=== ConcurrentHashMap ===");
        var executor = Executors.newCachedThreadPool();
        var random = new Random();
        var valuesPerUuid = new ConcurrentHashMap<UUID, Integer>();
        // atomic operations
        valuesPerUuid.putIfAbsent(UUID.randomUUID(), random.nextInt(100));

        for (int i = 0; i < 100; i++) {
            if (i % 4 == 0) {
                // write
                executor.execute(() -> {
                    UUID uuid = UUID.randomUUID();
                    Integer value = random.nextInt(10);
                    System.out.println("Added " + uuid + " - " + value);
                    valuesPerUuid.putIfAbsent(uuid, value);
                });
            } else {
                // read
                executor.execute(() -> System.out.println("Printed " + valuesPerUuid.values().toString()));
            }
        }

        // Finishing
        executor.shutdown();
        try {
            executor.awaitTermination(2000, TimeUnit.SECONDS);
            // space for other examples
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * === ConcurrentHashMap ===
         * Printed [14]
         * Printed [14]
         * Printed [14]
         * Printed [14]
         * Printed [14]
         * Printed [14]
         * Printed [14]
         * Printed [14]
         * Printed [14]
         * Printed [14]
         * Printed [14]
         * Printed [14]
         * Printed [14]
         * Printed [14]
         * Added a10f9fb9-1305-4b41-9463-a798a7051f47 - 6
         * Added 91dcc861-e9a6-4724-acd7-34fb1461cb5a - 7
         * Added 786c7c65-85de-4679-8d4d-a7b97e175900 - 8
         * Added 57fc3cfe-f57e-43c4-91da-7e30e13e1e1d - 0
         * Added e4a14823-42d9-4ac8-b3e1-18b231a4788b - 0
         * Printed [0, 7, 14, 6, 8, 0]
         * Printed [0, 7, 14, 6, 8, 0]
         * Added dbd51810-9171-4288-aeaa-e887df927848 - 3
         * Printed [0, 7, 14, 6, 3, 8, 0]
         * Printed [0, 7, 14, 6, 3, 8, 0]
         * Printed [0, 7, 14, 6, 3, 8, 0]
         * Added 2b273823-97f1-4880-bfbe-3c636b2551c6 - 5
         * Printed [0, 7, 14, 6, 3, 5, 8, 0]
         * Added 3fdc56f6-860f-4f57-ab0d-4629b9c63f98 - 5
         * Printed [0, 7, 14, 6, 3, 5, 8, 0]
         * Printed [0, 7, 5, 14, 6, 3, 5, 8, 0]
         * Printed [0, 7, 5, 14, 6, 3, 5, 8, 0]
         * Printed [0, 7, 5, 14, 6, 3, 5, 8, 0]
         * Added 33a33860-3d72-4b4a-82d9-cb424fe35f72 - 4
         * Printed [0, 4, 7, 5, 14, 6, 3, 5, 8, 0]
         * Printed [0, 4, 7, 5, 14, 6, 3, 5, 8, 0]
         * Printed [0, 4, 7, 5, 14, 6, 3, 5, 8, 0]
         * Added f947fa67-c7e2-4240-b622-7b9107566c8c - 2
         * Printed [0, 4, 7, 5, 14, 6, 3, 5, 8, 0]
         * Printed [0, 4, 7, 5, 14, 6, 3, 5, 8, 0]
         * Printed [0, 4, 7, 5, 14, 6, 3, 5, 2, 8, 0]
         * Added e2a7eea0-506e-40bd-823d-dda0080f2b16 - 4
         * Printed [7, 5, 6, 3, 5, 2, 8, 0, 4, 0, 4, 14]
         * Printed [7, 5, 6, 3, 5, 2, 8, 0, 4, 0, 4, 14]
         * Added 71f07218-f782-459e-89ed-55eb9f7c572a - 1
         * Printed [7, 5, 6, 3, 5, 2, 8, 0, 4, 0, 4, 14]
         * Printed [1, 7, 5, 6, 3, 5, 2, 8, 0, 4, 0, 4, 14]
         * Printed [1, 7, 5, 6, 3, 5, 2, 8, 0, 4, 0, 4, 14]
         * Added 927ff589-a62a-4c12-bf70-7e531741c62e - 3
         * Printed [1, 7, 5, 6, 3, 5, 2, 8, 0, 4, 0, 4, 14]
         * Printed [3, 1, 7, 5, 6, 3, 5, 2, 8, 0, 4, 0, 4, 14]
         * Printed [3, 1, 7, 5, 6, 3, 5, 2, 8, 0, 4, 0, 4, 14]
         * Added 630d2bb1-746c-4180-b824-f64b019aee87 - 2
         * Printed [3, 1, 7, 5, 6, 3, 5, 2, 8, 0, 4, 0, 4, 14]
         * Printed [3, 2, 1, 7, 5, 6, 3, 5, 2, 8, 0, 4, 0, 4, 14]
         * Added 621101d1-27b7-4dd0-a646-d9296e567371 - 9
         * Printed [3, 2, 1, 7, 5, 6, 3, 5, 2, 8, 0, 4, 0, 4, 14]
         * Printed [3, 2, 1, 7, 5, 6, 3, 5, 2, 8, 0, 4, 0, 4, 14]
         * Printed [3, 2, 1, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14]
         * Printed [3, 2, 1, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14]
         * Printed [3, 2, 1, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14]
         * Added 8875ba92-be5f-44b9-a962-775b2e2e95b1 - 5
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14]
         * Added b8f9384c-67ed-474a-8337-63b6c75a9f96 - 3
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14, 3]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14, 3]
         * Added 4d180eb9-3d9a-46bb-9d42-d80472be77e2 - 1
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14, 1, 3]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14, 1, 3]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14, 1, 3]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14, 1, 3]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14, 1, 3]
         * Added 7ff07734-f7ed-4e56-ae36-636539829f33 - 9
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 14, 1, 9, 3]
         * Added 9ad09867-4076-4418-978e-b58db5282a05 - 4
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 4, 14, 1, 9, 3]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 4, 14, 1, 9, 3]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 4, 14, 1, 9, 3]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 4, 14, 1, 9, 3]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 4, 14, 1, 9, 3]
         * Added 64c5b04b-a52c-4c3b-b09a-1b07c0851ebb - 5
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 4, 14, 1, 5, 9, 3]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 4, 14, 1, 5, 9, 3]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 4, 14, 1, 5, 9, 3]
         * Added 2768d7af-0ad5-48b8-9b91-21ad4f673c89 - 0
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 4, 0, 14, 1, 5, 9, 3]
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 4, 0, 14, 1, 5, 9, 3]
         * Added 34f69517-1864-4592-b6ab-309974ce4038 - 5
         * Printed [3, 2, 1, 5, 7, 5, 6, 3, 5, 2, 8, 0, 9, 4, 0, 4, 5, 4, 0, 14, 1, 5, 9, 3]
         * Printed [3, 5, 5, 3, 6, 8, 4, 5, 14, 1, 9, 3, 2, 1, 5, 7, 2, 0, 9, 0, 4, 4, 0, 5]
         * Printed [3, 5, 5, 3, 6, 8, 4, 5, 14, 1, 9, 3, 2, 1, 5, 7, 2, 0, 9, 0, 4, 4, 0, 5]
         * Added 6a1142df-6c5d-476f-9f24-30bb0540f8b8 - 9
         * Printed [3, 5, 5, 3, 6, 8, 4, 5, 14, 1, 9, 9, 3, 2, 1, 5, 7, 2, 0, 9, 0, 4, 4, 0, 5]
         * Printed [3, 5, 5, 3, 6, 8, 4, 5, 14, 1, 9, 9, 3, 2, 1, 5, 7, 2, 0, 9, 0, 4, 4, 0, 5]
         * Added 4743ab39-135b-4465-bace-5ccb5827bcad - 2
         * Printed [3, 5, 5, 3, 6, 2, 8, 4, 5, 14, 1, 9, 9, 3, 2, 1, 5, 7, 2, 0, 9, 0, 4, 4, 0, 5]
         * Printed [3, 5, 5, 3, 6, 2, 8, 4, 5, 14, 1, 9, 9, 3, 2, 1, 5, 7, 2, 0, 9, 0, 4, 4, 0, 5]
         * Printed [3, 5, 5, 3, 6, 2, 8, 4, 5, 14, 1, 9, 9, 3, 2, 1, 5, 7, 2, 0, 9, 0, 4, 4, 0, 5]
         * Printed [3, 5, 5, 3, 6, 2, 8, 4, 5, 14, 1, 9, 9, 3, 2, 1, 5, 7, 2, 0, 9, 0, 4, 4, 0, 5]
          */

    }

}
