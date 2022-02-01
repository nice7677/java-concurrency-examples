package tutorial.first;

public class ThreadJoinExample {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            System.out.println("Thread 1 실행");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("Thread 1 종료");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread 2 실행");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("Thread 2 종료");
        });

        System.out.println("Thread 1 시작");
        thread1.start();

        System.out.println("Thread 1 이 끝나기를 기다리는 중");
        try {
            thread1.join(500);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        System.out.println("충분히 기다렸으니 Thread2 출격");
        thread2.start();
    }

}
