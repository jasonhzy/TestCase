package singleton;

public class Singleton3 {

    private Singleton3(){}

    private static class SingletonHandler {
        private static final Singleton3 instance = new Singleton3();
    }

    //静态内部类
    public static Singleton3 getSingleton() {
        return SingletonHandler.instance;
    }
}