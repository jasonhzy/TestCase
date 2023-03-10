package cn.jasonhu.learn.TestCase.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread1 {

    static int x = 0;
    static volatile boolean v = false;
    public static void writer() {
        x = 42;
        v = true;
    }

    public static void reader() {
        if (v == true) {
            //uses x - guaranteed to see 42.
            System.out.println(x);
        }
    }

    public static void testWrite() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("==1==");
                writer();
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    public static void testReader() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("==2==");
                reader();
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    public static void main(String[] args) {
        testWrite();
        testReader();
    }
}
