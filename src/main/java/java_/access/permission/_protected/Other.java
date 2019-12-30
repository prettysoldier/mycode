package java_.access.permission._protected;

import java_.access.permission._protected.parent.Child1;

/**
 * @author hsj
 * @create 2019/12/24
 */
public class Other{

    public static void main(String[] args) throws Exception{
        Child1 c = new Child1();
        // 下面报错？因为与父类不在一个包内
//        c.f();
//        c.f;

        // 下面报错？因为与父类不在一个包内
//        new Other1().clone();
    }
}
