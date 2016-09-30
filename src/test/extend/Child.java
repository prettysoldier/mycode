/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test.extend;

/**
 * @author Shuaijun He
 */
public class Child {

    public void name() {
        System.out.println("child");
    }

    public void f() {
        this.name();
        System.out.println("child haha");
    }
}
