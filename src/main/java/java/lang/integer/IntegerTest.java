
package main.test.java.lang.integer;

/**
 * @author Shuaijun He
 */
@SuppressWarnings("ALL")
public class IntegerTest {

    public static void main(String[] args) {
        System.out.println(new Integer(1) == Integer.valueOf(1));// false
        System.out.println(1 == Integer.valueOf(1)); // true
        System.out.println(Integer.valueOf(128) == Integer.valueOf(128)); // false
        System.out.println(Integer.valueOf(127) == Integer.valueOf(127)); // true
    }
}
