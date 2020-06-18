package cn.jasonhu.learn.TestCase.designPattern.singleton;

public class Singleton {
    private static Singleton instance = null;

    private Singleton(){}

    //懒汉式，线程不安全
    public static Singleton getInstance(){
        if(null == instance){
            instance = new Singleton();
        }
        return instance;
    }

    //懒汉式，线程安全
    public static synchronized Singleton getInstance1() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }


    //单例类是否可以继承？
    //1、当子类是父类单例类的内部类时，继承是可以的，如下所示。
    //编译和执行上允许的，但是继承单例没有实际的意义，反而会变得更加事倍功半，其代价要大于新写一个单例类
    public static class  MySingleton extends Singleton {

    }
    //2、如果子类为单独的类，非单例类的内部类的话，那么在编译时就会出错
    // Implicit super constructor BaseSingleton() is not visible for default constructor. Must define an explicit constructor，
    // 主要原因是单例类的构造器是private，解决方法是讲构造器设置为可见，但是这样做就无法保证单例的唯一性。所以这种方式不可以继承。
}

//参考文档：
//1、http://www.blogjava.net/kenzhh/archive/2016/05/16/357824.html


//真的只有一个对象么？
//其实，单例模式并不能保证实例的唯一性，只要我们想办法的话，还是可以打破这种唯一性的。以下几种方法都能实现。
//
//1、使用反射，虽然构造器为非公开，但是在反射面前就不起作用了。
//2、如果单例的类实现了cloneable，那么还是可以拷贝出多个实例的。
//3、Java中的对象序列化也有可能导致创建多个实例。避免使用readObject方法。
//4、使用多个类加载器加载单例类，也会导致创建多个实例并存的问题。