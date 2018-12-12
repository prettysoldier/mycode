package java_.util.collection.list.arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args) {
        //
        ArrayList<String> list = new ArrayList<String>();
        list.add(null);
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.get(0) == null);

        // subListArrayList的subList结果不可强转成ArrayList，否则会抛出ClassCastException
        //异常，即 java.util.RandomAccessSubList cannot be cast to java.util.ArrayList.
        //说明：subList 返回的是 ArrayList 的内部类 SubList，并不是 ArrayList ，而是
        //ArrayList 的一个视图，对于 SubList 子列表的所有操作最终会反映到原列表上。
        // 在 subList 场景中，高度注意对原集合元素个数的修改，会导致子列表的遍历、增加、
        //删除均会产生 ConcurrentModificationException 异常。
        ArrayList<String> list1 = new ArrayList<String>(2);
        list1.add("a");
        list1.add("b");
        List<String> sub = list1.subList(0, 1);
        System.out.println(sub);

        /**
         * 【强制】使用集合转数组的方法，必须使用集合的 toArray(T[] array)，传入的是类型完全
         * 一样的数组，大小就是 list.size()
         */
        String[] strArr = new String[list1.size()];
        strArr = list1.toArray(strArr);
        System.out.println(strArr[0]);

        /**
         * 使用工具类 Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方
         * 法，它的 add/remove/clear 方法会抛出 UnsupportedOperationException 异常
         */
        List<String> list2 = Arrays.asList(strArr);
        System.out.println(list2);
//        list2.add("sdf");
        list2.set(0, "aa");
        strArr[1] = "bb";
        System.out.println(strArr[0]);
        System.out.println(list2);

        /**
         *
         */
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();
        if(stringArrayList.equals(stringArrayList)){
            System.out.println("泛型测试" + "类型相同");
        }
        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();
        if(classStringArrayList.equals(classIntegerArrayList)){
            System.out.println("泛型测试" + "class相同");
        }

        /**
         * 【强制】泛型通配符<? extends T>来接收返回的数据，此写法的泛型集合不能使用 add 方
         * 法，而<? super T>不能使用 get 方法，做为接口调用赋值时易出错。
         * 说明：扩展说一下 PECS(Producer Extends Consumer Super)原则：第一、频繁往外读取内
         * 容的，适合用<? extends T>。第二、经常往里插入的，适合用<? super T>
         */
        List<? super Number> list3 = new ArrayList<>();
        Integer i = new Integer(1);
        list3.add(i);
        Object num = list3.get(0);

        List<? extends Number> list4 = new ArrayList<>();
//        list4.add(i);
//        Number num1 = list4.get(0);

        List<String> list5 = new ArrayList<String>();
        list5.add("1");
        list5.add("2");
        for (String item : list5) {
            if ("1".equals(item)) {
                list5.remove(item);
            }
        }



    }








}


