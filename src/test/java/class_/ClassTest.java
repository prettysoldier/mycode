package test.java.class_;

/**
 * @author Shuaijun He
 * @param <T>
 */
public class ClassTest<T> {

    public static final int i = 1;
    public static int j = 1;
    private int m = 1;

    public T f() throws Exception {
        int n = 0;
        System.out.println(n);
        this.m++;
        throw new Exception();
    }

}
