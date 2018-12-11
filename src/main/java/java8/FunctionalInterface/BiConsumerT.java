
package java8.FunctionalInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author Shuaijun He
 */
public class BiConsumerT {

    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<>(5);
        map.put(1, 2);
        BiConsumer<Integer, Integer> bic = (k, v) -> {
            map.remove(k);
            k = k + 10;
            v = v + 10;
            map.put(k, v);

        };
        /*
         * bic.andThen((k, v) -> {
         * k = k + 10;
         * v = v + 10;
         * System.out.println("k=" + k + ",v=" + v);
         * }).andThen((k, v) -> {
         * k = k + 10;
         * v = v + 10;
         * System.out.println("k=" + k + ",v=" + v);
         * }).accept(1, 2);
         */

        map.forEach(bic);

        System.out.println(map);
    }
}
