package closure;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/11 20:10
 **/
public class JavaClosureClient {
    public static void main(String[] args) {

        JavaClosure javaClosure = new JavaClosure();
        Inner inner = javaClosure.outer();
        inner.inner();
    }

}
