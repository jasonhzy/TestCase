package cn.jasonhu.learn.TestCase.java8Feature;

/**
 * Java8允许在接口里定义默认方法和静态方法
 * 1、什么是默认方法？
 *      即接口可以有实现方法，而且不需要实现类去实现其方法。只需在方法名前面加个default关键字即可。
 * 2、为我们修改接口而不破坏原来的实现类的结构提供了便利，解决了接口的修改与现有的实现不兼容的问题
 */

interface A {
    default void print() {
        System.out.println("Calling A.print()");
    }

    //声明一个接口的静态方法
    static void testStatic(){
        System.out.println("MyFunction 静态方法");
    }
}


interface B {
    default void print() {
        System.out.println("Calling B.print()");
    }
}


public class DefaultFunClass implements A {
    public static void main(String[] args) {
        DefaultFunClass df = new DefaultFunClass();
        df.print();
    }
}

//这两种情况报错
//interface C extends A,B{}
//class C implements A,B{}

interface C extends A, B {
    @Override
    default void print() {
        System.out.println("Calling C.print()");
    }
}


class D implements A,B,C {

}

class E implements C{
    @Override
    public void print(){
        C.super.print(); //C.super表示的是C接口，同时E无法访问A,B的print
    }
}


class F implements A, B {
    @Override
    public void print() {
        A.super.print();
    }
}
