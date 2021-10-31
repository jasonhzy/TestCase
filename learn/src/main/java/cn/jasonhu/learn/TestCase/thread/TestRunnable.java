package cn.jasonhu.learn.TestCase.thread;

import junit.framework.TestCase;
import org.junit.Test;

public class TestRunnable extends TestCase {

    @Test
    public void testCancelledTaggedRunnnable()throws InterruptedException{
        CancelledTaggedRunnnable taggedRunnnable=new CancelledTaggedRunnnable();
        Thread thread=new Thread(taggedRunnnable);
        thread.start();
        System.err.println(thread.isAlive());
        Thread.sleep(1000);
        taggedRunnnable.cancel();
        Thread.sleep(1000);
        System.err.println(thread.isAlive());
        Thread.sleep(1000);
        System.err.println(thread.isAlive());
    }
}
