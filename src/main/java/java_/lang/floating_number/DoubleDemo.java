package java_.lang.floating_number;

/**
 * @author hsj
 * @create 2019/11/20
 */
public class DoubleDemo {

    public static void main(String[] args) {

        double d1 = Double.NaN;
        double d2 = Double.NaN;
        // 答案是 false ！ 惊不惊喜。因为所有NaN都认为是不相等的。
        // 为什么java里每个NaN都不相等,如何实现的？
        System.out.println(d1 == d2);
        System.out.println(d1 == Double.NaN);
        System.out.println(Double.NaN == Double.NaN);

        Double d3 = Double.NaN;
        Double d4 = Double.NaN;
        System.out.println(d3.equals(d4));

        System.out.println("Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY : "
                + (Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY));
        System.out.println("Double.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY : "
                + (Double.NEGATIVE_INFINITY == Double.NEGATIVE_INFINITY));

        // 答案：true。当一个数与本身不相等时，就是Nan。
        System.out.println(Double.isNaN(d1));

        float f = 0;
        System.out.println(f);
        // -Infinity
        System.out.println(-1f/0);
    }
}
