package cn.jasonhu.test.load;

//1.1 没有父类的情况
//    静态变量/代码块（优先级一样，看谁在前面）
//    实例变量/代码块（优先级一样，看谁在前面）
//    构造方法
//1.2 有父类的情况
//    父类静态变量/代码块（优先级一样，看谁在前面）
//    子类静态变量/代码块（优先级一样，看谁在前面）
//    父类实例变量/代码块（优先级一样，看谁在前面）
//    父类构造方法
//    子类实例变量/代码块（优先级一样，看谁在前面）
//    子类构造方法
//1.3 内部类加载
//    内部类也是类，仅在使用的时候加载。

class A {

    static {
        System.out.print("1");
    }

    public A() {
        System.out.print("2");
    }
}

class B extends A{

    static {
        System.out.print("a");
    }

    public B() {
        System.out.print("b");
    }
}

public class TestClass {

    public static void main(String[] args) {
        B ab = new B();
        ab = new B();
    }
}

// 运行结果：1a2b2b