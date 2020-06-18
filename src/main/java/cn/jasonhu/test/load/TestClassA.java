package cn.jasonhu.test.load;

public class TestClassA {
    static int count = 0;
    static {
        count++;
        System.out.println("A");
    }
    public TestClassA() {
        System.out.println("B");
    }
}
class ClassB {
    static {
        TestClassA t2;
        System.out.println("C");
    }
    public static void main(String[] args) {
        Class c1;
        Class c2;
        Class c3;
        try {
            c1 = TestClassA.class;
            c2 = Class.forName("cn.jasonhu.test.load.TestClassA");
            TestClassA a = new TestClassA();
            c3 = a.getClass();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        if (c2 == c1&& c1 == c3) {
            System.out.println("D");
        } else {
            System.out.println("E");
        }
        System.out.println(TestClassA.count);
    }
}