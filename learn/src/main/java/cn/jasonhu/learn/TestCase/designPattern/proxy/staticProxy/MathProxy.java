package cn.jasonhu.learn.TestCase.designPattern.proxy.staticProxy;

import cn.jasonhu.learn.TestCase.designPattern.proxy.jdk.IMath;

/**
 * 静态代理实现步骤:
 * 1、定义一个接口及其实现类；
 * 2、创建一个代理类同样实现这个接口
 * 3、将目标对象注入进代理类，然后在代理类的对应方法调用目标类中的对应方法。
 *    这样的话，我们就可以通过代理类屏蔽对目标对象的访问，并且可以在目标方法执行前后做一些自己想做的事情。
 */
public class MathProxy implements IMath {

    private final IMath iMath;

    public MathProxy(IMath iMath) {
        this.iMath = iMath;
    }

    @Override
    public int add(int n1, int n2) {
        return 0;
    }

    @Override
    public int sub(int n1, int n2) {
        return 0;
    }

    @Override
    public int mut(int n1, int n2) {
        return 0;
    }

    @Override
    public int div(int n1, int n2) {
        return 0;
    }
}
