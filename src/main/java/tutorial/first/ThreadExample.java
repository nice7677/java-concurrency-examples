package tutorial.first;

public class ThreadExample extends Thread{

    @Override
    public void run() {
        System.out.println("Current : " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("Current : " + Thread.currentThread().getName());

        System.out.println("스레드 생성..");
        Thread thread = new ThreadExample();

        System.out.println("스레드 시작...");
        thread.start();
    }

}
