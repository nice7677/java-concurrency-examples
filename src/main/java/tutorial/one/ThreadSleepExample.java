package tutorial.one;

public class ThreadSleepExample {

    public static void main(String[] args) {
        System.out.println("Current : " + Thread.currentThread().getName());

        String[] messages = {
                "1. 동해물과 백두산이 마르고 닳도록",
                "하느님이 보우하사 우리나라 만세",
                "무궁화 삼천리 화려 강산",
                "대한 사람 대한으로 길이 보전하세",
                "2. 남산 위에 저 소나무 철갑을 두른 듯",
                "바람 서리 불변함은 우리 기상일세",
                "무궁화 삼천리 화려 강산",
                "대한 사람 대한으로 길이 보전하세"
        };

        Runnable runnable = () -> {
            System.out.println("Current : " + Thread.currentThread().getName());
            for (String message : messages) {
                System.out.println(message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        Thread thread = new Thread(runnable);

        thread.start();
    }

}
