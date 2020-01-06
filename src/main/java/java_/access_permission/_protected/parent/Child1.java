package java_.access_permission._protected.parent;

import java_.access_permission._protected.Child3;

/**
 * @author hsj
 * @create 2019/12/24
 */
public class Child1 extends Parent {


    void h(){
        m();
        int i = f;
    }

    public static void main(String[] args) {
        Child1 c = new Child1();
        c.m();
        System.out.println(c.f);

        Child3 c3 = new Child3();
        c3.m();
//        System.out.println(c3.f);
    }
}
