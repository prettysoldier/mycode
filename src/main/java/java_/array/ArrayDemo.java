package java_.array;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/3 13:56
 **/
public class ArrayDemo {
    public static void main(String[] args) {
        // -1 编译不会报错，运行时报错：java.lang.NegativeArraySizeException
        String[] arr = new String[-1];


    }
}
