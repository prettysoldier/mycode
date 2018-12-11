
package java8.FunctionalInterface;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Shuaijun He
 */
public class BiFunctionTest {
    static int i = 0;

    public static void main(String[] args) {

        BiFunction<String, String, String> bi = (x, y) -> {
            return x + y;
        };
        Function<String, String> f = x -> x + " " + (++BiFunctionTest.i);

        System.out.println(bi.andThen(f).andThen(f).andThen(f).andThen(f)
            .andThen(f).andThen(f).andThen(f).andThen(f)
            .apply("java2s.com", " tutorial"));
    }
}
