package java_.lang.class_;

import java.lang.reflect.InvocationTargetException;

/**
 * 1.Class只能表示对象的类型吗？并不一定是对象的类型，还可能是一种基本类型，比如int.class
 * 2.构造器可以抛出异常吗？可以！
 * @author heshuaijun
 * @date 2019/11/30 22:42
 */
public class Class_Test {

    public static void main (String[] args) {

        System.out.println(int.class == Integer.TYPE);
        System.out.println(int.class.isPrimitive());

        Class clazz = Double[].class;
        System.out.println(clazz);
        Class clazz1 = double[].class;
        System.out.println(clazz1);

        try {
            // 此方法已经在java9中被淘汰，用下面的方法代替
//            A a = A.class.newInstance();
//            System.out.println(a.getI());
            try {
                A a1 = A.class.getDeclaredConstructor(int.class).newInstance(1);
                System.out.println(a1.getI());
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class A{
    int i;
    A (int i) throws Exception{
        this.i = i;
    }

    int getI () {
        return i;
    }
}
