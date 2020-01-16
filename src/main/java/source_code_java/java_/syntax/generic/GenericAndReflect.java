package source_code_java.java_.syntax.generic;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author heshuaijun
 * @date 2020/1/12 22:58
 */
public class GenericAndReflect<K, V> {

    void test(){
        TypeVariable[] type = GenericAndReflect.class.getTypeParameters();
        System.out.println(Arrays.toString(type));

        List<String> list = new ArrayList<>();
        TypeVariable t = list.getClass().getTypeParameters()[0];
        System.out.println(t.getTypeName());
    }
    public static void main (String[] args) {

        new GenericAndReflect<Integer, String>().test();
    }
}
