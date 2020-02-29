package thread;
/**
 * 题目：建立三个线程，A线程打印10次A，B线程打印10次B,C线程打印10次C，要求线程同时运行，交替打印10次ABC
 */
public class TestThread3 implements Runnable {

    private String name;
    private Object prev;
    private Object self;

    private TestThread3(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                synchronized (self) {
                    System.out.print(name);
                    if("C".equals(name)){
                        System.out.println();
                    }
                    count--;

                    self.notify();
                }
                try {
                    if (count > 0) {
                        prev.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        TestThread3 pa = new TestThread3("A", c, a);
        TestThread3 pb = new TestThread3("B", a, b);
        TestThread3 pc = new TestThread3("C", b, c);

        new Thread(pa).start();
        Thread.sleep(10);
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
        Thread.sleep(10);
    }
}