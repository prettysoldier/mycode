
package source_code_java.java_.syntax.interface_;

/**
 * @author Shuaijun He
 */
public class Class implements InterfaceTest.IHello {


    @Override
    public void sayHello() {
        System.out.println(a);
    }

    @Override
    public void sort(Comparable[] arr) {

    }

    public static void main(String[] args) {
        Object[] arr = {};
//        new Class().sort(arr);
    }

}
