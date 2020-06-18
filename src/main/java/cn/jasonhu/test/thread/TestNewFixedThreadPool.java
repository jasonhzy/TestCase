package cn.jasonhu.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestNewFixedThreadPool {

    /**
     * 不能用junit测试，因其调用了System.exit();
     *
     * @param args
     */
//    @cn.jasonhu.test.Test
//    public void testNewCahcedThreadPool() {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);// 每隔两秒打印2个数
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + ":" + index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
