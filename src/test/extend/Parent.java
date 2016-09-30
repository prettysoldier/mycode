/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test.extend;

/**
 * @author Shuaijun He
 */
public class Parent extends Child {

    /*
     * (non-Javadoc)
     * @see test.extend.Child#name()
     */
    @Override
    public void name() {
        System.out.println("parent");
    }

    public static void main(String[] args) {
        Parent p = new Parent();

        p.f();
    }
}
