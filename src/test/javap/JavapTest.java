/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.javap;

/**
 * javap -verbose JavapTest.class
 * 
 * @author Shuaijun He
 */
public class JavapTest extends Parent {

    public static void sayHello() {
        System.out.println("hello world");
    }

    public final void sayHello1() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        JavapTest.sayHello();
        JavapTest jp = new JavapTest();
        jp.sayHello1();
        jp.f();
    }

}
