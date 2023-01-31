package cn.jasonhu.learn.TestCase.dataType;

/**
 * 自动转换按从低到高的顺序转换。不同类型数据间的优先关系如下： 低 ———————————————> 高 byte,short,char-> int -> long -> float ->
 * double
 * <p>
 * 运算中，不同类型的数据先转化为同一类型，然后进行运算，转换规则如下： 操作数 1 类型                    操作数 2 类型    转换后的类型 byte/short/char
 *                     int             int byte/short/char/int                 long            long
 * byte/short/char/int/long            float           float byte/short/char/int/long/float
 * double          double
 */
public class TestFloat {

    float func0() {
        byte i = 1;
        return i;
    }

    // float func1() {
    // int i = 1;
    // return;
    // }

    float func2() {
        short i = 2;
        return i;
    }

    float func3() {
        long i = 3;
        return i;
    }

    // float func4() {
    // double i = 4;
    // return i;
    // }
}
