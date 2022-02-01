package tutorial.third;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        long startTime = System.nanoTime();
        Future<String> future = executorService.submit(() -> {
            Thread.sleep(2000);
            return "안녕 Callable!";
        });

        while (!future.isDone()) {
            System.out.println("Task가 아직 실행 중 입니다.");
            Thread.sleep(200);
            double elapsedTimeInSec = (System.nanoTime() - startTime)/1000000000.0;
            if (elapsedTimeInSec > 1) future.cancel(true);
        }

        // (1)
//        System.out.println("Task 실행 완료!");
//        String result = future.get();
//        System.out.println(result);

        /**
         * Task가 아직 실행 중 입니다.
         * Task가 아직 실행 중 입니다.
         * Task가 아직 실행 중 입니다.
         * Task가 아직 실행 중 입니다.
         * Task가 아직 실행 중 입니다.
         * Task 실행 완료!
         * Exception in thread "main" java.util.concurrent.CancellationException
         * 	at java.base/java.util.concurrent.FutureTask.report(FutureTask.java:121)
         * 	at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:191)
         * 	at tutorial.third.FutureCancelExample.main(FutureCancelExample.java:29)
         */

        // (2) isCancelled 추가 CancellationException 호출 전 cancel check
        if(!future.isCancelled()) {
            System.out.println("Task 실행 완료!");
            String result = future.get();
            System.out.println(result);
        } else {
            System.out.println("Task가 취소 됬습니다.");
        }

        /**
         * Task가 아직 실행 중 입니다.
         * Task가 아직 실행 중 입니다.
         * Task가 아직 실행 중 입니다.
         * Task가 아직 실행 중 입니다.
         * Task가 아직 실행 중 입니다.
         * Task 실행 완료!
         * Exception in thread "main" java.util.concurrent.CancellationException
         * 	at java.base/java.util.concurrent.FutureTask.report(FutureTask.java:121)
         * 	at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:191)
         * 	at tutorial.third.FutureCancelExample.main(FutureCancelExample.java:28)
         */

        executorService.shutdown();

    }

}
