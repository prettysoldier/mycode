package java_.syntax.generic;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/26 13:33
 **/
public class GenericName<Integer>{
    /**
     * 泛型的名字可以与其他类一样。
     * @param t
     * @param <String>
     * @return
     */
    <String> String get(String t){
        // 此处的String与java.lang.String 不同，编译报错
//        String t2 = "asdf";
        System.out.println(t);
        return t;
    }

    java.lang.Integer s = new java.lang.Integer(1);
//    Integer i = 1;

    public static void main(String[] args) {
        System.out.println(new GenericName().get(new java.lang.Integer(1)).getClass());
    }
}

