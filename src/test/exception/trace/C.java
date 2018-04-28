/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.exception.trace;

/**
 * @author Shuaijun He
 */
public class C {

    public void c() {
        System.out.println("c");
        throw new RuntimeException("DeadLock");
    }

}
