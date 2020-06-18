package cn.jasonhu.learn.TestCase.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestNewSingleThreadPool {

    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + ":" + index);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
