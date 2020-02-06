
package source_code_java.java_.syntax.keyword.extend;

/**
 * @author Shuaijun He
 */
public class Parent {

    static int m = 1;

    static {
        System.out.println("Parent clinit");
    }

    int m1 = 2;
    {
        System.out.println("parent init");
    }

    public Parent (int m1) {
        this.m1 = m1;
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
