
package java_.interface_;

import java.util.Arrays;

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
