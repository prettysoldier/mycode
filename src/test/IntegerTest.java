/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test;

/**
 * @author Shuaijun He
 */
public class IntegerTest {

    public static void main(String[] args) {
        System.out.println(new Integer(1) == Integer.valueOf(1));// false
        System.out.println(1 == Integer.valueOf(1)); // true
        System.out.println(Integer.valueOf(128) == Integer.valueOf(128)); // false
        System.out.println(Integer.valueOf(127) == Integer.valueOf(127)); // true
    }
}