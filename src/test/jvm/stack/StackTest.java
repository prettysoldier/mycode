/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.jvm.stack;

/**
 * 栈的高度称为栈的深度，栈深度受栈帧大小影响。
 * 在栈中存放局部变量，参数，运行中间结果
 *
 * @author Shuaijun He
 */
public class StackTest {
    int count = 0;

    /**
     * java.lang.StackOverflowError
     * stack height:18562
     *
     * @author Shuaijun He
     */
    public void testStack() {
        this.count++;
        this.testStack();
    }

    /**
     * 增加参数
     * java.lang.StackOverflowError
     * stack height:17478
     *
     * @author Shuaijun He
     * @param a
     * @param b
     */
    public void testStack(int a, int b) {
        this.count++;
        this.testStack(a, b);
    }

    /**
     * 增加局部变量 数量
     * java.lang.StackOverflowError
     * stack height:7845
     *
     * @author Shuaijun He
     * @param a
     * @param b
     */
    public void testStackWithLocalVar(int a, int b) {
        int c = 5;
        long d = 4L;
        System.out.println(c + d);
        this.count++;
        this.testStackWithLocalVar(a, b);
    }

    public static void main(String[] args) {
        StackTest test = new StackTest();
        try {
//            test.testStack();
            test.testStack(1, 2);
//            test.testStackWithLocalVar(1, 2);
        } catch (Throwable e) {
            System.out.println(e);
            System.out.println("stack height:" + test.count);
        }

    }

}
