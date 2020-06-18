package cn.jasonhu.learn.TestCase.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class InterruptRunnable implements Runnable{
    private BlockingQueue queue = new ArrayBlockingQueue(10);
    @Override
    public void run() {
        int i= 0;
        for (;;) {
            try {
                //线程的操作
                i++;
                queue.put(i);
            } catch (InterruptedException e) {
                //捕获到了异常 该怎么做
                System.out.println(queue);
                e.printStackTrace();
                return;
            }
        }
    }
}