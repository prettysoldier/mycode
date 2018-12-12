package java_.overload;

/**
 * 重载时如果有两个被重载方法，如果会引起歧义，编译不过
 *
 * @author Shuaijun He
 */
public class Overload2 {

//    public static void sayHello(A arg) {
//        System.out.println("hello A : " + arg);
//    }
//
    public static void sayHello(B arg) {
        System.out.println("hello B : " + arg);
    }

//    public static void sayHello(C arg) {
//        System.out.println("hello C : " + arg);
//    }
//
//    public static void sayHello(C1 arg) {
//        System.out.println("hello C1 : " + arg);
//    }

    public static void main(String[] args) {
        // A 可以向上强转为 B、C或C1。
        Overload2.sayHello(new A());
    }
}

class A extends B implements C, C1 {
}

class B {
}

class B1 {
}

interface C {
}

interface C1 {
}
