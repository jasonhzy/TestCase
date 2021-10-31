package cn.jasonhu.learn.TestCase.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jason hu
 * @since 2021/10/26 14:50
 */
public class TestLock {

    private Lock lock =new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    private int remark = 1;

    private static final int TIMES = 5; //循环次数

    public void printA() {
        for (int i = 0; i < TIMES; i++) {
            try {
                lock.lock();
                if (remark != 1) {
                    c1.await();
                }
                System.out.print(Thread.currentThread().getName());
                remark = 2;
                c2.signal();//依次唤醒下一个需要打印的线程
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }

    public void printB() {
        for (int i = 0; i < TIMES; i++) {
            try {
                lock.lock();
                if (remark != 2) {
                    c2.await();
                }
                System.out.print(Thread.currentThread().getName());
                remark = 3;
                c3.signal();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }

    public void printC() {
        for (int i = 0; i < TIMES; i++) {
            try {
                lock.lock();
                if (remark != 3) {
                    c3.await();
                }
                System.out.println(Thread.currentThread().getName());
                remark = 1;
                c1.signal();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final TestLock test = new TestLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.printA();
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.printB();
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.printC();
            }
        }, "C").start();

    }
}
