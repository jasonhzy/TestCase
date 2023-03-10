package cn.jasonhu.learn.TestCase.abstractAndInterface;

/**
 * @author jason hu
 * @since 2023/3/1 11:22
 */
public abstract class TestAbstract {

    // 定义普通变量
    int count = 2;
    // 定义私有变量
    private static int total = 10;

    void TestAbstract() {
    }

    /**
     *  add抽象方法
     *
     * @return
     */
    abstract Integer add();

    // 定义普通方法
    public void test() {

    }

}

//1、抽象类使用 abstract 关键字声明。
//2、抽象类中可以包含普通方法和抽象方法，抽象方法不能有具体的代码实现（抽象方法必须为public或者protected， 因private不能被子类继承，子类便无法实现该方法）
//3、抽象类需要使用 extends 关键字实现继承。
//4、抽象类不能直接实例化。
//5、抽象类中属性控制符无限制，可以定义 private 类型的属性
//6、抽象类中可使用静态代码块
