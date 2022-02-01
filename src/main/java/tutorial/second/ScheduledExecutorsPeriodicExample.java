package tutorial.second;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorsPeriodicExample {

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Task 실행 시간 : " + LocalDateTime.now());
        };

        System.out.println("모든 스케줄 작업은 2초 간격으로 실행됩니다.");
        scheduledExecutorService.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);

        /**
         * 모든 스케줄 작업은 2초 간격으로 실행됩니다.
         * Task 실행 시간 : 2022-02-01T19:57:44.689096
         * Task 실행 시간 : 2022-02-01T19:57:46.628822
         * Task 실행 시간 : 2022-02-01T19:57:48.624801
         * Task 실행 시간 : 2022-02-01T19:57:50.624715
         */

    }

}
