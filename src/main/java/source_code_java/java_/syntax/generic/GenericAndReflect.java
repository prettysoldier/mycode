package source_code_java.java_.syntax.generic;

/**
 * @author heshuaijun
 * @date 2020/1/12 22:58
 */
public class GenericAndReflect<T> {

    void test(){
        Object type = new GenericAndReflect<Integer>().getClass().getGenericInterfaces();
        System.out.println(type);
//        Number number = (Number)type;
//        clazz=(Class)numbergetActualTypeArguments()[0];//得到实际的参数泛型类型 Person
    }
    public static void main (String[] args) {

        new GenericAndReflect<Integer>().test();
    }
}
