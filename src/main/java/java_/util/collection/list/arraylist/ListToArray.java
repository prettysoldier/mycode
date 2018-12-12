package java_.util.collection.list.arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/3 14:31
 **/
public class ListToArray {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(3);
        list.add("one");
        list.add("two");
        list.add("three");
        // 问题是泛型丢失，不可取
        Object array1 = list.toArray();

        String[] array2 = new String[2];
        String[] array3 = list.toArray(array2);
        // 如果array2分配的空间不够，将丢弃此数组,返回一个新数组
        System.out.println(Arrays.asList(array2));
        System.out.println(Arrays.asList(array3));

        // 如果分配的空间太大(array4)，多余的数组空间会设置为null
        String[] array4 = new String[4];
        list.toArray(array4);
        System.out.println(Arrays.asList(array4));

        // 最好是分配的大小正好是list.size()
        String[] array5 = new String[list.size()];
        list.toArray(array5);
        System.out.println(Arrays.asList(array5));

    }
}
