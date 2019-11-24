package java_.lang.floating_number;

/**
 * @author hsj
 * @create 2019/11/20
 */
public class DoubleDemo {

    public static void main(String[] args) {

        double d1 = equalSignTest();

//        equalTest();

//        infinityTest();

        // 答案：true。当一个数与本身不相等时，就是Nan。NaN 是唯一与自身不等的值
        System.out.println(Double.isNaN(d1));

    }

    private static double equalSignTest() {
        double d1 = Double.NaN;
        double d3 = d1;
        System.out.println("d3 == d1 : " + (d3 == d1));
        double d2 = Double.NaN;
        // 答案是 false ！ 惊不惊喜。因为所有NaN都认为是不相等的。
        // 为什么java里每个NaN都不相等,如何实现的？
        System.out.println("d1 == d2 : " + (d1 == d2));
        System.out.println(d1 == Double.NaN);
        System.out.println("Double.NaN == Double.NaN : " + (Double.NaN == Double.NaN));
        System.out.println("0.0/0 == 0.0/0 : " + (0.0/0 == 0.0/0));
        return d1;
    }

    /**
     * 所有的正无穷数都是==且equals。所有的负无穷数都是==且equals。
     */
    private static void infinityTest() {
        System.out.println("Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY : "
                + (Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY));
        System.out.println("Double.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY : "
                + (Double.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY));

        System.out.println("Double.valueOf(Double.NEGATIVE_INFINITY).equals(Double.longBitsToDouble(0xfff0000000000000L)) : "
                + (Double.valueOf(Double.NEGATIVE_INFINITY).equals(Double.longBitsToDouble(0xfff0000000000000L))));

        System.out.println("Double.valueOf(Double.NEGATIVE_INFINITY).equals(Double.valueOf(Double.NEGATIVE_INFINITY)) : "
                + (Double.valueOf(Double.NEGATIVE_INFINITY).equals(Double.valueOf(Double.NEGATIVE_INFINITY))));

        // 说明所有的正无穷数都是相等的。所有的负无穷数都是相等的
        System.out.println("1D/0 == 2D/0 : " + (1.0/0 == 2.0/0));
        System.out.println("1D/0 == 2D/0 : " + (1.0/0 == -2.0/0));
        System.out.println("-1D/0 == -2D/0 : " + (-1.0/0 == -2.0/0));
    }



    private static void equalTest() {
        Double d3 = Double.NaN;
        Double d4 = Double.NaN;
        // Double 覆写了equals 方法
        System.out.println(d3.equals(d4));
    }


}
