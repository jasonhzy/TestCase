package cn.jasonhu.learn.TestCase.designPattern.singleton;

public class Singleton1 {

    private static Singleton1 instance = new Singleton1();

    //另一种写法 静态代码块
//    private static Singleton1 instance = null;
//    static {
//        instance = new Singleton1();
//    }

    private Singleton1() {
    }

    //饿汉式，线程安全
    public static Singleton1 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
    }
}
