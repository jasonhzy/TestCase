package cn.jasonhu.learn.TestCase.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jason hu
 * @since 2023/3/8 13:41
 */
public class TestLockSupport {

    public static Object u = new Object();

    public static ReentrantLock lock = new ReentrantLock(true);

    static boolean flag = false;

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
                System.out.println("继续执行");
            }
        }
    }

    public static class TestThread extends Thread {

        public TestThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            Thread current = Thread.currentThread();
            System.out.println(current.getName() + ",开始执行!");
            for (; ; ) {//spin 自旋
                System.out.println("准备park住当前线程：" + current.getName());
                LockSupport.park();
                System.out.println("当前线程" + current.getName() + "已经被唤醒...");
                //打印线程中断标识，注意：Thread.interrupted()和Thread.currentThread().isInterrupted的区别
                //前者会清除标记，后者不会（其实就是调用方法参数true 或 false的问题）
                // 放开下面的打印输出语句后：输出的执行结果中没有不断的打印循环体中的日志，说明后续的阻塞生效了
                System.out.println("当前线程" + current.getName() + "是否被中断：" + Thread.interrupted());
            }
        }
    }

    public static void main(String[] args) {
        //ChangeObjectThread t1 = new ChangeObjectThread("t1");
        //System.out.println("====1====");
        //t1.start();
        //System.out.println("====2====");
        //LockSupport.unpark(t1);
        //System.out.println("====3====");

        //try {
        //    TestThread t1 = new TestThread("t1");
        //    t1.start();
        //    System.out.println("准备唤醒线程：" + t1.getName());
        //    LockSupport.unpark(t1);
        //    Thread.sleep(2000);
        //
        //    //[1]测试中断：输出的执行结果发现在不断的打印循环体中的日志，说明后续的阻塞失效了
        //    t1.interrupt();
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        //存放10个线程
        List<Thread> list = new ArrayList<>();

        //创建10个线程，只有一个持有锁，并一直持有，那么后续的其他线程都在阻塞队列中
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock");
                //等待flag结束信号，实际上永远不会退出while，故意第一个线程持有锁，其他线程一直在等待队列中
                while (true) {
                    if (flag) {
                        break;
                    }
                }
                lock.unlock();
            }, "t-" + i);
            list.add(t);
            t.start();

            //增加时间间隔，保证第一个线程能持有锁
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            //等待2s，保证其他线程在阻塞队列
            Thread.sleep(2000);
            //[5]取第四个线程，将其中断
            list.get(3).interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
