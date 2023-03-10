package cn.jasonhu.learn.TestCase.testJvm;

public class SimpleJVMArg {

    /**
     * @param args
     */
    public static void main(String[] args) {
        demo();
    }

    /**
     * VM arg：-verbose:gc -Xms200M -Xmx200M -Xmn100M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
     */
    @SuppressWarnings("unused")
    public static void demo() {

        final int tenMB = 10 * 1024 * 1024;

        byte[] alloc1, alloc2, alloc3;

        alloc1 = new byte[tenMB / 5];
        alloc2 = new byte[5 * tenMB];
        alloc3 = new byte[4 * tenMB];
        alloc3 = null;
        alloc3 = new byte[6 * tenMB];
    }
}

//JVM使用的默认的垃圾收集器：java -XX:+PrintCommandLineFlags -version

//1、查看堆的默认值，使用下面的代码，其中InitialHeapSize为最开始的堆的大小，MaxHeapSize为堆的最大值。
// java -XX:+PrintFlagsFinal -version | grep HeapSize

//2、查看栈的默认值,其中ThreadStackSize为栈内存的大小。
// java -XX:+PrintFlagsFinal -version | grep ThreadStackSize

//栈和堆功能
//最主要的区别就是栈内存用来存储局部变量和方法调用。
//而堆内存用来存储Java中的对象。无论是成员变量，局部变量，还是类变量，它们指向的对象都存储在堆内存中。

//独有与共享
//栈内存归属于单个线程，每个线程都会有一个栈内存，其存储的变量只能在其所属线程中可见，即栈内存可以理解成线程的私有内存。
//而堆内存中的对象对所有线程可见。堆内存中的对象可以被所有线程访问。
