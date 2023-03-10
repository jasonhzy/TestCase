package cn.jasonhu.learn.TestCase.thread;

public class TestSync {

    // 同步实例方法
    public synchronized void test() {
        int count = 5;
        for (int i = 0; i < 5; i++) {
            count--;
            System.out.println(Thread.currentThread().getName() + " - " + count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    // 同步代码块
    public void test1() {
        int count = 5;
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                count--;
                System.out.println(Thread.currentThread().getName() + " - " + count);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    // 静态同步方法
    public static synchronized void test2() {
        int count = 5;
        for (int i = 0; i < 5; i++) {
            count--;
            System.out.println(Thread.currentThread().getName() + " - " + count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    // 静态同步代码块
    public static synchronized void test3() {
        int count = 5;
        synchronized (TestSync.class) {
            for (int i = 0; i < 5; i++) {
                count--;
                System.out.println(Thread.currentThread().getName() + " - " + count);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }
    // synchronized作用范围：
    // 1、同步实例方法：锁定的是该类的实例对象
    // 2、同步代码块：this修饰，锁定的是该类的实例对象
    // 3、静态同步方法：锁定的是类对象
    // 4、静态同步代码块：锁定的是类对象
    // 备注、类锁和对象锁是不一样的锁，是互相独立的

    public static void main(String[] args) {
        TestSync sync = new TestSync();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //sync.test();
                TestSync.test2();
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //sync.test();
                //sync.test1();
                TestSync.test3();
            }
        }, "t2").start();
    }
}
