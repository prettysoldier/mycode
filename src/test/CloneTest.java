/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test;

/**
 * @author Shuaijun He
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        Object a = new Object();
        Object se = a.clone();
        System.out.println(a + "|" + se);
    }
}
