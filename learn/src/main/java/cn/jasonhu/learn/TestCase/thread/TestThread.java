package cn.jasonhu.learn.TestCase.thread;

import java.util.concurrent.atomic.AtomicInteger;

// 线程状态：http://codingdict.com/article/11931
// https://www.cnblogs.com/i-code/p/13839020.html
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
