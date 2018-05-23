
package test.java;

/**
 * @author Shuaijun He
 */
public class MyOperator {

    public static void main(String[] args) {
        // 右移
        long a = -1;
        a >>= 1;
        System.out.println(a);

        long b = 11;
        b >>>= 1;
        System.out.println(b);
    }
}
