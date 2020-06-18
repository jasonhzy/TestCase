package cn.jasonhu.test.classCast;

class Base {
    public void method() {
        System.out.println("cn.jasonhu.test.classCast.Base");
    }
}


class Son extends Base {
    @Override
    public void method() {
        System.out.println("cn.jasonhu.test.classCast.Son");
    }

    public void methodB() {
        System.out.println("SonB");
    }
}


public class TestClass {
    public static void main(String[] args) {
        Base base = new Son();
        base.method();
        // base.methodB();
    }
}
