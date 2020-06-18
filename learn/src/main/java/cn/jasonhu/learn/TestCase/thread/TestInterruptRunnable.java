package cn.jasonhu.learn.TestCase.thread;

import junit.framework.TestCase;
import org.junit.Test;

public class TestInterruptRunnable extends TestCase {

    @Test
    public void testInterruptRunnable() throws InterruptedException {
        InterruptRunnable runnable = new InterruptRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        System.err.println(thread.isAlive());
        Thread.sleep(1000);
        thread.interrupt();
        Thread.sleep(1000);
        System.err.println(thread.isAlive());
        Thread.sleep(1000);
        System.err.println(thread.isAlive());
    }
}
