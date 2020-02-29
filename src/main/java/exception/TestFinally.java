package exception;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 1、finally语句是在return语句执行之后，return返回之前执行的
 * 2、finally块中的return语句会覆盖try块中的return返回
 * 4、当发生异常后，catch中的return执行情况与未发生异常时try中return的执行情况完全一样
 * 5、a.如果finally中有return语句，则会将try中的return语句“覆盖”掉，直接执行finally中的return语句，
 *      得到返回值，这样便无法得到try之前保留好的返回值。
 *    b.如果finally中没有return语句，也没有改变要返回值，则执行完finally中的语句后，会接着执行try中的
 *      return语句，返回之前保留的值。
 *    c.如果finally中没有return语句，但是改变了要返回的值，这里有点类似与引用传递和值传递的区别，分以下两种情况：
 *      1）如果return的数据是基本数据类型或文本字符串，则在finally中对该基本数据的改变不起作用，
 *          try中的return语句依然会返回进入finally块之前保留的值。
 *      2）如果return的数据是引用数据类型，而在finally中对该引用数据类型的属性值的改变起作用，
 *          try中的return语句返回的就是在finally中改变后的该属性的值。
 */

public class TestFinally {

    /**
     * 第一点
     */
    public static String test1() {
        try {
            System.out.println("try block");
            return test12();
        } finally {
            System.out.println("finally block");
        }
    }

    public static String test12() {
        System.out.println("return statement");
        return "after return";
    }

    @Test
    public void test10() {
        System.out.println(test1());
    }
    /**
     * 运行结果：
         try block
         return statement
         finally block
         after return
     */

    public static int test2() {
        int b = 20;

        try {
            System.out.println("try block");

            return b += 80;
        } catch (Exception e) {

            System.out.println("catch block");
        } finally {
            System.out.println("finally block");

            if (b > 25) {
                System.out.println("b>25, b = " + b);
            }

            return 200;
        }
    }
    /**
     * 运行结果：
         try block
         finally block
         b>25, b = 100
         200
     */

    @Test
    public void test20() {
        System.out.println(test2());
    }


    public static int test3() {
        int b = 20;

        try {
            System.out.println("try block");

            return b += 80;
        } catch (Exception e) {

            System.out.println("catch block");
        } finally {

            System.out.println("finally block");

            if (b > 25) {
                System.out.println("b>25, b = " + b);
            }

            b = 150;
        }

        return 2000;
    }

    @Test
    public void test30() {
        System.out.println(test3());
    }

    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("KEY", "INIT");

        try {
            map.put("KEY", "TRY");
            return map;
        } catch (Exception e) {
            map.put("KEY", "CATCH");
        } finally {
            map.put("KEY", "FINALLY");
            map = null;
        }
        return map;
    }

    @Test
    public void getResult() {
        System.out.println(getMap());
    }


    public static int test4() {
        int b = 20;

        try {
            System.out.println("try block");
            b = b / 0;
            return b += 80;
        } catch (Exception e) {
            System.out.println("catch block");
            return b += 15;
        } finally {
            System.out.println("finally block");
            if (b > 25) {
                System.out.println("b>25, b = " + b);
            }
            b += 50;
        }
        //return b;
    }

    @Test
    public void test40() {
        System.out.println(test4());
    }
    /**
     * 运行结果：
         try block
         catch block
         finally block
         b>25, b = 35
         35
     */


    public int vaule = 0;

    public  TestFinally test5(){
        TestFinally t = new TestFinally();
        try {
            t.vaule = 1;
            System.out.println("try block: " + t.vaule);
            return t;
        }catch (Exception e){
            t.vaule = -1;
            System.out.println("catch block: " + t.vaule);
            return t;
        }finally {
            t.vaule = 3;
            System.out.println("finally block: " + t.vaule);
        }
    }

    @Test
    public void test50() {
        System.out.println(test5().vaule);
    }
    /**
     * 运行结果：
         try block: 1
         finally block: 3
         3
     */

}
