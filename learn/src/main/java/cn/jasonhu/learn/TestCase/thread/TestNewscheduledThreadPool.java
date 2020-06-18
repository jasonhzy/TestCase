package cn.jasonhu.learn.TestCase.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestNewscheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": delay 1 seconds, and excute every 3 seconds, "
                        + System.currentTimeMillis());
            }
        }, 1, 3, TimeUnit.SECONDS);
    }
}
