package java_.access_permission._protected.parent;

import java_.access_permission._protected.Child3;

/**
 * @author hsj
 * @create 2019/12/24
 */
public class Child2 extends Parent {

    int f = 2;

    void h(){
        m();
    }

    public static void main(String[] args) {

        Child1 c1 = new Child1();
        c1.m();
        System.out.println(c1.f);

        Child3 c3 = new Child3();
        c3.m();
        System.out.println(c3.f);
    }
}
