package java_.lang.object;

/**
 * 1、实例域和静态域的 初始化块 可以在声明之前，但是初始化和赋值语句是按照类定义顺序执行，后面的赋值覆盖前面的！
 *
 *
 * @author heshuaijun
 * @date 2019/11/24 0:41
 */
public class ObjectInitialization {
    static{
        a = 2;
    }
    static int a;

    {
        i = 2;
    }
    int i;

    public static void main (String[] args) {
        System.out.println(a);
        System.out.println(new ObjectInitialization().i);
    }
}

class A{
    int a;
    private A () {}
}
