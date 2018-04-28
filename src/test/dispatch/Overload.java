/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.dispatch;

/**
 * 重载优先级：自动转型和自动装箱的顺序
 * 顺序：char->int-> long ->float-> double-> 自动装箱-> 接口或父类-> Object -> char...
 * 如果有两个接口（或父类）的重载，会导致编译不通过
 *
 * @author Shuaijun He
 */
public class Overload {

    public static void sayHello(char arg) {
        System.out.println("hello char : " + arg);
    }

    public static void sayHello(int arg) {
        System.out.println("hello int : " + arg);
    }

    public static void sayHello(long arg) {
        System.out.println("hello long : " + arg);
    }

    public static void sayHello(float arg) {
        System.out.println("hello float : " + arg);
    }

    public static void sayHello(double arg) {
        System.out.println("hello double : " + arg);
    }

    public static void sayHello(byte arg) {
        System.out.println("hello byte : " + arg);
    }

    public static void sayHello(short arg) {
        System.out.println("hello short : " + arg);
    }

//    public static void sayHello(Character arg) {
//        System.out.println("hello character : " + arg);
//    }

//    public static void sayHello(Serializable arg) {
//        System.out.println("hello Serializable : " + arg);
//    }

//    public static void sayHello(Comparable<?> arg) {
//        System.out.println("hello Comparable : " + arg);
//    }

//    public static void sayHello(char... arg) {
//        System.out.println("hello char : " + arg);
//    }

//    public static void sayHello(int... arg) {
//        System.out.println("hello int... : " + arg);
//    }

//    public static void sayHello(float... arg) {
//        System.out.println("hello float... : " + arg);
//    }

//    public static void sayHello(Object arg) {
//        System.out.println("hello object : " + arg);
//    }

    public static void main(String[] args) {

//        Overload.sayHello('a');
//        Overload.sayHello(null);
    }

}
