
package test.java.operand;

/**
 * @author Shuaijun He
 */
public class MyOperator {

    public static void main(String[] args) {
        // 右移
        long a = -1;
        a >>= 1;
        System.out.println(a);

        long c = -11;
        c >>= 1;
        System.out.println(c);

        long b = 11;
        b >>>= 1;
        System.out.println(b);


    }
}
