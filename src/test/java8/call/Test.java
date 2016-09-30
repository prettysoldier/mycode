/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test.java8.call;

/**
 * @author Shuaijun He
 *         函数式接口：接口中只有一个抽象方法
 *         注意如果@FunctionalInterface如果没有指定也可
 */
@FunctionalInterface
public interface Test {

    int f(int t);

//    int j(int t);

    default int g() {
        return 1;
    }

}
