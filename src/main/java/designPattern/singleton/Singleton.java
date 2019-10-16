package designPattern.singleton;

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
}

//参考文档：
//1、http://www.blogjava.net/kenzhh/archive/2016/05/16/357824.html