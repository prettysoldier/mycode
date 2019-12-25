package java_.access.permission._protected;

/**
 * @author hsj
 * @create 2019/12/24
 */
public class Child extends MyProtected {
    void h(){
        f();
    }

    public static void main(String[] args) {
        Child c = new Child();
        c.f();
        c.h();
    }
}
