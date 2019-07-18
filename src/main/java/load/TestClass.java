package load;

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