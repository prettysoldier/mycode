
package main.test.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Shuaijun He
 */
/**
 * @author Shuaijun He
 */
public class Lambda {

    int local = 1;
    static int staticLocal = 10;

    void testScope() {
        List names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                // 实例变量和静态变量 既可读又可写
                System.out.println(Lambda.this.local++);
                System.out.println(Lambda.staticLocal++);
                return b.compareTo(a);
            }
        });

        System.out.println(names);

    }

    public static void main(String[] args) {
        List names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        System.out.println(names);
        /**
         * 2
         */
        String out = "out";

        Collections.sort(names, (String a, String b) -> {
            // 外层局部变量out，不可修改，具有隐藏的final语义
            System.out.println(out);
            return a.compareTo(b);
        });

        /**
         * 3
         */

        Collections.sort(names, (String a, String b) -> a.compareTo(b));

        /**
         * 4 ?
         */
//        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.println(names);
    }
}
