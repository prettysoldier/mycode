package java_.access.permission._protected;

/**
 * @author hsj
 * @create 2019/12/24
 */
public class Other extends Object{

    public static void main(String[] args) throws Exception{
        Child c = new Child();
        c.f();
        c.h();
//        c.

        new Other().clone();
    }
}
