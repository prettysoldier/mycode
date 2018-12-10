
package test.java.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Shuaijun He
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("1000.10");
        bd.setScale(2, RoundingMode.HALF_UP);
        System.out.println(bd);
    }
}
