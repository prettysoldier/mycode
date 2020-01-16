package source_code_java.java_.syntax.exception.stack_trace;

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
