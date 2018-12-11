
package java.extend;

/**
 * @author Shuaijun He
 */
public class Child extends Parent {

    static {
        System.out.println("Child init");
    }

    // 说明属性是可以重载的，但不建议使用
//    static int m = 2;


    /*
     * (non-Javadoc)
     * @see test.extend.Parent#f()
     */
    @Override
    public void f() {
        try {
            name();
        } catch (ArithmeticException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void g() {

    }

    public static void main(String[] args) {
//        Parent p = new Child();
//
//        p.f();
        System.out.println(m);
    }
}
