package tutorial.one;

public class RunnableExample implements Runnable{

    public static void main(String[] args) {
        System.out.println("Current : " + Thread.currentThread().getName());

        System.out.println("런에이블 생성...");
        Runnable runnable = new RunnableExample();

        System.out.println("스레드 생성...");
        Thread thread = new Thread(runnable);

        System.out.println("스레드 시작...");
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Current : " + Thread.currentThread().getName());
    }

}
