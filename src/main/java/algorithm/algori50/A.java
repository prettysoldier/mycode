package algorithm.algori50;

import java.util.Arrays;
import java.util.List;

/**
 * @author Shuaijun He
 */
public class A {

    public static void main(String[] args) {
        char s = '-';
        switch (s) {
            case '+':
                System.out.println(1);
                break;
            case '-':
                System.out.println(2);
                break;
            case '/':
                System.out.println(3);
        }

        Integer[] i = { 1, 2 };
        List<Integer> list = Arrays.asList(i);
        list.clear();

    }
}
