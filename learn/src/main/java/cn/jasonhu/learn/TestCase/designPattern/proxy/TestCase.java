package cn.jasonhu.learn.TestCase.designPattern.proxy;

import cn.jasonhu.learn.TestCase.designPattern.proxy.cglib.CglibMath;
import cn.jasonhu.learn.TestCase.designPattern.proxy.cglib.CglibProxyFactory;
import cn.jasonhu.learn.TestCase.designPattern.proxy.jdk.JdkInvocationHandler;
import cn.jasonhu.learn.TestCase.designPattern.proxy.jdk.IMath;
import cn.jasonhu.learn.TestCase.designPattern.proxy.jdk.Math;
import cn.jasonhu.learn.TestCase.designPattern.proxy.staticProxy.MathProxy;
import org.junit.Test;

/**
 *
 *  JDK 动态代理和 CGLIB 动态代理对比JDK
 *  1、动态代理只能代理实现了接口的类或者直接代理接口，而 CGLIB 可以代理未实现任何接口的类。
 *     另外， CGLIB 动态代理是通过生成一个被代理类的子类来拦截被代理类的方法调用，因此不能代理声明为 final 类型的类和方法。
 *  2、就二者的效率来说，大部分情况都是 JDK 动态代理更优秀，随着 JDK 版本的升级，这个优势更加明显。
 *
 *  静态代理和动态代理的对比
 *  1、灵活性 ：动态代理更加灵活，不需要必须实现接口，可以直接代理实现类，并且可以不需要针对每个目标类都创建一个代理类。
 *      另外，静态代理中，接口一旦新增加方法，目标对象和代理对象都要进行修改，这是非常麻烦的！
 *  2、JVM 层面 ：静态代理在编译时就将接口、实现类、代理类这些都变成了一个个实际的 class 文件。
 *      而动态代理是在运行时动态生成类字节码，并加载到 JVM 中的。
 */
public class TestCase {

    int n1 = 100, n2 = 5;

    /**
     *  jdk 动态代理
     */
    @Test
    public void test1() {
        // 实例化一个MathProxy代理对象
        // 通过getProxyObject方法获得被代理后的对象
        IMath math = (IMath) new JdkInvocationHandler()
                .getProxyObject(new Math());
        math.add(n1, n2);
        math.sub(n1, n2);
        math.mut(n1, n2);
        math.div(n1, n2);
    }

    /**
     *  cglib 动态代理（Code Generation Library）
     */
    @Test
    public void test2() {
        CglibMath math = (CglibMath) CglibProxyFactory.getProxy(CglibMath.class);
        math.add(n1, n2);
    }

    /**
     *  静态代理
     */
    @Test
    public void test3() {
        IMath iMath = new Math();
        MathProxy mathProxy = new MathProxy(iMath);
        mathProxy.add(n1, n2);
    }
}
