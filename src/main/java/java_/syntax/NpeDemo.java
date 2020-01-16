package java_.syntax;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc 各种NPE反例
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 18:21
 **/
public class NpeDemo {



    public static int a() {
        Integer a = null;
        return a;//Exception in thread "main" java.lang.NullPointerException
    }

    public static void b() {
        List<Integer> list = new ArrayList<>();
        list.add(null);
        System.out.println(list.size());// 1
    }

    public static void main(String[] args) {
        a();
        b();
    }
}
