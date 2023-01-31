package cn.jasonhu.learn.TestCase.dataType;

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

    /**
     * 1、switch 控制表达式支持的类型有byte、short、char、int、enum（Java 5）、String(Java
     * 7)。不支持boolean、long、float、double 2、必须先进行null的判断 3、String字符串的switch是通过equals()和hashCode()方法来实现的
     */
    @Test
    public void testSwitch() {
        //long作为参数时，报错
//        Long a = 10L;
//        switch (a){
//            case 10L:
//                break;
//        }
        //boolean作为参数时，报错
//        boolean a = false;
//        switch (a){
//            case true:
//                break;
//        }

    }

}
