package cn.jasonhu.learn.TestCase.thread;

//让线程顺序执行

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThread4 {

    private static Thread t1 = new Thread(() -> System.out.println("t1 start"));
    private static Thread t2 = new Thread(() -> System.out.println("t2 start"));
    private static Thread t3 = new Thread(() -> System.out.println("t3 start"));


    //设置线程优先级--不准确
    @Test
    public void test1() {
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
    }

    //Thread.join()方法
    //在子线程调用了join()方法后面的代码，只有等到子线程结束了当前线程才能执行。
    @Test
    public void test2() throws Exception {
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
        System.out.println("cn/jasonhu/learn");
    }

    //Executors.newSingleThreadExecutor() 创建单线程池

    @Test
    public void test3() {
        ExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.execute(t1);
        service.execute(t2);
        service.execute(t3);
    }
}
