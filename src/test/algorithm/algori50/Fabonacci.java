/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.algori50;

/**
 * @author Shuaijun He
 */
public class Fabonacci {

    //用迭代法寻找编程环境支持的最大整数(int型)的斐波那契数是第几个斐波那契数 47或 48
    public static int max_int_iteration() {
        int a = 0, b = 1, c = 1;
        int count = 3;
        for (; b <= c;) {   //一旦c达到编程环境最大斐波那契数，便会产生内存溢出，从而变成一个负数，到此循环结束
            a = b;
            b = c;
            c = a + b;
            count++;
        }
        return count;
    }

    //用迭代法寻找编程环境支持的最大整数(long型)的斐波那契数是第几个斐波那契数 93或94
    public static long max_long_iteration() {
        long a = 0, b = 1, c = 1;
        long count = 3;
        for (; b <= c;) {    //一旦c达到编程环境最大斐波那契数，便会产生内存溢出，从而变成一个负数，到此循环结束
            a = b;
            b = c;
            c = a + b;
            count++;
        }
        return count;
    }

    public static int recursion(int n) {
        int result = 0;   //最后一个斐波那契数及存储中间斐波那契数的变量
        if (n <= 0) {
            result = 0;
        }
        if (n == 1 || n == 2) {
            result = 1;
        }
        if (n > 2) {
            result = Fabonacci.recursion(n - 1) + Fabonacci.recursion(n - 2);
            //System.out.print(result+"  ");
        }
        return result;
    }

    public static int int_iteration(int n) {
        int first = 0;
        int second = 1;
        int fb = 1;
        if (n <= 1) {
            return 1;
        }
        for (int i = 2; i <= n; i++) {
            fb = first + second;
            first = second;
            second = fb;
        }
        return fb;
    }

    public static void main(String[] args) {
        System.out.println(Fabonacci.max_int_iteration());
        System.out.println(Fabonacci.max_long_iteration());

        //12789ms
//        long start = System.currentTimeMillis();
//        Fabonacci.recursion(47);
//        long end = System.currentTimeMillis();

        long start = System.currentTimeMillis();
        Fabonacci.int_iteration(47);
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");

        System.out.println(Fabonacci.int_iteration(4));
    }

}
