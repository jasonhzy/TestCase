package cn.jasonhu.learn.TestCase.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author jason hu
 * @since 2021/8/18 17:54
 */

public class ABA {
    private static AtomicInteger atomicInt = new AtomicInteger(100);
    /**
     *  AtomicstampedReference 这个类 通过维护一个内部类Pair (它包括的字段 ：对象的引用，以及相对应的stamp(版本号)）来避免ABA问题，
     *  类似oracle的乐观锁中的多版本控制！
     *  构造方法中，第一个参数为reference，第二个为stamp的初始化值！int类型
     *  他的compareAndSet(expectedRerference , newReference , expectStamp ,newStamp),
     *  CAS的时候，就会先检查stamp是否相符！不符合 == false;
     */
    private static AtomicStampedReference atomicStampedRef = new AtomicStampedReference(100, 0);

    public static void main(String[] args) throws InterruptedException {
        Thread intT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInt.compareAndSet(100, 101);
                atomicInt.compareAndSet(101, 100);
            }
        });

        Thread intT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                boolean c3 = atomicInt.compareAndSet(100, 101);
                System.out.println(c3); // true
                System.out.println(atomicInt.get()); // 101
            }
        });

        intT1.start();
        intT2.start();
        intT1.join();
        intT2.join();

        Thread refT1 = new Thread(new Runnable() {
            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println("refT1stamp: " +  atomicStampedRef.getStamp()); // 0
                System.out.println("refT2stamp init value: " +  atomicStampedRef.getReference());    // 100
                atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
                System.out.println("refT1stamp: " +  atomicStampedRef.getStamp());    // 1
                System.out.println("refT1stamp value first: " +  atomicStampedRef.getReference());    // 101
                atomicStampedRef.compareAndSet(101, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
                System.out.println("refT1stamp: " +  atomicStampedRef.getStamp());    // 2
                System.out.println("refT1stamp value second: " +  atomicStampedRef.getReference());    // 100
            }
        });

        Thread refT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedRef.getStamp();// 这个地方是关键
                System.out.println("refT2stamp: " + stamp); // stamp = 0 说明他对应的版本号为0 而当refT1运行完以后，对应的版本号为1所以不行
                System.out.println("refT2stamp init value: " +  atomicStampedRef.getReference());    // 100
                try {
                    Thread.sleep(5000); // sleep() 保证refT1先运行
                } catch (InterruptedException e) {
                }
                boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println(c3); // false
                System.out.println("refT2stamp: " +  atomicStampedRef.getStamp());    // 2
                System.out.println("refT2stamp value: " +  atomicStampedRef.getReference());    // 100
            }
        });

        refT1.start();
        refT2.start();
    }
}
