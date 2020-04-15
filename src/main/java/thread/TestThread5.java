package thread;

public class TestThread5 {
    public static boolean flag = true;

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("线程 " + this.getName() + " in");
            // 死循环
            while (flag) {
            }
            // 不会死循环
//            while (flag) {
//                System.out.println("死循环");
//            }
            System.out.println("线程 " + this.getName() + " 停止了");
        }
    }


    public static void main(String[] args) {
        try {
            new T1("t1").start();
            //休眠1秒
            Thread.sleep(1000);
            //将flag置为false
            flag = false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
//https://www.shangmayuan.com/a/aa84404d39bd456693d20160.html
/**
1、运行上面代码，会发现程序没法终止。

线程t1的run()方法中有个循环，经过flag来控制循环是否结束，主线程中休眠了1秒，将flag置为false，按说此时线程t1会检测到flag为false，打印“线程t1中止了”，
为什么和咱们指望的结果不同呢？运行上面的代码咱们能够判断，t1中看到的flag一直为ture，主线程将flag置为false以后，t1线程中没有看到，因此一直死循环。

2、那么t1中为何看不到被主线程修改以后的flag？

要解释这个，咱们须要先了解一下java内存模型（JMM），Java线程之间的通讯由Java内存模型（本文简称为JMM）控制，
JMM决定一个线程对共享变量的写入什么时候对另外一个线程可见。

3、使用volatile修饰共享变量
a. 线程中读取的时候，每次读取都会去主内存中读取共享变量最新的值，而后将其复制到工做内存
b. 线程中修改了工做内存中变量的副本，修改以后会当即刷新到主内存

4、修改一下开头的示例代码：
 public volatile static boolean flag = true;
 使用volatile修饰flag变量，而后运行一下程序，输出：

     线程t1 in
     线程t1中止了

 此时程序能够正常中止了。volatile解决了共享变量在多线程中可见性的问题，可见性是指一个线程对共享变量的修改，对于另外一个线程来讲是不是能够看到的。

5、在while循环体中加上一段输出语句，也能够停止线程，代码：
     while (flag) {
         System.out.println("死循环");
     }

 因为输出语句的内容，有一个同步代码块，进入、离开同步代码块，都会和主内存的共享变量的值保证一致，从而实现了可见性。源码如下：
     public void println(String x) {
         synchronized (this) {
             print(x);
             newLine();
         }
     }
 */
