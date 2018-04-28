/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test.extend;

/**
 * @author Shuaijun He
 */
public class Parent {

    static int m = 1;

    static {
        System.out.println("Parent init");
    }

    public void name() {
        System.out.println("parent");
    }

    public void f() {
        this.name();
        System.out.println("parent haha");
    }

    public static void g() {

    }
}
