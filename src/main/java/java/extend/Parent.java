
package main.test.java.extend;

/**
 * @author Shuaijun He
 */
public class Parent {

    static int m = 1;

    static {
        System.out.println("Parent init");
    }

    public void name() throws ArithmeticException {
        System.out.println("parent");
    }

    public void f() throws Exception {
        this.name();
        System.out.println("parent haha");
    }

    public static void g() {

    }
}
