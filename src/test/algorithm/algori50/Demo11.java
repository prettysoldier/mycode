/**
 * Copyright(c) 2011-2018 by YouCredit Inc.
 * All Rights Reserved
 */
package test.algorithm.algori50;

/**
 * @author Shuaijun He
 */
public class Demo11 {

    public static void main(String[] args) {
        int count = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 4; k++) {
                    if (i != j && j != k && i != k) {
                        count++;
                        System.out.println(i * 100 + j * 10 + k);
                    }
                }
            }
        }
        System.out.println("count :" + count);
    }
}
