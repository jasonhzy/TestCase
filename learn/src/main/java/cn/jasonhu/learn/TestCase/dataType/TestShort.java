package cn.jasonhu.learn.TestCase.dataType;

import org.junit.Test;

public class TestShort {

    public static void test1() {
        short s1 = 1;
        // s1 = s1 + 1;
    }

    public static void test2() {
        short s1 = 1;
        s1 += 1;
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    @Test
    public void test3() {
        Short f1 = 100, f2 = 100, f3 = 150, f4 = 150, f5 = -128, f6 = -128, f7 = 127, f8 = 127;

        System.out.println(f1 == f2); // true
        System.out.println(f3 == f4); // false
        System.out.println(f5 == f6); // true
        System.out.println(f7 == f8); // true
    }

    @Test
    public void test4() {
        Character f1 = 100, f2 = 100, f3 = 150, f4 = 150, f5 = 127, f6 = 127, f7 = 128, f8 = 128;
        System.out.println(f1 == f2); // true
        System.out.println(f3 == f4); // false
        System.out.println(f5 == f6); // true
        System.out.println(f7 == f8); // false
    }
}
