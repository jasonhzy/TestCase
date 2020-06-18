package cn.jasonhu.learn.TestCase.classCast;

// 在多态中，成员变量的特点： 无论编译和运行，都参考左边（引用型变量所属的类）
// 在多态中，静态成员函数的特点：无论编译和运行，都参考做左边。
// 在多态中，非静态成员函数的特点：编译看左边，运行看右边。

public class TestHuman {

    public void sleep() {
        System.out.println("Human sleep..");
    }

    public static void main(String[] args) {
        // 向上转型
        TestHuman h = new Male();
        h.sleep();
        // h.speak();此方法不能编译，报错说Human类没有此方法
        Male m = new Male();
        m.sleep();

        // 错误的
        // Male m1 = (Male) new TestHuman(); // java.lang.ClassCastException

        // 向下转型（先向上后向下）
        TestHuman h2 = new Male();
        Male m2 = (Male) h2;
        m2.sleep();
        m2.speak();
    }
}


class Male extends TestHuman {
    @Override
    public void sleep() {
        System.out.println("Male sleep..");
    }

    public void speak() {
        System.out.println("I am Male");
    }
}
