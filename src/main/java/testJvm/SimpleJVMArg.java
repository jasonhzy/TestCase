package testJvm;

public class SimpleJVMArg {

    /**
     * @param args
     */
    public static void main(String[] args) {
        demo();
    }

    /**
     * VM arg：-verbose:gc -Xms200M -Xmx200M -Xmn100M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     * -XX:+UseSerialGC
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

