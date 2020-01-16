package source_code_java.java_.util.collection.list;


import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/3 14:53
 **/
public class ListNoGeneric {
    public static void main(String[] args) {
        List a1 = new ArrayList();
        a1.add(new Object());
        a1.add(new Integer(11));
        a1.add(new String("hello a1"));
        System.out.println(a1);

        List<Object> a2 = a1;
        a2.add(new Object());
        a2.add(new Integer(22));
        a2.add(new String("hello a2"));
        System.out.println(a2);

        List<Integer> a3 = a1;
        a3.add(new Integer(33));
        // 下面代码报错
//        a3.add(new Object());
//        a3.add(new String("hello a3"));
        System.out.println(a3);

        List<?> a4 = a1;
        // 报错，不允许添加任何元素
//        a4.add(new Object());
//        a4.add(new Integer(1));
        a4.remove(new Integer(11));
        System.out.println(a4);
        a4.clear();
        System.out.println(a4);

        List<Integer> a5 = new ArrayList<Integer>();
//        a5.add(new String("asdf"));
//        a5.add(new Integer(55));
//        a2 = a5;
        System.out.println(a5);

        Object[] o = new Integer[1];//数组可以这样赋值，因为数组是协变的


    }



}
