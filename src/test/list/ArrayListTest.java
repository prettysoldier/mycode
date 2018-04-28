/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Shuaijun He
 */
public class ArrayListTest {

    public static void main(String[] args) {

        List<String> l = new ArrayList<>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");
        l.add("e");
        l.add("f");

        List newList = l.subList(3, 5);
        System.out.println(newList);
        Iterator<String> iter = l.iterator();
        while (iter.hasNext()) {
            String e = iter.next();

            if ("c".equals(e)) {
                iter.remove();
            }
        }
        System.out.println(l);
        l.remove("g");
        List<String> newL = new ArrayList<>(l.size());
        for (String e : l) {
            if ("c".equals(e)) {
                continue;
            }

            newL.add(e);
        }
        System.out.println(newL);

        ArrayListTest.removeList4For();
    }

    public static List<String> g() {
        return null;
    }

    public static void nullTest() {
        List<String> l = ArrayListTest.g();
        for (String s : l) {
            System.out.println(s + "hahahah");
        }
    }

    public static void removeList4ForEach() {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            if ("1".equals(temp)) {
                a.remove(temp);
            }
        }
        System.out.println(a);
    }

    public static void removeList4For() {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        a.add("1");
        a.add("1");
        int j = a.size();
        for (int i = 0; i < j; i++) {
            if ("1".equals(a.get(i))) {
                a.remove(i);
            }
        }
        System.out.println(a);
    }

}
