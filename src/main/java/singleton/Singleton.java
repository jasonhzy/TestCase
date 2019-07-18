package singleton;

public class Singleton {
    private static Singleton instance = null;

    private Singleton(){

    }

    //懒汉式，线程不安全
    public static Singleton getInstance(){
        if(null == instance){
            instance = new Singleton();
        }
        return instance;
    }

    //线程安全
    public static synchronized Singleton getInstance1() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    //双检锁
    public static Singleton getInstance2(){
        if(null == instance){
            synchronized(Singleton.class){
                if(null == instance){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
