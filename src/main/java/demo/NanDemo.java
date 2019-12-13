package demo;

/**
 * @author hsj
 * @create 2019/12/13
 */
public class NanDemo {
    public static void main(String[] args) {
        double d1 = Double.NaN;
        double d2 = Double.NaN;
        System.out.println("d1 == d2 : " + (d1 == d2));
        double d3 = d1;
        System.out.println("d3 == d1 : " + (d3 == d1));

        System.out.println("Double.NaN == Double.NaN : " + (Double.NaN == Double.NaN));
    }
}
