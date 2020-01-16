package java_.syntax.access_permission._protected;

import java_.syntax.access_permission._protected.parent.Child1;
import java_.syntax.access_permission._protected.parent.Parent;

/**
 * @author hsj
 * @create 2019/12/24
 */
public class Child3 extends Parent {

    void h(){
        m();
    }


    public static void main(String[] args) {

        Child1 c1 = new Child1();
//        c1.m();
//        c1.f;

    }
}
