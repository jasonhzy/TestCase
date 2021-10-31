package cn.jasonhu.learn.TestCase.juc;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jason hu
 * @since 2021/10/26 16:25
 */

class Runner implements Runnable {
    // 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)
    private CyclicBarrier barrier;

    private String name;

    public Runner(CyclicBarrier barrier, String name) {
        super();
        this.barrier = barrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000 * (new Random()).nextInt(8));
            System.out.println(name + " 准备好了...");
            // barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(name + " 起跑！");
    }
}

public class TestCyclicBarrier {
    /**
     * CyclicBarrier可以用于多线程计算数据，最后合并计算结果的应用场景
     */

    public static void main(String[] args) throws IOException, InterruptedException {
        //如果将参数改为4，但是下面只加入了3个选手，这永远等待下去
        //Waits until all parties have invoked await on this barrier.
        CyclicBarrier barrier = new CyclicBarrier(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Thread(new Runner(barrier, "1号选手")));
        executor.submit(new Thread(new Runner(barrier, "2号选手")));
        executor.submit(new Thread(new Runner(barrier, "3号选手")));

        executor.shutdown();
    }
}

/**
 *
     CountDownLatch : 一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行。
     CyclicBarrier : N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待。
     细分：
     CountDownLatch简单的说就是一个线程等待，直到他所等待的其他线程都执行完成并且调用countDown()方法发出通知后，当前线程才可以继续执行。
     cyclicBarrier是所有线程都进行等待，直到所有线程都准备好进入await()方法之后，所有线程同时开始执行！

     1）CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：
     CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
     而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
     2）CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。
     3）CyclicBarrier还提供其他有用的方法，比如getNumberWaiting方法可以获得CyclicBarrier阻塞的线程数量。isBroken方法用来知道阻塞的线程是否被中断。
 */

