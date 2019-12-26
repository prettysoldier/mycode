package java_.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericType {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        A<Integer> aInt = new A(1);
        A<Number> aNum = new A(1);
        /**
         * A<Integer> 不是 A<Number> 的子类
         */
//        testGeneric(aInt); // 编译不通过
        testGeneric(aNum);

//        testGeneric1(aNum);
        testGeneric1(aInt);

        testGeneric2(aNum);
//        testGeneric2(aInt);

        testGeneric3(aNum);
        testGeneric3(aInt);

        testAllGeneric(aNum);
        testAllGeneric(aInt);

        genericMethod(GenericType.class);

        B<Integer> b = new B<>(1);
//        testGeneric(b);
        testGeneric1(b);
        testGeneric2(b);

        // 数组不能用泛型！！
        List<?>[] ls = new ArrayList<?>[10];
        List<String>[] ls1 = new ArrayList[10];
//        List<String>[] ls = new ArrayList<String>[10];// 这样不行
        A<String>[] arr = new A[10];
        whyArrayCannotUseGeneric();
        fixWhyArrayCannotUseGeneric();

    }

    private static void testGeneric(A<Number> a){ }
    private static void testGeneric1(A<? extends Integer> a){ }
    private static void testGeneric2(A<? super Number> a){ }
    private static void testGeneric3(A<? extends Number> a){ }

    private static void testGeneric(B<Number> a){ }
    private static void testGeneric1(B<? extends Number> a){ }
    private static <T extends Number> T testGeneric2(B<T> b){
        return b.getT();
    }

    private static void testAllGeneric(A<?> a){ }


    private static <S> S genericMethod(Class<S> tClass)throws InstantiationException ,
            IllegalAccessException{
        S instance = tClass.newInstance();
        return instance;
    }

    /**
     * 为什么数组不能用泛型：这个方法会报运行时错误
     * 泛型会自动进行强转
     */
    private static void whyArrayCannotUseGeneric(){
        List<String>[] lsa = new List[10]; // Not really allowed.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Unsound, but passes run time store check
        String s = lsa[1].get(0); // Run-time error: ClassCastException.
    }

    private static void fixWhyArrayCannotUseGeneric(){
        List<?>[] lsa = new List[10]; // Not really allowed.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Unsound, but passes run time store check
        Integer s = (Integer) lsa[1].get(0); // Run-time error: ClassCastException.
    }




}

/**
 * 泛型类
 * @param <T>
 */
class A<T>{
    private T t;

    private T[] tarr;

    public A(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}

/**
 * 泛型类
 * @param <T>
 */
class B<T extends Number>{
    private T t;

    public B(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}
