package cn.jasonhu.learn.TestCase.designPattern.singleton;

//枚举, 线程安全
public enum Singleton4 {
    INSTANCE;

    public void print() {
        System.out.println("cn/jasonhu/learn");
    }
}

class TestSingleton{
    public static void main(String[] args) {
        Singleton4.INSTANCE.print();
    }
}