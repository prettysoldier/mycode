
package javap;

/**
 * javap -verbose JavapDemo.class
 * 
 * @author Shuaijun He
 */
public class JavapDemo extends Parent {

    public static void sayHello() {
        System.out.println("hello world");
    }

    public final void sayHello1(int a) {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        JavapDemo.sayHello();
        JavapDemo jp = new JavapDemo();
        jp.f();
    }
}

class Parent {

    public void f() {
        System.out.println("hello world");
    }
}
