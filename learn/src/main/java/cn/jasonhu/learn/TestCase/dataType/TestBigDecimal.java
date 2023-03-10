package cn.jasonhu.learn.TestCase.dataType;

import java.math.BigDecimal;
import org.junit.Test;

public class TestBigDecimal {

    @Test
    public void testBigDecimal() {
        Double a = 0.3;

        // 不推荐此方式，BigDecimal(double)存在精度损失风险
        // 浮点数没有办法用二进制精确表示，因此存在精度丢失的风险
        BigDecimal b1 = new BigDecimal(a);
        // 推荐方式, 以下两种方式等价的
        BigDecimal b2 = BigDecimal.valueOf(a);
        BigDecimal b3 = new BigDecimal("0.3");

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }
}
