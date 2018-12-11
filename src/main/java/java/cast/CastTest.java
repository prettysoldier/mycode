package java.cast;

import java.throwab.A;

import java.math.BigDecimal;

/**
 *
 */
public class CastTest {



    public A getA() {
        Object a = null;
        return (A) a;
    }

    public static void main(String[] args) {
        int a = 2;
        long b = a;
//        Long b1 = a;// 编译失败


        System.out.println((A) null + "--");
        System.out.println(new CastTest().getA() + "--");

        System.out.println(new BigDecimal(5000000));
    }
}
