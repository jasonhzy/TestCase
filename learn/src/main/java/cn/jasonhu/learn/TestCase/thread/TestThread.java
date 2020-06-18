package cn.jasonhu.learn.TestCase.thread;

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
