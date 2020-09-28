package cn.jasonhu.learn.TestCase.thread;

// 线程状态：http://codingdict.com/article/11931
public class TestThread extends Thread {

    @Override
    public void run(){
        System.out.println("learn run...");
    }

    public static void main(String[] args) {
        Thread t = new TestThread();
        t.start();
    }
}
