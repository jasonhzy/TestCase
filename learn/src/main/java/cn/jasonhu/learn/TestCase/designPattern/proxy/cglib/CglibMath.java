package cn.jasonhu.learn.TestCase.designPattern.proxy.cglib;

/**
 * @author jason hu
 * @since 2023/3/2 15:51
 */
public class CglibMath {
    // 加
    public int add(int n1, int n2) {
        int result = n1 + n2;
        System.out.println(n1 + "+" + n2 + "=" + result);
        return result;
    }

    // 减
    public int sub(int n1, int n2) {
        int result = n1 - n2;
        System.out.println(n1 + "-" + n2 + "=" + result);
        return result;
    }

    // 乘
    public int mut(int n1, int n2) {
        int result = n1 * n2;
        System.out.println(n1 + "X" + n2 + "=" + result);
        return result;
    }

    // 除
    public int div(int n1, int n2) {
        int result = n1 / n2;
        System.out.println(n1 + "/" + n2 + "=" + result);
        return result;
    }
}
