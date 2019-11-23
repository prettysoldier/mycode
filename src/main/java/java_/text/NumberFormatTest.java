package java_.text;

import java.text.NumberFormat;

/**
 * @author heshuaijun
 * @date 2019/11/23 23:24
 */
public class NumberFormatTest {

    public static void main (String[] args) {

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
        NumberFormat numberFormatter = NumberFormat.getNumberInstance();
        NumberFormat integerFormatter = NumberFormat.getIntegerInstance();
        double x = 11.1;
        System.out.println(currencyFormatter.format(x));
        System.out.println(percentFormatter.format(x));
        System.out.println(numberFormatter.format(x));
        System.out.println(integerFormatter.format(x));
    }
}
