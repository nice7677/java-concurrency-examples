package tutorial.third;

import java.util.concurrent.*;

public class FutureTimeoutExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> {
            Thread.sleep(500);
            return "Future 타임아웃 테스트!";
        });

        System.out.println("실행");
        String result = future.get(1, TimeUnit.SECONDS);
        System.out.println(result);

        /**
         *  12:line Thread.sleep(1500)
         *  실행
         *  Exception in thread "main" java.util.concurrent.TimeoutException
         * 	at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:204)
         * 	at tutorial.third.FutureTimeoutExample.main(FutureTimeoutExample.java:17)
         *
         * 	12:line Thread.sleep(500)
         *  실행
         *  Future 타임아웃 테스트!
         */

        executorService.shutdown();

    }

}
