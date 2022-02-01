package tutorial.second;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsExample1 {

    public static void main(String[] args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Executor Service 생성...");
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        System.out.println("Runnable 생성...");
        Runnable runnable = () -> {
            System.out.println("Insdie : " + Thread.currentThread().getName());
        };


        System.out.println("Runnable Exexcutor에 submit");
        executorService.submit(runnable);

        // shutdown 하지 않으면 종료되지않음.
        System.out.println("Executor 종료");
        executorService.shutdown();
    }

}
