package jvm;

/**
 * 1）静态域和静态块的执行顺序：按照代码顺序
 * @author heshuaijun
 * @date 2019/10/5 14:59
 */
public class StaticOrderTest {

    public int nonStaticMethod = nonStaticMethod();
    public static final int aaa = 111;
    public final int bbb = 222;
    static {
        System.out.println("执行静态块!");
    }

    public static int staticMethod = staticMethod();

    public static int staticMethod () {
        System.out.println("staticMethod");
        return 1;
    }

    public int nonStaticMethod () {
        System.out.println("nonStaticMethod");
        return 1;
    }

    public static void main (String[] args) {
        System.out.println("ha");
    }
}