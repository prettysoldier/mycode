/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.extend;

/**
 * @author Shuaijun He
 */
public class NotInitialization {

    public static void main(String[] args) {
        Parent[] sca = new Parent[10];
        System.out.println(sca);
    }
}
