package demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hsj
 * @create 2020/1/6
 */
public class InnerClass {

    public static void main(String[] args) {

        InnerClass.f(new ArrayList<Integer>(){{add(1); add(2);}});
    }

    private static void f(List<Integer> list){

        System.out.println(list);
    }
}
