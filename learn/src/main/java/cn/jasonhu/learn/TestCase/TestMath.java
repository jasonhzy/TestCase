package cn.jasonhu.learn.TestCase;

import org.junit.Test;

// Math.floor() 表示向下取整，返回double类型（floor---地板）。即小于参数的最大整数
// Math.ceil() 表示向上取整，返回double类型ceil---天花板）。即大于参数的最小整数
// Math.round() 四舍五入，返回int类型。算法为Math.floor(x+0.5)，即将原来的数字加上0.5后再向下取整

public class TestMath {

    @Test
    public void test() {
        System.out.println(Math.floor(8.5));
        System.out.println(Math.ceil(8.5));
        System.out.println(Math.round(8.5));

        System.out.println(Math.floor(-8.5));
        System.out.println(Math.ceil(-8.5));
        System.out.println(Math.round(-8.5));
    }

    @Test
    public void testCeil() {
        int a = 10;
        int b = 3;
        System.out.println(Math.ceil(a * 1.0 / b));
    }

    @Test
    public void testDivision() {
        int a = 10;
        int b = 3;
        System.out.println(Math.ceil(a * 1.0 / b));
    }

    @Test
    public void testOperators(){
        System.out.println(1 << 30);
    }
}
