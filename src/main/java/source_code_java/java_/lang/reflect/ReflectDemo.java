package source_code_java.java_.lang.reflect;

import java.lang.reflect.Field;

/**
 * @author hsj
 * @create 2019/12/12
 */
public class ReflectDemo {


    public static void main(String[] args) {

        A obj = new A();
        try {
            Field field = A.class.getDeclaredFields()[0];
            // 此处修改后，以后访问该Field，还需要重新设置吗？需要。因为只是获取了Field的一个副本。
            field.setAccessible(true);
            Object fieldVal = field.get(obj);
            System.out.println(fieldVal);
            field.set(obj, "111");
            System.out.println(obj.s);

            System.out.println(new A().s);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class A {
    String s = "asdf";
}
