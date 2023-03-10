package cn.jasonhu.learn.TestCase.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatch的两种使用场景： 场景1：让多个线程等待 场景2：让单个线程等待
 *
 * @author jason hu
 * @since 2021/10/26 15:52
 */
public class TestCountDownLatch {

    CountDownLatch c1 = new CountDownLatch(1);
    CountDownLatch c2 = new CountDownLatch(2);

    /**
     * 场景1 让多个线程等待：模拟并发，让并发线程一起执行 运用案例： 1、为了模拟高并发，让一组线程在指定时刻(秒杀时间)执行抢购，这些线程在准备就绪后，进行等待(CountDownLatch.await())，直到秒杀时刻的到来，然后一拥而上；
     * 2、田径赛跑时，运动员会在起跑线做准备动作，等到发令枪一声响，运动员就会奋力奔跑
     */
    public void test1() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    //准备完毕……运动员都阻塞在这，等待号令
                    c1.await();
                    String parter = "【" + Thread.currentThread().getName() + "】";
                    System.out.println(parter + "开始执行……");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try {
            Thread.sleep(2000);// 裁判准备发令
        } catch (Exception e) {

        }
        c1.countDown();// 发令枪：执行发令
    }

    /**
     * 场景2 让单个线程等待：多个线程(任务)完成后，进行汇总合并 我们的并发任务，存在前后依赖关系；比如数据详情页需要同时调用多个接口获取数据，并发请求获取到数据后、需要进行结果合并；或者多个数据操作完成后，
     * 需要数据check；这其实都是：在多个线程(任务)完成后，进行汇总合并的场景。
     */
    public void test2() {
        AtomicInteger a = new AtomicInteger(0);
        AtomicInteger b = new AtomicInteger(0);
        new Thread(() -> {
            try {
                Thread.sleep(1000 + ThreadLocalRandom.current().nextInt(1000));
                a.set(10);
                c2.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000 + ThreadLocalRandom.current().nextInt(1000));
                b.set(20);
                c2.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            c2.await();// 主线程在阻塞，当计数器==0，就唤醒主线程往下执行。
            System.out.println("主线程:在所有任务运行完成后，进行结果汇总, sum=" + (a.get() + b.get()));
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        TestCountDownLatch t = new TestCountDownLatch();
        //t.test1();
        t.test2();
    }
}

/**
 * CountDownLatch: 减计数方式 计算为0时释放所有等待的线程 计数为0时，无法重置 调用countDown()方法计数减一，调用await()方法只进行阻塞，对计数没任何影响
 * 不可重复利用
 * <p>
 * CyclicBarrier: 加计数方式 计数达到指定值时释放所有等待线程 计数达到指定值时，计数置为0重新开始 调用await()方法计数加1，若加1后的值不等于构造方法的值，则线程阻塞
 * 可重复利用
 */
