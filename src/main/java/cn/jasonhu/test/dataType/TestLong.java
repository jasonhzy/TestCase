package cn.jasonhu.test.dataType;

import org.junit.Test;

public class TestLong {

    @Test
    public void testRemainder() {
        int amount = 189;
        System.out.println(amount / 10);

        Long a = 1000000000000000000L;
        Integer b = 999999999;
        System.out.println(a.intValue());
        if (a < b.longValue()) {
            System.out.println("true");
        }
    }

    @Test
    public void testCompare() {
        Long a = 1583164800000L;
        long b = 1583164800000L;
        Long c = 1583164800000L;
        long d = 1583164800000L;

        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a.equals(c));
        System.out.println(a == c);
        System.out.println(b == d);
    }

}
