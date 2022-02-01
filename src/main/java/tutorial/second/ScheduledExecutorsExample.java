package tutorial.second;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorsExample {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            System.out.println("Task 실행 시간 : " + LocalDateTime.now());
        };

        System.out.println(LocalDateTime.now() + "에 제출한 작업은 5초 후 실행됩니다.");
        scheduledExecutorService.schedule(task, 5, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();

        /**
         * 2022-02-01T19:54:42.372679에 제출한 작업은 5초 후 실행됩니다.
         * Task 실행 시간 : 2022-02-01T19:54:47.401606 <- 5초 후 실행됬다
         */
    }

}
