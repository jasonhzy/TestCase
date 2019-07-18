package singleton;

//枚举, 线程安全
public enum Singleton4 {
    INSTANCE;

    public void print() {
        System.out.println("test");
    }
}

class TestSingleton{
    public static void main(String[] args) {
        Singleton4.INSTANCE.print();
    }
}