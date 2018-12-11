package java.exception.trace;

/**
 * @author Shuaijun He
 */
public class A {

    private B b = new B();

    public void a() {
        System.out.println("a");
        this.b.b();
    }

}
