package cn.jasonhu.learn.TestCase.thread;

import java.io.Serializable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jason hu
 * @since 2023/2/1 16:41
 */
public class TestThreadPool {

    public static void main(String[] args) {
        // 构造一个线程池
        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(8, 8, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1),
                        new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 1; i <= 11; i++) {
            try {
                // 产生一个任务，并将其加入到线程池
                String task = "task@ " + i;
                //System.out.println("put " + task);
                threadPool.execute(new ThreadPoolTask(task));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadPoolTask implements Runnable, Serializable {
    // 保存任务所需要的数据
    private Object data;

    ThreadPoolTask(Object obj) {
        this.data = obj;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName() + "：" + data);
                //this.wait();
                // //便于观察，等待一段时间
                Thread.sleep(15000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            data = null;
        }
    }
}
