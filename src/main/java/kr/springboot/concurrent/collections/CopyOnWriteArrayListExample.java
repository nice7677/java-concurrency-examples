package kr.springboot.concurrent.collections;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CopyOnWriteArrayListExample {

    public static void main(String[] args) {
        System.out.println("=== CopyOnWriteArrayList ===");
        var executor = Executors.newCachedThreadPool();
        var random = new Random();
        // No ConcurrentModificationException
        var copyOnWriteArrayList = new CopyOnWriteArrayList<Integer>();

        for (int i = 0; i < 100; i++) {
            if (i % 5 == 0) {
                executor.execute(() -> {
                    var value = random.nextInt(10);
                    System.err.println("Added " + value);
                    copyOnWriteArrayList.add(value);
                });
            } else {
                executor.execute(() -> {
                    var builder = new StringBuilder();
                    for (var value : copyOnWriteArrayList) {
                        builder.append(value + " ");
                    }
                    System.out.println("Reading " + builder.toString());
                });
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(2000, TimeUnit.SECONDS);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * === CopyOnWriteArrayList ===
         * Reading
         * Reading
         * Reading
         * Reading
         * Reading
         * Reading
         * Reading
         * Reading
         * Added 5
         * Added 7
         * Added 5
         * Reading 5
         * Reading 5 5 7
         * Reading 5 5 7
         * Reading 5 5 7
         * Reading 5 5 7
         * Reading 5 5 7
         * Reading 5 5 7
         * Reading 5 5 7 8
         * Reading 5 5 7 8 1
         * Reading 5 5 7 8 1
         * Reading 5 5 7 8 1
         * Reading 5 5 7 8 1
         * Reading 5 5 7 8 1 3
         * Reading 5 5 7 8 1 3
         * Reading 5 5 7 8 1 3
         * Reading 5 5 7 8 1 3
         * Reading 5 5 7 8 1 3 6
         * Reading 5 5 7 8 1 3 6
         * Reading 5 5 7 8 1 3 6
         * Reading 5 5 7 8 1 3 6
         * Reading 5 5 7 8 1 3 6 7
         * Reading 5 5 7 8 1 3 6 7
         * Reading 5 5 7 8 1 3 6 7
         * Reading 5 5 7 8 1 3 6 7
         * Reading 5 5 7 8 1 3 6 7 6
         * Reading 5 5 7 8 1 3 6 7 6
         * Reading 5 5 7 8 1 3 6 7 6
         * Reading 5 5 7 8 1 3 6 7 6
         * Reading 5 5 7 8 1 3 6 7 6
         * Reading 5 5 7 8 1 3 6 7 6
         * Reading 5 5 7 8 1 3 6 7 6 9
         * Reading 5 5 7 8 1 3 6 7 6 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0
         * Reading 5 5 7 8 1 3 6 7 6 9 0
         * Reading 5 5 7 8 1 3 6 7 6 9 0
         * Reading 5 5 7 8 1 3 6 7 6 9 0
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9 9
         * Added 8
         * Added 1
         * Added 3
         * Added 6
         * Added 7
         * Added 6
         * Added 9
         * Added 0
         * Added 2
         * Added 9
         * Added 9
         * Added 4
         * Added 4
         * Added 0
         * Added 9
         * Added 9
         * Added 7
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9 9
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9 9 7
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9 9 7
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9 9 7
         * Reading 5 5 7 8 1 3 6 7 6 9 0 2 9 9 4 4 0 9 9 7
         */

    }

}
