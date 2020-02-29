package thread;

public class TestSync {

    public void test() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
        }
        try {
            Thread.sleep(1000);
            System.out.println();
        }catch (Exception e){

        }
        synchronized (this) {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + " s: " + i);
            }
        }
    }

    public static void main(String[] args) {
//        TestSync sync = new TestSync();
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                sync.test();
//            }
//        }, "t1").start();
//
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                sync.test();
//            }
//        },"t2").start();
    }
}
