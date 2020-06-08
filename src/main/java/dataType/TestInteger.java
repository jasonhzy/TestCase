package dataType;

import org.junit.Test;

public class TestInteger {

    public static void change(Integer jj) {
        jj = 30;// 将传递过来的引用，修改了
    }

    @Test
    public void test1() {
        Integer jk = 25;// jk是引用
        change(jk);// 引用传递
        System.out.println(jk);// 返回，25
    }

    @Test
    public void test2() {
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150, f5 = -128, f6 = -128, f7 = 127, f8 = 127;

        System.out.println(f1 == f2); // true
        System.out.println(f3 == f4); // false
        System.out.println(f5 == f6); // true
        System.out.println(f7 == f8); // true
    }

    @Test
    public void test3() {
        Integer a = new Integer(3);
        Integer b = 3; // 将3自动装箱成Integer类型
        int c = 3;
        System.out.println(a == b); // false 两个引用没有引用同一对象
        System.out.println(a == c); // true a自动拆箱成int类型再和c比较
        System.out.println(b == c); // true
    }

    // 中间缓存变量
    @Test
    public void test4() {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            j = j++; // 即为： temp = j; j = j + 1; j = temp;
        }
        System.out.println(j);
    }

    @Test
    public void test5() {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            j = ++j; // 即为： j = j + 1; temp = j; j = temp;
        }
        System.out.println(j);
    }

    @Test
    public void test6() {
        Integer b = 10;
        b += 1;
        System.out.println(b);

        Integer a = null;
        System.out.println(a + 1);
    }

    @Test
    public void test7() {
        int i = 40;
        int i0 = 40;
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);
        Double d1 = 1.0;
        Double d2 = 1.0;

        System.out.println("i=i0\t" + (i == i0)); // true

        // 1. int值只要在-128和127之间的自动装箱对象都从缓存中获取的
        System.out.println("i1=i2\t" + (i1 == i2)); // true

        // 2. 涉及到数字的计算，就必须先拆箱成int再做加法运算，所以不管他们的值是否在-128和127之间，只要数字一样就为true
        System.out.println("i1=i2+i3\t" + (i1 == i2 + i3)); // true

        // 比较的是对象内存地址
        System.out.println("i4=i5\t" + (i4 == i5)); // false

        // 同第2条解释，拆箱做加法运算，对比的是数字
        System.out.println("i4=i5+i6\t" + (i4 == i5 + i6)); // true

        // float, double的装箱操作没有使用缓存，每次都是new Double
        System.out.println("d1=d2\t" + (d1 == d2)); // false
    }

}


class Demo {
    int a;

    public Demo(int a) {
        this.a = a;
    }
}


class TestQuote {
    public static void main(String args[]) {
        Demo d1 = new Demo(1);
        Demo d2 = new Demo(2);
        System.out.println(d1.a);
        System.out.println(d2.a);
        function(d1, d2);
        System.out.println(d1.a);
        System.out.println(d2.a);
    }

    private static void function(Demo d1, Demo d2) {
        Demo temp;
        temp = d1;
        d1 = d2;
        d2 = temp;
    }
}
