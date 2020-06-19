package cn.jasonhu.learn.TestCase.dataType;

import org.junit.Test;

import java.math.BigDecimal;

public class TestBigDecimal {

    @Test
    public void testBigDecimal() {
        Double a = 0.3;

        BigDecimal b1 = new BigDecimal(a); //不推荐此方式，BigDecimal(double)存在精度损失风险
        BigDecimal b2 = BigDecimal.valueOf(a); //推荐方式
        BigDecimal b3 = new BigDecimal("0.3");

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }
}
