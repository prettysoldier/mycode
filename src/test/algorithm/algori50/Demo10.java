/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.algori50;

/**
 * @author Shuaijun He
 */
public class Demo10 {
    public static void main(String[] args) {
        double s = 0;
        double h = 100;
        for (int i = 1; i <= 10; i++) {
            s += h;
            h = h / 2;
            s += h;
        }
        System.out.println("经过路程：" + (s - h));
        System.out.println("反弹高度：" + h);
    }
}