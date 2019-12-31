package java_.access_permission._protected.parent;

/**
 * @author hsj
 * @create 2019/12/24
 */
public class Child2 extends Parent {
    void h(){
        m();
    }

    public static void main(String[] args) {

        Child1 c1 = new Child1();
        c1.m();
        int i = c1.f;


    }
}
