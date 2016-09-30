package test;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Stack;

public class Myyest {

    public void a() {
        Stack<B> bs = new Stack<B>();
        bs.push(new B());
        System.out.println(bs);

        new ArrayDeque<>();
    }

    public A getA() {
        Object a = null;
        return (A) a;
    }

    public static void main(String[] args) {
        System.out.println(false && true || true);
        System.out.println(true || true && false);

        System.out.println((A) null + "--");
        System.out.println(new Myyest().getA() + "--");

        System.out.println(new BigDecimal(5000000));
    }
}
