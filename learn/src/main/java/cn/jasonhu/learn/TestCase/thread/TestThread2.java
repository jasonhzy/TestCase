package cn.jasonhu.learn.TestCase.thread;

//sleep和wait的区别有：
//1、这两个方法来自不同的类分别是Thread和Object
//2、最主要是sleep方法没有释放锁,只是停在休眠的位置等待时间到，因此没有让出cpu给其他线程，而wait方法释放了锁，
//   使得其他线程可以使用同步控制块或者方法。
//3、wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用，例如：
//    synchronized(x){
//        x.notify()
//        //或者wait()
//    }
// wait，notify和notifyAll不搭配 synchronized使用,会出现IllegalMonitorStateException和Lost Wake-Up Problem(唤醒丢失的问题)
//4、sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常


public class TestThread2 {
    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable {
        @Override
        public void run() {
            synchronized (TestThread2.class) {
                System.out.println("enter thread1...");
                System.out.println("thread1 is waiting...");
                try {
                    // 调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
                    TestThread2.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 is going on ....");
                System.out.println("thread1 is over!!!");
            }
        }
    }

    private static class Thread2 implements Runnable {
        @Override
        public void run() {
            synchronized (TestThread2.class) {
                System.out.println("enter thread2....");
                System.out.println("thread2 is sleep....");
                // 只有针对此对象调用notify()方法后本线程才进入对象锁定池准备获取对象锁进入运行状态。
                TestThread2.class.notify();
                // ==================
                // 区别
                // 如果我们把代码：TestThread2.class.notify();给注释掉，即TestThread2.class调用了wait()方法，但是没有调用notify()
                // 方法，则线程永远处于挂起状态。
                try {
                    // sleep()方法导致了程序暂停执行指定的时间，让出cpu给其他线程，
                    // 但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
                    // 在调用sleep()方法的过程中，线程不会释放对象锁。
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on....");
                System.out.println("thread2 is over!!!");
            }
        }
    }
}
