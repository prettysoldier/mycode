package main.test.java.exception.finally_;

/**
 * @author Shuaijun He
 */
public class FinallyTest {

    public int inc() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
            System.out.println(x);
        }
    }

    public static void main(String[] args) {
        System.out.println(new FinallyTest().inc());
    }
}
