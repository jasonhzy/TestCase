package cn.jasonhu.learn.TestCase.thread;

// 线程状态：http://codingdict.com/article/11931
// https://www.cnblogs.com/i-code/p/13839020.html
/**
 * Java中的线程的生命周期大体可分为5种状态。
 * 1. 新建状态（New）：新创建了一个线程对象。
 * 2. 就绪状态（Runnable）：线程对象创建后，其他线程调用了该对象的start()方法。
 *      该状态的线程位于可运行线程池中，变得可运行，等待获取CPU的使用权。
 * 3. 运行状态（Running）：就绪状态的线程获取了CPU，执行程序代码。
 * 4. 阻塞状态（Blocked）：阻塞状态是线程因为某种原因放弃CPU使用权，暂时停止运行。
 *      直到线程进入就绪状态，才有机会转到运行状态。阻塞的情况分三种：
 * （一）、等待阻塞：运行的线程执行wait()方法，JVM会把该线程放入等待池中。
 * （二）、同步阻塞：运行的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则JVM会把该线程放入锁池中。
 * （三）、其他阻塞：运行的线程执行sleep()或join()方法，或者发出了I/O请求时，JVM会把该线程置为阻塞状态。
 *      当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。
 * 5. 死亡状态（Dead）：线程执行完了或者因异常退出了run()方法，该线程结束生命周期。
 */
public class TestThread extends Thread {

    @Override
    public void run() {
        System.out.println("learn run...");
    }

    public static void main(String[] args) {
        Thread t = new TestThread();
        t.start();
    }
}
