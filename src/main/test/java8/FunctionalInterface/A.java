
package main.test.java8.FunctionalInterface;

/**
 * @author Shuaijun He
 */
public class A {

    public static void main(String[] args) {

        Test t = a -> a;

        Test t2 = a -> {
            int w = a * 2;
            return w * w;
        };

        System.out.println(t.f(200));
        System.out.println(t2.f(200));
    }
}
