
package java_.exception.stack_trace;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * @author Shuaijun He
 */
public class B {

    private C c = new C();

    public void b() {
        System.out.println("b");
        try {
            this.c.c();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("--ExceptionUtils--");
            System.out.println(ExceptionUtils.getFullStackTrace(e));
        }
    }

}
