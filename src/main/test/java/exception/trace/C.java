
package main.test.java.exception.trace;

/**
 * @author Shuaijun He
 */
public class C {

    public void c() {
        System.out.println("c");
        throw new RuntimeException("DeadLock");
    }

}
