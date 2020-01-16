package java_.syntax.overload;

import java.io.Serializable;

/**
 * 重载优先级：自动转型和自动装箱的顺序
 * 顺序：char->int-> long ->float-> double-> 自动装箱-> 接口或父类-> Object -> char...
 * byte->short->int-> long ->float-> double-> 自动装箱-> 接口或父类-> Object ->
 * boolean->Boolean->Serializable/Comparable->Object->boolean.../Boolean...
 *
 * @author Shuaijun He
 */
public class Overload {

    public static void main(String[] args) {

        sayHello(1);
        Overload.sayHello("a".getBytes()[0]);
        Overload.sayHello(true);
//        Overload.sayHello(null);
    }

    public static void sayHello(boolean arg) {
        System.out.println("hello boolean : " + arg);
    }

    public static void sayHello(char arg) {
        System.out.println("hello char : " + arg);
    }

    public static void sayHello(byte arg) {
        System.out.println("hello byte : " + arg);
    }

    public static void sayHello(short arg) {
        System.out.println("hello short : " + arg);
    }


//    public static void sayHello(int arg) {
//        System.out.println("hello int : " + arg);
//    }

    public static void sayHello(long arg) {
        System.out.println("hello long : " + arg);
    }

    public static void sayHello(float arg) {
        System.out.println("hello float : " + arg);
    }

    public static void sayHello(double arg) {
        System.out.println("hello double : " + arg);
    }


//    public static void sayHello(Boolean arg) {
//        System.out.println("hello Boolean : " + arg);
//    }
//    public static void sayHello(Byte arg) {
//        System.out.println("hello Byte : " + arg);
//    }
//
//    public static void sayHello(Character arg) {
//        System.out.println("hello character : " + arg);
//    }



    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable : " + arg);
    }

    public static void sayHello(Comparable<?> arg) {
        System.out.println("hello Comparable : " + arg);
    }

    public static void sayHello(Object arg) {
        System.out.println("hello Object : " + arg);
    }

//    public static void sayHello(boolean... arg) {
//        System.out.println("hello boolean... : " + arg[0]);
//    }

//    public static void sayHello(Boolean... arg) {
//        System.out.println("hello Boolean... : " + arg[0]);
//    }

//    public static void sayHello(char... arg) {
//        System.out.println("hello char : " + arg[0]);
//    }
//
//    public static void sayHello(short... arg) {
//        System.out.println("hello short... : " + arg[0]);
//    }
//
//    public static void sayHello(int... arg) {
//        System.out.println("hello int... : " + arg[0]);
//    }
//
//    public static void sayHello(float... arg) {
//        System.out.println("hello float... : " + arg[0]);
//    }





}
