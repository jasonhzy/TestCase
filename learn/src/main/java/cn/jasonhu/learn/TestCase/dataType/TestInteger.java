package cn.jasonhu.learn.TestCase.dataType;

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

    /**
     * 1、自动装箱和拆箱的原理: 自动装箱时编译器调用valueOf将原始类型值转换成对象，同时自动拆箱时， 编译器通过调用类似intValue(),doubleValue()这类的方法将对象转换成原始类型值。
     * 2、只有double和float的自动装箱代码没有使用缓存，每次都是new新的对象，其它的6种基本类型都使用了缓存策略。 3、有如下几种情况时，编译器会自动帮我们进行装箱或拆箱： a.
     * 进行 = 赋值操作（装箱或拆箱） b. 进行+，-，*，/混合运算 （拆箱） c. 进行>,<,==比较运算（拆箱） d. 调用equals进行比较（装箱） e.
     * ArrayList,HashMap等集合类添加基础类型数据时（装箱）
     */
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

    /**
     * 自动装箱有一个问题，那就是在一个循环中进行自动装箱操作的情况，如下面的例子就会创建多余的对象，影响程序的性能。举例说明如下：
     * <p>
     * 下面的代码sum+=i可以看成sum = sum + i，但是+这个操作符不适用于Integer对象，首先sum进行自动拆箱操作，进行数值相加操作，
     * 最后发生自动装箱操作转换成Integer对象。其内部变化如下： int result = sum.intValue() + i; Integer sum = new
     * Integer(result); 由于我们这里声明的sum为Integer类型，在上面的循环中会创建将近4000个无用的Integer对象，在这样庞大的循环中，会降低程序的性能
     * 并且加重了垃圾回收的工作量。因此在我们编程时，需要注意到这一点，正确地声明变量类型，避免因为自动装箱引起的性能问题。
     */
    @Test
    public void test() {
        Integer sum = 0;
        for (int i = 1000; i < 5000; i++) {
            sum += i;
        }
    }

    /**
     * intValue()是把Integer对象类型变成int的基础数据类型； parseInt()是把String 变成int的基础数据类型； Valueof()是把String
     * 转化成Integer对象类型；（现在JDK版本支持自动装箱拆箱了。）
     */
    @Test
    public void testFunc() {
        int a = Integer.parseInt("1024");
        int b = Integer.valueOf("1024").intValue();
    }

    @Test
    public void test8() {
        Integer i = 42;
        Long l = 42L;
        Double d = 42.0;
//        System.out.println(i == l);
//        System.out.println(i == d);
//        System.out.println(l == d);
        System.out.println(i.equals(d));
        System.out.println(d.equals(l));
        System.out.println(i.equals(l));
        System.out.println(l.equals(42L));
    }

    @Test
    public void testMapSize() {
        int cap = 10000;
        int n = cap - 1;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println("n1=" + n);
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println("n2=" + n);
        n |= n >>> 4;
        System.out.println("n4=" + n);
        n |= n >>> 8;
        System.out.println("n8=" + n);
        n |= n >>> 16;
        System.out.println("n16=" + n);
        System.out.println(n);
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
