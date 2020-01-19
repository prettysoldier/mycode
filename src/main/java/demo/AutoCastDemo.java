package demo;

/**
 * https://www.cnblogs.com/bokeofzp/p/4745681.html
 * @author hsj
 * @create 2019/12/13
 */
public class AutoCastDemo {
    public static void main(String[] args) {
        short s = 1;
//        s = s + 1;
        s += 1;

        final byte b1=1;
        final byte b2=3;
        //不会出错，被fianl修饰的变量不会改变，会被JVM优化，当2个final修饰相加时候会根据左边变量的类型而转化
        byte b3=b1+b2;

        final int a=2;
        final byte a2=3;
        int a3=a+a2;
        byte a4=a+a2;
        System.out.println(a4);
    }
}
