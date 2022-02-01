package tutorial.third;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureIsDoneExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> {
            Thread.sleep(1000);
            return "안녕 Callable";
        });

        while (!future.isDone()) {
            System.out.println("Taks가 아직 끝나지 않았습니다...");
            Thread.sleep(200);
        }

        System.out.println("Taks 실행 완료!");
        String result = future.get();
        System.out.println(result);

        executorService.shutdown();

        /**
         * Taks가 아직 끝나지 않았습니다...
         * Taks가 아직 끝나지 않았습니다...
         * Taks가 아직 끝나지 않았습니다...
         * Taks가 아직 끝나지 않았습니다...
         * Taks가 아직 끝나지 않았습니다...
         * Taks 실행 완료!
         * 안녕 Callable
         */

    }

}
