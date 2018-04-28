/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.exception.trace;

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
