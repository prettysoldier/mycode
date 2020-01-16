
package java_.syntax.keyword.extend;

/**
 * @author Shuaijun He
 */
public class Child extends Parent {

    // 说明属性是可以覆写的，但不建议使用
    static int m = 10;
    static {
        System.out.println("Child clinit");
    }

    int m1 = 20;
    {
        System.out.println("Child init");
    }


    public Child () {

    }




    /*
     * (non-Javadoc)
     * @see test.extend.Parent#f()
     */
    @Override
    public void f() {
        try {
            System.out.println(Child.m);
            name();
        } catch (ArithmeticException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void g() {

    }

    public static void main(String[] args) {
        Parent p = new Child();
//
//        p.f();
//        System.out.println(m);
    }
}
