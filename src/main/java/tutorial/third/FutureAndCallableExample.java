package tutorial.third;

import java.util.concurrent.*;

public class FutureAndCallableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            System.out.println("Callable 실행");
            Thread.sleep(1000);
            return "안녕 Callable!";
        };

        System.out.println("Callable Submit");
        Future<String> future = executorService.submit(callable);

        System.out.println("Callable이 실행되는 동안 다른 작업 수행");

        System.out.println("Retrieve the result of the future");
        // Future.get() => Result 결과를 받을때까지 Blocks
        String result = future.get();
        System.out.println(result);

        executorService.shutdown();

        /**
         * Callable Submit
         * Callable이 실행되는 동안 다른 작업 수행
         * Callable 실행
         * Retrieve the result of the future
         * 안녕 Callable!
         */

    }

}
