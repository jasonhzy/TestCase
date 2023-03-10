package cn.jasonhu.learn.TestCase.abstractAndInterface;

/**
 * @author jason hu
 * @since 2023/3/1 11:22
 */
public interface TestInterface {

    //默认 public static final
    int a = 0;

    public static final int b = 10;

    /**
     *  add desc
     * @return
     */
    abstract Integer add();

    default void test() {

    }

    static void test1() {

    }
}

//1、JDK 8 中接口可以定义 static 和 default 方法，并且这两种方法可以包含具体的代码实现。
//2、实现接口要使用 implements 关键字。
//3、接口不能直接实例化。
//4、接口中定义的变量默认为 public static final 类型。
//5、子类可以不重写接口中的 static 和 default 方法，不重写的情况下，默认调用的是接口的方法实现
//6、接口中不能使用静态代码块
