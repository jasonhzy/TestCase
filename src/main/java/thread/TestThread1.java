package thread;

public class TestThread1 {

    public static void main(String[] args) {
        new TestThread1().go();
    }

    public void go() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.print("foo");
            }
        };
        Thread t = new Thread(r);
        t.start();
    }
}
