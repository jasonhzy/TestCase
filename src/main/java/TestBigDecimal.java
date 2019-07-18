import org.junit.Test;

import java.math.BigDecimal;

public class TestBigDecimal {

    @Test
    public void testBigDecimal() {
        Double a = 0.3;

        BigDecimal b1 = new BigDecimal(a);
        BigDecimal b2 = new BigDecimal("0.3");

        System.out.println(b1);
        System.out.println(b2);
    }
}
