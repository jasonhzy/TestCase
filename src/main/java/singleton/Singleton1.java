package singleton;

public class Singleton1 {
    //类加载时就初始化
    private static Singleton1 instance = new Singleton1();

//    private static Singleton1 instance = null;
//    static {
//        instance = new Singleton1();
//    }

    private Singleton1(){

    }

    //饿汉式
    public static Singleton1 getInstance1(){
        return instance;
    }
}




