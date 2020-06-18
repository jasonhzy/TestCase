package cn.jasonhu.learn.TestCase.thread;

class CancelledTaggedRunnnable implements Runnable {
    private volatile boolean cancelled = false;

    @Override
    public void run() {
        while (!cancelled) {
            System.out.println(Thread.currentThread().getName());
            //没有停止需要做什么操作
        }            //线程停止后需要干什么
        System.out.println("任务结束了");
    }

    public void cancell() {
        cancelled = true;
    }
}
