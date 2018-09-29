/**
 * 
 */
package test.java.construct;

/**
 * 父子之间的构造器，可以不一样（主要指参数）。父子构造器之间没有继承关系
 * 
 * @author shuaijunhe
 */
public class A extends B {

    int a1, b1, c1;

    A a;

    public A() {
//        System.out.println("A before");
        this(1, 2); // 必须是第一行
//        super(1); // 必须是第一行
        System.out.println("A after");
        a = this;
    }


    public A(int a, int b) {
        super(a);
    }

    public A(int a, int b, int c) {

        this(a, b);

    }


    public static void main(String[] args) {
        new A();
    }
}

class B {
    B() {
        System.out.println("B");
    }
    B(int b) {
        System.out.println("B" + b);
    }
}