package java.generic;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/26 13:33
 **/
public class GenericDemo{
    <String> String get(String t){
        System.out.println(t);
        return t;
    }

    public static void main(String[] args) {
        System.out.println(new GenericDemo().get(new Integer(1)).getClass());
    }
}

