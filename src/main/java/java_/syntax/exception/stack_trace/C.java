
package java_.syntax.exception.stack_trace;

/**
 * @author Shuaijun He
 */
public class C {

    public void c() {

        System.out.println("c");
        System.out.println("--getStackTrace--");
        System.out.println(StackTraceUtils.getStackTrace());

        System.out.println("--getStackTrace2--");
        System.out.println(StackTraceUtils.getStackTrace2());

        System.out.println("--getStackTrace3--");
        System.out.println(StackTraceUtils.getStackTrace3());

        System.out.println("--dumpStack--");
        Thread.dumpStack();
        throw new RuntimeException("MyException");
    }

}
