package java_.syntax.cast;

import java.math.BigDecimal;

/**
 * 基本类型不能强转为包装类型
 */
public class CastTest {



    public CastTest getA() {
        Object a = null;
        return (CastTest) a;
    }

    public static void main(String[] args) {
        int a = 2;
        long b2 = 2;
        long b = a;
//        Long b1 = (Long)a;// 编译失败


        System.out.println((CastTest) null + "--");
        System.out.println(new CastTest().getA() + "--");

        System.out.println(new BigDecimal(5000000));
    }
}
