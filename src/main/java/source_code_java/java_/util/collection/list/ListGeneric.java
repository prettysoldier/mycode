package source_code_java.java_.util.collection.list;


import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/3 14:53
 **/
public class ListGeneric<T> {
    public static void main(String[] args) {

        /**
         * 【强制】泛型通配符<? extends T>来接收返回的数据，此写法的泛型集合不能使用add()，
         *  而<? super T>不能使用 get 方法，作为接口调用赋值时易出错。
         *  说明：扩展说一下 PECS(Producer Extends Consumer Super)原则：第一、频繁往外读取内
         *  容的，适合用<? extends T>。第二、经常往里插入的，适合用<? super T>。
         */
        List<? extends Number> a1 = new ArrayList<>();
        a1.add(null);// 除了null外 其他元素插不进来
//        a1.add(new Integer(33));
        try {
            //
            a1.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<? super Number> a2 = new ArrayList<>();
        a2.add(new Integer(33));
        // 可以get，但是类型缺失
        System.out.println(a2.get(0));

//        List<Integer> a3 = a1;
//        List<Comparable> a4 = a2;

    }

    public void t(){
        List<? extends T> a3 = new ArrayList<>();
//        List<T> a4 = a3;

    }



}
