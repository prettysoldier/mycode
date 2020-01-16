package source_code_java.java_.math;

import java.math.BigDecimal;

/**
 * 银行家舍入的规律：四舍六入五考虑，五后非零就进一，五后为零看前面，五前为偶应舍去，五前为奇要进一（保证最后一位是偶数）
 *
 * @author Shuaijun He
 */
public class HalfEvenTest {

    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("65465.55000");
        bd.setScale(1, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(bd);

        BigDecimal bd1 = BigDecimal.valueOf(1200);
        bd1.setScale(1, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(bd1);

    }
}
