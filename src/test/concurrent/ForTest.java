/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuaijun He
 */
public class ForTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        for (int i = 0; i < list.size(); i++) {
            list.add(i);
            System.out.println(list.size() + ":" + i);
        }

    }
}
