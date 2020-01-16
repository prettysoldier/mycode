package java_.syntax.generic;

/**
 * @author hsj
 * @create 2019/12/26
 */
public class GenericMethod {

    public static void main(String[] args) {

        GenericMethod.<Integer>genericMethod(1);

        System.out.println(GenericMethod.<Integer>genericMethod2(2));

        GenericMethod.genericMethod3(3);
    }

    private static <S> void genericMethod(int i) {
        System.out.println(i);
    }

    private static <S> S genericMethod2(Integer i) {
        System.out.println(i);
        S s = (S)i;
        return s;
    }

    private static <S> S genericMethod3(S i) {
        System.out.println(i);
        return i;
    }
}
