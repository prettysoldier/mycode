package jvm.generic;

import java.util.List;

/**
 * 泛型引入后的JVM字节码中方法的特征签名问题：加入的返回值和受检查异常表。
 * 如果参数顺序和类型相同，方法名相同，但是泛型参数不同，返回值不同，可以允许重载！！
 * 注意：在JDK1.6可以编译。我用的JDK10.0.1不能编译
 *
 * @author heshuaijun
 * @date 2020/1/16 23:59
 */
public class GenericOverload {

//    static String f(List<String> list){
//        System.out.println("f1");
//        return "";
//    }

    static int f(List<Integer> list){
        System.out.println("f2");
        return 1;
    }

    public static void main (String[] args) {

    }
}
