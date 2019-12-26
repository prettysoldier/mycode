
package java_.exception.stack_trace;

/**
 * @author Shuaijun He
 */
public class C {

    public void c() {

        System.out.println("c");
        System.out.println(StackTraceUtils.getStackTrace());
        System.out.println();
        System.out.println(StackTraceUtils.getStackTrace2());
        throw new RuntimeException("MyException");
    }

}
