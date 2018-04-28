/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.dispatch;

/**
 * 父类和接口的重载，会导致编译不通过
 *
 * @author Shuaijun He
 */
public class Overload2 {

    public static void sayHello(A arg) {
        System.out.println("hello A : " + arg);
    }

    public static void sayHello(B arg) {
        System.out.println("hello B : " + arg);
    }

//    public static void sayHello(C arg) {
//        System.out.println("hello C : " + arg);
//    }

    public static void main(String[] args) {
        Overload2.sayHello(new A());
    }
}

class A extends B implements C {
}

class B {
}

interface C {
}
